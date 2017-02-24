package org.wso2.edgeanalyticsservice;


import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.hardware.SensorEvent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import org.wso2.carbon.iot.android.sense.event.streams.sensor.SensorDataReader;

import java.util.List;
import java.util.Stack;

import agent.sense.android.iot.carbon.wso2.org.wso2_senseagent.R;

import static android.hardware.Sensor.TYPE_ACCELEROMETER;
import static android.hardware.Sensor.TYPE_GRAVITY;
import static android.hardware.Sensor.TYPE_GYROSCOPE;
import static android.hardware.Sensor.TYPE_LIGHT;
import static android.hardware.Sensor.TYPE_MAGNETIC_FIELD;
import static android.hardware.Sensor.TYPE_PRESSURE;
import static android.hardware.Sensor.TYPE_PROXIMITY;

public class SiddhiActivity extends AppCompatActivity {
    public static IEdgeAnalyticsService mIEdgeAnalyticsService;
    public static IEdgeAnalyticsCallback.Stub mCallback;
    public static boolean isServiceConnected = false;
    private static final String TAG = SiddhiActivity.class.getName();
    private static List<String> values = new Stack<>();
    private static List<String> types = new Stack<>();
    private static final String packagename =  "org.wso2.edgeanalyticsservice";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siddhi);
        finish();
        types.add("float");
        Intent intentIEdgeAnalyticsService = new Intent()
                .setComponent(new ComponentName
                        (packagename,
                                "org.wso2.edgeanalyticsservice.EdgeAnalyticsService"));
        intentIEdgeAnalyticsService.putExtra("package",packagename);
        startService(intentIEdgeAnalyticsService);
        bindService(intentIEdgeAnalyticsService, mConnection, BIND_AUTO_CREATE);
    }

    public ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(final ComponentName className, final IBinder service) {
            Log.i(TAG, "Siddhi Service Started.\n");
            mIEdgeAnalyticsService = IEdgeAnalyticsService.Stub.asInterface(service);
            isServiceConnected =true;
            mCallback = new IEdgeAnalyticsCallback.Stub() {
                @Override
                public void callback(String event) throws RemoteException {
                    handleMessage(event);
                }
            };
            SensorDataReader.setStarted(true);
        }

        @Override
        public void onServiceDisconnected(ComponentName className) {
            isServiceConnected = false;
            Log.i(TAG, "Service disconnected.\n");
        }
    };

    private void handleMessage(String event){
        Log.i(TAG,event);
        Log.i(TAG,"Your phone dropped");
        final String message = "Your phone dropped...";
        Thread t = new Thread(){
            public void run() {
                Message myMessage = new Message();
                Bundle resBundle = new Bundle();
                resBundle.putString( "message", message );
                myMessage.setData( resBundle );
                handler.sendMessage( myMessage );
            }
        };
        t.start();
    }

    private Handler handler = new Handler(){
        public void handleMessage( Message msg ){
            Toast.makeText( getBaseContext(), msg.getData().getString( "message" ),
                    Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConnection);
    }
    public static void setExecutionPlan(String streamDefinition, String queryDefinition, String callbackStream){
        try {
            mIEdgeAnalyticsService.addStream(streamDefinition, packagename);
            mIEdgeAnalyticsService.addQuery(queryDefinition, packagename);
            mIEdgeAnalyticsService.startExecutionPlan(packagename);
            mIEdgeAnalyticsService.addStreamCallback(callbackStream, packagename, packagename, mCallback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public static void addQuerytoExistingStream(String queryDefinition){
        try {
            mIEdgeAnalyticsService.addQuery(queryDefinition,packagename);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public static void removeExecutionPlan(String streamDefinition, String queryDefinition){
        try {
            mIEdgeAnalyticsService.removeStream(streamDefinition,packagename);
            mIEdgeAnalyticsService.removeQuery(queryDefinition,packagename);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public static void removeQuery(String queryDefinition){
        try {
            mIEdgeAnalyticsService.removeQuery(queryDefinition,packagename);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public static void getData(SensorEvent event){
        //todo: there are several other sensors. implement for them.
        switch(event.sensor.getType()){
            case TYPE_ACCELEROMETER:
                if(isServiceConnected) {
                    setExecutionPlan("" +
                                    "define stream accelerometerStream (data float); ",
                                    "from accelerometerStream#window.timeBatch(1500)" +
                                    "select avg(data) as avgData " +
                                    "insert into avgStream; ",
                            packagename);
                    addQuerytoExistingStream("" +
                            "from avgStream[avgData < 2]" +
                            "select avgData " +
                            "insert into newStream; ");
                    try {
                        mIEdgeAnalyticsService.startExecutionPlan(packagename);
                        mIEdgeAnalyticsService.addStreamCallback("" +
                                "newStream", packagename,
                                packagename, mCallback);
                        values.add(String.valueOf(event.values[0]));
                        mIEdgeAnalyticsService.sendData(packagename, "accelerometerStream",
                                values, types);
                        values.remove(String.valueOf(event.values[0]));
                        values.add(String.valueOf(event.values[1]));
                        mIEdgeAnalyticsService.sendData(packagename, "accelerometerStream",
                                values, types);
                        values.remove(String.valueOf(event.values[1]));
                        values.add(String.valueOf(event.values[2]));
                        mIEdgeAnalyticsService.sendData(packagename, "accelerometerStream",
                                values, types);
                        values.remove(String.valueOf(event.values[2]));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                else Log.i(TAG,"Siddhi Service Error!!!!!!!!!!!!!");
                break;
            case TYPE_MAGNETIC_FIELD:
                publishToSiddhi(event);
                break;
            case TYPE_GYROSCOPE:
                publishToSiddhi(event);
                break;
            case TYPE_LIGHT:
                publishToSiddhi(event);
                break;
            case TYPE_PRESSURE:
                publishToSiddhi(event);
                break;
            case TYPE_PROXIMITY:
                publishToSiddhi(event);
                break;
            case TYPE_GRAVITY:
                publishToSiddhi(event);
                break;
        }
    }
    private static void publishToSiddhi(SensorEvent event){
        if(isServiceConnected) {
            try {
                for(int i=0;i<event.values.length;i++){
                    values.add(String.valueOf(event.values[i]));
                    mIEdgeAnalyticsService.sendData(packagename, "newStream", values, types);
                    values.remove(String.valueOf(event.values[i]));
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        else Log.i(TAG,"Siddhi Service Error!!!!!!!!!!!!!");
    }
}




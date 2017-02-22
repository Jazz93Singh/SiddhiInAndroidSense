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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siddhi);
        finish();
        types.add("float");
        Intent intentIEdgeAnalyticsService = new Intent()
                .setComponent(new ComponentName
                        ("org.wso2.edgeanalyticsservice",
                                "org.wso2.edgeanalyticsservice.EdgeAnalyticsService"));
        intentIEdgeAnalyticsService.putExtra("package", "org.wso2.edgeanalyticsservice");
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
                    Log.i(TAG, "################" + event + "\n");
                    handleMessage("Your Phone dropped!!!");
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

    private void handleMessage(String msg){
        final String message = msg;
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


    public static void addExecutionPlan() {
        /*for (Sensor s : ActivitySelectSensor.sensors) {*/
            try {/*
                mIEdgeAnalyticsService.addStream("" +
                            "define stream newStream (data float); ",
                    "org.wso2.edgeanalyticsservice");
                mIEdgeAnalyticsService.addQuery("" +
                                "@info(name = 'query1') " +
                                "from newStream[data <= 100.0] " +
                                "select data " +
                                "insert into outputStream; ",
                        "org.wso2.edgeanalyticsservice");*/
                mIEdgeAnalyticsService.addStream("" +
                                "define stream accelerometerStream (data float); ",
                        "org.wso2.edgeanalyticsservice");
                mIEdgeAnalyticsService.addQuery("" +
                                "@info(name = 'query2') " +
                                "from accelerometerStream#window.timeBatch(1500)" +
                                "select avg(data) as avgData " +
                                "insert into avgStream; ",
                        "org.wso2.edgeanalyticsservice");
                mIEdgeAnalyticsService.addQuery("" +
                                "@info(name = 'query3') " +
                                "from avgStream[avgData < 2]" +
                                "select avgData " +
                                "insert into newStream; ",
                        "org.wso2.edgeanalyticsservice");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
       /* }*/
        try {
            mIEdgeAnalyticsService.startExecutionPlan("org.wso2.edgeanalyticsservice");/*
            mIEdgeAnalyticsService.addStreamCallback("outputStream",
                    "org.wso2.edgeanalyticsservice", "org.wso2.edgeanalyticsservice", mCallback);*/
            mIEdgeAnalyticsService.addStreamCallback("newStream",
                    "org.wso2.edgeanalyticsservice", "org.wso2.edgeanalyticsservice", mCallback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConnection);
    }

/*
    public static void Communicate(SensorEvent event) {

        try {
            mIEdgeAnalyticsService.subscribeStreamToData("org.wso2.edgeanalyticsservice",
                    "define stream newStream (data double); ");
            mIEdgeAnalyticsService.addStreamCallback("newStream",
                    "org.wso2.edgeanalyticsservice", "org.wso2.edgeanalyticsservice", mCallback);
            Log.i(TAG, "Starting Communication\n");
            mIEdgeAnalyticsService.addStream("" +
                            "define stream tempStream (HUMIDITY int); ",
                    "org.wso2.edgeanalyticsservice");
            mIEdgeAnalyticsService.addQuery("" +
                            "@info(name = 'query1') " +
                            "from tempStream[HUMIDITY <= 100] " +
                            "select HUMIDITY " +
                            "insert into outputStream; ",
                    "org.wso2.edgeanalyticsservice");
            Log.i(TAG, "addQuery query1\n");
            mIEdgeAnalyticsService.startExecutionPlan("org.wso2.edgeanalyticsservice");
            Log.i(TAG, "startExecutionPlan\n");
            Log.i(TAG, "subscribeExecutionPlan: to start collecting data from sensors...\n");
            List<Stream> a = mIEdgeAnalyticsService.getAllStreams();
            for (Stream o : a) {
                Log.i(TAG, "Stream :" + o.streamName + "\n");
                Log.i(TAG, "Stream describeContents:" + Arrays.toString(o.inputTypes) + "\n");
                Log.i(TAG, "Stream streamDefinition:" + o.streamDefinition + "\n");
            }
            mIEdgeAnalyticsService.addStreamCallback("outputStream",
                    "org.wso2.edgeanalyticsservice", "org.wso2.edgeanalyticsservice", mCallback);
            mIEdgeAnalyticsService.subscribeExecutionPlan("org.wso2.edgeanalyticsservice");
            values.add("100");
            types.add("int");
            mIEdgeAnalyticsService.sendData("org.wso2.edgeanalyticsservice", "tempStream", values, types);
            values.remove("100");
            types.remove("int");
            values.add("10");
            types.add("int");
            mIEdgeAnalyticsService.sendData("org.wso2.edgeanalyticsservice", "tempStream", values, types);
            values.remove("10");
            types.remove("int");
            values.add("34");
            types.add("int");
            mIEdgeAnalyticsService.sendData("org.wso2.edgeanalyticsservice", "tempStream", values, types);
            values.remove("34");
            types.remove("int");
            values.add("50");
            types.add("int");
            mIEdgeAnalyticsService.sendData("org.wso2.edgeanalyticsservice", "tempStream", values, types);
        } catch (RemoteException e) {
            e.printStackTrace();

        }
    }*/

    public static void getData(SensorEvent event){
        //todo: there are several other sensors. implement for them.
        switch(event.sensor.getType()){
            case TYPE_ACCELEROMETER:
                if(isServiceConnected) {
                    try {
                        values.add(String.valueOf(event.values[0]));
                        mIEdgeAnalyticsService.sendData("org.wso2.edgeanalyticsservice",
                                "accelerometerStream", values, types);
                        values.remove(String.valueOf(event.values[0]));
                        values.add(String.valueOf(event.values[1]));
                        mIEdgeAnalyticsService.sendData("org.wso2.edgeanalyticsservice",
                                "accelerometerStream", values, types);
                        values.remove(String.valueOf(event.values[1]));
                        values.add(String.valueOf(event.values[2]));
                        mIEdgeAnalyticsService.sendData("org.wso2.edgeanalyticsservice",
                                "accelerometerStream", values, types);
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
                    mIEdgeAnalyticsService.sendData("org.wso2.edgeanalyticsservice",
                            "newStream", values, types);
                    values.remove(String.valueOf(event.values[i]));
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        else Log.i(TAG,"Siddhi Service Error!!!!!!!!!!!!!");
    }
}




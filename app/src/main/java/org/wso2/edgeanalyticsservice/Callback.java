package org.wso2.edgeanalyticsservice;

/**
 * Created by root on 1/7/16.
 */
public class Callback {

    public static final String STREAM_STATIC = "stream_static";
    public static final String STREAM_DYNAMIC = "stream_dynamic";
    public static final String QUERY_STATIC = "query_static";
    public static final String QUERY_DYNAMIC = "query_dynamic";

    private String type, source, target, receiverPkg;
    private IEdgeAnalyticsCallback callback;

    public Callback(String type, String source, String target,  String receiverPkg,
                    IEdgeAnalyticsCallback callback) {
        this.type = type;
        this.source = source;
        this.target = target;
        this.receiverPkg = receiverPkg;
        this.callback = callback;
    }

    public String getType() {
        return type;
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }

    public String getReceiverPkg() {
        return receiverPkg;
    }

    public IEdgeAnalyticsCallback getCallback() {
        return callback;
    }

    @Override
    public String toString() {
        return type + " : " + source + ", " + target ;
    }
}

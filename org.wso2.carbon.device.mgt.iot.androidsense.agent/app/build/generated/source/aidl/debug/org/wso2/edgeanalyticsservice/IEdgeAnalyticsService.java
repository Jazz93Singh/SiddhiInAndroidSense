/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /home/vimukthi/Desktop/carbon device mgt plugins/components/device-types/androidsense-plugin/org.wso2.carbon.device.mgt.iot.androidsense.agent/app/src/main/aidl/org/wso2/edgeanalyticsservice/IEdgeAnalyticsService.aidl
 */
package org.wso2.edgeanalyticsservice;
public interface IEdgeAnalyticsService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements org.wso2.edgeanalyticsservice.IEdgeAnalyticsService
{
private static final java.lang.String DESCRIPTOR = "org.wso2.edgeanalyticsservice.IEdgeAnalyticsService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an org.wso2.edgeanalyticsservice.IEdgeAnalyticsService interface,
 * generating a proxy if needed.
 */
public static org.wso2.edgeanalyticsservice.IEdgeAnalyticsService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof org.wso2.edgeanalyticsservice.IEdgeAnalyticsService))) {
return ((org.wso2.edgeanalyticsservice.IEdgeAnalyticsService)iin);
}
return new org.wso2.edgeanalyticsservice.IEdgeAnalyticsService.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_addStream:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
this.addStream(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_removeStream:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
this.removeStream(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_addQuery:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
this.addQuery(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_removeQuery:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
this.removeQuery(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_getAllStreams:
{
data.enforceInterface(DESCRIPTOR);
java.util.List<org.wso2.edgeanalyticsservice.Stream> _result = this.getAllStreams();
reply.writeNoException();
reply.writeTypedList(_result);
return true;
}
case TRANSACTION_validateExecutionPlan:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
this.validateExecutionPlan(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_startExecutionPlan:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
this.startExecutionPlan(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_addQueryCallback:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
java.lang.String _arg2;
_arg2 = data.readString();
org.wso2.edgeanalyticsservice.IEdgeAnalyticsCallback _arg3;
_arg3 = org.wso2.edgeanalyticsservice.IEdgeAnalyticsCallback.Stub.asInterface(data.readStrongBinder());
this.addQueryCallback(_arg0, _arg1, _arg2, _arg3);
return true;
}
case TRANSACTION_addDynamicQueryCallback:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
this.addDynamicQueryCallback(_arg0, _arg1);
return true;
}
case TRANSACTION_addStreamCallback:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
java.lang.String _arg2;
_arg2 = data.readString();
org.wso2.edgeanalyticsservice.IEdgeAnalyticsCallback _arg3;
_arg3 = org.wso2.edgeanalyticsservice.IEdgeAnalyticsCallback.Stub.asInterface(data.readStrongBinder());
this.addStreamCallback(_arg0, _arg1, _arg2, _arg3);
return true;
}
case TRANSACTION_addDynamicStreamCallback:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
this.addDynamicStreamCallback(_arg0, _arg1);
return true;
}
case TRANSACTION_sendData:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
java.util.List<java.lang.String> _arg2;
_arg2 = data.createStringArrayList();
java.util.List<java.lang.String> _arg3;
_arg3 = data.createStringArrayList();
this.sendData(_arg0, _arg1, _arg2, _arg3);
reply.writeNoException();
return true;
}
case TRANSACTION_subscribeStreamToData:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
this.subscribeStreamToData(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_subscribeExecutionPlan:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
this.subscribeExecutionPlan(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_stop:
{
data.enforceInterface(DESCRIPTOR);
this.stop();
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements org.wso2.edgeanalyticsservice.IEdgeAnalyticsService
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public void addStream(java.lang.String streamDefinition, java.lang.String packageName) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(streamDefinition);
_data.writeString(packageName);
mRemote.transact(Stub.TRANSACTION_addStream, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void removeStream(java.lang.String streamName, java.lang.String packageName) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(streamName);
_data.writeString(packageName);
mRemote.transact(Stub.TRANSACTION_removeStream, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void addQuery(java.lang.String queryDefinition, java.lang.String packageName) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(queryDefinition);
_data.writeString(packageName);
mRemote.transact(Stub.TRANSACTION_addQuery, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void removeQuery(java.lang.String queryName, java.lang.String packageName) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(queryName);
_data.writeString(packageName);
mRemote.transact(Stub.TRANSACTION_removeQuery, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public java.util.List<org.wso2.edgeanalyticsservice.Stream> getAllStreams() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<org.wso2.edgeanalyticsservice.Stream> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getAllStreams, _data, _reply, 0);
_reply.readException();
_result = _reply.createTypedArrayList(org.wso2.edgeanalyticsservice.Stream.CREATOR);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void validateExecutionPlan(java.lang.String packageName) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(packageName);
mRemote.transact(Stub.TRANSACTION_validateExecutionPlan, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void startExecutionPlan(java.lang.String packageName) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(packageName);
mRemote.transact(Stub.TRANSACTION_startExecutionPlan, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void addQueryCallback(java.lang.String queryName, java.lang.String packageName, java.lang.String ownPackageName, org.wso2.edgeanalyticsservice.IEdgeAnalyticsCallback callback) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(queryName);
_data.writeString(packageName);
_data.writeString(ownPackageName);
_data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_addQueryCallback, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void addDynamicQueryCallback(java.lang.String queryName, java.lang.String packageName) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(queryName);
_data.writeString(packageName);
mRemote.transact(Stub.TRANSACTION_addDynamicQueryCallback, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void addStreamCallback(java.lang.String stream, java.lang.String packageName, java.lang.String ownPackageName, org.wso2.edgeanalyticsservice.IEdgeAnalyticsCallback callback) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(stream);
_data.writeString(packageName);
_data.writeString(ownPackageName);
_data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_addStreamCallback, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void addDynamicStreamCallback(java.lang.String stream, java.lang.String packageName) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(stream);
_data.writeString(packageName);
mRemote.transact(Stub.TRANSACTION_addDynamicStreamCallback, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void sendData(java.lang.String id, java.lang.String stream, java.util.List<java.lang.String> values, java.util.List<java.lang.String> types) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(id);
_data.writeString(stream);
_data.writeStringList(values);
_data.writeStringList(types);
mRemote.transact(Stub.TRANSACTION_sendData, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void subscribeStreamToData(java.lang.String packageName, java.lang.String streamDefinition) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(packageName);
_data.writeString(streamDefinition);
mRemote.transact(Stub.TRANSACTION_subscribeStreamToData, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void subscribeExecutionPlan(java.lang.String packageName) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(packageName);
mRemote.transact(Stub.TRANSACTION_subscribeExecutionPlan, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void stop() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_stop, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_addStream = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_removeStream = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_addQuery = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_removeQuery = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_getAllStreams = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_validateExecutionPlan = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_startExecutionPlan = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_addQueryCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
static final int TRANSACTION_addDynamicQueryCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
static final int TRANSACTION_addStreamCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 9);
static final int TRANSACTION_addDynamicStreamCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 10);
static final int TRANSACTION_sendData = (android.os.IBinder.FIRST_CALL_TRANSACTION + 11);
static final int TRANSACTION_subscribeStreamToData = (android.os.IBinder.FIRST_CALL_TRANSACTION + 12);
static final int TRANSACTION_subscribeExecutionPlan = (android.os.IBinder.FIRST_CALL_TRANSACTION + 13);
static final int TRANSACTION_stop = (android.os.IBinder.FIRST_CALL_TRANSACTION + 14);
}
public void addStream(java.lang.String streamDefinition, java.lang.String packageName) throws android.os.RemoteException;
public void removeStream(java.lang.String streamName, java.lang.String packageName) throws android.os.RemoteException;
public void addQuery(java.lang.String queryDefinition, java.lang.String packageName) throws android.os.RemoteException;
public void removeQuery(java.lang.String queryName, java.lang.String packageName) throws android.os.RemoteException;
public java.util.List<org.wso2.edgeanalyticsservice.Stream> getAllStreams() throws android.os.RemoteException;
public void validateExecutionPlan(java.lang.String packageName) throws android.os.RemoteException;
public void startExecutionPlan(java.lang.String packageName) throws android.os.RemoteException;
public void addQueryCallback(java.lang.String queryName, java.lang.String packageName, java.lang.String ownPackageName, org.wso2.edgeanalyticsservice.IEdgeAnalyticsCallback callback) throws android.os.RemoteException;
public void addDynamicQueryCallback(java.lang.String queryName, java.lang.String packageName) throws android.os.RemoteException;
public void addStreamCallback(java.lang.String stream, java.lang.String packageName, java.lang.String ownPackageName, org.wso2.edgeanalyticsservice.IEdgeAnalyticsCallback callback) throws android.os.RemoteException;
public void addDynamicStreamCallback(java.lang.String stream, java.lang.String packageName) throws android.os.RemoteException;
public void sendData(java.lang.String id, java.lang.String stream, java.util.List<java.lang.String> values, java.util.List<java.lang.String> types) throws android.os.RemoteException;
public void subscribeStreamToData(java.lang.String packageName, java.lang.String streamDefinition) throws android.os.RemoteException;
public void subscribeExecutionPlan(java.lang.String packageName) throws android.os.RemoteException;
public void stop() throws android.os.RemoteException;
}

/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\workspace\\MultiDownload\\src\\com\\labmojo\\demo\\multidownload\\service\\ILibraryCallback.aidl
 */
package com.labmojo.demo.multidownload.service;
public interface ILibraryCallback extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.labmojo.demo.multidownload.service.ILibraryCallback
{
private static final java.lang.String DESCRIPTOR = "com.labmojo.demo.multidownload.service.ILibraryCallback";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.labmojo.demo.multidownload.service.ILibraryCallback interface,
 * generating a proxy if needed.
 */
public static com.labmojo.demo.multidownload.service.ILibraryCallback asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = (android.os.IInterface)obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.labmojo.demo.multidownload.service.ILibraryCallback))) {
return ((com.labmojo.demo.multidownload.service.ILibraryCallback)iin);
}
return new com.labmojo.demo.multidownload.service.ILibraryCallback.Stub.Proxy(obj);
}
public android.os.IBinder asBinder()
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
case TRANSACTION_updatingLibrary:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
this.updatingLibrary(_arg0, _arg1);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.labmojo.demo.multidownload.service.ILibraryCallback
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
public void updatingLibrary(int bookId, int number) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(bookId);
_data.writeInt(number);
mRemote.transact(Stub.TRANSACTION_updatingLibrary, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_updatingLibrary = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public void updatingLibrary(int bookId, int number) throws android.os.RemoteException;
}

/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\workspace\\MultiDownload\\src\\com\\labmojo\\demo\\multidownload\\service\\ILibrary.aidl
 */
package com.labmojo.demo.multidownload.service;
public interface ILibrary extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.labmojo.demo.multidownload.service.ILibrary
{
private static final java.lang.String DESCRIPTOR = "com.labmojo.demo.multidownload.service.ILibrary";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.labmojo.demo.multidownload.service.ILibrary interface,
 * generating a proxy if needed.
 */
public static com.labmojo.demo.multidownload.service.ILibrary asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = (android.os.IInterface)obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.labmojo.demo.multidownload.service.ILibrary))) {
return ((com.labmojo.demo.multidownload.service.ILibrary)iin);
}
return new com.labmojo.demo.multidownload.service.ILibrary.Stub.Proxy(obj);
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
case TRANSACTION_updateLibrary:
{
data.enforceInterface(DESCRIPTOR);
boolean _arg0;
_arg0 = (0!=data.readInt());
this.updateLibrary(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_downloadBooks:
{
data.enforceInterface(DESCRIPTOR);
this.downloadBooks();
reply.writeNoException();
return true;
}
case TRANSACTION_register:
{
data.enforceInterface(DESCRIPTOR);
com.labmojo.demo.multidownload.service.ILibraryCallback _arg0;
_arg0 = com.labmojo.demo.multidownload.service.ILibraryCallback.Stub.asInterface(data.readStrongBinder());
this.register(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.labmojo.demo.multidownload.service.ILibrary
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
public void updateLibrary(boolean downloadBooks) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(((downloadBooks)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_updateLibrary, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void downloadBooks() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_downloadBooks, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void register(com.labmojo.demo.multidownload.service.ILibraryCallback notifier) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((notifier!=null))?(notifier.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_register, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_updateLibrary = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_downloadBooks = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_register = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
}
public void updateLibrary(boolean downloadBooks) throws android.os.RemoteException;
public void downloadBooks() throws android.os.RemoteException;
public void register(com.labmojo.demo.multidownload.service.ILibraryCallback notifier) throws android.os.RemoteException;
}

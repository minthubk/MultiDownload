package com.labmojo.demo.multidownload.service;

import java.util.ArrayList;

import com.labmojo.demo.multidownload.model.Book;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

public class LibraryService extends Service {    
	
	final RemoteCallbackList<ILibraryCallback> libraryCallbacks = new RemoteCallbackList<ILibraryCallback>();
	
	protected static final String TAG = "LibraryService";
	private final IBinder mBinder = new ILibrary.Stub() {

		@Override
		public void updateLibrary(boolean downloadBooks) throws RemoteException {
			Log.d(TAG, "updateLibrary2");
			new Thread(new DownloadRun()).start();
		}

		@Override
		public void downloadBooks() throws RemoteException {
			Log.d(TAG, "downloadBooks");
		}
		
		@Override
		public void register(ILibraryCallback notifier) throws RemoteException {
            if (notifier != null) libraryCallbacks.register(notifier);
        }
		
	};

	private ArrayList<Book> books;
	
	public class DownloadRun implements Runnable {

		@Override
		public void run() {
			
			for (Book book: books) {
				int count = 0; 
				while(true) {			
					notifyUpdatingLibrary(book.id, count*5);		
					Log.d(TAG,"count:"+count);				
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					if(count == 20) break;
					count++;
				}
			}
		}
    	
    }
	
	public class DownloadRun2 implements Runnable {

		@Override
		public void run() {
			
			final int N = libraryCallbacks.beginBroadcast();
			int count = 0; 
			while(true) {
				if(count == 100) break;
				
				
				for (int i=0; i<N; i++) {
		            try {
		            	libraryCallbacks.getBroadcastItem(i).updatingLibrary(0,count);
		            } catch (RemoteException e) {
		                Log.e(TAG, "error while notifying callback", e);
		            }
		        }
				
				Log.d(TAG,"count:"+count);
				count++;
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			for (int i=0; i<N; i++) {
	            try {
	            	libraryCallbacks.getBroadcastItem(i).updatingLibrary(0,count);
	            } catch (RemoteException e) {
	                Log.e(TAG, "error while notifying callback", e);
	            }
	        }
			
			libraryCallbacks.finishBroadcast();
		}
    	
    }
	
	private void notifyUpdatingLibrary(int bookId, int percent) {
        final int N = libraryCallbacks.beginBroadcast();
        for (int i=0; i<N; i++) {
            try {
            	libraryCallbacks.getBroadcastItem(i).updatingLibrary(bookId, percent);
            } catch (RemoteException e) {
                Log.e(TAG, "error while notifying callback", e);
            }
        }
        libraryCallbacks.finishBroadcast();
    }

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return mBinder;
	}
	
	@Override
    public void onCreate() {
        super.onCreate();
        books = new ArrayList<Book>();
        books.add(new Book(12,"一本少個"));
        books.add(new Book(43,"我更啊電風扇"));
        books.add(new Book(36,"一白癜風把電飯煲少個"));
        books.add(new Book(72,"牛肉湯牛肉湯呢"));
        Log.i(TAG, "On create ");
    }
	
	@Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "On destroy");
    }

}

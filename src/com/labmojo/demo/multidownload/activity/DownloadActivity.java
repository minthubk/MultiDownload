package com.labmojo.demo.multidownload.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.labmojo.demo.multidownload.R;
import com.labmojo.demo.multidownload.activity.DownloadActivity.BookArrayAdapter;
import com.labmojo.demo.multidownload.model.Book;
import com.labmojo.demo.multidownload.model.BookStatus;
import com.labmojo.demo.multidownload.service.ILibrary;
import com.labmojo.demo.multidownload.service.ILibraryCallback;

public class DownloadActivity extends Activity {

    public static final String TAG = "DownloadActivity";
    
    boolean mBound;

	private ProgressBar progress1;

	private BookArrayAdapter bookadpater;
	
	

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        Log.d(TAG,"onCreate");
        progress1 = (ProgressBar)findViewById(R.id.progressBar1);
        ProgressBar progress2 = (ProgressBar)findViewById(R.id.progressBar2);
        ProgressBar progress3 = (ProgressBar)findViewById(R.id.progressBar3);
        //new DownloadTask(progress1).execute(" ");
        //new DownloadTask(progress2).execute(" ");
        //new DownloadTask(progress3).execute(" ");
        /*new Thread(new DownloadRun(new DownloadHandler(progress1))).start();
        new Thread(new DownloadRun(new DownloadHandler(progress2))).start();
        new Thread(new DownloadRun(new DownloadHandler(progress3))).start();
        
        //*/
        Intent intent = new Intent("com.labmojo.demo.multidownload.service.ILibrary");  
        boolean result = bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        if (!result) {
            Toast.makeText(this, "error", Toast.LENGTH_LONG).show();
        }
        
        
        ListView list_books = (ListView)findViewById(R.id.list);
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(new Book(12,"一本少個"));
        books.add(new Book(43,"我更啊電風扇"));
        books.add(new Book(36,"一白癜風把電飯煲少個"));
        books.add(new Book(72,"牛肉湯牛肉湯呢"));
        bookadpater= new BookArrayAdapter(this, R.layout.download_row, books);
        list_books.setAdapter(bookadpater);
    }
	
	public class BookArrayAdapter extends ArrayAdapter<ArrayList<Book>> {

		Context context;
		ArrayList<Book> books = new ArrayList<Book>();
		int textViewResourceId;

		public BookArrayAdapter(Context context, int textViewResourceId,
				ArrayList books) {
			super(context, textViewResourceId, books);
			this.textViewResourceId = textViewResourceId;
			this.context = context;
			this.books = books;
		}

		BookStatus mBookStatus;
		
		public void updateStatus(long bookId, int percent) {
	        mBookStatus = new BookStatus(bookId, percent);
	        notifyDataSetChanged();
	    }
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = convertView;
			if (row == null) {
				LayoutInflater inflater = ((Activity) context)
						.getLayoutInflater();
				row = inflater.inflate(textViewResourceId, parent, false);
			}
			Book book =  books.get(position);

			
			final BookStatus status = mBookStatus;
			
			TextView title = (TextView) row.findViewById(R.id.title);		
			title.setText("" + book.title);
			
			ProgressBar progress = (ProgressBar) row.findViewById(R.id.progress);
			
			if (status != null && status.getBookId() == book.getId()) {
                progress.setProgress(status.getPercent());
            }
			return row;
		}
	}
	
	private ILibraryCallback libraryCallback = new ILibraryCallback.Stub() {

		@Override
		public void updatingLibrary(final int bookId,final int percent) throws RemoteException {
			Log.d(TAG,"updatingLibrary callback booid:"+bookId +" percent:"+ percent);
			
			runOnUiThread(new Runnable() {
                public void run() {
                	bookadpater.updateStatus(bookId, percent);
                }
            });
		}

        
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_download, menu);
        return true;
    }
    
    ILibrary mService;
    
    private ServiceConnection mConnection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			Log.d(TAG, "onServiceConnected");
			mBound = true;
			mService = ILibrary.Stub.asInterface(service);
			
			try {
				mService.register(libraryCallback);
				mService.updateLibrary(true);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.e(TAG, "Service has unexpectedly disconnected");
			mService = null;
			mBound = false;
		}
    	
    };
    
    public class DownloadHandler extends Handler {
    	ProgressBar bar;
    	public DownloadHandler(ProgressBar bar) {
    		this.bar = bar;
    	}
    	
		@Override
		public void handleMessage(Message msg) {
			bar.setProgress(msg.what);
		}   	
    }
    
    public class DownloadRun implements Runnable {
    	Handler handler;
    	public DownloadRun(Handler handler) {
    		this.handler = handler;
    	}

		@Override
		public void run() {
			int count = 0; 
			while(true) {
				if(count == 100) break;
				publishProgress(count);
				Log.d(TAG,"count:"+count);
				count++;
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			publishProgress(100);
		}

		private void publishProgress(int count) {
			Message m = handler.obtainMessage(count);
			handler.sendMessage(m);
		}
    	
    }
    
    public class DownloadTask extends AsyncTask<String ,Integer ,String> {
    	ProgressBar progress;
		public DownloadTask(ProgressBar progress) {
			this.progress = progress;
		}

		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			int count = 0; 
			while(true) {
				if(count == 100) break;
				this.publishProgress(count);
				Log.d(TAG,"count:"+count);
				count++;
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			progress.setProgress(100);
		}

		@Override
		protected void onPreExecute() {
			progress.setProgress(0);
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			progress.setProgress(values[0]);
		}
    	
    }
    
    @Override
	protected void onStop() {
    	Log.d(TAG,"onStop");
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(TAG,"onDestroy");
		if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
	}

}

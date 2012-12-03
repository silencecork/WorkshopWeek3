package com.android.demo.nasa.feed;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class NasaDaily {
	
	private Activity mContext;
	private static final String URL = "http://www.nasa.gov/rss/image_of_the_day.rss";
	private IotdHandler iotdHandler;
	private Handler mHandler = new Handler();
	private Bitmap mBitmap;
	
	public NasaDaily(Activity activity) {
		mContext = activity;
	}
	
	public void processDialyImage(final TextView title, final TextView date, final TextView description, final ImageView image) {
		iotdHandler = new IotdHandler();
		final ProgressDialog progress = new ProgressDialog(mContext);
		progress.setMessage("Loading data, Please wait\u2026");
		progress.show();
		
		Thread th = new Thread(new Runnable() {
			public void run() {
				try {
			        iotdHandler.processFeed(mContext, new URL(URL));
			        final String strTitle = iotdHandler.getTitle();
			        final String strDate = iotdHandler.getDate();
			        final String strUrl = iotdHandler.getUrl();
			        final String strDesc = iotdHandler.getDescription();
			        Log.i("NasaDaily", "title " + strTitle + ", Date " + strDate + ", Url " + strUrl + ", Desc " + strDesc);
			        mBitmap = downloadBitmap(strUrl);
			        
			        mHandler.post(new Runnable() {
			        	public void run() {
			        		if (title != null) {
			        			title.setText(strTitle);
			        		}
			        		if (date != null) {
			        			date.setText(strDate);
			        		}
			        		if (description != null) {
			        			description.setText(strDesc);
			        		}
			        		if (mBitmap != null && image != null) {
			        			image.setImageBitmap(mBitmap);
			        		}
			        		
			        		progress.dismiss();
			        	}
			        });
			        
			    } catch (Exception e) { 
			    	e.printStackTrace();
			    }
			}
		}, "get_nasa_data");
		
		th.start();
	}
	
	public void setAsWallpaper() {
		
		final ProgressDialog progress = new ProgressDialog(mContext);
		progress.setMessage("Please wait when setting wallpaper\u2026");
		progress.show();
		
		Thread th = new Thread(new Runnable() {
			public void run() {
				if (mBitmap != null && !mBitmap.isRecycled()) {
					try {
						WallpaperManager.getInstance(mContext).setBitmap(mBitmap);
					} catch (IOException e) {
						e.printStackTrace();
					}
					progress.dismiss();
				}
			}
		}, "set_wallpaper_thread");
		th.start();
	}
	
	private Bitmap downloadBitmap(String strUrl) {
		HttpURLConnection conn = null;
		InputStream is = null;
		try {
			URL url = new URL(strUrl);
			conn = (HttpURLConnection) url.openConnection();
			conn.connect();
			
			int code = conn.getResponseCode();
			Log.d("Download", "check image result " + code);
			
			is = conn.getInputStream();
			if (is != null) {
				Bitmap b = BitmapFactory.decodeStream(is);
				return b;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(is);
			disconnect(conn);
		}
		
		return null;
	}
	
	private void close(Closeable c) {
		if (c != null) {
			try {
				c.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void disconnect(HttpURLConnection conn) {
		if (conn != null) {
			conn.disconnect();
		}
	}
	
}

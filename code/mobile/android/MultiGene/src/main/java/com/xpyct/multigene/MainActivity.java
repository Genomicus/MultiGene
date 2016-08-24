package com.xpyct.multigene;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends Activity
{
    private static final String LOG_TAG = "MultiGene";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


	// Get intent, action and MIME type
	Intent intent = getIntent();
	String action = intent.getAction();
	String type   = intent.getType();

	if (Intent.ACTION_SEND.equals(action) && type != null) {
	    if("text/plain".equals(type)) {
		handleSendText(intent);
	    } else if (type.startsWith("image/")) {
		handleSendImage(intent);
	    } else {
		Context ctx = getApplicationContext();
		Toast toast = Toast.makeText(ctx, "onCreate: "+type, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	    }
	} else if (Intent.ACTION_SEND_MULTIPLE.equals(action) && type != null) {
	    if (type.startsWith("image/")) {
		handleSendMultipleImages(intent);
	    }
        } else {
	    Log.i(LOG_TAG, "["+LOG_TAG+"] onCreate begin");

	    Context ctx = getApplicationContext();
	    Toast toast = Toast.makeText(ctx, "version: "+getVersionName("com.xpyct.multigene")+"\n"+
			                      "wifi-ip: "+getWiFiIPAddress(), Toast.LENGTH_SHORT);
	    toast.setGravity(Gravity.CENTER, 0, 0);
	    toast.show();
	}
    }

    String getWiFiIPAddress() {
        //WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
        //String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
	String ip = "0.0.0.0";
	return ip;
    }

    void handleSendText(Intent intent) {
        Log.i(LOG_TAG, "["+LOG_TAG+"] sendText: Begin");

	String sharedTitle = intent.getStringExtra(Intent.EXTRA_SUBJECT);
	String sharedText  = intent.getStringExtra(Intent.EXTRA_TEXT);
	if (sharedText != null) {
	    Context ctx = getApplicationContext();

	    // Update UI to reflect text being shared
	    TextView tvT = (TextView) findViewById(R.id.tvSendTitle);
	    tvT.setText(sharedTitle);

            String tvW = tvT.getText().toString();
            tvW = tvW.replace("\"", "\"\"");
            tvW = tvW.replace("\'", "\'\'");

	    TextView tvL = (TextView) findViewById(R.id.tvSendText);
	    tvL.setText(sharedText);

	    String tvS = tvL.getText().toString();
	    tvS = tvS.replace("\"", "\"\"");
	    tvS = tvS.replace("\'", "\'\'");

            //Toast toast = Toast.makeText(ctx, "sendText", Toast.LENGTH_SHORT);
	    //toast.setGravity(Gravity.CENTER, 0, 0);
	    //toast.show();

            try {
	        DbLite dbs = new DbLite(ctx);
	        dbs.create();
	        dbs.insert(tvW, tvS);
	        String info = tvS + "\nsaved in: " + dbs._dbPath;

	        Toast toast = Toast.makeText(ctx, info, Toast.LENGTH_SHORT);
	        toast.setGravity(Gravity.CENTER, 0, 0);
	        toast.show();
	    } catch (IOException e) {
	        e.printStackTrace();
            }
	}
    }

    void handleSendImage(Intent intent) {
        Log.i(LOG_TAG, "["+LOG_TAG+"] sendImage: Begin");

        Context ctx = getApplicationContext();
        Toast toast = Toast.makeText(ctx, "sendImage", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    void handleSendMultipleImages(Intent intent) {
        Log.i(LOG_TAG, "["+LOG_TAG+"] sendMultipleImages: Begin");

        Context ctx = getApplicationContext();
        Toast toast = Toast.makeText(ctx, "sendMultipleImages", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    //---------------------------------------------------------------------------------------------------------
    //Retrieves the version name of the application and returns to method caller
    //---------------------------------------------------------------------------------------------------------
    public String getVersionName(String packageName) {

        PackageInfo pinfo = null;
        try {
            pinfo = getPackageManager().getPackageInfo(packageName, 0);
        } catch (NameNotFoundException e) {         
            e.printStackTrace();
        }

        return pinfo.versionName;    
    }
}

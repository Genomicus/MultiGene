package com.xpyct.multigene;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import java.io.IOException;

//import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

public class DbLite {
    private   Context context;
    protected String _dbName;
    public    String _dbPath;
    protected SQLiteDatabase _db = null;
    protected String TAG = "Database Meta-Data";
	
    public DbLite(Context context) throws IOException {
        this.context          = context;
	//MainActivity activity = (MainActivity) getParent();
        String _appFolder     = "MultiGene";
        String _dbName        = "test.db";
        try {
            //ApplicationInfo ai = activity.getPackageManager().getApplicationInfo(activity.getPackageName(), PackageManager.GET_META_DATA);
	    ApplicationInfo ai = this.context.getPackageManager().getApplicationInfo(this.context.getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = ai.metaData;
            _dbName = bundle.getString("DATABASE");
        } catch (NameNotFoundException e) {
            Log.e(TAG, "Failed to load meta-data, NameNotFound: " + e.getMessage());
        } catch (NullPointerException e) {
            Log.e(TAG, "Failed to load meta-data, NullPointer: " + e.getMessage());			
        }
        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();  // sdcard
        InitDbLite(extStorageDirectory + '/' + _appFolder + '/' + _dbName);
    }

    public DbLite(String pathname) throws IOException {
	this._dbName = pathname;
    }

    private void InitDbLite(String path) {
        this._dbPath = path;
        this._db = context.openOrCreateDatabase(this._dbPath, Context.MODE_PRIVATE, null);
    }
	
    public void create() {
        //this._db.execSQL("create table IF NOT EXISTS notes (id      integer primary key autoincrement, "
        //        + " created date    default CURRENT_DATE, "
        //        + " text    varchar, "
        //        + " field   INT(3));");
        this._db.execSQL("create table IF NOT EXISTS notes2 (id      integer primary key autoincrement, "
                       + " created date    default CURRENT_DATE, "
		       + " title   varchar, "
                       + " text    varchar, "
                       + " field   INT(3));");
        //this._db.execSQL("create table IF NOT EXISTS config (id      integer primary key autoincrement, "
        //               + " created date    default CURRENT_DATE, "
        //               + " text    varchar, "
        //               + " field   INT(3));");
        //this._db.execSQL("INSERT INTO config (created, text) VALUES (datetime(), \"localhost\");");
        //System.out.println("record created");
    }

    public void insert(String title, String text) {
        //if( !this._db.isReadOnly() ) {
        //    this._db.execSQL("INSERT INTO notes (created, text) VALUES (datetime(), \"" + text + "\");");
        //}
        String aa = null;
        this._db.beginTransaction();
        try{
            //this._db.execSQL("INSERT INTO notes (created, text) VALUES (datetime(), \"" + text + "\");");
            this._db.execSQL("INSERT INTO notes2 (created, title, text) VALUES (datetime(), \"" + title + "\", \"" + text + "\");");
        }
        catch (SQLException ex){
            //return ex.toString();
            aa = ex.toString();
        }
        this._db.setTransactionSuccessful();
        this._db.endTransaction();
        aa = null;
        //return null;
    }

    public String getText() {
        Cursor c = this._db.rawQuery("SELECT * FROM config WHERE id=1", null);
        c.moveToFirst(); //c.moveToNext();
        String txt = c.getString( c.getColumnIndex("text"));
        System.out.println("localhost is: " + txt );
        return txt;
    }

    public void open() {
        ;
    }

    public void execute() {
	;
    }

    public void select() {
	;
    }

    public void close() {
	;
    }

    public void delete() {
	;
    }
}

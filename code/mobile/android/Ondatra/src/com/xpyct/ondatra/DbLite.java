package com.xpyct.ondatra;

import java.io.IOException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

//import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

public class DbLite {
    private Context context;

	protected String _appFolder;
	protected String _dbName;
    public    String _dbPath;
    protected SQLiteDatabase _db = null;
	
	public DbLite(Context context) throws IOException {
        this.context = context;
		String _appFolder = "/Ondatra";
		String _dbName    = "test.db";
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
        this._db.execSQL("create table IF NOT EXISTS notes (id      integer primary key autoincrement, "
                                                        + " created date    default CURRENT_DATE, "
                                                        + " text    varchar, "
                                                        + " field   INT(3));");
	}

    public void insert(String text) {
        this._db.execSQL("INSERT INTO notes (created, text) VALUES (datetime(), \"" + text + "\");");
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

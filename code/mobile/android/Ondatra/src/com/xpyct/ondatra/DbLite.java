package com.xpyct.ondatra;

import java.io.IOException;

import android.os.Environment;

public class DbLite {
	protected String _appFolder;
	protected String _dbName;
	
	public DbLite() {
		_appFolder = "/download/Ondatra";
		_dbName = "test.db";
		String extStorageDirectory = Environment.getExternalStorageDirectory().toString(); // /sdcard
		//DbLite(_dbname);
	}

	public DbLite(String pathname) throws IOException {
		this._dbName = pathname;
	}
	
	public void create() {
		;
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

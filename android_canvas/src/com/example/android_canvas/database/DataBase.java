package com.example.android_canvas.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class DataBase {

	private SQLiteDatabase mDatabase = null;

	private final static String DATABASE_NAME = "Tetris.db";

	private final static String TABLE_NAME = "player1";

	private final static String _ID = "_id";

	private final static String _NAME = "name";

	private final static String _POINT = "point";
	
	private final static String _TIME = "time";
	
	private static List<Player> players;
	
	public static Context context;

	private String Create_Table = "create table if not exists " + TABLE_NAME + 
			"(" + _ID + " integer primary key autoincrement," + _NAME + 
			" text," + _POINT + " integer," + _TIME + " TEXT)";

	@SuppressWarnings("static-access")
	public DataBase(Context context){
		mDatabase = context.openOrCreateDatabase(DATABASE_NAME,context.MODE_PRIVATE , null);
		mDatabase.execSQL(Create_Table);
		players = new ArrayList<Player>();
	}
	

	public void AddData(String name,int point,String time){
		ContentValues cv = new ContentValues();
		cv.put(_NAME, name);
		cv.put(_POINT, point);
		cv.put(_TIME, time);
		mDatabase.insert(TABLE_NAME, null, cv);
	}
	
	public List<Player> SearchPlayer(){
		Cursor cursor = mDatabase.rawQuery("select * from(select * from player1 order by point desc) limit 0,10", null);
		if(cursor != null){
			if(cursor.moveToFirst() ){
				do{
					Player player = new Player();
					player.setName(cursor.getString(cursor.getColumnIndex("name")));
					player.setPoint(cursor.getInt(cursor.getColumnIndex("point")));
					player.setTime(cursor.getString(cursor.getColumnIndex("time")));
					players.add(player);
				}while(cursor.moveToNext());
			}
		}
		return players;		
	}
	
	public void CloseDB(){
		mDatabase.close();
	}
}

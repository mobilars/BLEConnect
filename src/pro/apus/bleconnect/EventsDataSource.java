/*
 * Thanks to: http://www.vogella.com/articles/AndroidSQLite/article.html
 * 
 * Copyright APUS 2013. GPL Licensed. 
 * 
 */

package pro.apus.bleconnect;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class EventsDataSource {

  // Database fields
  private SQLiteDatabase database;
  private BLESQLLiteHelper dbHelper;
  private String[] allColumns = { BLESQLLiteHelper.COLUMN_ID,
		  BLESQLLiteHelper.COLUMN_SESSION,
		  BLESQLLiteHelper.COLUMN_TIME,
		  BLESQLLiteHelper.COLUMN_EVENTDATA };

  public EventsDataSource(Context context) {
    dbHelper = new BLESQLLiteHelper(context);
  }

  public void open() throws SQLException {
    database = dbHelper.getWritableDatabase();
  }

  public void close() {
    dbHelper.close();
  }

  public Event createEvent(int session, long time, int eventData) {
	  
    ContentValues values = new ContentValues();
    values.put(BLESQLLiteHelper.COLUMN_SESSION, session);
    values.put(BLESQLLiteHelper.COLUMN_TIME, time);
    values.put(BLESQLLiteHelper.COLUMN_EVENTDATA, eventData);
    
    long insertId = database.insert(BLESQLLiteHelper.TABLE_HRLOG, null,
        values);
    Cursor cursor = database.query(BLESQLLiteHelper.TABLE_HRLOG,
        allColumns, BLESQLLiteHelper.COLUMN_ID + " = " + insertId, null,
        null, null, null);
    cursor.moveToFirst();
    Event newEvent = cursorToEvent(cursor);
    cursor.close();
    return newEvent;
  }

  public void deleteEvent(Event event) {
    long id = event.getId();
    System.out.println("Comment deleted with id: " + id);
    database.delete(BLESQLLiteHelper.TABLE_HRLOG, BLESQLLiteHelper.COLUMN_ID
        + " = " + id, null);
  }

  public List<Event> getAllComments() {
    List<Event> events = new ArrayList<Event>();

    Cursor cursor = database.query(BLESQLLiteHelper.TABLE_HRLOG,
        allColumns, null, null, null, null, null);

    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
    	Event event = cursorToEvent(cursor);
    	events.add(event);
      cursor.moveToNext();
    }
    // make sure to close the cursor
    cursor.close();
    return events;
  }

  private Event cursorToEvent(Cursor cursor) {
    Event event = new Event();
    event.setId(cursor.getLong(0));
    event.setSession(cursor.getInt(1));
    event.setTime(cursor.getLong(2));
    event.setEventdata(cursor.getInt(3));    
    return event;
  }
} 
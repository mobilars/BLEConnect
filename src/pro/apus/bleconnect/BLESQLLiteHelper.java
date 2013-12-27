/*
 * Thanks to: http://www.vogella.com/articles/AndroidSQLite/article.html
 * 
 * Copyright APUS 2013. GPL Licensed. 
 * 
 */

package pro.apus.bleconnect;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BLESQLLiteHelper extends SQLiteOpenHelper {

  public static final String TABLE_HRLOG = "hrlog";
  public static final String COLUMN_ID = "_id";
  public static final String COLUMN_SESSION = "session";
  public static final String COLUMN_TIME = "time";
  public static final String COLUMN_EVENTDATA = "eventdata";

  private static final String DATABASE_NAME = "bleevents.db";
  private static final int DATABASE_VERSION = 1;

  // Database creation sql statement
  private static final String DATABASE_CREATE = "create table "
      + TABLE_HRLOG + "(" + COLUMN_ID
      + " integer primary key autoincrement, " 
      + COLUMN_SESSION + " int,"
      + COLUMN_TIME + " long,"
      + COLUMN_EVENTDATA + " int);";

  public BLESQLLiteHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase database) {
    database.execSQL(DATABASE_CREATE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.w(BLESQLLiteHelper.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    db.execSQL("DROP TABLE IF EXISTS " + COLUMN_SESSION);
    onCreate(db);
  }

} 
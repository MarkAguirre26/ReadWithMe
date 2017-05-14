package readwithme.com;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Sampler;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark on 10/18/2016.
 */

public class PlayerHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "PlayerDb";
    private static final String TABLE_PLAYER = "PlayerTable";
    private static final String KEY_ID = "cn";
    private static final String KEY_NAME = "name";
    private static final String KEY_GRADE = "GRADE";
    private static final String KEY_AGE = "AGE";
    private static final String KEY_TEACHER = "teacher";
    private static final String KEY_REMARK = "remark";


    public PlayerHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_PLAYER + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,  " + KEY_AGE + " TEXT, " + KEY_GRADE + " TEXT, " + KEY_TEACHER + " TEXT, " + KEY_REMARK + " TEXT)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYER);
        onCreate(db);
    }

    public void addPlayer(Player player) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, player.getName());
        values.put(KEY_AGE, player.getAge());
        values.put(KEY_GRADE, player.getGrade());
        values.put(KEY_TEACHER, player.getTeacherIndex());
        values.put(KEY_REMARK, player.getRemark());
        db.insert(TABLE_PLAYER, null, values);
        db.close(); // Closing database connection
    }

    public void getPlayerName(int val) {

        Cursor c = null;
        try {


            SQLiteDatabase db = this.getWritableDatabase();
            c = db.rawQuery("SELECT * FROM  " + TABLE_PLAYER + " WHERE " + KEY_REMARK + " =?", new String[]{val + ""});
            if (c.getCount() > 0) {

                c.moveToFirst();
                userInfo.cn = c.getColumnIndex(KEY_ID);
                userInfo.name = c.getString(c.getColumnIndex(KEY_NAME));
                userInfo.age = c.getString(c.getColumnIndex(KEY_AGE));
                userInfo.grade = c.getString(c.getColumnIndex(KEY_GRADE));
                userInfo.teacher = c.getInt(c.getColumnIndex(KEY_TEACHER));
                userInfo.remark = c.getString(c.getColumnIndex(KEY_REMARK));

                // p.setCn(c.getInt());

            }
            // return p;
        } finally {

            c.close();
        }
    }


    public void getPlayerLastIndex() {

        Cursor c = null;
        try {


            SQLiteDatabase db = this.getWritableDatabase();
            c = db.rawQuery("SELECT * FROM  " + TABLE_PLAYER + " ORDER BY DESC LIMIT 1", new String[]{null});
            if (c.getCount() > 0) {

                c.moveToFirst();
                variables.lastRowIndex = c.getColumnIndex(KEY_ID);
                variables.lastRowIndex++;

                // p.setCn(c.getInt());

            }
            // return p;
        } finally {

            c.close();
        }
    }

    public List<Player> getAllPlayer() {
        List<Player> pList = new ArrayList<Player>();
        String selectQuery;
        selectQuery = "SELECT * FROM " + TABLE_PLAYER + " ORDER BY " + KEY_ID + " DESC ";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Player player = new Player();
                player.setCn(Integer.parseInt(cursor.getString(0)));
                player.setName(cursor.getString(1));
                player.setAge(cursor.getString(2));
                player.setGrade(cursor.getString(3));
                player.setTeacherIndex(cursor.getInt(4));
                player.setRemark(cursor.getString(5));
                pList.add(player);
            } while (cursor.moveToNext());
        }

        return pList;
    }


    public int getPlayerCount() {
        String countQuery = "SELECT * FROM " + TABLE_PLAYER + " ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        //cursor.close();
// return count
        return cursor.getCount();
    }

    public int getCheckDuplicate(String name) {
        String countQuery = "SELECT * FROM " + TABLE_PLAYER + " Where " + KEY_NAME + " = '" + name + "'";
        Log.d("qry", countQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        //cursor.close();
// return count
        return cursor.getCount();
    }

    /*  public int updateScore(String Score) {
          SQLiteDatabase db = this.getWritableDatabase();
          ContentValues values = new ContentValues();
          values.put(KEY_SCORE, Score);
          return db.update(TABLE_PLAYER, values, KEY_ID + " = ?", new String[]{String.valueOf(Variables.user_cn)});
      }

      public int updateGameLevel(String Level) {
          SQLiteDatabase db = this.getWritableDatabase();
          ContentValues values = new ContentValues();
          values.put(KEY_REMARK, Level);
          return db.update(TABLE_PLAYER, values, KEY_ID + " = ?", new String[]{String.valueOf(Variables.user_cn)});
      }
  */
    public void updatePlayer(int val) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //  values.put(KEY_NAME, p.getName());
        values.put(KEY_REMARK, "1");
        values.put(KEY_TEACHER, userInfo.teacher);
// updating row
        db.update(TABLE_PLAYER, values, KEY_ID + " = ?", new String[]{String.valueOf(val)});
        // return
    }

    public void updateAllReamark() {

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_PLAYER + " SET " + KEY_REMARK + " = 0");
        db.close();
    }

    public void deletePlayer(Player player) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PLAYER, KEY_ID + " = ?", new String[]{String.valueOf(player.getCn())});
        db.close();
    }

}

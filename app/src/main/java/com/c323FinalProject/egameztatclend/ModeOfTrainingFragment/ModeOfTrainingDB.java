package com.c323FinalProject.egameztatclend.ModeOfTrainingFragment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.telephony.ims.RcsUceAdapter;

import androidx.annotation.Nullable;

import com.c323FinalProject.egameztatclend.ExerciseFragments.Exercise;

import java.util.ArrayList;

public class ModeOfTrainingDB extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ModesDB.db";
    private static final String TABLE_MODE = "myModes";
    private static final String COLUMN_NAME = "_name";
    private static final String COLUMN_SECONDS = "_seconds";


    public ModeOfTrainingDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_MODE_TABLE = "CREATE TABLE " + TABLE_MODE +
                "(" + COLUMN_NAME + " STRING PRIMARY KEY)";
        sqLiteDatabase.execSQL(CREATE_MODE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MODE);
        onCreate(sqLiteDatabase);
    }

    public void addMode(Mode mode) {
        deleteExpenseDB(TABLE_MODE);
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, mode.get_name());
        values.put(COLUMN_SECONDS, mode.get_seconds());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_MODE, null, values);
        db.close();
    }

    public Boolean deleteExpenseDB(String key) {
        boolean result = false;
        String query = "Select * FROM " + TABLE_MODE + " WHERE " + COLUMN_NAME + " =  \"" + key + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Exercise exercise = new Exercise();
        if (cursor.moveToFirst()) {
            exercise.set_name(cursor.getString(0));
            db.delete(TABLE_MODE, COLUMN_NAME + " = ?", new String[]{exercise.get_name()});
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_MODE, null);
        return res;
    }
}
package com.c323FinalProject.egameztatclend.ExerciseFragments;

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

import java.util.ArrayList;

public class MyExerciseDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ExercisesDB.db";
    private static final String TABLE_EXERCISE = "myExercises";
    private static final String COLUMN_NAME = "_name";
    private static final String COLUMN_BITMAP = "_bitmap";




    public MyExerciseDBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_EXPENSES_TABLE = "CREATE TABLE " + TABLE_EXERCISE +
                "(" + COLUMN_NAME + " STRING PRIMARY KEY," + COLUMN_BITMAP + " TEXT)";
        sqLiteDatabase.execSQL(CREATE_EXPENSES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_EXERCISE);
        onCreate(sqLiteDatabase);
    }

    public void addExercise(Exercise exercise) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, exercise.get_name());
        values.put(COLUMN_BITMAP, exercise.get_bitmap());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_EXERCISE, null, values);
        db.close();
    }


    public Boolean deleteExpenseDB(String key) {
        boolean result = false;
        String query = "Select * FROM " + TABLE_EXERCISE + " WHERE " + COLUMN_NAME + " =  \"" + key + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Exercise exercise = new Exercise();
        if(cursor.moveToFirst()){
            exercise.set_name(cursor.getString(0));
            db.delete(TABLE_EXERCISE, COLUMN_NAME + " = ?",new String[] {exercise.get_name()});
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_EXERCISE, null);
        return res;
    }

}

package com.example.employees;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper
{
    private Context context;
    private static final String DATABASE_NAME = "EmployeeDB.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "employees_table";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "employee_name";
    private static final String COLUMN_DESIGNATION = "employee_designation";


    public MyDatabaseHelper(@Nullable Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_DESIGNATION + " TEXT);";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addEmployee(String employee_name,String employee_designation)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();


        cv.put(COLUMN_NAME,employee_name);
        cv.put(COLUMN_DESIGNATION,employee_designation);

        long result =  db.insert(TABLE_NAME,null,cv);

        if (result == -1)
        {
            Toast.makeText(context, "Insertion Failed", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Inserted Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData()
    {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null)
        {
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }

    void deleteOneRow(String row_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
       
        long result = db.delete(TABLE_NAME,"id=?",new String[]{row_id});
        if (result == -1)
        {
            Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show();
        }
    }
}

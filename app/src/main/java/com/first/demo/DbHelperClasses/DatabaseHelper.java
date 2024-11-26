package com.first.demo.DbHelperClasses;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    public final static String DBNAME="healthConnect.db";
    public final static int DATABASE_VERSION = 1;
    public static DatabaseHelper instance = null;
    private  DatabaseHelper(Context context){
        super(context,DBNAME,null, DATABASE_VERSION);
    }


    public static DatabaseHelper getInstance(Context context){
        if(instance == null){
            instance = new DatabaseHelper(context);
        }
        return instance;
    }



    public void onCreate(SQLiteDatabase db) {
        PatientDbHelper.create(db);
        AppointmentDbHelper.create(db);
        ConsultationDbHelper.create(db);
        MedicationTrackingDbHelper.create(db);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        onCreate(db);

    }


    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }




    @Override
    public synchronized void close() {
        super.close();
    }



}

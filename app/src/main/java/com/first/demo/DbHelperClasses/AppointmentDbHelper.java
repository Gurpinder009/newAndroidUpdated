package com.first.demo.DbHelperClasses;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.first.demo.AppointmentsFragment;
import com.first.demo.models.Appointment;

import java.sql.Date;

public class AppointmentDbHelper {


    private final static String TABLE_NAME = "appointments";
    private final static String ID = "id";
    private final static String REASON = "reason";
    private final static String DATE = "date";
    private final static String PATIENT_ID ="patient_id";

    // Create table SQL statement
    public static void create(SQLiteDatabase db) {
        String sqlStatement = "CREATE TABLE " + TABLE_NAME + " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                REASON + " TEXT NOT NULL, " +
                DATE + " TEXT NOT NULL," +
                PATIENT_ID + " INTEGER,"+
                "FOREIGN KEY ("+PATIENT_ID+")REFERENCES patients (id)"+
                ");";
        db.execSQL(sqlStatement);
    }

    // Insert new appointment
    public static long add(DatabaseHelper dbHelper, Appointment appointment) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(REASON, appointment.getReason());
        values.put(DATE, appointment.getDate());
        values.put(PATIENT_ID,appointment.getPatientId());

        long result = db.insert(TABLE_NAME, null, values);
        db.close();
        return result;
    }

    // Update existing appointment
    public static int update(DatabaseHelper dbHelper, int id, Appointment appointment) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(REASON, appointment.getReason());
        values.put(DATE, appointment.getDate());
        values.put(PATIENT_ID,appointment.getPatientId());


        String selection = ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};

        int result = db.update(TABLE_NAME, values, selection, selectionArgs);
        db.close();
        return result;
    }

    // Delete an appointment
    public static int delete(DatabaseHelper dbHelper, int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = ID + " = ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_NAME, selection, args);
        db.close();
        return result;
    }



    public static String[] queryAll(DatabaseHelper dbHelper){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] apps;
        String[] projections = {DATE};
        Cursor cursor = db.query(TABLE_NAME, projections, null,null, null, null, null);
        apps = Appointment.allFromCursorStrings(cursor);
        cursor.close();
        db.close();
        return  apps;
    }

    // Query a single appointment by ID
    public static Appointment query(DatabaseHelper dbHelper, int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projections = {ID, REASON, DATE};
        String selection = ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};

        Cursor cursor = db.query(TABLE_NAME, projections, selection, selectionArgs, null, null, null);
        Appointment appointment = null;

        if (cursor.moveToFirst()) {
            appointment = Appointment.fromCursor(cursor);
        }

        cursor.close();
        db.close();
        return appointment;
    }


    public static void seedAppointments(DatabaseHelper dbHelper){

        AppointmentDbHelper.add(dbHelper,new Appointment(1,"fever","2000-11-10",1));
        AppointmentDbHelper.add(dbHelper,new Appointment(2,"fever","2000-11-10",2));
        AppointmentDbHelper.add(dbHelper,new Appointment(3,"fever","2000-11-10",1));
        AppointmentDbHelper.add(dbHelper,new Appointment(4,"fever","2000-11-10",4));
        AppointmentDbHelper.add(dbHelper,new Appointment(5,"fever","2000-11-10",3));
        AppointmentDbHelper.add(dbHelper,new Appointment(6,"fever","2000-11-10",2));

    }

}

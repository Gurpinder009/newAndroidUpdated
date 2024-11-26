package com.first.demo.DbHelperClasses;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.first.demo.models.Appointment;
import com.first.demo.models.Consultation;


//all done
public class ConsultationDbHelper {
    private final static String TABLE_NAME = "consultations";
    private final static String ID = "id";
    private final static String DATE = "date";
    private final static String SYMPTOMS = "symptoms";
    private final static String DIAGNOSES = "diagnoses";
    private final static String TREATMENTS = "treatments";
    private final static String PATIENT_ID = "patient_id";

    // Create table SQL statement
    public static void create(SQLiteDatabase db) {
        String sqlStatement = "CREATE TABLE " + TABLE_NAME + " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DATE + " DATETIME, " +
                SYMPTOMS + " TEXT NOT NULL, " +
                DIAGNOSES + " TEXT NOT NULL, " +
                TREATMENTS + " TEXT NOT NULL," +
                PATIENT_ID + " INTEGER,"+
                "FOREIGN KEY ("+PATIENT_ID+")REFERENCES patients (id)"+
                ");";
        db.execSQL(sqlStatement);
    }

    // Insert new consultation record
    public static long add(DatabaseHelper dbHelper, Consultation consultation) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DATE, String.valueOf(consultation.getDate()));
        values.put(SYMPTOMS, consultation.getSymptoms());
        values.put(DIAGNOSES, consultation.getDiagnoses());
        values.put(TREATMENTS, consultation.getTreatments());
        values.put(PATIENT_ID,consultation.getPatientId());

        long result = db.insert(TABLE_NAME, null, values);
        db.close();
        return result;
    }

    // Update existing consultation record
    public static int update(DatabaseHelper dbHelper, int id, Consultation consultation) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DATE, String.valueOf(consultation.getDate()));
        values.put(SYMPTOMS, consultation.getSymptoms());
        values.put(DIAGNOSES, consultation.getDiagnoses());
        values.put(TREATMENTS, consultation.getTreatments());
        values.put(PATIENT_ID,consultation.getPatientId());


        String selection = ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};

        int result = db.update(TABLE_NAME, values, selection, selectionArgs);
        db.close();
        return result;
    }

    // Delete a consultation record
    public static int delete(DatabaseHelper dbHelper, int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = ID + " = ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_NAME, selection, args);
        db.close();
        return result;
    }

    // Query a single consultation record by ID
    public static Consultation query(DatabaseHelper dbHelper, int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projections = {ID, DATE, SYMPTOMS, DIAGNOSES, TREATMENTS};
        String selection = ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};

        Cursor cursor = db.query(TABLE_NAME, projections, selection, selectionArgs, null, null, null);
        Consultation consultation = null;

        if (cursor.moveToFirst()) {
            consultation = Consultation.fromCursor(cursor);
        }

        cursor.close();
        db.close();
        return consultation;
    }


    public static String[] queryAll(DatabaseHelper dbHelper){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] apps;
        String[] projections = {DATE};
        Cursor cursor = db.query(TABLE_NAME, projections, null,null, null, null, null);
        apps = Consultation.allFromCursorStrings(cursor);
        cursor.close();
        db.close();
        return  apps;
    }


    public static void seedConsultation(DatabaseHelper dbHelper){
        ConsultationDbHelper.add(dbHelper,new Consultation(1,"2000-11-20","demo","kimo","drink water",1));
        ConsultationDbHelper.add(dbHelper,new Consultation(2,"2000-11-20","demo","kimo","drink water",2));
        ConsultationDbHelper.add(dbHelper,new Consultation(3,"2000-11-20","demo","kimo","drink water",3));
        ConsultationDbHelper.add(dbHelper,new Consultation(4,"2000-11-20","demo","kimo","drink water",1));
        ConsultationDbHelper.add(dbHelper,new Consultation(5,"2000-11-20","demo","kimo","drink water",4));
        ConsultationDbHelper.add(dbHelper,new Consultation(6,"2000-11-20","demo","kimo","drink water",1));
        ConsultationDbHelper.add(dbHelper,new Consultation(7,"2000-11-20","demo","kimo","drink water",6));
        ConsultationDbHelper.add(dbHelper,new Consultation(8,"2000-11-20","demo","kimo","drink water",6));
        Log.i("seeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeed", "seedConsultation: seeded");
    }






}

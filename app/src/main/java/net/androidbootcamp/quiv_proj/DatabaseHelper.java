package net.androidbootcamp.quiv_proj;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Name
    private static final String DATABASE_NAME = "quizproject.db";

    // Table Names
    private static final String TABLE_teacher = "Teacher";
    private static final String TABLE_course = "Course";
    private static final String TABLE_Student = "Student";

    // Column of teacher
    private static final String id_teacher = "id_teacher";
    private static final String name_teacher = "name_teacher";

    // Column of course
    private static final String id_course = "id_course";
    private static final String name_course = "name_course";
    private static final String id_teacher_course = "id_teacher_in";

    // Column of Student
    private static final String id_Student = "id_Student";
    private static final String name_Student = "name_Student";


    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Table Create  teacher
        db.execSQL("create table " + TABLE_teacher + " (id_teacher INTEGER PRIMARY KEY ,name_teacher TEXT,password_teacher TEXT DEFAULT '123456')");

        //  Table create course

        db.execSQL("create table " + TABLE_course + " (id_course INTEGER PRIMARY KEY ,name_course TEXT,id_teacher_in INTEGER, FOREIGN KEY (id_teacher_in) REFERENCES teacher (id_teacher))");

        //  Table create Student

        db.execSQL("create table " + TABLE_Student + " (id_Student INTEGER PRIMARY KEY ,name_Student TEXT,password_Student TEXT DEFAULT '123')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_teacher);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_course);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Student);
        onCreate(db);
    }

    public boolean insertDataTeacher(String id_teacher_in, String name_teacher_in) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(id_teacher, id_teacher_in);
        contentValues.put(name_teacher, name_teacher_in);
        long result = db.insert(TABLE_teacher, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }


    public boolean insertDataCourse(String id_course_in, String name_course_in, String id_teacher_in) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(id_course, id_course_in);
        contentValues.put(name_course, name_course_in);
        contentValues.put(id_teacher_course, id_teacher_in);

        long result = db.insert(TABLE_course, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }


    public boolean insertDataStudent(String id_Student_in, String name_Student_in) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(id_Student, id_Student_in);
        contentValues.put(name_Student, name_Student_in);

        long result = db.insert(TABLE_Student, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }


    public ArrayList<Course_Items> getAllDataCourse(int teacherId) {
        SQLiteDatabase db = this.getWritableDatabase();
       ArrayList<Course_Items> course = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + TABLE_course + " where id_teacher_in=" + teacherId, null);
        if (cursor.moveToFirst()) {
            do {
                String  nameCourse = cursor.getString (cursor.getColumnIndex("name_course"));
                 course.add(new Course_Items(nameCourse));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return course;
    }



    public String getNameTeacher(int idTeacherIn) {
        String name = "";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_teacher, null);
        if (cursor.moveToFirst()) {
            do {
                int id_teacher = cursor.getInt(cursor.getColumnIndex("id_teacher"));
                String name_teacher = cursor.getString(cursor.getColumnIndex("name_teacher"));
                if (idTeacherIn == id_teacher)
                    name = name_teacher;
            } while (cursor.moveToNext());
        }
        cursor.close();
        return name;
    }


    public boolean loginTeacher(String idTeacherIn, String passwordTeacherIn) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_teacher, null);
        String passwordTeacher;
        if (cursor.moveToFirst()) {
            do {
                passwordTeacher = cursor.getString(cursor.getColumnIndex("password_teacher"));
                int id_teacher = cursor.getInt(cursor.getColumnIndex("id_teacher"));
                if ((id_teacher + "").equals(idTeacherIn) && passwordTeacher.equals(passwordTeacherIn)) {
                    return true;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return false;
    }  

  


  /*  public boolean updateData_check(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name_teach", id);

        db.update(TABLE_Check, contentValues, "", null);
        return true;
    }*/
/*
    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }*/


}





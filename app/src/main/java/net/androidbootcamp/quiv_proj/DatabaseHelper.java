package net.androidbootcamp.quiv_proj;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Stack;


public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Name
    private static final String DATABASE_NAME = "quizproject.db";

    // Table Names
    private static final String TABLE_teacher = "Teacher";
    private static final String TABLE_course = "Course";
    private static final String TABLE_Student = "Student";
    private static final String TABLE_Add_Student = "Student_Add";

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


        db.execSQL("create table " + TABLE_Add_Student + " (id_student INTEGER  ,id_Course INTEGER ,FOREIGN KEY (id_student) REFERENCES Student (id_Student)," +
                "FOREIGN KEY (id_Course) REFERENCES Course (id_course))");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_teacher);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_course);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Student);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Add_Student);
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

    public boolean insertStudentInCourse(String id_course_in, String id_Student_in) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_Course", id_course_in);
        contentValues.put("id_student", id_Student_in);

        long result = db.insert(TABLE_Add_Student, null, contentValues);
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

   public int getIdCourse(int teacherId,String courseName) {
        SQLiteDatabase db = this.getWritableDatabase();
       int idCoursee=0;
        Cursor cursor = db.rawQuery("select * from " + TABLE_course + " where id_teacher_in=" + teacherId, null);
        if (cursor.moveToFirst()) {
            do {
                String  nameCourse = cursor.getString (cursor.getColumnIndex("name_course"));
                int  idCourse = cursor.getInt (cursor.getColumnIndex("id_course"));
               if (courseName==nameCourse){
                   idCoursee=idCourse;
               }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return idCoursee;
    }



    public Cursor getAllDataAddStudent(int teacherId,String courseName) {

        SQLiteDatabase db = this.getWritableDatabase();
        int idCourse =getIdCourse(teacherId,courseName);
        Cursor res = db.rawQuery("select * from "+TABLE_Add_Student +" where id_Course= "+idCourse , null);
        return res;

    }

// public ArrayList<StudentItems> getAllStudent(int teacherId,String courseName) {
//
//     SQLiteDatabase db = this.getWritableDatabase();
//     Cursor cursorAddStudent=getAllDataAddStudent(teacherId,courseName);
//     ArrayList<StudentItems> course = new ArrayList<>();
//     int  idStudentAdd = cursorAddStudent.getInt(cursorAddStudent.getColumnIndex("id_student"));
//     Cursor cursor = db.rawQuery("select * from " + TABLE_Student , null);
//     int  idStudent = cursor.getInt (cursor.getColumnIndex("id_Student"));
//     String  nameStudent = cursor.getString (cursor.getColumnIndex("name_Student"));
//
//
//     if (cursorAddStudent.moveToFirst()) {
//            do {
//
//                if (cursor.moveToFirst()) {
//                    do {
//                   if (idStudent==idStudentAdd){
//                       course.add(new StudentItems(nameStudent,idStudent));
//                   }
//                    } while (cursor.moveToNext());
//                }
//                cursor.close();
//
//            } while (cursorAddStudent.moveToNext());
//        }
//     cursorAddStudent.close();
//
//       return course;
//    }

    public boolean checkIdStudent(String idStudentIn) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_Student, null);
        String passwordTeacher;
        if (cursor.moveToFirst()) {
            do {
                int id_teacher = cursor.getInt(cursor.getColumnIndex("id_Student"));
                if ((id_teacher + "").equals(idStudentIn)) {
                    return true;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return false;
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

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_Add_Student, "Id_student = ?",new String[] {id});
    }


}





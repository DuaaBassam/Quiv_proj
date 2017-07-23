package net.androidbootcamp.quiv_proj;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
    private static final String TABLE_Quiz = "Quiz_table";
    private static final String TABLE_Question="question";

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
        db.execSQL("create table " + TABLE_teacher + " (id_teacher INTEGER PRIMARY KEY ,name_teacher TEXT,password_teacher TEXT DEFAULT '123')");

        //  Table create course

        db.execSQL("create table " + TABLE_course + " (id_course INTEGER not null ,name_course TEXT not null,id_teacher_in INTEGER, FOREIGN KEY (id_teacher_in) REFERENCES teacher (id_teacher),PRIMARY KEY(id_course,name_course))");

        //  Table create Student

        db.execSQL("create table " + TABLE_Student + " (id_Student INTEGER PRIMARY KEY ,name_Student TEXT,password_Student TEXT DEFAULT '123')");


        db.execSQL("create table " + TABLE_Add_Student + " (id_studentt INTEGER  ,id_Course INTEGER ,FOREIGN KEY (id_studentt) REFERENCES Student (id_Student)," +
                "FOREIGN KEY (id_Course) REFERENCES Course (id_course),primary key (id_studentt,id_Course))");

        db.execSQL("create table " + TABLE_Quiz + " (id_quiz INTEGER  ,name_quiz TEXT , password_quiz  INTEGER , id_teacher INTEGER  ,time_quiz INTEGER" +
                " , FOREIGN KEY (id_teacher) REFERENCES Teacher (id_teacher) , primary key (id_quiz))");

        db.execSQL("create table " + TABLE_Question + " (id_question INTEGER  ,statement TEXT , answer  TEXT , correct_answer TEXT ,id_quiz INTEGER" +
                " , FOREIGN KEY (id_quiz) REFERENCES "+ TABLE_Quiz+" (id_quiz) , primary key (id_question , id_quiz))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_teacher);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_course);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Student);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Add_Student);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Quiz);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Question);


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
        contentValues.put("id_studentt", id_Student_in);

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


    public boolean insertDataQuiz(String id_quiz_in, String name_quiz_in,String password_quiz_in ,String id_teacher_in ,String time_quiz_in) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_quiz", id_quiz_in);
        contentValues.put("name_quiz", name_quiz_in);
        contentValues.put("password_quiz", password_quiz_in);
        contentValues.put("id_teacher", id_teacher_in);
        contentValues.put("time_quiz", time_quiz_in);

        long result = db.insert(TABLE_Quiz, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean insertDataQuestion(String id_quiz_in, String name_quiz_in,String password_quiz_in ,String id_teacher_in ,String time_quiz_in) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("id_question", id_quiz_in);
        contentValues.put("statement", name_quiz_in);
        contentValues.put("answer", password_quiz_in);
        contentValues.put("correct_answer", id_teacher_in);
        contentValues.put("id_quiz", time_quiz_in);

        long result = db.insert(TABLE_Quiz, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }


    public Cursor getAllDataAddStudent(int teacherId,String courseName) {

        SQLiteDatabase db = this.getWritableDatabase();
        int idCourse =getIdCourse(teacherId,courseName);
        Cursor res = db.rawQuery("select * from "+TABLE_Add_Student +" where id_Course= "+idCourse , null);
        return res;

    }

    public ArrayList getAllData(int teacherId,String courseName) {

        SQLiteDatabase db = this.getWritableDatabase();
        int idCourse =getIdCourse(teacherId,courseName);
        ArrayList<StudentItems> arrayList = new ArrayList<>();
        Cursor res = db.rawQuery("select * from "+TABLE_Student + " inner join "+TABLE_Add_Student+" on id_studentt=id_Student "+
                " where id_Course= "+idCourse , null);

        if (res.moveToFirst()) {
            do {
                int  idStudentAdd = res.getInt (res.getColumnIndex("id_studentt"));
                String  nameStudent = res.getString (res.getColumnIndex("name_Student"));
                arrayList.add(new StudentItems(nameStudent,idStudentAdd));
            } while (res.moveToNext());
        }
        res.close();

        return arrayList;
    }

    public ArrayList<StudentItems> getAllStudent(int teacherId,String courseName) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursorAddStudent=getAllDataAddStudent(teacherId,courseName);
        ArrayList<StudentItems> course = new ArrayList<>();
        int  idStudentAdd = cursorAddStudent.getInt(cursorAddStudent.getColumnIndex("id_student"));
        Cursor cursor = db.rawQuery("select * from " + TABLE_Student , null);
        int  idStudent = cursor.getInt (cursor.getColumnIndex("id_Student"));
        String  nameStudent = cursor.getString (cursor.getColumnIndex("name_Student"));



        return course;
    }
    //

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


    public boolean getcheckadd(int idStudentIn,int courseIdIn) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_Add_Student, null);
        if (cursor.moveToFirst()) {
            do {
                int idStudent = cursor.getInt(cursor.getColumnIndex("id_studentt"));
                int idCourse = cursor.getInt(cursor.getColumnIndex("id_Course"));

                if ((idStudent==idStudentIn)&&(idCourse==courseIdIn)) {
                    return true;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return false;
    }



    public int getIdCourse(int teacherId,String courseName) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_course + " where id_teacher_in=" + teacherId, null);
        if (cursor.moveToFirst()) {
            do {
                String  nameCourse = cursor.getString (cursor.getColumnIndex(name_course));
                int idCourse = cursor.getInt (cursor.getColumnIndex(id_course));

                if (courseName.equals(nameCourse)){

                    return idCourse;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();

        return 0;
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
        Log.d("namee",name);
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
        return db.delete(TABLE_Add_Student, "id_studentt = ?",new String[] {id});
    }


}


package net.androidbootcamp.quiv_proj;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Name
    private static final String DATABASE_NAME = "quizproject.db";

    // Table Names
    private static final String TABLE_teacher = "Teacher";
    private static final String TABLE_course = "Course";
    private static final String TABLE_Student = "Student";
    private static final String TABLE_Add_Student = "Student_Add";
    private static final String TABLE_Quiz = "Quiz_table";
    private static final String TABLE_Question = "question";
    private static final String TABLE_AnswerStudent = "AnswerStudent";
    private static final String TABLE_GradeStudent = "GradeStudent";



    // Column of teacher
    private static final String id_teacher = "id_teacher";
    private static final String name_teacher = "name_teacher";
    // Column of course
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

        db.execSQL("create table " + TABLE_course + " (name_course TEXT not null,id_teacher_in INTEGER," +
                " FOREIGN KEY (id_teacher_in) REFERENCES teacher (id_teacher),PRIMARY KEY(name_course))");

        //  Table create Student

        db.execSQL("create table " + TABLE_Student + " (id_Student INTEGER PRIMARY KEY ,name_Student TEXT,password_Student TEXT DEFAULT '111')");


        db.execSQL("create table " + TABLE_Add_Student + " (id_studentt INTEGER  ," +
                " nameCourse TEXT , idTeach integer  , FOREIGN KEY (id_studentt) REFERENCES Student (id_Student),"
                +"FOREIGN KEY (idTeach) REFERENCES teacher (id_teacher),"+
                "FOREIGN KEY (nameCourse) REFERENCES Course (name_course),primary key (id_studentt, nameCourse ,idTeach))");

        db.execSQL("create table " + TABLE_Quiz + " (name_quiz TEXT , password_quiz  INTEGER , nameCourse TEXT " +
                " ,time_quiz INTEGER , idTeacher integer ,FOREIGN KEY (idTeacher) REFERENCES Teacher (id_teacher) " +
                " , FOREIGN KEY (nameCourse) REFERENCES Course (name_course) , primary key (name_quiz,nameCourse,idTeacher))");

        db.execSQL("create table " + TABLE_Question + " (id_question INTEGER  ,statement TEXT,nameCourse TEXT " +
                ", answer  TEXT , correct_answer TEXT ,name_quiz TEXT,time_ques INTEGER" +
                " , FOREIGN KEY (name_quiz) REFERENCES Quiz_table (name_quiz) , " +
                "FOREIGN KEY (nameCourse) REFERENCES Course (name_course) ,primary key (id_question , name_quiz,nameCourse))");

        db.execSQL("create table " + TABLE_AnswerStudent + " (nameQuiz TEXT , nameCourse TEXT " +
                "  ,idStudent integer , answers Text ,indexAnswer Text,FOREIGN KEY (idStudent) REFERENCES Student (id_Student) " +
                ",FOREIGN KEY (nameQuiz) REFERENCES  Quiz_table (name_quiz)  , FOREIGN KEY (nameCourse) REFERENCES Course (name_course)" +
                " , primary key (nameQuiz,nameCourse,idStudent))");


        db.execSQL("create table " + TABLE_GradeStudent + "(nameCourse TEXT not null,nameQuiz INTEGER,idStudentt INTEGER ," +
                "gradeQuiz integer , FOREIGN KEY (nameQuiz) REFERENCES Quiz_table (name_quiz)," +
                "FOREIGN KEY (nameCourse) REFERENCES Course (name_course)," +
                "FOREIGN KEY (idStudentt) REFERENCES Student (idStudent)," +
                " PRIMARY KEY(nameQuiz,nameCourse,idStudentt))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_teacher);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_course);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Student);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Add_Student);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Quiz);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Question);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AnswerStudent);


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


    public boolean insertDataCourse( String name_course_in, String id_teacher_in) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(name_course, name_course_in);
        contentValues.put(id_teacher_course, id_teacher_in);

        long result = db.insert(TABLE_course, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
    //بضيف  على حسب الا id كانت
    public boolean insertStudentInCourse(String nameCourse, int id_Student_in , int idTeach) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nameCourse", nameCourse);
        contentValues.put("id_studentt", id_Student_in);
        contentValues.put("idTeach", idTeach);

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


    public boolean insertDataQuiz(String name_quiz_in, int password_quiz_in, int id_teacher_in, int time_quiz_in, String course_in) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name_quiz", name_quiz_in);
        contentValues.put("password_quiz", password_quiz_in);
        contentValues.put("idTeacher", id_teacher_in);
        contentValues.put("time_quiz", time_quiz_in);
        contentValues.put("nameCourse", course_in);


        long result = db.insert(TABLE_Quiz, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean insertDataQuestion(int id_ques_in, String name_quiz_in, String statement, String answer, String correct,
                                      int time_ques_in, String namee) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("id_question", id_ques_in);
        contentValues.put("name_quiz", name_quiz_in);
        contentValues.put("statement", statement);
        contentValues.put("answer", answer);
        contentValues.put("correct_answer", correct);
        contentValues.put("time_ques", time_ques_in);
        contentValues.put("nameCourse", namee);
        long result = db.insert(TABLE_Question, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean insertDataQuestion(int id_ques_in, String name_quiz_in, String statement, String answer, String correct, String namee) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("id_question", id_ques_in);
        contentValues.put("name_quiz", name_quiz_in);
        contentValues.put("statement", statement);
        contentValues.put("answer", answer);
        contentValues.put("correct_answer", correct);
        contentValues.put("nameCourse", namee);
        long result = db.insertOrThrow(TABLE_Question, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }



    public boolean insertGradeStudent(String nameCourse, String nameQuiz,int  idStudent,int grade ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("nameQuiz", nameQuiz);
        contentValues.put("idStudentt", idStudent);
        contentValues.put("nameCourse", nameCourse);
        contentValues.put("gradeQuiz",grade);

        long result = db.insert(TABLE_GradeStudent, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }


    public boolean insertStudentAnswer(String nameCourse, String nameQuiz,int  idStudent,String answers,String index ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nameQuiz", nameQuiz);
        contentValues.put("idStudent", idStudent);
        contentValues.put("nameCourse", nameCourse);
        contentValues.put("answers", answers);
        contentValues.put("indexAnswer", index);
        long result = db.insert(TABLE_AnswerStudent, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public String getStudentAnswer(String nameQuiz, String nameCoursee,int idStudent) {
        SQLiteDatabase db = this.getWritableDatabase();
        String course = "";
        Cursor cursor = db.rawQuery("select * from " + TABLE_AnswerStudent + " where (nameQuiz= '" + nameQuiz
                + "'" + "and nameCourse = '" + nameCoursee + "' and idStudent = "+idStudent+" )", null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("answers"));
                course=name;
            } while (cursor.moveToNext());
        }
        cursor.close();
        return course;
    }

    public ArrayList<Course_Items> getAllDataCourse(int teacherId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Course_Items> course = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + TABLE_course + " where id_teacher_in=" + teacherId, null);
        if (cursor.moveToFirst()) {
            do {
                String nameCourse = cursor.getString(cursor.getColumnIndex("name_course"));
                course.add(new Course_Items(nameCourse));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return course;
    }

    public ArrayList<String> getQuestion(String nameQuiz, String nameCoursee) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> course = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + TABLE_Question + " where (name_quiz= '" + nameQuiz
                + "'" + "and nameCourse = '" + nameCoursee + "')", null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("statement"));
                course.add(name);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return course;
    }

    public ArrayList<String> getQuestionAnswer(String nameQuiz, String nameCourse ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> course = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from " + TABLE_Question + " where(name_quiz= '" +
                nameQuiz + "'" + "and nameCourse = '" + nameCourse + "')", null);
        if (cursor.moveToFirst()) {
            do {
                String name_Course1 = cursor.getString(cursor.getColumnIndex("answer"));
                course.add(name_Course1);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return course;
    }
    public String getAnswerforQues(String nameQuiz, String nameCourse,int noQues) {
        SQLiteDatabase db = this.getWritableDatabase();
        String course = "";
        Cursor cursor = db.rawQuery("select * from " + TABLE_Question + " where(name_quiz= '" +
                nameQuiz + "'" + "and nameCourse = '" + nameCourse + "' and id_question = "+noQues+")", null);
        if (cursor.moveToFirst()) {
            do {
                String name_Course1 = cursor.getString(cursor.getColumnIndex("answer"));
                course = name_Course1;
            } while (cursor.moveToNext());
        }
        cursor.close();
        return course;
    }


    public String getQuestionAnswerCorrect(int idQuestion,String nameQuiz, String nameCourse) {
        SQLiteDatabase db = this.getWritableDatabase();
        String course = "";
        Cursor cursor = db.rawQuery("select * from " + TABLE_Question + " where name_quiz= '" + nameQuiz +
                "'" + "and nameCourse = '" + nameCourse + "'"+"and id_question ="+idQuestion, null);
        if (cursor.moveToFirst()) {
            do {
                String name_Course1 = cursor.getString(cursor.getColumnIndex("correct_answer"));
                course=(name_Course1);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return course;
    }


    public ArrayList getAllData(int teacherId, String courseName) {

        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<StudentItems> arrayList = new ArrayList<>();
        Cursor res = db.rawQuery("select * from " + TABLE_Student +
                " inner join " + TABLE_Add_Student + " on id_studentt=id_Student " +
                " where nameCourse= '" + courseName+"'", null);

        if (res.moveToFirst()) {
            do {
                int idStudentAdd = res.getInt(res.getColumnIndex("id_studentt"));
                String nameStudent = res.getString(res.getColumnIndex("name_Student"));
                arrayList.add(new StudentItems(nameStudent, idStudentAdd));
            } while (res.moveToNext());
        }
        res.close();

        return arrayList;
    }
    public List<String> getAllQuestion(String nameCourse, String nameQuiz) {

        SQLiteDatabase db = this.getWritableDatabase();
        List<String> arrayList= new ArrayList() ;
        Cursor res = db.rawQuery("select * from " + TABLE_Question +
                " where (nameCourse= '" + nameCourse+"' and name_quiz = '"+nameQuiz+"')", null);

        if (res.moveToFirst()) {
            do {
                String statement = res.getString(res.getColumnIndex("statement"));
                arrayList.add(statement);
            } while (res.moveToNext());
        }
        res.close();

        return arrayList;
    }
///////////////

    public boolean checkStudentSolveQuiz(int idStudentIn,String nameCourse,String nameQuiz) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_GradeStudent, null);
        if (cursor.moveToFirst()) {
            do {
                int idStudent = cursor.getInt(cursor.getColumnIndex("idStudentt"));
                String nameCourseIn = cursor.getString(cursor.getColumnIndex("nameCourse"));
                String nameQuizIn = cursor.getString(cursor.getColumnIndex("nameQuiz"));


                if ((idStudentIn==idStudent)&&(nameCourse.equals(nameCourseIn))&&(nameQuizIn.equals(nameQuiz))) {
                    return true;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return false;
    }


    ////////////////////
    public int getGradeStudent(int idStudentIn,String nameCourse,String nameQuiz) {
        SQLiteDatabase db = this.getWritableDatabase();
        int grade =0 ;
        Cursor cursor = db.rawQuery("select * from " + TABLE_GradeStudent + " where nameQuiz= '" + nameQuiz +
                "'" + "and nameCourse = '" + nameCourse + "'"+"and idStudentt ="+idStudentIn, null);
        if (cursor.moveToFirst()) {
            do {
                int gradeInDatabase = cursor.getInt(cursor.getColumnIndex("gradeQuiz"));
                grade=gradeInDatabase;
            } while (cursor.moveToNext());
        }
        cursor.close();
        return grade;
    }

    //

    public boolean checkIdStudent(int idStudentIn) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_Student, null);
        if (cursor.moveToFirst()) {
            do {
                int idStudent = cursor.getInt(cursor.getColumnIndex("id_Student"));
                if (idStudent==idStudentIn) {
                    return true;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return false;
    }

    public boolean getcheckadd(int idStudentIn, String  nameCoursee ,int idTeach) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_Add_Student, null);
        if (cursor.moveToFirst()) {
            do {
                int idStudent = cursor.getInt(cursor.getColumnIndex("id_studentt"));
                String nameCourse = cursor.getString(cursor.getColumnIndex("nameCourse"));
                int idTeach1 = cursor.getInt(cursor.getColumnIndex("idTeach"));


                if ((idStudent == idStudentIn) && (nameCourse.equals(nameCoursee))&&(idTeach1==idTeach)) {
                    return true;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return false;
    }

    ///////////////////////////

    public boolean getCheckTeacher(int idTeacher) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_teacher, null);
        if (cursor.moveToFirst()) {
            do {
                int idTeach = cursor.getInt(cursor.getColumnIndex("id_teacher"));
                if (idTeacher==idTeach) {
                    return true;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return false;
    }

    //////////////

    public boolean getCheckStudent(int idStudent) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_Student, null);
        if (cursor.moveToFirst()) {
            do {
                int idStud = cursor.getInt(cursor.getColumnIndex("id_Student"));
                if (idStudent==idStud) {
                    return true;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return false;
    }

    /////////////////////////
    public boolean getCheckPasswordInTeacher(String password , int idTeacher){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_teacher + " where id_teacher ="+idTeacher, null);
        if (cursor.moveToFirst()) {
            do {
                String passwordTeach = cursor.getString(cursor.getColumnIndex("password_teacher"));
                if (password.equals(passwordTeach)) {
                    return true;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();

        return false;
    }
///////////////////////////////////

    public boolean getCheckPasswordInStudent(String password , int idStudent){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_Student + " where id_Student ="+idStudent, null);
        if (cursor.moveToFirst()) {
            do {

                String passwordStudent = cursor.getString(cursor.getColumnIndex("password_Student"));
                if (password.equals(passwordStudent)) {
                    return true;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();

        return false;
    }


    //////////////////////
    public int getTotalTime(String courseName,String nameQuiz) {
        int totalTime = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_Quiz+ " where nameCourse = '" + courseName +"' and name_quiz = '"+nameQuiz+"'", null);
        if (cursor.moveToFirst()) {
            do {
                totalTime = cursor.getInt(cursor.getColumnIndex("time_quiz"));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return totalTime;
    }

    public int getQuesTime(int idQuestion,String courseName,String nameQuiz) {
        int totalTime = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_Question+ " where nameCourse = '" + courseName +"' " +
                "and name_quiz = '"+nameQuiz+"' and id_question = "+ idQuestion, null);
        if (cursor.moveToFirst()) {
            do {
                totalTime = cursor.getInt(cursor.getColumnIndex("time_ques"));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return totalTime;
    }

    public int  getItemsQuestion(String nameCourse,String nameQuiz) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from " + TABLE_Question+" where (name_quiz = '"+nameQuiz+"' and nameCourse" +
                " = '"+nameCourse+"')", null);
        int num=cursor.getCount();
        cursor.close();
        Log.d("nummmmm",num+"");
        return num;
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
        Log.d("namee", name);
        return name;
    }

    public boolean getNameQuiz(String nameQuiz,String nameCourse,int idTeacher) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_Quiz +
                " where (nameCourse = '"+nameCourse+"' and idTeacher ="+idTeacher+")", null);
        if (cursor.moveToFirst()) {
            do {

                String nameQ = cursor.getString(cursor.getColumnIndex("name_quiz"));
                if (nameQuiz.equals(nameQ))
                    return true;

            } while (cursor.moveToNext());
        }
        cursor.close();
        return false;
    }

    public ArrayList getAllNameQuiz(String nameCourse,int idTeacher) {
        String name = "";
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String>arrayList1=new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + TABLE_Quiz +
                " where (nameCourse = '"+nameCourse+"' and idTeacher ="+idTeacher+")", null);
        if (cursor.moveToFirst()) {
            do {

                String nameQ = cursor.getString(cursor.getColumnIndex("name_quiz"));
                arrayList1.add(nameQ);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return arrayList1;
    }


    public boolean checkTime(String nameQuiz,String nameCourse,int idTeacher) {
        String name = "";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_Quiz+" where (time_quiz <> 0 and nameCourse = '"+nameCourse
                + "' and idTeacher = "+idTeacher+")", null);
        if (cursor.moveToFirst()) {
            do {

                String nameQ = cursor.getString(cursor.getColumnIndex("name_quiz"));
                if (nameQuiz.equals(nameQ))
                    return true;
            } while (cursor.moveToNext());
        }
        cursor.close();
        return false;
    }

    public boolean checkTime(String nameQuiz,String nameCourse) {
        String name = "";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_Quiz+" where (time_quiz <> 0 and nameCourse = '"+nameCourse +"')", null);
        if (cursor.moveToFirst()) {
            do {
                String nameQ = cursor.getString(cursor.getColumnIndex("name_quiz"));
                if (nameQuiz.equals(nameQ))
                    return true;
            } while (cursor.moveToNext());
        }
        cursor.close();
        return false;
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

    public Integer deleteData(String id ,String nameCourse,String idTeach) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_Add_Student, "id_studentt = ? and nameCourse = ? and idTeach = ? ", new String[]{id,nameCourse,idTeach});
    }
    public Integer deleteStudentFromAnswerStudent(String id ,String nameCourse) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_AnswerStudent, "idStudent = ? and nameCourse = ? ", new String[]{id,nameCourse});
    }
    public Integer deleteStudentFromGradeStudent(String id ,String nameCourse) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_GradeStudent, "idStudentt = ? and nameCourse = ? ", new String[]{id,nameCourse});

    }


    public Integer deleteQuiz(String id,String nameCourse,int idTeacher) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_Quiz, "name_quiz = ? and nameCourse = ? and idTeacher = ?",
                new String[]{id,nameCourse,idTeacher+""});
    }

    public Integer deleteQues(int id,String nameCourse,String nameQuiz) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_Question, "id_question = "+id+" and nameCourse = '"+nameCourse+"'" +
                        " and name_quiz =  '"+nameQuiz+"'",
                null);
    }

    public Integer deleteQues(String nameCourse,String nameQuiz) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_Question, "nameCourse = '"+nameCourse+"'" +
                        " and name_quiz =  '"+nameQuiz+"'",
                null);
    }

    public Integer updateIdQuestion(int noQuesNew,int noQuesOld, String nameQuiz, String courseName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_question", noQuesNew);

        return db.update(TABLE_Question, contentValues, " (name_quiz= '" + nameQuiz + "'" +
                "and   nameCourse='" + courseName + "' and id_question ="+noQuesOld+")", null);
    }

    public Integer updateAnswer(int noQues, String nameQuiz, String answer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("answer", answer);


        return db.update(TABLE_Question, contentValues, " (name_quiz= '" + nameQuiz + "'" +
                "and id_question = '" + noQues + "')", null);
    }
    public Integer updateCorrectAnswer(int noQues, String nameQuiz, String correct) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("correct_answer", correct);

        return db.update(TABLE_Question, contentValues, " (id_question= " + noQues + " and  name_quiz ='" +
                nameQuiz + "')", null);

    }
    public Integer updateTimeQuiz(String nameCourse, String nameQuiz,int timeQuiz,int idTeacher) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("time_quiz", timeQuiz);


        return db.update(TABLE_Quiz, contentValues, " (name_quiz= '" + nameQuiz + "'" +
                "and nameCourse = '" + nameCourse + "' and idTeacher = "+idTeacher+")", null);
    }
    public Integer updatePasswordQuiz(String nameCourse, String nameQuiz,int password, int idTeacher) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password_quiz", password);


        return db.update(TABLE_Quiz, contentValues, " (name_quiz= '" + nameQuiz + "'" +
                "and nameCourse = '" + nameCourse + "' and idTeacher = "+idTeacher+")", null);
    }
    public Integer updateTimeQuestion(int noQues, String nameQuiz, int time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("time_ques", time);


        return db.update(TABLE_Question, contentValues, " (name_quiz= '" + nameQuiz + "'" +
                "and id_question = '" + noQues + "')", null);
    }
    public Integer updateStatementQuestion(int noQues, String nameQuiz, String statement) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("statement", statement);


        return db.update(TABLE_Question, contentValues, " (name_quiz= '" + nameQuiz + "'" +
                "and id_question = '" + noQues + "')", null);

    }

    /////////////////////////////

    public Integer updatePasswordInTeacher(int idTeacher , String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password_teacher", password);

        return db.update(TABLE_teacher, contentValues, " (id_teacher= "+idTeacher + ")", null);
    }
    //////////////////////

    public Integer updatePasswordInStudent(int idStudent , String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password_Student", password);

        return db.update(TABLE_Student, contentValues, " (id_Student= "+idStudent + ")", null);
    }
    //////////////////////

    public boolean loginStudent(String idStudentIn, String passwordStudentIn) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_Student, null);
        String passwordTeacher;
        if (cursor.moveToFirst()) {
            do {
                passwordTeacher = cursor.getString(cursor.getColumnIndex("password_Student"));
                int id_teacher = cursor.getInt(cursor.getColumnIndex("id_Student"));
                if ((id_teacher + "").equals(idStudentIn) && passwordTeacher.equals(passwordStudentIn)) {
                    return true;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return false;
    }

    public ArrayList<Course_Items> getListCourse(int studId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Course_Items> course = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + TABLE_course + " inner join " + TABLE_Add_Student + "" +
                " on name_course = nameCourse where (id_studentt = "+studId+")", null);
        if (cursor.moveToFirst()) {
            do {
                String nameCourse = cursor.getString(cursor.getColumnIndex("name_course"));
                course.add(new Course_Items(nameCourse));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return course;
    }

    public ArrayList<Course_Items> getListQuiz(String name_course) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Course_Items> course = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + TABLE_Quiz + " where nameCourse = '"+name_course+"'", null);
        if (cursor.moveToFirst()) {
            do {
                String nameCourse = cursor.getString(cursor.getColumnIndex("name_quiz"));
                course.add(new Course_Items(nameCourse));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return course;
    }

    public String getNameStudent(int idStudIn) {
        String name = "";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_Student, null);
        if (cursor.moveToFirst()) {
            do {
                int id_teacher = cursor.getInt(cursor.getColumnIndex("id_Student"));
                String name_teacher = cursor.getString(cursor.getColumnIndex("name_Student"));
                if (idStudIn == id_teacher)
                    name = name_teacher;
            } while (cursor.moveToNext());
        }
        cursor.close();
        Log.d("nameStud", name);
        return name;
    }
    public boolean checkPasswordQuiz(String nameQuiz,String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_Quiz, null);

        if (cursor.moveToFirst()) {
            do {
                String namequiz = cursor.getString(cursor.getColumnIndex("name_quiz"));
                String pass = cursor.getString(cursor.getColumnIndex("password_quiz"));

                if ((namequiz.equals(nameQuiz)) && ( pass.equals(password))) {
                    return true;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return false;
    }
}
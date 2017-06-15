package net.androidbootcamp.quiv_proj;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Name
    private static final String DATABASE_NAME = "quizproject.db";

    // Table Names
    private static final String TABLE_teacher= "teacher";
    private static final String TABLE_course= "course";

    // Column of teacher
    private static final String id_teacher = "id_teacher";
    private static final String name_teacher = "name_teacher";
    private static final String password_teacher = "password_teacher";

    // Column of course
    private static final String id_course= "id_course";
    private static final String name_course = "name_course";
    private static final String id_teacher_course = "id_teacher_in";

    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Table Create  teacher
        db.execSQL("create table " + TABLE_teacher +" (id_teacher INTEGER PRIMARY KEY AUTOINCREMENT,name_teacher TEXT,password_teacher TEXT)");

        //  Table create course

        db.execSQL("create table " + TABLE_course +" (id_course INTEGER PRIMARY KEY AUTOINCREMENT,name_course TEXT,id_teacher_in INTEGER, FOREIGN KEY (id_teacher_in) REFERENCES teacher (id_teacher))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_teacher);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_course);
        onCreate(db);
    }

    public boolean insertData_teacher(String id_teacher_in,String name_teacher_in,String password_teacher_in) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(id_teacher,id_teacher_in);
        contentValues.put(name_teacher,name_teacher_in);
        contentValues.put(password_teacher,password_teacher_in);

        long result = db.insert(TABLE_teacher,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }


    public boolean insertData_course(String id_course_in,String name_course_in,String id_teacher_in) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(id_course,id_course_in);
        contentValues.put(name_course,name_course_in);
        contentValues.put(id_teacher_course,id_teacher_in);

        long result = db.insert(TABLE_course,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

  public Cursor getAllData_teacher() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_teacher,null);
        return res;
    }

     /* public boolean updateData(String id,String name,String surname,String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }*/
}





/*
public class databaseHelper extends SQLiteOpenHelper{
    public  static final String DB_name = "quizSystem.sqlite";
    public  static final String DB_path = "/data/data/net.androidbootcamp.quiv_proj/database/";
    public Context context ;
    private SQLiteDatabase database ;

    public databaseHelper(Context context) {
        super(context,DB_name,null,1);
    }

   @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void openDatabase(){
        String db_path = context.getDatabasePath(DB_name).getPath();
        if (database != null && database.isOpen()){
            return;
        }
        database =SQLiteDatabase.openDatabase(db_path,null,SQLiteDatabase.OPEN_READWRITE);
    }
    public void closeDB(){
        if (database!= null){
            database.close();
        }
    }
public List<Product> getListP()

    {
        Product prodect= null;
        List

        openDatabase();
        Cursor cursor =database.rawQuery("SELECT * From techer",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            prodect = new Product(cursor.getInt(0),cursor.getString(1),cursor.getInt(2));
            Product


        }

    }*/



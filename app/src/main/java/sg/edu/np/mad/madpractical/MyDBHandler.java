package sg.edu.np.mad.madpractical;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class MyDBHandler extends SQLiteOpenHelper {
    String title = "MyDBHandler";
    public static String DATABASE_NAME = "user.db";
    public static int DATABASE_VERSION = 1;
    public static String USERS = "user";
    public static String COLUMN_NAME = "UserName";
    public static String COLUMN_DESCRIPTION = "Description";
    public static String COLUMN_ID = "ID";
    public static String COLUMN_FOLLOWED = "Followed";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        Log.i(title, "DB constructor");
    }
    @Override
    public void onCreate(SQLiteDatabase db){
//        String CREATE_TABLE_COMMAND = "CREATE TABLE " + USERS + "(" + COLUMN_NAME + " TEXT," + COLUMN_DESCRIPTION + " TEXT," + COLUMN_ID + " PRIMARY KEY AUTOINCREMENT," + COLUMN_FOLLOWED + " TEXT)";
        StringBuilder CREATE_TABLE_COMMAND;
        CREATE_TABLE_COMMAND = new StringBuilder()
                .append("CREATE TABLE")
                        .append(USERS).append("(")
                        .append(COLUMN_NAME).append(" TEXT, ")
                        .append(COLUMN_DESCRIPTION).append(" TEXT, ")
                        .append(COLUMN_ID).append(" PRIMARY KEY AUTO INCREMENT, ")
                        .append(COLUMN_FOLLOWED).append(" TEXT, ");
        Log.i(title, CREATE_TABLE_COMMAND.toString());
        db.execSQL(CREATE_TABLE_COMMAND.toString());
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + USERS);
        Log.i(title, "Drop and Create new DB");
        onCreate(db);
    }
    public void addUser(User user){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.name);
        values.put(COLUMN_DESCRIPTION, user.description);
        values.put(COLUMN_ID, user.id);
        values.put(COLUMN_FOLLOWED, user.followed);
        SQLiteDatabase db = this.getWritableDatabase();
        Log.i(title, "Adding User" + values);
        db.insert(USERS, null, values);
        db.close();
    }
    public ArrayList<User> getUsers(){
        ArrayList<User>readuser = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursorUser = db.rawQuery("SELECT * FROM " + USERS, null);
        if (cursorUser.moveToFirst()){
            do{
                readuser.add(new User(cursorUser.getString(0),
                        cursorUser.getString(1),
                        cursorUser.getInt(2),
                        Boolean.parseBoolean(cursorUser.getString(3))));

            } while(cursorUser.moveToNext());
        }
        cursorUser.close();
        return readuser;
    }
    public User updateUser(String name){
        String QUERY = "SELECT * FROM " + USERS + "WHERE " + COLUMN_NAME + "=\"" + name + "\"";
        Log.i(title, "Find with command " + QUERY);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(QUERY, null);

        User queryResult = new User();

        if(cursor.moveToFirst()){
            queryResult.setFollowed(Boolean.parseBoolean(cursor.getString(3)));
        }else{
            queryResult = null;
        }
        return queryResult;
    }



}

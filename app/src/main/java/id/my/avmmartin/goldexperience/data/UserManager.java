package id.my.avmmartin.goldexperience.data;

import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import id.my.avmmartin.goldexperience.data.model.User;
import id.my.avmmartin.goldexperience.exception.UserNotFoundException;
import id.my.avmmartin.goldexperience.utils.Constants;

public class UserManager extends SQLiteOpenHelper {
    static final String TABLE_NAME = "users";
    static final int VERSION = 1;

    public static final String ID = "id";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String FULLNAME = "fullname";
    public static final String BIRTHDAY = "birthday";
    public static final String PHONE = "phone";
    public static final String USERTYPE = "usertype";
    public static final String SEX = "sex";

    // create read update

    void insertNewUser(User user) {
        try (SQLiteDatabase db = getWritableDatabase()) {
            db.insert(TABLE_NAME, null, user.toContentValues());
        }
    }

    User getUserById(int id) {
        String selection = (
            ID + " = ?"
        );
        String[] selectionArgs = {
            Integer.toString(id)
        };

        try (SQLiteDatabase db = getReadableDatabase()) {
            try (Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null)) {
                cursor.moveToFirst();
                return new User(cursor);
            }
        }
    }

    User getUserByEmail(String email) throws UserNotFoundException {
        String selection = (
            EMAIL + " = ?"
        );
        String[] selectionArgs = {
            email
        };

        try (SQLiteDatabase db = getReadableDatabase()) {
            try (Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null)) {
                cursor.moveToFirst();
                return new User(cursor);

            } catch (CursorIndexOutOfBoundsException e) {
                throw new UserNotFoundException();
            }
        }

    }

    void updateUser(User user) {
        String whereClause = (
            ID + " = ?"
        );
        String[] whereArgs = {
            Integer.toString(user.getId())
        };

        try (SQLiteDatabase db = getWritableDatabase()) {
            db.update(TABLE_NAME, user.toContentValues(), whereClause, whereArgs);
        }
    }

    // overridden method

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + EMAIL + " TEXT UNIQUE, "
                + PASSWORD + " TEXT, "
                + FULLNAME + " TEXT, "
                + BIRTHDAY + " INTEGER, "
                + PHONE + " TEXT, "
                + USERTYPE + " INTEGER, "
                + SEX + " INTEGER"
                + ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL(
                "DROP TABLE IF EXISTS " + TABLE_NAME + ";"
            );
            onCreate(db);
        }
    }

    // constructor

    UserManager(Context context) {
        super(context, Constants.DB_NAME, null, VERSION);
    }
}

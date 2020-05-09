package id.my.avmmartin.goldexperience.data;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import id.my.avmmartin.goldexperience.data.model.Plan;
import id.my.avmmartin.goldexperience.utils.Constants;

public class PlanManager extends SQLiteOpenHelper {
    static final String TABLE_NAME = "plans";
    static final int VERSION = 1;

    public static final String ID = "id";
    public static final String FK_PLACE_ID = "fk_place_id";
    public static final String FK_USER_ID = "fk_user_id";
    public static final String NAME = "name";
    public static final String DATE = "date";
    public static final String TIME = "time";
    public static final String NOTE = "note";

    int sizeByUser(int fkUserId) {
        String selection = (
            FK_USER_ID + " = ?"
        );
        String[] selectionArgs = {
            Integer.toString(fkUserId)
        };

        try (SQLiteDatabase db = getReadableDatabase()) {
            // TODO: check this behaviour with onCreate(db);
            onCreate(db);
            return (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME, selection, selectionArgs);
        }
    }

    // create read delete

    void insertNewPlan(Plan plan) {
        try (SQLiteDatabase db = getWritableDatabase()) {
            db.insert(TABLE_NAME, null, plan.toContentValues());
        }
    }

    Plan getPlanByUserByPosition(int fkUserId, int position) {
        String selection = (
            FK_USER_ID + " = ?"
        );
        String[] selectionArgs = {
            Integer.toString(fkUserId)
        };

        try (SQLiteDatabase db = getReadableDatabase()) {
            try (Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null)) {
                cursor.moveToPosition(position);
                return new Plan(cursor);
            }
        }
    }

    void deletePlanById(int fkUserId, int id) {
        String whereClause = (
            ID + " = ?" + " AND " +
            FK_USER_ID + " = ?"
        );
        String[] whereArgs = {
            Integer.toString(id),
            Integer.toString(fkUserId)
        };

        try (SQLiteDatabase db = getReadableDatabase()) {
            db.delete(TABLE_NAME, whereClause, whereArgs);
        }
    }

    // overridden method

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FK_PLACE_ID + " INTEGER, "
                + FK_USER_ID + " INTEGER, "
                + ""
                + NAME + " TEXT, "
                + DATE + " INTEGER, "
                + TIME + " INTEGER, "
                + NOTE + " TEXT, "
                + ""
                + "FOREIGN KEY (" + FK_USER_ID + ") "
                + "REFERENCES " + UserManager.TABLE_NAME + " (" + UserManager.ID + ") "
                + "ON DELETE CASCADE "
                + "ON UPDATE NO ACTION"
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

    PlanManager(Context context) {
        super(context, Constants.DB_NAME, null, VERSION);
    }
}

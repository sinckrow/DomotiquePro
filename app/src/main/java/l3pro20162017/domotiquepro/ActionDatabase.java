package l3pro20162017.domotiquepro;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ActionDatabase extends SQLiteOpenHelper{

    SQLiteDatabase db;


    public  ActionDatabase(Context context){
        super(context, KeyWords.DATABADE_NAME, null, KeyWords.DATABASE_VERSION);
        db  = super.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        //db.execSQL("DROP TABLE IF EXISTS actions");
        db.execSQL(KeyWords.CREATE_DATABASE_ACTIONS);
        db.execSQL("INSERT INTO actions (libelle, code, confirm, option) VALUES ('Tout ouvrir','ALL UP', 1,0);");
        db.execSQL("INSERT INTO actions (libelle, code, confirm, option) VALUES ('Tout fermer','ALL DOWN', 1,0);");
        db.execSQL("INSERT INTO actions (libelle, code, confirm, option) VALUES ('Ouvrir Sud','SUD UP', 0,0);");
        db.execSQL("INSERT INTO actions (libelle, code, confirm, option) VALUES ('Fermer Sud','SUD DOWN', 0,0);");
        db.execSQL("INSERT INTO actions (libelle, code, confirm, option) VALUES ('Ouvrir Est','EST UP', 0,0);");
        db.execSQL("INSERT INTO actions (libelle, code, confirm, option) VALUES ('Fermer Est','EST DOWN', 0,0)");
        db.execSQL("INSERT INTO actions (libelle, code, confirm, option) VALUES ('Ouvrir Ouest','OUEST UP', 0,0);");
        db.execSQL("INSERT INTO actions (libelle, code, confirm, option) VALUES ('Fermer Ouest','OUEST DOWN', 0,0);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertAction(String libelle, String code, boolean confirm, boolean option){
        ContentValues values = new ContentValues();
        values.put("libelle", libelle);
        values.put("code", code);
        values.put("confirm", confirm);
        values.put("option", option);
        db.insert(KeyWords.DATABASE_TABLE_ACTIONS, null, values);
    }

    public void insertAction(Action action){
        ContentValues values = new ContentValues();
        values.put("libelle", action.getLibelle());
        values.put("code", action.getCode());
        values.put("confirm", action.getConfirm());
        values.put("option", action.getOption());
        db.insert(KeyWords.DATABASE_TABLE_ACTIONS, null, values);
    }

    public void updateAction(Action action){
        ContentValues values = new ContentValues();
        int id = action.getId();
        values.put("libelle", action.getLibelle());
        values.put("code", action.getCode());
        values.put("confirm", action.getConfirm());
        values.put("option", action.getOption());
        db.update(KeyWords.DATABASE_TABLE_ACTIONS, values,"_id=?", new String[]{String.valueOf(id)});
    }

    public void updateAction(int id, String libelle, String code, boolean confirm, boolean option){
        ContentValues values = new ContentValues();
        values.put("libelle", libelle);
        values.put("code", code);
        values.put("confirm", confirm);
        values.put("option", option);
        db.update(KeyWords.DATABASE_TABLE_ACTIONS, values,"_id=?", new String[]{String.valueOf(id)});
    }

    public void deleteAction(int id){
        db.delete(KeyWords.DATABASE_TABLE_ACTIONS, "_id=?", new String[]{String.valueOf(id)});
    }
}

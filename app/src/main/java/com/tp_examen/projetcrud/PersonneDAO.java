package com.tp_examen.projetcrud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PersonneDAO extends SQLiteOpenHelper {

    public PersonneDAO(@Nullable Context context) {
        super(context, "mabase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table Personne(_id integer primary key," +
                "nom text, prenom text, age integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop table if exists Personne");
        onCreate(db);
    }

    public void insertPersonne(Personne p){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("nom", p.getNom());
        cv.put("prenom", p.getPrenom());
        cv.put("age", p.getAge());

        db.insert("Personne",null,cv);
        db.close();
    }

    public void updatePersonne(Personne p){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("nom", p.getNom());
        cv.put("prenom", p.getPrenom());
        cv.put("age", p.getAge());

        db.update("Personne",cv,"_id=?",new String[]{String.valueOf(p.getId())});
        db.close();
    }

    public void deletePersonne(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("Personne","_id=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public Cursor getAllPersonne(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("Select * From Personne",null);
        return c;
    }

    public Personne getOnePersonne(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.query("Personne", new String[]{"_id","nom","prenom","age"},
                "_id=?", new String[]{String.valueOf(id)},
                null, null, null);

        c.moveToFirst();
        Personne p = new Personne(c.getInt(0),c.getString(1),c.getString(2),c.getInt(3));

        return p;
    }
}

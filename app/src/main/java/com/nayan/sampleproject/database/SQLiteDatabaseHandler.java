package com.nayan.sampleproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Sampler;
import android.util.Log;

import androidx.annotation.Nullable;

import com.nayan.sampleproject.adapter.ListAdapter;
import com.nayan.sampleproject.model.Doc;
import com.nayan.sampleproject.view.MainActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SQLiteDatabaseHandler extends SQLiteOpenHelper {

    Context context;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "SampleDB";
    private static final String TABLE_NAME = "Sample";
    private static final String KEY_UNIQUE = "sample_project_id";
    private static final String KEY_ID = "id";
    private static final String KEY_PUBLICATION_DATE = "publication_date";
    private static final String KEY_ARTICLE_TYPE = "article_type";
    private static final String KEY_ABSTRACT = "abstract";
    private static final String[] COLUMNS = {KEY_UNIQUE, KEY_ID, KEY_PUBLICATION_DATE, KEY_ARTICLE_TYPE, KEY_ABSTRACT};

    public SQLiteDatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATION_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_UNIQUE + "INTEGER PRIMARY KEY," + KEY_ID + " TEXT," + KEY_PUBLICATION_DATE + " TEXT,"
                + KEY_ARTICLE_TYPE + " TEXT," + KEY_ABSTRACT + " TEXT" + ")";
        db.execSQL(CREATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public Doc getDoc(int id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{KEY_UNIQUE, KEY_ID,
                        KEY_PUBLICATION_DATE, KEY_ARTICLE_TYPE, KEY_ABSTRACT}, KEY_UNIQUE + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();
        Doc doc = new Doc();
        doc.setId(cursor.getString(cursor.getColumnIndex(KEY_ID)));
        doc.setPublicationDate(cursor.getString(cursor.getColumnIndex(KEY_PUBLICATION_DATE)));
        doc.setArticleType(cursor.getString(cursor.getColumnIndex(KEY_ARTICLE_TYPE)));
        return doc;
    }

    public List<Doc> getAllDocs() {
        List<Doc> docList = new ArrayList<>();
        String query = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Doc doc;

        try {
            if (cursor.moveToFirst()) {
                do {
                    doc = new Doc();
             //       doc.setId(cursor.getString(cursor.getColumnIndex(KEY_ID)));
                    doc.setPublicationDate(cursor.getString(cursor.getColumnIndex(KEY_PUBLICATION_DATE)));
                    doc.setArticleType(cursor.getString(cursor.getColumnIndex(KEY_ARTICLE_TYPE)));
                    List<String> array = new ArrayList<String>();
                    String uname = cursor.getString(cursor.getColumnIndex(KEY_ABSTRACT));
                    array.add(uname);
                    doc.setAbstract(array);
                    docList.add(doc);
                } while (cursor.moveToNext());
            }
        }catch (Exception e){
            Log.d("Exception: ",e.toString());
        }


        return docList;
    }

    public void addDoc(List<Doc> doc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        try {
            for (int i = 0; i < doc.size(); i++) {
            //    values.put(KEY_ID, doc.get(i).getId());
                values.put(KEY_PUBLICATION_DATE, doc.get(i).getPublicationDate());
                values.put(KEY_ARTICLE_TYPE, doc.get(i).getArticleType());

                values.put(KEY_ABSTRACT, doc.get(i).getAbstract().get(0));
                Log.d("Added ", "" + values);
                // insert
                db.insertOrThrow(TABLE_NAME, null, values);
            }
        } catch (Exception e) {
            Log.e("Problem", e + " ");
        }
        db.close();

    }

    public int updateDoc(Doc doc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PUBLICATION_DATE, doc.getPublicationDate());
        values.put(KEY_ARTICLE_TYPE, doc.getArticleType());

        int i = db.update(TABLE_NAME, // table
                values, // column/value
                "id = ?", // selections
                new String[]{String.valueOf(doc.getId())});

        db.close();

        return i;
    }

    public int getCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME);
        db.close();
    }
}

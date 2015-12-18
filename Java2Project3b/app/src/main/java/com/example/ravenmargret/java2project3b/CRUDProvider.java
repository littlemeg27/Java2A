/**
 * Created by Brenna Pavlinchak on 12/14/15.
 */

package com.example.ravenmargret.java2project3b;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

public class CRUDProvider extends ContentProvider
{
    private static final String AUTHORITY = "com.example.ravenmargret.java2project3a.CRUDProvider";
    private static final String BASE_DATA = "Crud";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_DATA);

    private static final int CRUD = 4;
    private static final int CRUD_ID = 1;

    private static final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    static
    {
        matcher.addURI(AUTHORITY, BASE_DATA, CRUD);
        matcher.addURI(AUTHORITY, BASE_DATA + "/#", CRUD_ID);
    }

    SQLiteDatabase database;

    @Override
    public boolean onCreate()
    {
        DatabaseSyncer sync = new DatabaseSyncer(getContext());
        database = sync.getWritableDatabase();
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder)
    {
        return database.query(DatabaseSyncer.CRUD_TABLE, DatabaseSyncer.ALL, selection, selectionArgs, sortOrder, null, null);
    }

    @Nullable
    @Override
    public String getType(Uri uri)
    {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values)
    {
        long id = database.insert(DatabaseSyncer.CRUD_TABLE, null, values);
        return Uri.parse(BASE_DATA + "/" + id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs)
    {
        return database.delete(DatabaseSyncer.CRUD_TABLE, selection, selectionArgs);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs)
    {
        return database.update(DatabaseSyncer.CRUD_TABLE, values, selection, selectionArgs);
    }
}

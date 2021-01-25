package com.example.findme;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.util.List;

public class DatabaseManager extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "FindMeGame.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseManager( Context context ) {
        super( context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable( connectionSource, Score.class );
            Log.i( "DATABASE", "onCreate invoked" );
        } catch( Exception exception ) {
            Log.e( "DATABASE", "Can't create Database", exception );
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable( connectionSource, Score.class, true );
            onCreate( database, connectionSource);
            Log.i( "DATABASE", "onUpgrade invoked" );
        } catch( Exception exception ) {
            Log.e( "DATABASE", "Can't upgrade Database", exception );
        }
    }

    public void insertScore( Score score ) {
        try {
            Dao<Score, Integer> dao = getDao( Score.class );
            dao.create( score );
            Log.i( "DATABASE", "insertScore invoked" );
        } catch( Exception exception ) {
            Log.e( "DATABASE", "Can't insert score into Database", exception );
        }
    }


    public List<Score> getAllScores() {
        try {
            Dao<Score, Integer> dao = getDao( Score.class );
            List<Score> scores = dao.queryForAll();
            Log.i( "DATABASE", "getAllScores invoked" );
            return scores;
        } catch( Exception exception ) {
            Log.e( "DATABASE", "Can't get score from Database", exception );
            return null;
        }
    }

    public void resetAll(List<Score> scores)
    {
        try {
            Dao<Score, Integer> dao = getDao( Score.class );
            dao.delete(scores);
            Log.i( "DATABASE", "All scores deleted" );
        } catch( Exception exception ) {
            Log.e( "DATABASE", "Can't delete scores from Database", exception );
        }
    }

}
package com.example.findme;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable( tableName = "T_Scores" )
public class Score {

    @DatabaseField( columnName = "idScore", generatedId = true )
    private int idScore;
    @DatabaseField
    private boolean score;
    @DatabaseField
    private int number;
    @DatabaseField
    private Date when;


    public Score(){

    }

    public Score(boolean score, int number, Date when) {
        this.score = score;
        this.number = number;
        this.when = when;
    }


}
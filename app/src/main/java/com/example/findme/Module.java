package com.example.findme;

import android.content.Context;
import android.content.Intent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Module {

    public static String getDateTime(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault());
        return dateFormat.format(date);
    }

    public static void setMenuItem(Context context, int id)
    {
        switch (id){
            case R.id.to_app_home:
                Intent intentToMainPage = new Intent(context, MainPage.class);
                context.startActivity(intentToMainPage);
                break;
            case R.id.to_game_play:
                Intent intentToPlayPage = new Intent(context, PlayPage.class);
                context.startActivity(intentToPlayPage);
                break;
            case R.id.to_game_history:
                Intent intentToHistoricPage = new Intent(context, HistoricPage.class);
                context.startActivity(intentToHistoricPage);
               break;
        }
    }
}

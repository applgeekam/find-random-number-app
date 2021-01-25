package com.example.findme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class PlayPage extends AppCompatActivity {

//    Components

    private EditText input;
    private TextView labelInfo;
    private TextView labelShowNUmber;
    private Button btnRest;
    private Button btnCheck;
    private Button btnStart;


    private int secretNumber;
    private int chance;
    private  Random random = new Random();
    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_play_page);
        databaseManager = new DatabaseManager( this );

        input = (EditText) findViewById(R.id.input_number);
        labelInfo = (TextView) findViewById(R.id.label_info);
        labelShowNUmber = (TextView) findViewById(R.id.label_show_number);
        btnRest = (Button) findViewById(R.id.btn_reset);
        btnCheck = (Button) findViewById(R.id.btn_check);
        btnStart = (Button) findViewById(R.id.btn_start);

        btnCheck.setEnabled(false);
        labelShowNUmber.setVisibility(View.INVISIBLE);

        btnRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayPage.this.showToast("Game reset ! ");
                labelShowNUmber.setVisibility(View.INVISIBLE);
                labelInfo.setVisibility(View.INVISIBLE);
                btnCheck.setEnabled(false);
                btnStart.setEnabled(true);
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText("");
                labelShowNUmber.setText("?");
                labelShowNUmber.setVisibility(View.VISIBLE);
                labelInfo.setVisibility(View.VISIBLE);
                labelInfo.setText("Enter a number.");
                showToast("Game start !");
                btnCheck.setEnabled(true);
                btnStart.setEnabled(false);

                secretNumber = random.nextInt(100);
                chance = 0;
            }
        });

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                labelInfo.setVisibility(View.VISIBLE);
                if (input.getText().toString().equals(""))
                {
                    labelInfo.setText("Please, enter a number.");
                }
                else {
                    int currentNUmber = Integer.parseInt(input.getText().toString());
                    if (chance == 4 && currentNUmber != secretNumber)
                    {
                        btnCheck.setEnabled(false);
                        btnStart.setEnabled(true);
                        showToast("Game lose. ");
                        labelShowNUmber.setText(Integer.toString(secretNumber));
                        labelInfo.setText("You lose ! The number was " + secretNumber + ". You may win next time. Play again");

                        // Save data to db
                        databaseManager.insertScore( new Score( false, secretNumber, new Date() ));
                    }
                    else
                    {
                        if (currentNUmber == secretNumber)
                        {
                            btnCheck.setEnabled(false);
                            btnStart.setEnabled(true);
                            showToast("You win ! ");
                            labelShowNUmber.setText(Integer.toString(secretNumber));
                            labelInfo.setText("You win ! The number was " + (secretNumber) + ". Good job. Play again");

//                            Save data to db
                            databaseManager.insertScore( new Score( true, secretNumber, new Date() ));

                        }
                        else if (currentNUmber > secretNumber)
                        {
                            showToast("You lose ! Your number is greater. ");
                            input.setText("");
                            labelInfo.setText("You lose. You still have " + (4 - chance) + " try. Try again.");
                            chance = ((int) chance) + 1;
                        }
                        else
                        {

                            showToast("You lose ! Your number is smaller. ");
                            input.setText("");
                            labelInfo.setText("You lose. You still have " + (4 - chance) + " try. Try again.");
                            chance = ((int) chance) + 1;
                        }
                    }
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Module.setMenuItem(PlayPage.this, item.getItemId());

        return super.onOptionsItemSelected(item);
    }


    public void showToast(String message)
    {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        View view = toast.getView();

        //To change the Background of Toast
        assert view != null;
        view.setBackgroundColor(Color.BLACK);
        TextView text = (TextView) view.findViewById(android.R.id.message);

        //Shadow of the Of the Text Color
        text.setShadowLayer(0, 0, 0, Color.TRANSPARENT);
        text.setTextColor(Color.WHITE);
        toast.show();

    }


}
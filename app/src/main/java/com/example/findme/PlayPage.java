package com.example.findme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import java.util.Random;

public class PlayPage extends AppCompatActivity {

//    Components

    private EditText input;
    private TextView labelInfo;
    private Button btnRest;
    private Button btnCheck;
    private int secretNumber;
    private int chance;
    private  Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_play_page);


        input = (EditText) findViewById(R.id.input_number);
        labelInfo = (TextView) findViewById(R.id.label_info);
        btnRest = (Button) findViewById(R.id.btn_reset);
        btnCheck = (Button) findViewById(R.id.btn_check);
        secretNumber = random.nextInt(100);
        chance = 0;



        btnRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText("");
                secretNumber = random.nextInt(100);
                chance = 0;
                labelInfo.setVisibility(View.INVISIBLE);
                PlayPage.this.showToast("Game reseted ! ");
            }
        });


        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                int currentNUmber = Integer.parseInt(input.getText().toString());
                labelInfo.setVisibility(View.VISIBLE);
                if (chance == 4 && currentNUmber != secretNumber)
                {
                    input.setText("");
                    showToast("Game lose. ");
                    labelInfo.setText("You lose ! The number was " + Integer.toString(secretNumber) + ". You may win next time. Try again");
                    secretNumber = random.nextInt(100);
                    chance = 0;
                }
                else
                {
                    if (currentNUmber == secretNumber)
                    {
                        showToast("You win ! ");
                        input.setText("");
                        secretNumber = random.nextInt(100);
                        chance = 0;
                        labelInfo.setText("You win ! The number was " + Integer.toString(secretNumber) + ". Good job. Try again");
                    }
                    else if (currentNUmber > secretNumber)
                    {
                        showToast("You lose ! Your number is greater. ");
                        input.setText("");
                        labelInfo.setText("You lose. You still have " + Integer.toString(4 - chance) + " try. Try again.");
                        chance = ((int) chance) + 1;
                    }
                    else
                    {

                        showToast("You lose ! Your number is smaller. ");
                        input.setText("");
                        labelInfo.setText("You lose. You still have " + Integer.toString(4 - chance) + " try. Try again.");
                        chance = ((int) chance) + 1;
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

        switch (item.getItemId()){
            case R.id.to_game_quite:
                finish();
                return true;
            case R.id.to_game_play:
                Intent intent = new Intent(PlayPage.this, PlayPage.class);
                startActivity(intent);
                return true;
        }

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
package com.example.findme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.List;


public class HistoricPage extends AppCompatActivity {

    private DatabaseManager databaseManager;
    private Button btnDelete;
    private List<Score> historics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique_page);
        databaseManager = new DatabaseManager( this );

        btnDelete = (Button) findViewById(R.id.btnDeleteALl);
        historics = databaseManager.getAllScores();


        setDataOnTableView(historics);

        if (historics.size() == 0)
        {
            btnDelete.setEnabled(false);
        }

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseManager.resetAll(historics);
                setDataOnTableView(databaseManager.getAllScores());
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

        Module.setMenuItem(HistoricPage.this, item.getItemId());

        return super.onOptionsItemSelected(item);
    }

    public void setDataOnTableView(List<Score> data)
    {
        RecyclerView recyclerView = findViewById(R.id.recyclerViewScoreHistoric);

        TableViewAdapter adapter = new TableViewAdapter(data);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(adapter);
    }
}
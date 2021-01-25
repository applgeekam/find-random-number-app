package com.example.findme;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TableViewAdapter extends RecyclerView.Adapter<TableViewAdapter.ViewHolder> {
    List<Score> historic;

    public TableViewAdapter(List<Score> historic) {
        this.historic = historic;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.table_list_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewHolder rowViewHolder = (ViewHolder) holder;

        int rowPos = rowViewHolder.getAbsoluteAdapterPosition();

        if (rowPos == 0) {
            // Header Cells. Main Headings appear here
            rowViewHolder.date.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.number.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.score.setBackgroundResource(R.drawable.table_header_cell_bg);

            rowViewHolder.date.setText("Date");
            rowViewHolder.number.setText("Number");
            rowViewHolder.score.setText("Score");

        } else {
            Score modal = historic.get(rowPos-1);

            // Content Cells. Content appear here
            rowViewHolder.date.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.number.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.score.setBackgroundResource(R.drawable.table_content_cell_bg);

            rowViewHolder.date.setText(modal.getWhen()+"");
            rowViewHolder.number.setText(Integer.toString(modal.getNumber()));
            if (modal.isScore())
            {
                rowViewHolder.score.setText("Win");
            }
            else {
                rowViewHolder.score.setText("Lose");
            }
        }
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        protected TextView date;
        protected TextView number;
        protected TextView score;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            date = itemView.findViewById(R.id.tableHeaderDate);
            number = itemView.findViewById(R.id.tableHeaderNumber);
            score = itemView.findViewById(R.id.tableHeaderScore);
        }
    }

    @Override
    public int getItemCount() {
//        one more to add header row
        return historic.size() + 1;
    }
}

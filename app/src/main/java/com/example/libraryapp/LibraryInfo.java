package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

public class LibraryInfo extends AppCompatActivity {

    private TextView txtTitle,txtSummary, txtDate,txtCopies;
    private ImageView bookImgView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_info);
        Intent intent =getIntent();
        LibraryItems item = intent.getParcelableExtra("Library item");

        String title = item.getBook();
        String author = item.getAuthor();
        String summary = item.getSummary();
        String date = item.getDate();
        int copy = item.getNumBooks();
        int image = item.getImageResource();

        bookImgView = findViewById(R.id.itemImageView);
        bookImgView.setImageResource(image);

        txtTitle = findViewById(R.id.titleTextView);
        txtTitle.setText(title + " by " + author);

        txtDate = findViewById(R.id.dateTextView);
        txtDate.append(date);

        txtCopies = findViewById(R.id.copiesTextView);
        txtCopies.append(" " + copy);

        txtSummary = findViewById(R.id.summaryTextView);
        txtSummary.setText(summary + "\n" + summary);
        //scrollbar for the textview
        //txtSummary.setMovementMethod(new ScrollingMovementMethod());


    }
}

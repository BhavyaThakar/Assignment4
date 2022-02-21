package com.example.random;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class BookDetails extends AppCompatActivity {

    TextView bookTextView, authorTextView, genreTextView, dateTextView, typeTextView, ageGroupTextView;
    Button edit;
    ArrayList<DataFile> dataFileArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        bookTextView = findViewById(R.id.getBookName);
        authorTextView = findViewById(R.id.getAuthorName);
        genreTextView = findViewById(R.id.getGenre);
        dateTextView = findViewById(R.id.getDate);
        typeTextView = findViewById(R.id.getType);
        ageGroupTextView = findViewById(R.id.getAgeGroup);

        String bookName = getIntent().getStringExtra("book");
        String authorName = getIntent().getStringExtra("author");
        String genre = getIntent().getStringExtra("genre");
        String type = getIntent().getStringExtra("type");
        String date = getIntent().getStringExtra("date");
        String age = getIntent().getStringExtra("age");

        bookTextView.setText(bookName);
        authorTextView.setText(authorName);
        genreTextView.setText(genre);
        typeTextView.setText(type);
        dateTextView.setText(date);
        ageGroupTextView.setText(age);

        edit = findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookDetails.this, UpdateDetails.class);
                intent.putExtra("BOOK", bookName);
                intent.putExtra("AUTHOR", authorName);
                intent.putExtra("GENRE", genre);
                intent.putExtra("TYPE", type);
                intent.putExtra("DATE", date);
                intent.putExtra("AGE", age);
                startActivity(intent);
            }
        });
    }
}
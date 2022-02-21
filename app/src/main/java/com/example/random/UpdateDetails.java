package com.example.random;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class UpdateDetails extends AppCompatActivity {

    private EditText et_book, et_author;
    private Button update;
    private TextView tvDate;
    private String date;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private CheckBox child, youth,adults, seniors;
    private DatePickerDialog.OnDateSetListener setListener;
    private ArrayList<String> checkedAge = new ArrayList<>();

    String genre;

    DbHandler dbHandler = new DbHandler(UpdateDetails.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details);

        Spinner spinner = findViewById(R.id.genre);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.genres, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        tvDate = findViewById(R.id.tv_date);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        UpdateDetails.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,setListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                date = year+ " / "+month+" / "+day;
                tvDate.setText(date);
            }
        };

        child = findViewById(R.id.child2);
        youth = findViewById(R.id.youth2);
        adults = findViewById(R.id.adult2);
        seniors = findViewById(R.id.senior2);




        child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (child.isChecked())
                    checkedAge.add("Children");

                else
                    checkedAge.remove("Children");

            }
        });

        youth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (youth.isChecked())
                    checkedAge.add("Youth");
                else
                    checkedAge.remove("Youth");
            }
        });

        adults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adults.isChecked())
                    checkedAge.add("Adults");
                else
                    checkedAge.remove("Adults");

            }
        });

        seniors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (seniors.isChecked())
                    checkedAge.add("Seniors");
                else
                    checkedAge.remove("Seniors");

            }
        });

        radioGroup = findViewById(R.id.radioGroup1);


        String book = getIntent().getStringExtra("BOOK");
        String author = getIntent().getStringExtra("AUTHOR");



        et_book = findViewById(R.id.editBook);
        et_book.setText(book);
        et_author = findViewById(R.id.editAuthor);
        et_author.setText(author);

        StringBuilder stringBuilder = new StringBuilder();
        for (String s : checkedAge)
            stringBuilder.append(s).append("\n ");






        dbHandler = new DbHandler(UpdateDetails.this);

        update = findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                genre = spinner.getSelectedItem().toString();

                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);

                StringBuilder stringBuilder = new StringBuilder();
                for (String s : checkedAge)
                    stringBuilder.append(s).append("\n");

                dbHandler.updateBook(book, et_book.getText().toString(), et_author.getText().toString(), genre, radioButton.getText().toString(), date, stringBuilder.toString());
                Intent i = new Intent(UpdateDetails.this, ShowBookList.class);
                startActivity(i);
            }
        });

    }
    public  void checkedRadioButton(View v){
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        Toast.makeText(this, "Selected Type : "+ radioButton.getText(), Toast.LENGTH_SHORT).show();
    }

}
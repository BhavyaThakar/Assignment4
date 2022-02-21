package com.example.random;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class ShowBookList extends AppCompatActivity {

    private ArrayList<DataFile> dataFileArrayList;
    private DbHandler dbHandler;
    private BookRVAdapter bookRVAdapter;
    private RecyclerView bookRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_book_list);

        dataFileArrayList = new ArrayList<>();

        dbHandler = new DbHandler(ShowBookList.this);
        dataFileArrayList = dbHandler.readBooks();

        bookRVAdapter = new BookRVAdapter(dataFileArrayList, ShowBookList.this);
        bookRV = findViewById(R.id.recyclerView);
        bookRV.setLayoutManager( new LinearLayoutManager(this));


        bookRV.setAdapter(bookRVAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        MenuItem BookSearchItem = menu.findItem(R.id.search_action);
        SearchView searchViewBook = (SearchView) BookSearchItem.getActionView();
        searchViewBook.setMaxWidth(Integer.MAX_VALUE);
        searchViewBook.setQueryHint("Search For Book Name");

        searchViewBook.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                bookRVAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }



//                                                           Implement Sort by Book Name in List

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.sort_action1:
                Collections.sort(dataFileArrayList, DataFile.BookNameComparator);
                bookRVAdapter.notifyDataSetChanged();
                return true;
            case R.id.sort_action2:
                Collections.sort(dataFileArrayList, DataFile.DateCompare);
                bookRVAdapter.notifyDataSetChanged();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
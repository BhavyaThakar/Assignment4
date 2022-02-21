package com.example.random;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookRVAdapter extends RecyclerView.Adapter<BookRVAdapter.ViewHolder>  implements Filterable {

    private  ArrayList<DataFile> bookDataArrayList;
    ArrayList<DataFile> backup;

    private Context context;

    public BookRVAdapter(ArrayList<DataFile> bookDataArrayList, Context context) {
        this.bookDataArrayList = bookDataArrayList;
        this.context = context;
        backup = new ArrayList<>(bookDataArrayList);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataFile data = bookDataArrayList.get(position);
        holder.t1.setText(data.getBookName());
        holder.t2.setText(data.getAuthorName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToDetails = new Intent(context, BookDetails.class);
                intentToDetails.putExtra("book", data.getBookName());
                intentToDetails.putExtra("author", data.getAuthorName());
                intentToDetails.putExtra("genre", data.getGenre());
                intentToDetails.putExtra("type", data.getType());
                intentToDetails.putExtra("date", data.getDate());
                intentToDetails.putExtra("age", data.getAge());


                context.startActivity(intentToDetails);
            }
        });

    }

    @Override
    public int getItemCount() {
        return bookDataArrayList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;

    }

        Filter filter = new Filter(){

            @Override
            protected FilterResults performFiltering(CharSequence keyword) {
                ArrayList<DataFile> filteredBook = new ArrayList<>();
                if (keyword.toString().isEmpty()){
                    filteredBook.addAll(backup);
                }

                else {
                    for (DataFile obj : backup){
                        if (obj.getBookName().toLowerCase().contains(keyword.toString().toLowerCase()) || obj.getAuthorName().toLowerCase().contains(keyword.toString().toLowerCase()) ||  obj.getGenre().toLowerCase().contains(keyword.toString().toLowerCase()) || obj.getType().toLowerCase().contains(keyword.toString().toLowerCase()))
                            filteredBook.add(obj);

                    }
                }
                FilterResults results = new FilterResults();
                results.values = filteredBook;

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                bookDataArrayList.clear();
                bookDataArrayList.addAll((ArrayList<DataFile>)results.values);
                notifyDataSetChanged();
            }
        };

    public class ViewHolder extends RecyclerView.ViewHolder {
        private  TextView t1, t2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            t1 = itemView.findViewById(R.id.bookName);
            t2 = itemView.findViewById(R.id.authorName);
        }
    }
}

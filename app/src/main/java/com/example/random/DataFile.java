package com.example.random;

import java.util.Comparator;

public class DataFile {
    private String bookName;
    private String authorName;
    private String genre;
    private String type;
    private String date;
    private String age;
    private int id;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DataFile(String bookName, String authorName, String genre, String type, String date, String age) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.genre = genre;
        this.type = type;
        this.date = date;
        this.age = age;
    }
    public static Comparator<DataFile> BookNameComparator = (book1, book2) -> book1.getBookName().compareTo(book2.getBookName());

    public static Comparator<DataFile> DateCompare = (date1, date2) -> date1.getDate().compareTo(date2.getDate());

}

package com.example.libraryapp;

import android.os.Parcel;
import android.os.Parcelable;

public class LibraryItems implements Parcelable {

    private int imageResource;
    private String author;
    private String book;
    private int numBooks;
    private String summary;
    private String date;


    public LibraryItems(int newImage,String newAuthor, String newBook,
                        int numberBooks,String summaryBook,String newDate ){
        imageResource= newImage;
        author = newAuthor;
        book= newBook;
        numBooks = numberBooks;
        summary = summaryBook;
        date = newDate;

    }//end constructor

    protected LibraryItems(Parcel in) {
        imageResource = in.readInt();
        author = in.readString();
        book = in.readString();
        numBooks = in.readInt();
        summary = in.readString();
        date = in.readString();
    }

    public static final Creator<LibraryItems> CREATOR = new Creator<LibraryItems>() {
        @Override
        public LibraryItems createFromParcel(Parcel in) {
            return new LibraryItems(in);
        }

        @Override
        public LibraryItems[] newArray(int size) {
            return new LibraryItems[size];
        }
    };

    //get/set methods
    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public int getNumBooks() {
        return numBooks;
    }

    public void setNumBooks(int numBooks) {
        this.numBooks = numBooks;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return "LibraryItems{" +
                "author='" + author + '\'' +
                ", book='" + book + '\'' +
                ", numBooks=" + numBooks +
                ", summary='" + summary + '\'' +
                ", date=" + date +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(imageResource);
        parcel.writeString(author);
        parcel.writeString(book);
        parcel.writeInt(numBooks);
        parcel.writeString(summary);
        parcel.writeString(date);
    }
}//end class

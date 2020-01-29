package com.cxl.iterator;

import java.util.ArrayList;

public class BookShelf implements Aggregate {
    private ArrayList books;

    public BookShelf(int initialsize) {
        this.books = new ArrayList(initialsize);
    }
    public Book get(int index){
        return (Book) books.get(index);
    }
    public void append(Book e){
        books.add(e);
    }
    public void remove(int index){
        books.remove(index);
    }
    public int getLength(){
        return books.size();
    }
    public Iterator iterator() {
        return new BookShelfIterator(this);
    }
}

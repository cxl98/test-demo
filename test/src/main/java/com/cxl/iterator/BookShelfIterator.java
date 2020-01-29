package com.cxl.iterator;

public class BookShelfIterator implements Iterator {
    private BookShelf bookShelf;
    private int index;

    public BookShelfIterator(BookShelf bookShelf) {
        this.bookShelf = bookShelf;
        this.index = 0;
    }

    public boolean hasNext() {
        return index<bookShelf.getLength();
    }

    public Object next() {
        Book book=  bookShelf.get(index);
        index++;
        return book;
    }

    public void remove() {
        bookShelf.remove(index);
    }
}

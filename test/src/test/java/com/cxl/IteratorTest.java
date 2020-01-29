package com.cxl;

import com.cxl.iterator.Book;
import com.cxl.iterator.BookShelf;
import com.cxl.iterator.Iterator;

public class IteratorTest {
    public static void main(String[] args) {
        BookShelf bookShelf = new BookShelf(6);
        bookShelf.append(new Book("你好"));
        bookShelf.append(new Book("你好1"));
        bookShelf.append(new Book("你好2"));
        bookShelf.append(new Book("你好3"));
        bookShelf.append(new Book("你好4"));
        bookShelf.append(new Book("你好5"));
        bookShelf.append(new Book("你好6"));
        bookShelf.append(new Book("你好7"));
        Iterator it=bookShelf.iterator();
        while(it.hasNext()){
            Book book= (Book) it.next();
            System.out.println(book.getName());
        }
    }

}

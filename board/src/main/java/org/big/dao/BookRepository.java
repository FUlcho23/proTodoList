package org.big.dao;

import org.big.dto.Book;

import java.util.ArrayList;

public class BookRepository {
//여기안씀진짜안씀1도안씀제발안씀여기안쓴다진짜안쓴다알지안쓴다안쓴다!!!!!!!!!!!!!!!!!!!!
    private ArrayList<Book> listOfBooks = new ArrayList<Book>();
    private static BookRepository instance = new BookRepository();

    public static BookRepository getInstance() {
        return instance;
    }

    // 책 추가
    public void insertBook(Book book) {
        listOfBooks.add(book);
    }

    // 모든 책 조회
    public ArrayList<Book> getAllBooks() {
        return listOfBooks;
    }

    // bookId로 책 조회
    public Book getBookById(String bookId) {
        Book bookById = null;

        for (int i = 0; i < listOfBooks.size(); i++) {
            Book book = listOfBooks.get(i);
            if (book != null && book.getBookId() != null && book.getBookId().equals(bookId)) {
                bookById = book;
                break;
            }
        }
        return bookById;
    }
}

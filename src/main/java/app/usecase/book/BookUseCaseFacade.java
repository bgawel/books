package app.usecase.book;

import app.domain.Book;

public interface BookUseCaseFacade {

    Iterable<Book> getBooks();

    Iterable<Book> getBooks(Iterable<Long> ids);

    Book getBook(Long id);

    void deleteBook(Long id);

    Book createBook(Book newBook);

    Book updateBook(Book book);
}

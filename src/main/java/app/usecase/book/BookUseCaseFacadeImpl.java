package app.usecase.book;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.domain.Book;

@Service
class BookUseCaseFacadeImpl implements BookUseCaseFacade {

    @Autowired
    private BookRepository bookRepository;

    @Override
    @Transactional(readOnly = true)
    public Iterable<Book> getBooks() {
        return bookRepository.findAll(new Sort("title"));
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Book> getBooks(final Iterable<Long> ids) {
        List<Book> books = bookRepository.findAll(ids);
        books.sort(Comparator.comparing(Book::getTitle));
        return books;
    }

    @Override
    @Transactional(readOnly = true)
    public Book getBook(final Long id) {
        return bookRepository.getOne(id);
    }

    @Override
    @Transactional
    public void deleteBook(final Long id) {
        bookRepository.delete(id);
    }

    @Override
    @Transactional
    public Book createBook(final Book newBook) {
        return bookRepository.save(newBook);
    }

    @Override
    @Transactional
    public Book updateBook(final Book book) {
        return bookRepository.save(book);
    }
}

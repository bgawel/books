package app.usecase.book;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.domain.Author;
import app.domain.Book;
import app.usecase.author.AuthorUseCaseFacade;

@Service
class BookUseCaseFacadeImpl implements BookUseCaseFacade {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorUseCaseFacade authorUseCaseFacade;

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
        Set<Author> authors = new HashSet<>();
        Author existingAuthor;
        for (Author author : newBook.getAuthors()) {
            existingAuthor = authorUseCaseFacade.findByName(author.getFirstName(), author.getLastName());
            authors.add(existingAuthor != null ? existingAuthor : author);
        }
        newBook.setAuthors(authors);
        return bookRepository.save(newBook);
    }
}

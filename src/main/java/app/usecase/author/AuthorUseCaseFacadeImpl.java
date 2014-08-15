package app.usecase.author;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.domain.Author;
import app.domain.Book;
import app.usecase.book.BookUseCaseFacade;

@Service
class AuthorUseCaseFacadeImpl implements AuthorUseCaseFacade {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookUseCaseFacade bookUseCaseFacade;

    @Override
    @Transactional(readOnly = true)
    public Iterable<Author> getAuthors() {
        return authorRepository.findAll(new Sort("lastName"));
    }

    @Override
    public Iterable<Author> getAuthors(final Iterable<Long> ids) {
        List<Author> authors = authorRepository.findAll(ids);
        authors.sort(Comparator.comparing(Author::getLastName));
        return authors;
    }

    @Override
    @Transactional
    public Author createAuthor(final Author author) {
        return authorRepository.save(author);
    }

    @Override
    @Transactional
    public void deleteAuthor(final Long id) {
        Author author = authorRepository.getOne(id);
        if (author.getBooks().size() > 0) {
            for (Book book : author.getBooks()) {
                book.getAuthors().remove(author);
                if (book.getAuthors().size() == 0) {
                    bookUseCaseFacade.deleteBook(book.getId());
                }
            }
        }
        authorRepository.delete(id);
    }

    @Override
    @Transactional
    public Author updateAuthor(final Author author) {
        return authorRepository.save(author);
    }

    @Override
    @Transactional(readOnly = true)
    public Author getAuthor(final Long id) {
        return authorRepository.getOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Author findByName(final String firstName, final String lastName) {
        Iterator<Author> authors = authorRepository.findByLastNameIgnoreCaseAndFirstNameIgnoreCase(lastName, firstName)
                .iterator();
        return authors.hasNext() ? authors.next() : null;
    }
}

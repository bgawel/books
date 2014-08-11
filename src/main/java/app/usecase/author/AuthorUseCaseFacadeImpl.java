package app.usecase.author;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.domain.Author;

@Service
class AuthorUseCaseFacadeImpl implements AuthorUseCaseFacade {

    @Autowired
    private AuthorRepository authorRepository;

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
        authorRepository.delete(id);
    }

    @Override
    @Transactional
    public Author updateAuthor(final Author author) {
        return authorRepository.save(author);
    }
}

package app.usecase.author;

import app.domain.Author;

public interface AuthorUseCaseFacade {

    Iterable<Author> getAuthors();

    Iterable<Author> getAuthors(Iterable<Long> ids);

    Author createAuthor(Author author);

    void deleteAuthor(Long id);

    Author updateAuthor(Author author);

    Author getAuthor(Long id);

    Author findByName(String firstName, String lastName);
}

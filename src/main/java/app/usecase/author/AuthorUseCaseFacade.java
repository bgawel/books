package app.usecase.author;

import app.domain.Author;

public interface AuthorUseCaseFacade {

    Iterable<Author> getAllAuthors();
}

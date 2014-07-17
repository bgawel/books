package app.usecase.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.domain.Author;

@Service
class AuthorUseCaseFacadeImpl implements AuthorUseCaseFacade {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    @Transactional(readOnly = true)
    public Iterable<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
}

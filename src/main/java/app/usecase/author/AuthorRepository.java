package app.usecase.author;

import org.springframework.data.repository.CrudRepository;
import app.domain.Author;

interface AuthorRepository extends CrudRepository<Author, Long> {
}

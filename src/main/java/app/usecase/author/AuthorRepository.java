package app.usecase.author;

import org.springframework.data.jpa.repository.JpaRepository;
import app.domain.Author;

interface AuthorRepository extends JpaRepository<Author, Long> {
}

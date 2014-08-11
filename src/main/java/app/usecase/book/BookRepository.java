package app.usecase.book;

import org.springframework.data.jpa.repository.JpaRepository;

import app.domain.Book;

interface BookRepository extends JpaRepository<Book, Long> {
}

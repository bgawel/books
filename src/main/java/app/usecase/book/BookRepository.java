package app.usecase.book;

import org.springframework.data.repository.Repository;

import app.domain.Book;

interface BookRepository extends Repository<Book, Long> {
}

package app.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "BOOK", uniqueConstraints = @UniqueConstraint(columnNames = {"isbn"}))
public class Book {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 128)
    private String title;

    @Column(nullable = false, length = 13)
    @Size(min = 13, max = 13)
    private String isbn;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @NotNull @Size(min = 1)
    @OrderBy("lastName ASC")
    private Set<Author> authors = new HashSet<Author>();

    protected Book() {}

    public Book(final String title, final String isbn, final Author... authors) {
        this.title = title;
        this.isbn = isbn;
        this.authors.addAll(Arrays.asList(authors));
    }

    @Override
    public String toString() {
        return String.format("Book[id=%d, title='%s', isbn='%s']", id, title, isbn);
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setIsbn(final String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setAuthors(final Set<Author> authors) {
        this.authors = authors;
    }

    public Set<Author> getAuthors() {
        return authors;
    }
}

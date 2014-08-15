package app.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "AUTHOR", uniqueConstraints = @UniqueConstraint(columnNames = {"firstName", "lastName"}))
public class Author {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 128)
    private String firstName;

    @Column(nullable = false, length = 128)
    private String lastName;

    @ManyToMany(mappedBy = "authors")
    @OrderBy("title ASC")
    private Set<Book> books = new HashSet<Book>();

    public Author() {}

    public Author(final String firstName, final String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("Author[id=%d, firstName='%s', lastName='%s']", id, firstName, lastName);
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public void setBooks(final Set<Book> books) {
        this.books = books;
    }

    public Set<Book> getBooks() {
        return books;
    }
}

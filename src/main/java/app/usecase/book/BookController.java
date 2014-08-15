package app.usecase.book;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.domain.Author;
import app.domain.Book;

@RestController
public class BookController {

    @Autowired
    private BookUseCaseFacade bookUseCases;

    private Function<Book, Map<String, Object>> bookDTO = (book) -> {
        Map<String, Object> properties = new HashMap<>();
        properties.put("id", book.getId());
        properties.put("title", book.getTitle());
        properties.put("isbn", book.getIsbn());
        properties.put("authors", book.getAuthors().stream().map(Author::getId).collect(Collectors.toList()));
        return properties;
    };

    @SuppressWarnings("serial")
    @RequestMapping(value = "/book", method = RequestMethod.GET)
    @ResponseBody
    Map<String, List<Map<String, Object>>> getBooks(@RequestParam(value = "ids[]", required = false) final Long[] ids) {
        return new HashMap<String, List<Map<String, Object>>>() {{
            put("books", StreamSupport.stream(
                    Optional.ofNullable(ids).orElse(new Long[0]).length > 0 ?
                            bookUseCases.getBooks(Arrays.asList(ids)).spliterator() :
                            bookUseCases.getBooks().spliterator(), false)
                    .map(bookDTO).collect(Collectors.toList()));
        }};
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    void deleteBook(@PathVariable("id") final Long id) {
        bookUseCases.deleteBook(id);
    }

    @SuppressWarnings("serial")
    @RequestMapping(value = "/book", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    Map<String, Map<String, Object>> createBook(@RequestBody final Book newBook) {
        return new HashMap<String, Map<String, Object>>() {{
            put("book", bookDTO.apply(bookUseCases.createBook(newBook)));
        }};
    }
}
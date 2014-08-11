package app.usecase.author;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import app.domain.Author;
import app.domain.Book;

@RestController
public class AuthorController {

    @Autowired
    private AuthorUseCaseFacade authorUseCases;

    private  Function<Author, Map<String, Object>> authorDTO = (author) -> {
        Map<String, Object> properties = new HashMap<>();
        properties.put("id", author.getId());
        properties.put("firstName", author.getFirstName());
        properties.put("lastName", author.getLastName());
        properties.put("nbooks", author.getBooks().size());
        properties.put("books", author.getBooks().stream().map(Book::getId).collect(Collectors.toList()));
        return properties;
    };

    @SuppressWarnings("serial")
    @RequestMapping(value = "/author", method = RequestMethod.GET)
    @ResponseBody
    Map<String, List<Map<String, Object>>> getAuthors(@RequestParam(value = "ids[]", required = false) final Long[] ids) {
        return new HashMap<String, List<Map<String, Object>>>() {{
            put("authors", StreamSupport.stream(
                    Optional.ofNullable(ids).orElse(new Long[0]).length > 0 ?
                            authorUseCases.getAuthors(Arrays.asList(ids)).spliterator() :
                            authorUseCases.getAuthors().spliterator(), false)
                    .map(authorDTO).collect(Collectors.toList()));
        }};
    }

    @SuppressWarnings("serial")
    @RequestMapping(value = "/author", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    Map<String, Map<String, Object>> createAuthor(@RequestBody final Author newAuthor) {
        return new HashMap<String, Map<String, Object>>() {{
            put("author", authorDTO.apply(authorUseCases.createAuthor(newAuthor)));
        }};
    }

    @RequestMapping(value = "/author/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    void deleteAuthor(@PathVariable("id") final Long id) {
        authorUseCases.deleteAuthor(id);
    }

    @SuppressWarnings("serial")
    @RequestMapping(value = "/author/{id}", method = RequestMethod.PUT)
    @ResponseBody
    Map<String, Map<String, Object>> updateAuthor(@PathVariable("id") final Long id, @RequestBody final Author author) {
        author.setId(id);
        return new HashMap<String, Map<String, Object>>() {{
            put("author", authorDTO.apply(authorUseCases.updateAuthor(author)));
        }};
    }
}
package app.presentation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import app.domain.Author;
import app.usecase.author.AuthorUseCaseFacade;

@RestController
public class AuthorController {

    @Autowired
    private AuthorUseCaseFacade authorUseCases;

    @RequestMapping(value = "/authors", method = RequestMethod.GET)
    @ResponseBody
    List<Map<String, Object>> authors() {
        Function<Author, Map<String, Object>> to = (author) -> {
            Map<String, Object> properties = new HashMap<String, Object>();
            properties.put("id", author.getId());
            properties.put("firstName", author.getFirstName());
            properties.put("lastName", author.getLastName());
            return properties;
        };
        return StreamSupport.stream(authorUseCases.getAllAuthors().spliterator(), false)
                .map(to).collect(Collectors.toList());
    }
}
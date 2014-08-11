package app.config;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.domain.Author;
import app.usecase.book.BookUseCaseFacade;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Component
public class AuthorJacksonModule extends SimpleModule implements InitializingBean {

    @Autowired
    private BookUseCaseFacade bookUseCaseFacade;

    private static final long serialVersionUID = -872688443747094275L;

    @Override
    public void afterPropertiesSet() throws Exception {
        addDeserializer(Author.class, new JsonDeserializer<Author>() {

            @Override
            public Author deserialize(final JsonParser jp, final DeserializationContext ctxt) throws IOException,
                    JsonProcessingException {
                Author author = new Author();
                JsonNode root = jp.getCodec().readTree(jp);
                Optional.ofNullable(root.get("id")).ifPresent(node -> author.setId(node.asLong()));
                Optional.of(root.get("lastName")).ifPresent(node -> author.setLastName(node.asText()));
                Optional.of(root.get("firstName")).ifPresent(node -> author.setFirstName(node.asText()));
                Optional.ofNullable(root.get("books")).ifPresent(node -> node.elements()
                        .forEachRemaining(id -> author.getBooks().add(bookUseCaseFacade.getBook(id.asLong()))));
                return author;
            }
        });
    }
}

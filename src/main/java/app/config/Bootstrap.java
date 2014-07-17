package app.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import app.domain.Author;

@Component
public class Bootstrap implements InitializingBean, ApplicationContextAware {

    @Autowired
    private Environment env;
    private ApplicationContext applicationContext;

    @Override
    @Transactional
    public void afterPropertiesSet() {
        if (env.acceptsProfiles("dev")) {
            initializeDevelopmentData();
        }
    }

    @SuppressWarnings("unchecked")
    private void initializeDevelopmentData() {
        CrudRepository<Author, Long> authorRepository = (CrudRepository<Author, Long>) applicationContext
                .getBean("authorRepository");
        authorRepository.save(new Author("John", "Doe"));
    }

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}

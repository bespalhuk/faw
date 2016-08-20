package bespalhuk.view.model.person;

import bespalhuk.domain.model.person.Person;
import bespalhuk.domain.model.person.QPerson;
import bespalhuk.view.ViewValidator;
import org.springframework.binding.validation.ValidationContext;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class PersonValidator {

    @PersistenceContext
    private EntityManager entityManager;

    public void validate(Person person, ValidationContext validationContext) {
        ViewValidator<Person> validator = ViewValidator.of(entityManager, validationContext, person);
        validator.or(QPerson.person.name.eq(person.getName()));
        validator.validate();
    }

}

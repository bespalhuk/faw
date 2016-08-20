package bespalhuk.view.model.person;

import bespalhuk.domain.model.person.Person;
import org.springframework.stereotype.Controller;

@Controller
public class PersonController {

    public PersonFilter filter() {
        return PersonFilter.of();
    }

    public Person create() {
        return Person.of();
    }

}

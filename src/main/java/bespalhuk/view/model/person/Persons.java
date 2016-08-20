package bespalhuk.view.model.person;

import bespalhuk.domain.model.person.QPerson;
import com.mysema.query.types.Predicate;

public class Persons {

    public static Predicate withName(String name) {
        return QPerson.person.name.stringValue().containsIgnoreCase(name);
    }

}

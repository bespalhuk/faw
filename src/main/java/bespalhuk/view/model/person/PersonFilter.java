package bespalhuk.view.model.person;

import bespalhuk.domain.model.person.Person;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.JPQLQuery;

import java.io.Serializable;
import java.util.function.Consumer;

public class PersonFilter implements Serializable, Consumer<JPQLQuery> {

    private Person person;

    private String name = "";

    private PersonFilter() {
    }

    public static PersonFilter of() {
        return new PersonFilter();
    }

    @Override
    public void accept(JPQLQuery jpqlQuery) {
        jpqlQuery.where(conditions());
    }

    private BooleanBuilder conditions() {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(Persons.withName(name));
        return builder;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

package bespalhuk.infra.persistence.repository;

import bespalhuk.domain.model.person.Person;
import me.yanaga.winter.data.jpa.Repository;

import java.util.UUID;

@org.springframework.stereotype.Repository
public interface PersonRepository extends Repository<Person, UUID> {
}

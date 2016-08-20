package bespalhuk.view.converter.entity;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

import static com.google.common.base.Preconditions.checkNotNull;

@Component
public class EntityConverterHelper {

	@PersistenceContext
	private EntityManager entityManager;

	<T> T find(Class<T> c, Serializable id) {
		checkNotNull(c);
		checkNotNull(id);
		return entityManager.find(c, id);
	}

	String getIdAsString(Object o) {
		checkNotNull(o);
		Object id = entityManager.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(o);
		checkNotNull(id);
		return id.toString();
	}

}

package bespalhuk.view;

import bespalhuk.view.model.ViewMessage;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;
import com.mysema.query.types.path.EntityPathBase;
import me.yanaga.winter.data.jpa.querydsl.EntityPathResolver;
import org.springframework.binding.message.MessageContext;
import org.springframework.binding.validation.ValidationContext;

import javax.persistence.EntityManager;

import static com.google.common.base.Preconditions.checkNotNull;

public class ViewValidator<T> {

	private final EntityManager entityManager;

	private final MessageContext messageContext;

	private final T entity;

	private final EntityPathBase<T> path;

	private BooleanExpression expression;

	@SuppressWarnings("unchecked")
	private ViewValidator(EntityManager entityManager, MessageContext messageContext, T entity) {
		this.entityManager = entityManager;
		this.messageContext = messageContext;
		this.entity = entity;
		this.path = (EntityPathBase<T>) new EntityPathResolver().resolveEntityPath(entity.getClass());
	}

	@SuppressWarnings("unchecked")
	public static <T> ViewValidator<T> of(EntityManager entityManager, ValidationContext validationContext, T entity) {
		checkNotNull(entityManager);
		checkNotNull(validationContext);
		checkNotNull(entity);
		return new ViewValidator(entityManager, validationContext.getMessageContext(), entity);
	}

	public ViewValidator<T> or(BooleanExpression expression) {
		checkNotNull(expression);
		if (this.expression == null) {
			this.expression = expression;
		} else {
			this.expression = this.expression.or(expression);
		}
		return this;
	}

	public ViewValidator<T> and(BooleanExpression expression) {
		checkNotNull(expression);
		if (this.expression == null) {
			this.expression = expression;
		} else {
			this.expression = this.expression.and(expression);
		}
		return this;
	}

	public void validate() {
		if (count() > 0) {
			ViewMessage.alreadyExists(messageContext);
		}
	}

	private long count() {
		BooleanBuilder builder = new BooleanBuilder();
		Object identifier = entityManager.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity);
		if (identifier != null) {
			builder.and(path.ne(entity));
		}
		if (expression != null) {
			builder.and(expression);
		}
		return new JPAQuery(entityManager).from(path).where(builder).count();
	}

}

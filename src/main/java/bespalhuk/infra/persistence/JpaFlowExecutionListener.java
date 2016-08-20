package bespalhuk.infra.persistence;

import org.hibernate.jpa.HibernateEntityManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.webflow.execution.RequestContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class JpaFlowExecutionListener extends org.springframework.webflow.persistence.JpaFlowExecutionListener {

	public JpaFlowExecutionListener(EntityManagerFactory entityManagerFactory,
	                                PlatformTransactionManager transactionManager) {
		super(entityManagerFactory, transactionManager);
	}

	@Override
	public void paused(RequestContext context) {
		super.paused(context);
		EntityManager entityManager = (EntityManager) context.getFlowScope().get(PERSISTENCE_CONTEXT_ATTRIBUTE);
		if (entityManager != null && entityManager instanceof HibernateEntityManager) {
			HibernateEntityManager hibernateEntityManager = (HibernateEntityManager) entityManager;
			hibernateEntityManager.getSession().disconnect();
		}
	}

}

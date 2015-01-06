package com.fgm.financeiro.util;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Interceptor
@Transactional
public class TransactionInterceptor implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private @Inject EntityManager manager;

	@AroundInvoke
	public Object invoke(InvocationContext context) throws Exception {
		EntityTransaction trx = manager.getTransaction();
		boolean criador = false;
		System.out.println("----- TransactionInterceptor > Invoke");
		try {
			if (!trx.isActive()) {
				// truque para fazer rollback no que já passou
				// (senão, um futuro commit, confirmaria até mesmo
				// operações sem transação)
				trx.begin();
				System.out.println("----- TransactionInterceptor > Invoke - try trx begin truqe rollback");
				trx.rollback();
				// agora sim inicia a transação
				System.out.println("----- TransactionInterceptor > Invoke - try trx begin ");				
				trx.begin();
				criador = true;
			}
			System.out.println("----- TransactionInterceptor > Invoke - proceed");			
			return context.proceed();
		} catch (Exception e) {
			if (trx != null && criador) {
				trx.rollback();
			}
			throw e;
		} finally {
			if (trx != null && trx.isActive() && criador) {
				trx.commit();
			}
		}
	}
}
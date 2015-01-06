package com.fgm.financeiro.util;
 
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProducer {
	
	private EntityManagerFactory factory;

	public EntityManagerProducer() {
		System.out.println("--------------- EntityManagerProducer -> Construtor");
		this.factory = Persistence.createEntityManagerFactory("FinanceiroPUFGM");
	}

	@Produces
	@RequestScoped
	public EntityManager createEntityManager() {
		System.out.println("--------------- EntityManagerProducer -> createEntityManager");
		return factory.createEntityManager();
	}

	public void closeEntityManager(@Disposes EntityManager manager) {
		System.out.println("--------------- EntityManagerProducer -> closeEntityManager");
		manager.close();
	}
}
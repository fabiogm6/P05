package com.fgm.financeiro.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
	private static EntityManagerFactory factory;
	static {
		factory = Persistence.createEntityManagerFactory("FinanceiroPUFGM");
	}

	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
}


/*Um EntityManager é responsável por gerenciar entidades no contexto de persistência.
Através dos métodos dessa interface, é possível persistir, pesquisar e excluir objetos do
banco de dados.
 * instância compartilhada de EntityManagerFactory, onde qualquer código tenha acesso fácil e rápido. Criaremos
a classe JpaUtil para armazenar a instância em uma variável estática.
 */
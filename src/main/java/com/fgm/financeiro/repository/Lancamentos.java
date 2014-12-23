package com.fgm.financeiro.repository;

import java.util.List;




import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;



import com.fgm.financeiro.model.Lancamento;

/*
 * Nosso primeiro repositório representará uma coleção de objetos do tipo Lancamento.
Podemos chamar nosso repositório de LancamentoRepository,
RepositorioLancamento, etc, mas preferimos chamá-lo de Lancamentos.
 */

public class Lancamentos {

	private EntityManager manager;

	@Inject
	public Lancamentos(EntityManager manager) {
		this.manager = manager;
	}

	public List<Lancamento> todos() {
		TypedQuery<Lancamento> query = manager.createQuery("from lancamento",Lancamento.class);
		return query.getResultList();
	}
	
	public void adicionar(Lancamento lancamento) {
		this.manager.persist(lancamento);
		}
	
	public List<String> descricoesQueContem(String descricao) {
		System.out.println(">>>>>>>>>descricoesQueContem>>>>>>>>>> "+descricao);
		
		TypedQuery<String> query = manager.createQuery(
				"select distinct descricao from Lancamento "
						+ "where upper(descricao) like upper(:descricao)",
				String.class);
		query.setParameter("descricao", "%" + descricao + "%");		
		
		return query.getResultList();
	}
}

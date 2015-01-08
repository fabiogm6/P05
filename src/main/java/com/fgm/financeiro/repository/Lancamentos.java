package com.fgm.financeiro.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.fgm.financeiro.filter.FiltroLancamento;
import com.fgm.financeiro.model.Lancamento;

public class Lancamentos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	@Inject
	public Lancamentos(EntityManager manager) {
		this.manager = manager;
	}

	public Lancamento porId(Long id) {
		return manager.find(Lancamento.class, id);
	}
	
	public List<String> descricoesQueContem(String descricao) {
		TypedQuery<String> query = manager.createQuery(
				"select distinct descricao from Lancamento "
				+ "where upper(descricao) like upper(:descricao)", 
				String.class);
		query.setParameter("descricao", "%" + descricao + "%");
		return query.getResultList();
	}
	
	public List<Lancamento> todos() {
		TypedQuery<Lancamento> query = manager.createQuery(
				"from Lancamento", Lancamento.class);
		return query.getResultList();
	}

	public void adicionar(Lancamento lancamento) {
		this.manager.persist(lancamento);
	}
	
	public Lancamento guardar(Lancamento lancamento) {
		System.out.println("---- Lancamentos > guardar");
		return this.manager.merge(lancamento);
	}
	
	public void remover(Lancamento lancamento) {
		this.manager.remove(lancamento);
	}
	
	/*  ---------------- Lazy ----------------*/
	@SuppressWarnings("unchecked")
	public List<Lancamento> filtrados(FiltroLancamento filtro) {
		Criteria criteria = criarCriteriaParaFiltro(filtro);
		
		criteria.setFirstResult(filtro.getPrimeiroRegistro());
		criteria.setMaxResults(filtro.getQuantidadeRegistros());
		
		if (filtro.isAscendente() && filtro.getPropriedadeOrdenacao() != null) {
			criteria.addOrder(Order.asc(filtro.getPropriedadeOrdenacao()));
		} else if (filtro.getPropriedadeOrdenacao() != null) {
			criteria.addOrder(Order.desc(filtro.getPropriedadeOrdenacao()));
		}
		
		return criteria.list();
	}
	
	public int quantidadeFiltrados(FiltroLancamento filtro) {
		Criteria criteria = criarCriteriaParaFiltro(filtro);
		// select count
		criteria.setProjection(Projections.rowCount());
		
		return ((Number) criteria.uniqueResult()).intValue();
	}
	
	private Criteria criarCriteriaParaFiltro(FiltroLancamento filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Lancamento.class);
		
		if (StringUtils.isNotEmpty(filtro.getDescricao())) {
			criteria.add(Restrictions.ilike("descricao", filtro.getDescricao(), MatchMode.ANYWHERE));
		}
		
		return criteria;
	}

	
	
	
	

}
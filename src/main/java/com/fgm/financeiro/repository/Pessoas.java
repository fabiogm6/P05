package com.fgm.financeiro.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.fgm.financeiro.model.Pessoa;

public class Pessoas {
	private EntityManager manager;

	public Pessoas(EntityManager manager) {
		this.manager = manager;
	}

	public Pessoa porId(Long id) {
		return manager.find(Pessoa.class, id);
	}

	public List<Pessoa> todas() {
		System.out.println(">>>>> Pessoas >>>>> todas");
		TypedQuery<Pessoa> query = manager.createQuery("from pessoa",
				Pessoa.class);
		return query.getResultList();
	}
}
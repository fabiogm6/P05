package com.fgm.financeiro.model;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class LivrosBeanInputUser {
	private List<Livro> livros;
	private Livro novoLivro;

	public LivrosBeanInputUser() {
		this.livros = new ArrayList<>();
		this.novoLivro = new Livro();
	}

	public void adicionar() {
		this.livros.add(this.novoLivro);
		this.novoLivro = new Livro();
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}

	public Livro getNovoLivro() {
		return novoLivro;
	}
}

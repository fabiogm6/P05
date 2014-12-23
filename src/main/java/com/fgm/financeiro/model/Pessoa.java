package com.fgm.financeiro.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

	/* A anotação @Entity diz que a classe é uma entidade, que representa uma tabela do
 	banco de dados, e @Table define detalhes da tabela no banco de dados, como por
 	exemplo o nome da tabela. */
@Entity
@Table(name = "pessoa")
public class Pessoa {
	private Long id;
	private String nome;

	/*
	 * As anotações @Id e @GeneratedValue são usadas para declarar o
	 * identificador do banco de dados, e esse identificador deve ter um valor
	 * gerado no momento de inserção (auto-incremento).
	 */

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotEmpty
	@Column(length = 60, nullable = false)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	/*
	 * gerado pelo eclipse 'generate hash' Para que os objetos de entidades
	 * sejam diferenciados uns de outros, precisamos implementar os métodos
	 * equals() e hashCode(). No banco de dados, as chaves primárias diferenciam
	 * registros distintos. Quando mapeamos uma entidade de uma tabela, devemos
	 * criar os métodos equals() e hashCode(), levando em consideração a forma
	 * em que os registros são diferenciados no banco de dados.
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

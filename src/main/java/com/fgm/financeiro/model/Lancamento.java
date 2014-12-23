package com.fgm.financeiro.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "lancamento")
public class Lancamento {
	private Long id;
	private Pessoa pessoa;
	private String descricao;
	private BigDecimal valor;
	private TipoLancamento tipo;
	private Date dataVencimento;
	private Date dataPagamento;
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/* A anotação
	@ManyToOne indica a multiplicidade do relacionamento 
	entre lançamentos e pessoas
	
	@JoinColumn indica que essa relação é conseguida através 
	da coluna especificada na propriedade name. 
	Para facilitar o entendimento, esse mapeamento foi
	necessário para dizermos ao provedor JPA que existe 
	uma chave estrangeira na coluna pessoa_id da tabela 
	lancamento, que referencia a tabela pessoa	
	  */ 
	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "pessoa_id")
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	@NotEmpty
	@Size(max = 80)
	@Column(length = 80, nullable = false)
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	//mudei para permitir nulo aqui e no database senão fica dificil o teste toda hora ficar preenchendo
	@NotNull
	@DecimalMin("0")
	@Column(precision = 10, scale = 2, nullable = true)
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	/*
	  O tipo do lançamento é uma enumeração, por isso, 
	  mapeamos com a anotação @Enumerated, indicando que 
	  queremos armazenar a string da constante na coluna da
	  tabela, e não o índice da constante
	 */
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	public TipoLancamento getTipo() {
		return tipo;
	}

	public void setTipo(TipoLancamento tipo) {
		this.tipo = tipo;
	}
	
	
	//mudei para permitir nulo aqui e no database
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "data_vencimento", nullable = true)
	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "data_pagamento", nullable = true)
	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	/* gerado pelo eclipse 'generate hash'
Para que os objetos de entidades sejam diferenciados uns de outros, precisamos
implementar os métodos equals() e hashCode().
No banco de dados, as chaves primárias diferenciam registros distintos. Quando
mapeamos uma entidade de uma tabela, devemos criar os métodos equals() e
hashCode(), levando em consideração a forma em que os registros são diferenciados
no banco de dados.
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
		Lancamento other = (Lancamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}

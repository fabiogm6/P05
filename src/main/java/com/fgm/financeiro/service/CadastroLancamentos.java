package com.fgm.financeiro.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import com.fgm.financeiro.model.Lancamento;
import com.fgm.financeiro.repository.Lancamentos;
import com.fgm.financeiro.util.Transactional;

public class CadastroLancamentos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Lancamentos lancamentos;
	
	@Transactional
	public void salvar(Lancamento lancamento) throws NegocioException {
		if (lancamento.getDataPagamento() != null &&
				lancamento.getDataPagamento().after(new Date())) {
			throw new NegocioException(
					"Data de pagamento não pode ser uma data futura.");
		}
		System.out.println("---- CadastroLancamentoBean > salvar");
		this.lancamentos.guardar(lancamento);
	}
	
	@Transactional
	public void excluir(Lancamento lancamento) throws NegocioException {
		lancamento = this.lancamentos.porId(lancamento.getId());
		
		if (lancamento.getDataPagamento() != null) {
			throw new NegocioException("Não é possível excluir um lançamento pago!");
		}
		
		this.lancamentos.remover(lancamento);
	}
	
}
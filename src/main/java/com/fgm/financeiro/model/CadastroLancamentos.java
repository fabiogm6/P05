package com.fgm.financeiro.model;

import java.util.Date;

import javax.inject.Inject;

import com.fgm.financeiro.repository.Lancamentos;
import com.fgm.financeiro.util.Transactional;

public class CadastroLancamentos {
	
	@Inject
	private Lancamentos lancamentos;

	public CadastroLancamentos(Lancamentos lancamentos) {
		this.lancamentos = lancamentos;
	}
	
	@Transactional
	public void salvar(Lancamento lancamento) throws NegocioException {
		if (lancamento.getDataPagamento() != null
				&& lancamento.getDataPagamento().after(new Date())) {
			throw new NegocioException(
					"Data de pagamento não pode ser uma data futura.");
		}
		this.lancamentos.adicionar(lancamento);
	}
}
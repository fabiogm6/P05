package com.fgm.financeiro.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.fgm.financeiro.model.Lancamento;
import com.fgm.financeiro.repository.Lancamentos;
import com.fgm.financeiro.service.CadastroLancamentos;
import com.fgm.financeiro.service.NegocioException;

@Named
@ViewScoped
public class ConsultaLancamentosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Lancamentos lancamentosRepository;
	
	@Inject
	private CadastroLancamentos cadastro;
	
	private List<Lancamento> lancamentos;
	
	private Lancamento lancamentoSelecionado;

	public void excluir() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {			
			this.cadastro.excluir(this.lancamentoSelecionado);
			this.consultar();
			
			context.addMessage(null, new FacesMessage("Lançamento excluído com sucesso!"));
		} catch (NegocioException e) {
			
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}
	
	public void consultar() {
		this.lancamentos = lancamentosRepository.todos(); 
	}
	
	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public Lancamento getLancamentoSelecionado() {
		System.out.println("----------- ConsultaLancamentosBean > getLancamentoSelecionado");
		return lancamentoSelecionado;
	}

	public void setLancamentoSelecionado(Lancamento lancamentoSelecionado) {
		System.out.println("----------- ConsultaLancamentosBean > setLancamentoSelecionado");	
		this.lancamentoSelecionado = lancamentoSelecionado;
	}

}
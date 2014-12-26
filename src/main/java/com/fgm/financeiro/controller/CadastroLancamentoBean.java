package com.fgm.financeiro.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import com.fgm.financeiro.model.Lancamento;
import com.fgm.financeiro.model.Pessoa;
import com.fgm.financeiro.model.TipoLancamento;
import com.fgm.financeiro.repository.Lancamentos;
import com.fgm.financeiro.repository.Pessoas;
import com.fgm.financeiro.service.CadastroLancamentos;
import com.fgm.financeiro.service.NegocioException;

@Named
@javax.faces.view.ViewScoped
public class CadastroLancamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroLancamentos cadastro;
	
	@Inject
	private Pessoas pessoas;
	
	@Inject
	private Lancamentos lancamentos;
	
	private Lancamento lancamento;
	private List<Pessoa> todasPessoas;

	public void prepararCadastro() {
		this.todasPessoas = this.pessoas.todas();
		
		if (this.lancamento == null) {
			this.lancamento = new Lancamento();
		}
	}
	
	public List<String> pesquisarDescricoes(String descricao) {
		return this.lancamentos.descricoesQueContem(descricao);
	}
	
	public void dataVencimentoAlterada(AjaxBehaviorEvent event) {
		if (this.lancamento.getDataPagamento() == null) {
			this.lancamento.setDataPagamento(this.lancamento.getDataVencimento());
		}
	}
	
	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			this.cadastro.salvar(this.lancamento);
			
			this.lancamento = new Lancamento();
			context.addMessage(null, new FacesMessage("Lançamento salvo com sucesso!"));
		} catch (NegocioException e) {
			
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}
	
	public List<Pessoa> getTodasPessoas() {
		return this.todasPessoas;
	}
	
	public TipoLancamento[] getTiposLancamentos() {
		return TipoLancamento.values();
	}
	
	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}
	
	// para estudo é executado no actionlistener do botão no xhtml
		public void registrarLogCadastro(ActionEvent event) {
			System.out.println(">>>>>> CadastroLancamentoBean-registrarLogCadastro Cadastrando...");
			}
	
}


/* old sem inject

public void prepararCadastro() {
	EntityManager manager = JpaUtil.getEntityManager();
	try {
		Pessoas pessoas = new Pessoas(manager);
		this.todasPessoas = pessoas.todas();
	} finally {
		manager.close();
	}
}
public void salvar() {
	EntityManager manager = JpaUtil.getEntityManager();
	EntityTransaction trx = manager.getTransaction();
	FacesContext context = FacesContext.getCurrentInstance();
	try {
		trx.begin();
		CadastroLancamentos cadastro = new CadastroLancamentos(
				new Lancamentos(manager));
		cadastro.salvar(this.lancamento);
		this.lancamento = new Lancamento();
		context.addMessage(null, new FacesMessage(
				"Lançamento salvo com sucesso!"));
		trx.commit();
	} catch (NegocioException e) {
		trx.rollback();
		FacesMessage mensagem = new FacesMessage(e.getMessage());
		mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
		context.addMessage(null, mensagem);
	} finally {
		manager.close();
	}
}
 */



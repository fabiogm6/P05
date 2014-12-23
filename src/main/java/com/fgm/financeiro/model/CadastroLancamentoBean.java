package com.fgm.financeiro.model;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

import com.fgm.financeiro.repository.Lancamentos;
import com.fgm.financeiro.repository.Pessoas;

@Named
@javax.faces.view.ViewScoped
public class CadastroLancamentoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroLancamentos cadastro;
	
	@Inject
	private Pessoas pessoas;
	
	private Lancamento lancamento = new Lancamento();	
	private List<Pessoa> todasPessoas;

	public void prepararCadastro() {
		System.out.println(">>>>>>>>>>> CadastroLancamentoBean prepararCadastro");
		this.todasPessoas = this.pessoas.todas();
		if (this.lancamento == null) {
			this.lancamento = new Lancamento();
		}
		}
	
	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.cadastro.salvar(this.lancamento);
			
			this.lancamento = new Lancamento();
			context.addMessage(null, new FacesMessage(
					"Lançamento salvo com sucesso!"));
			} catch (NegocioException e) {
				
				FacesMessage mensagem = new FacesMessage(e.getMessage());
				mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
				context.addMessage(null, mensagem);
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
	
	
	// para estudo é executado no actionlistener do botão no xhtml
	public void registrarLogCadastro(ActionEvent event) {
		System.out.println(">>>>>> CadastroLancamentoBean-registrarLogCadastro Cadastrando...");
		}
	
	// para estudo é executado no valueChangeListener do campo descricao do xhtml
	public void descricaoModificada(ValueChangeEvent event) {
		System.out.println(">>>>>> CadastroLancamentoBean-registrarLogCadastro Valor antigo: " + event.getOldValue());
		System.out.println(">>>>>> CadastroLancamentoBean-registrarLogCadastro Novo valor: " + event.getNewValue());
		//FacesContext.getCurrentInstance().renderResponse();
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
	
	public void dataVencimentoAlterada(AjaxBehaviorEvent event) {
			if (this.lancamento.getDataPagamento() == null) {
			this.lancamento.setDataPagamento(this.lancamento
			.getDataVencimento());
			}
		}

	@Inject
	private Lancamentos lancamentos;
	public List<String> pesquisarDescricoes(String descricao) {
		System.out.println(">>>>>>>pesquisarDescricoes>>>>>>>>>>>> "+descricao);
		return this.lancamentos.descricoesQueContem(descricao);
	}	
	
}
package com.fgm.financeiro.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.fgm.financeiro.filter.FiltroLancamento;
import com.fgm.financeiro.model.Lancamento;
import com.fgm.financeiro.repository.Lancamentos;
import com.fgm.financeiro.service.CadastroLancamentos;
import com.fgm.financeiro.service.NegocioException;

@Named
@ViewScoped
public class LazyConsultaLancamentosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Lancamentos lancamentos;

	private FiltroLancamento filtro = new FiltroLancamento();
	//model é LancamentosFiltrados
	private LazyDataModel<Lancamento> model;
	
	// -----------------------------
	
	@Inject
	private Lancamentos lancamentosRepository;
	
	@Inject
	private CadastroLancamentos cadastro;
	
	private List<Lancamento> lancamentos2;
		
	private Lancamento lancamentoSelecionado;

	// ----------------------------- Lazy	
	public LazyConsultaLancamentosBean() {
		model = new LazyDataModel<Lancamento>() {

			private static final long serialVersionUID = 1L;
			
			@Override
			public List<Lancamento> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				
				filtro.setPrimeiroRegistro(first);
				filtro.setQuantidadeRegistros(pageSize);
				filtro.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
				filtro.setPropriedadeOrdenacao(sortField);
				
				setRowCount(lancamentos.quantidadeFiltrados(filtro));
				
				return lancamentos.filtrados(filtro);
			}			
		};
	}
	
	public FiltroLancamento getFiltro() {
		return filtro;
	}

	//LancamentosFiltrados
	public LazyDataModel<Lancamento> getModel() {
		return model;
	}

	// -----------------------------
	
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
		System.out.println("consultar");
		this.lancamentos2 = lancamentosRepository.todos(); 
	}
	
	public List<Lancamento> getLancamentos() {
		return lancamentos2;
	}

	public Lancamento getLancamentoSelecionado() {
		return lancamentoSelecionado;
	}

	public void setLancamentoSelecionado(Lancamento lancamentoSelecionado) {
		this.lancamentoSelecionado = lancamentoSelecionado;
	}

}
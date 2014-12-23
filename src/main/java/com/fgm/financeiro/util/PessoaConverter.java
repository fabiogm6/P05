package com.fgm.financeiro.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import com.fgm.financeiro.model.Pessoa;
import com.fgm.financeiro.repository.Pessoas;

@FacesConverter(forClass = Pessoa.class)
public class PessoaConverter implements Converter {
	// @Inject (ainda não é suportado)
	private Pessoas pessoas;

	public PessoaConverter() {
		this.pessoas = CDILocator.getBean(Pessoas.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Pessoa retorno = null;
		if (value != null) {
			retorno = this.pessoas.porId(new Long(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		// TODO Auto-generated method stub
		return null;
	}
}

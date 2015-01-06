package com.fgm.financeiro.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.fgm.financeiro.model.Pessoa;
import com.fgm.financeiro.repository.Pessoas;


@FacesConverter(forClass = Pessoa.class)
public class PessoaConverter implements Converter {

	@Inject // funciona gracas ao OmniFaces
	private Pessoas pessoas;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Pessoa retorno = null;
		System.out.println("------ PessoaConverter > getAsObject");
		if (value != null) {
			retorno = this.pessoas.porId(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		System.out.println("------ PessoaConverter > getAsString");
		if (value != null) {
			return ((Pessoa) value).getId().toString();
		}
		return null;
	}

}
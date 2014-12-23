package com.fgm.financeiro.model;

import javax.persistence.Persistence;

public class CriaTabelas {

	public static void main(String[] args) {
			//FinanceiroPU está definido no persistence.xml dentro da pasta META-INF
			Persistence.createEntityManagerFactory("FinanceiroPU");
		
	}

	/* run as java application:
	 * deu pau depois que inclui JPA no Facets então:
	 * mensagem: Class is managed, but is not listed in the persistence.xml file
	 * solução:
	 * Check you JPA project properties and be sure to select 
	 * "Discover annotated classes automatically" 
	 * in the "Persistent class management" section. 
	 * Doing this should provide the correct validation for your use case.
	 */
	
	
}

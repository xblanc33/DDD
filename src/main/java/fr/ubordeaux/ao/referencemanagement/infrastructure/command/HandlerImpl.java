package fr.ubordeaux.ao.referencemanagement.infrastructure.command;

import fr.ubordeaux.ao.referencemanagement.application.command.Command;
import fr.ubordeaux.ao.referencemanagement.application.command.Handler;
import fr.ubordeaux.ao.referencemanagement.domain.exception.ReferenceManagementException;
import fr.ubordeaux.ao.referencemanagement.domain.model.Catalog;
import fr.ubordeaux.ao.referencemanagement.domain.model.KeyWordMap;

public class HandlerImpl implements Handler {

	private Catalog rootCatalog;
	private KeyWordMap keywordMap;


	public HandlerImpl(Catalog rootCatalog, KeyWordMap keywordMap) {
	    this.setRootCatalog(rootCatalog);
	    this.setKeyWordMap(keywordMap);
	}

	private void setRootCatalog(Catalog rootCatalog) {
	    if (rootCatalog == null) throw new ReferenceManagementException("cannot create SearchEngine with null as root catalog");
	    this.rootCatalog = rootCatalog;
	}
	
	private void setKeyWordMap(KeyWordMap keywordMap) {
	    if (keywordMap == null) throw new ReferenceManagementException("cannot create SearchEngine with null as a KeyWordMap");
	    this.keywordMap = keywordMap;
	}
    
	@Override
	public void handle(Command command) {
        command.execute(rootCatalog, keywordMap);
	}

}
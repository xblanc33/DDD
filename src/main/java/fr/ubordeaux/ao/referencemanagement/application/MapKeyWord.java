package fr.ubordeaux.ao.referencemanagement.application;

import fr.ubordeaux.ao.referencemanagement.domain.model.Catalog;
import fr.ubordeaux.ao.referencemanagement.domain.model.KeyWord;
import fr.ubordeaux.ao.referencemanagement.domain.model.KeyWordMap;
import fr.ubordeaux.ao.referencemanagement.domain.model.Reference;
import fr.ubordeaux.ao.referencemanagement.domain.service.SearchEngine;
import fr.ubordeaux.ao.referencemanagement.domain.exception.ReferenceManagementException;

public class MapKeyWord implements Command {
    private KeyWord keyword;
    private Reference reference;

    public MapKeyWord(KeyWord keyword, Reference reference) {
        this.setKeyWord(keyword);
        this.setReference(reference);
    }

    private void setKeyWord(KeyWord keyword) {
        if (keyword == null) throw new ReferenceManagementException("Cannot create command with null as keyword");
        this.keyword = keyword;
    }

    private void setReference(Reference reference) {
        if (reference == null) throw new ReferenceManagementException("Cannot create command with null as reference");
        this.reference = reference;
    }

    @Override
    public void execute(Catalog rootCatalog, KeyWordMap keywordMap) {
        Reference foundReference = (new SearchEngine(rootCatalog, keywordMap)).searchReferenceById(reference.getId());
        if (foundReference == null) throw new ReferenceManagementException("Cannot link reference, it does not exist in ReferenceRepository");
        
        keywordMap.map(keyword, reference);
    }
}
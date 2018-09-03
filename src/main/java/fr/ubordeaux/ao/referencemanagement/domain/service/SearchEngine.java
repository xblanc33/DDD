package fr.ubordeaux.ao.referencemanagement.domain.service;

import java.util.HashSet;
import java.util.Set;

import fr.ubordeaux.ao.referencemanagement.domain.model.KeyWordMap;
import fr.ubordeaux.ao.referencemanagement.domain.model.Catalog;
import fr.ubordeaux.ao.referencemanagement.domain.model.KeyWord;
import fr.ubordeaux.ao.referencemanagement.domain.model.Reference;
import fr.ubordeaux.ao.referencemanagement.domain.exception.ReferenceManagementException;

/**
 * Service 
 * 
 */
public class SearchEngine {

    private Catalog rootCatalog;
    private KeyWordMap keywordMap;

    public SearchEngine(Catalog rootCatalog, KeyWordMap keywordMap) {
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

    public Reference searchReferenceById(String id) {
        for (Reference reference : rootCatalog.getReferences()) {
            if (reference.getId().compareTo(id)==0) {
                return reference;
            }
        }
        throw new ReferenceManagementException("cannot find reference, id does not exist");
    }

    public Set<Reference> searchReferencesByKeyWords(Set<KeyWord> keywords) {
        Set<Reference> foundReferences = new HashSet<Reference>();
        for (KeyWord keyword : keywords) {
            foundReferences.addAll(keywordMap.findReferenceByKeyWord(keyword));
        }
        return foundReferences;
    }

    public Set<Reference> searchReferencesByName(String name) {
        Set<Reference> foundReferences = new HashSet<Reference>();
        for (Reference reference : rootCatalog.getReferences()) {
            if (reference.getName().compareTo(name)==0) {
                foundReferences.add(reference);
            }
        }
        return foundReferences;
    }
}
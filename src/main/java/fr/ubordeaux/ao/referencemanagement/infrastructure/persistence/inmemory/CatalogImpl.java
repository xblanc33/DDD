package fr.ubordeaux.ao.referencemanagement.infrastructure.persistence.inmemory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import fr.ubordeaux.ao.referencemanagement.domain.model.Catalog;
import fr.ubordeaux.ao.referencemanagement.domain.model.Reference;
import fr.ubordeaux.ao.referencemanagement.domain.exception.ReferenceManagementException;
import fr.ubordeaux.ao.referencemanagement.domain.type.CatalogName;



public class CatalogImpl implements Catalog {
    private CatalogName name;
    private Set<Reference> store;
    private Map<CatalogName, Catalog> subCatalogs;    

    public CatalogImpl(CatalogName name) {
        this.setName(name);
        store = new HashSet<Reference>();
        subCatalogs = new HashMap<>();
    }

    @Override
	public CatalogName getName() {
		return this.name;
    }
    
    private void setName(CatalogName name) {
        this.name = name;
    }

    @Override
	public int size() {
        int size = store.size();
        for (Catalog subCatalog : this.subCatalogs.values()) {
            size = size + subCatalog.size();
        }
        return size;
	}

	@Override
	public Set<Reference> getReferences() {
        Set<Reference> result = new HashSet<>();
        result.addAll(this.store);
        for (Catalog subCatalog : subCatalogs.values()) {
            result.addAll(subCatalog.getReferences());
        }
		return result;
	}

	@Override
	public Set<Catalog> getSubCatalogs() {
        Set<Catalog> result = new HashSet<Catalog>();
        result.addAll(this.subCatalogs.values());
        return result;
    }

    @Override
    public Catalog getSubCatalogByName(CatalogName catalogName) {
        if (this.getName().equals(catalogName)) return this;

        for (Catalog sub : subCatalogs.values()) {
            try {
                Catalog catalog = sub.getSubCatalogByName(catalogName);
                return catalog;
            } catch (ReferenceManagementException ex) {

            }
        }
        throw new ReferenceManagementException("can't find catalog with name "+catalogName.toString());
    }
    
    @Override
    public Catalog createSubCatalog(CatalogName subName) {
        for (Catalog subCatalog : this.getSubCatalogs()) {
            if (subCatalog.getName().equals(subName)) throw new ReferenceManagementException("cannot add sub catalog with same name");
        }
        Catalog subCatalog = new CatalogImpl(subName);
        this.subCatalogs.put(subName, subCatalog);
        return subCatalog;
    }
    
    @Override
	public void add(Reference reference) {
        if (reference == null) throw new ReferenceManagementException("cannot add null product to catalog");
        store.add(reference);
	}

}
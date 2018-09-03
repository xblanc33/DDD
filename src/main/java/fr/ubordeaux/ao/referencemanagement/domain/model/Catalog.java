package fr.ubordeaux.ao.referencemanagement.domain.model;

import java.util.Set;

import fr.ubordeaux.ao.referencemanagement.domain.type.CatalogName;

/**
 * Repository 
 * 
 */
public interface Catalog {
    public CatalogName getName();
    public Set<Catalog> getSubCatalogs();
    public Catalog getSubCatalogByName(CatalogName catalogName);
    public Catalog createSubCatalog(CatalogName subCatalogName);
    public int size();
    public Set<Reference> getReferences();
    public void add(Reference reference);
}
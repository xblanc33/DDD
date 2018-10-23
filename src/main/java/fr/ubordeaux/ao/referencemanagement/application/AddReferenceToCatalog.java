package fr.ubordeaux.ao.referencemanagement.application;

import fr.ubordeaux.ao.referencemanagement.domain.model.Catalog;
import fr.ubordeaux.ao.referencemanagement.domain.model.KeyWordMap;
import fr.ubordeaux.ao.referencemanagement.domain.model.Reference;
import fr.ubordeaux.ao.referencemanagement.domain.exception.ReferenceManagementException;
import fr.ubordeaux.ao.referencemanagement.domain.type.CatalogName;

public class AddReferenceToCatalog implements Command {
    private Reference reference;
    private CatalogName catalogName;

    public AddReferenceToCatalog(Reference reference, CatalogName catalogName) {
        this.setReference(reference);
        this.setCatalogName(catalogName);
    }

    private void setReference(Reference reference) {
        if (reference == null) throw new ReferenceManagementException("Product (AddReferenceToCatalog) cannot be null");
        this.reference = reference;
    }

    private void setCatalogName(CatalogName catalogName) {
        if (catalogName == null) throw new ReferenceManagementException("Name of Catalog (AddReferenceToCatalog) cannot be null");
        this.catalogName = catalogName;
    }

    @Override
    public void execute(Catalog rootCatalog, KeyWordMap keywordMap) {
        Catalog catalog = rootCatalog.getSubCatalogByName(catalogName);
        catalog.add(reference);
    }
}
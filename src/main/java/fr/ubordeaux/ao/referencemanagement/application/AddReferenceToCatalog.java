package fr.ubordeaux.ao.referencemanagement.application;

import fr.ubordeaux.ao.referencemanagement.domain.model.Catalog;
import fr.ubordeaux.ao.referencemanagement.domain.model.Reference;
import fr.ubordeaux.ao.referencemanagement.domain.exception.ReferenceManagementException;
import fr.ubordeaux.ao.referencemanagement.domain.type.CatalogName;

public class AddReferenceToCatalog extends Command {
    private Reference reference;
    private Catalog rootCatalog;
    private CatalogName catalogName;

    public AddReferenceToCatalog(String id, Reference reference, Catalog rootCatalog, CatalogName catalogName) {
        super(id);
        this.setReference(reference);
        this.setRootCatalog(rootCatalog);
        this.setCatalogName(catalogName);
    }

    @Override
    public void executeCommand() {
        Catalog catalog = rootCatalog.getSubCatalogByName(catalogName);
        catalog.add(reference);
    }

    private void setReference(Reference reference) {
        if (reference == null) throw new ReferenceManagementException("Product (AddReferenceToCatalog) cannot be null");
        this.reference = reference;
    }

    private void setRootCatalog(Catalog rootCatalog) {
        if (rootCatalog == null) throw new ReferenceManagementException("Product (AddReferenceToCatalog) cannot be null");
        this.rootCatalog = rootCatalog;
    }

    private void setCatalogName(CatalogName catalogName) {
        if (catalogName == null) throw new ReferenceManagementException("Name of Catalog (AddReferenceToCatalog) cannot be null");
        this.catalogName = catalogName;
    }

    
}
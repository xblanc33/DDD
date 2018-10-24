package fr.ubordeaux.ao.referencemanagement.application;

import fr.ubordeaux.ao.referencemanagement.domain.model.Catalog;
import fr.ubordeaux.ao.referencemanagement.domain.exception.ReferenceManagementException;
import fr.ubordeaux.ao.referencemanagement.domain.type.CatalogName;

public class CreateSubCatalog extends Command {
    private Catalog rootCatalog;
    private CatalogName name;
    private CatalogName parentName;
    
    public CreateSubCatalog(String id, Catalog rootCatalog, CatalogName name, CatalogName parentName) {
        super(id);
        this.setRootCatalog(rootCatalog);
        this.setCatalogName(name);
        this.setParentCatalogName(parentName);
    }

    @Override
    public void executeCommand() {
        Catalog parentCatalog = rootCatalog.getSubCatalogByName(parentName);
        parentCatalog.createSubCatalog(name);
    }

    private void setRootCatalog(Catalog rootCatalog) {
        if (rootCatalog == null) throw new ReferenceManagementException("Product (AddReferenceToCatalog) cannot be null");
        this.rootCatalog = rootCatalog;
    }

    private void setCatalogName(CatalogName name) {
        if (name == null) throw new ReferenceManagementException("cannot create catalog with null as name");
        this.name = name;
    }

    private void setParentCatalogName(CatalogName parentName) {
        if (parentName == null) throw new ReferenceManagementException("cannot create catalog with null as name");
        this.parentName = name;
    }
}
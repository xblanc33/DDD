package fr.ubordeaux.ao.referencemanagement.application;

import fr.ubordeaux.ao.referencemanagement.domain.model.Catalog;
import fr.ubordeaux.ao.referencemanagement.domain.model.KeyWordMap;
import fr.ubordeaux.ao.referencemanagement.domain.exception.ReferenceManagementException;
import fr.ubordeaux.ao.referencemanagement.domain.type.CatalogName;

public class CreateSubCatalog implements Command {
    private CatalogName name;
    private CatalogName parentName;
    
    public CreateSubCatalog(CatalogName name, CatalogName parentName) {
        this.setCatalogName(name);
        this.setParentCatalogName(parentName);
    }

    private void setCatalogName(CatalogName name) {
        if (name == null) throw new ReferenceManagementException("cannot create catalog with null as name");
        this.name = name;
    }

    private void setParentCatalogName(CatalogName parentName) {
        if (parentName == null) throw new ReferenceManagementException("cannot create catalog with null as name");
        this.parentName = name;
    }

    @Override
    public void execute(Catalog rootCatalog, KeyWordMap keywordMap) {
        Catalog parentCatalog = rootCatalog.getSubCatalogByName(parentName);
        parentCatalog.createSubCatalog(name);
    }
}
package fr.ubordeaux.ao.referencemanagement.application;

import fr.ubordeaux.ao.referencemanagement.domain.model.Catalog;
import fr.ubordeaux.ao.referencemanagement.domain.model.KeyWordMap;
import fr.ubordeaux.ao.referencemanagement.domain.model.Reference;
import fr.ubordeaux.ao.referencemanagement.domain.exception.ReferenceManagementException;

public class AddReference implements Command {
    private Reference reference;

    public AddReference(Reference reference) {
        this.setReference(reference);
    }

    private void setReference(Reference reference) {
        if (reference == null) throw new ReferenceManagementException("cannot create command with null reference");
        this.reference = reference;
    }

    @Override
    public void execute(Catalog rootCatalog, KeyWordMap keywordMap) {
        rootCatalog.add(reference);
    }

}
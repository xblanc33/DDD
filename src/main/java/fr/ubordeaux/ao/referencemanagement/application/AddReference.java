package fr.ubordeaux.ao.referencemanagement.application;

import fr.ubordeaux.ao.referencemanagement.domain.model.Catalog;
import fr.ubordeaux.ao.referencemanagement.domain.model.Reference;
import fr.ubordeaux.ao.referencemanagement.domain.exception.ReferenceManagementException;

public class AddReference extends Command {
    private Reference reference;
    private Catalog rootCatalog;

    @Override
    public void executeCommand() {
        rootCatalog.add(reference);
    }

    public AddReference(String id, Reference reference, Catalog rootCatalog) {
        super(id);
        this.setReference(reference);
        this.setRootCatalog(rootCatalog);
    }

    private void setReference(Reference reference) {
        if (reference == null) throw new ReferenceManagementException("cannot create command with null reference");
        this.reference = reference;
    }

    private void setRootCatalog(Catalog rootCatalog) {
        if (rootCatalog == null) throw new ReferenceManagementException("cannot create command without root catalog");
    }

}
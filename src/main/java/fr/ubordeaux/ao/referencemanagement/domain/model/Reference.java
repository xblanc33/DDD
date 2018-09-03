package fr.ubordeaux.ao.referencemanagement.domain.model;

import java.util.Objects;

import fr.ubordeaux.ao.referencemanagement.domain.exception.ReferenceManagementException;
import fr.ubordeaux.ao.referencemanagement.domain.type.Price;

/**
 * Value Object 
 * 
 * We consider that the basePrice never changes (as well as id, name and description)
 */
public class Reference {
    private String id;
    private String name;
    private String description;
    private Price basePrice;

    public Reference(String id, String name, String description, Price basePrice) {
        this.setReferenceId(id);
        this.setName(name);
        this.setDescription(description);
        this.setBasePrice(basePrice);
    }

    private void setReferenceId(String id) {
        if (id == null) throw new ReferenceManagementException("cannot create reference with null id");
        this.id = id;
    }

    private void setName(String name) {
        if (name == null) throw new ReferenceManagementException("cannot create reference with null name");
        this.name = name;
    }

    private void setDescription(String description) {
        if (description == null) throw new ReferenceManagementException("cannot create reference with null description");
        this.description = description;
    }

    private void setBasePrice(Price basePrice) {
        if (basePrice == null) throw new ReferenceManagementException("cannot create reference with null basePrice");
        this.basePrice = basePrice;
    }

    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

    public Price getBasePrice() {
        return this.basePrice;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Reference) {
            Reference otherReference = (Reference)other;
            boolean sameId = this.getId().compareTo(otherReference.getId())==0;
            boolean sameName = this.getName().compareTo(otherReference.getName())==0;
            boolean sameDescription = this.getDescription().compareTo(otherReference.getDescription())==0;
            boolean sameBasePrice = this.getBasePrice().equals(otherReference.getBasePrice());
			return sameId && sameName && sameDescription && sameBasePrice;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Reference id="+id+ " name="+name+" description="+description;
    }
}
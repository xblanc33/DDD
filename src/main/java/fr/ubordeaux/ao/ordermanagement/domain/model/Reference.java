package fr.ubordeaux.ao.ordermanagement.domain.model;

import java.util.Objects;

import fr.ubordeaux.ao.referencemanagement.domain.exception.ReferenceManagementException;
import fr.ubordeaux.ao.referencemanagement.domain.type.Price;

/**
 * Value Object 
 * 
 * We consider that the basePrice never changes (as well as name and description)
 */
public class Reference {
    private String id;
    private String name;
    private Price price;

    public Reference(String id, String name, Price price) {
        this.setId(id);
        this.setName(name);
        this.setPrice(price);
    }

    public String getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }

    public Price getPrice() {
        return this.price;
    }

    private void setId(String id) {
        if (id == null) throw new ReferenceManagementException("cannot create reference with null id");
        this.id = id;
    }

    private void setName(String name) {
        if (name == null) throw new ReferenceManagementException("cannot create reference with null name");
        this.name = name;
    }

    private void setPrice(Price price) {
        if (price == null) throw new ReferenceManagementException("cannot create reference with null price");
        this.price = price;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Reference) {
            Reference otherReference = (Reference)other;
            boolean sameName = this.getName().compareTo(otherReference.getName())==0;
            boolean samePrice = this.getPrice().equals(otherReference.getPrice());
			return sameName && samePrice;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.toString());
    }

    @Override
    public String toString() {
        return "Reference id="+id+"name="+name;
    }
}
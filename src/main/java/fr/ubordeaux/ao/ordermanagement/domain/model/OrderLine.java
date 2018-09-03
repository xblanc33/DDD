package fr.ubordeaux.ao.ordermanagement.domain.model;

import java.util.Objects;

import fr.ubordeaux.ao.ordermanagement.domain.exception.OrderException;

public class OrderLine {
    private String id;
    private String referenceId;
    private int quantity;
    private int amount;

    public OrderLine(String id, String referenceId, int quantity) {
        this.setId(id);
        this.setReferenceId(referenceId);
        this.setQuantity(quantity);
    }

    private void setId(String id) {
        this.id = id;
    }

    private void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    private void setQuantity(int quantity) {
        if (quantity <= 0) throw new OrderException("Order line must have a positive quantity");
        this.quantity = quantity;
    } 

    public String id() {
        return this.id;
    }
    
    public String getReferenceId() {
        return this.referenceId;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public int getAmount() {
        return this.amount;
    }

    public void changeQuantity(int newQuantity) {
        this.setQuantity(quantity);
    }

    @Override
    public boolean equals(Object other) {
        if (! (other instanceof OrderLine)) return false;

        OrderLine otherLine = (OrderLine) other;
        if (id.compareTo(otherLine.id)==0) return true;
        else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
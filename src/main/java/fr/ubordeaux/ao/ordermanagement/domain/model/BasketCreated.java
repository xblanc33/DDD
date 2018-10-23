package fr.ubordeaux.ao.ordermanagement.domain.model;

import fr.ubordeaux.ao.ordermanagement.domain.exception.OrderException;

public class BasketCreated extends Event {
    private String id;

    public BasketCreated(String id) {
        this.setId(id);
    }

    public String getId() {
        return this.id;
    }

    private void setId(String id) {
        if (id == null) throw new OrderException("Basket cannot be created with null id");
        this.id = id;
    }
}
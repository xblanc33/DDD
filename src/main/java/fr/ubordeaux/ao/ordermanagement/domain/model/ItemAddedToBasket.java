package fr.ubordeaux.ao.ordermanagement.domain.model;

import fr.ubordeaux.ao.ordermanagement.domain.exception.OrderException;

public class ItemAddedToBasket extends Event {
    private String basketId;
    private String referenceId;
    private int quantity;

    public ItemAddedToBasket(String basketId, String referenceId, int quantity) {
        this.setBasketId(basketId);
        this.setReferenceId(referenceId);
        this.setQuantity(quantity);
    }

    public String getBasketId() {
        return this.getBasketId();
    }

    public String getReferenceId() {
        return this.referenceId;
    }

    public int getQuantity() {
        return this.quantity;
    }

    
    private void setBasketId(String basketId) {
        if (basketId == null) throw new OrderException("Basket cannot be created with null id");
        this.basketId = basketId;
    }
    
    private void setReferenceId(String referenceId) {
        if (referenceId == null) throw new OrderException("Basket cannot be created with null id");
        this.referenceId = referenceId;
    }

    private void setQuantity(int quantity) {
        if (quantity <= 0 ) throw new OrderException("Basket cannot be created with null id");
        this.quantity = quantity;
    }
}
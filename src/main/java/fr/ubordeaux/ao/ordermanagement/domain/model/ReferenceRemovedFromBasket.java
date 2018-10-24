package fr.ubordeaux.ao.ordermanagement.domain.model;

import fr.ubordeaux.ao.ordermanagement.domain.exception.OrderException;

/**
 * Entity 
 * 
 */
public class ReferenceRemovedFromBasket extends Event {
    private String basketId;
    private String referenceId;
    private int quantity;

    public ReferenceRemovedFromBasket(String basketId, String referenceId, int quantity) {
        this.setBasketId(basketId);
        this.setReferenceId(referenceId);
        this.setQuantity(quantity);
    }

    public String getBasketId() {
        return basketId;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public int getQuantity() {
        return quantity;
    }

    private void setBasketId(String basketId) {
        if (basketId == null) throw new OrderException("Reference cannot be added to a null basket");
        this.basketId = basketId;
    }

    private void setReferenceId(String referenceId) {
        if (referenceId == null) throw new OrderException("Null reference cannot be added to basket");
        this.referenceId = basketId;
    }

    private void setQuantity(int quantity) {
        if (quantity <= 0) throw new OrderException("0 or lower quantity cannot be added to basket");
        this.referenceId = basketId;
    }

}
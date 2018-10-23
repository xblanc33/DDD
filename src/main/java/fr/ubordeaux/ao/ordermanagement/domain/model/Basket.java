package fr.ubordeaux.ao.ordermanagement.domain.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import fr.ubordeaux.ao.ordermanagement.domain.exception.OrderException;

public class Basket {
    private String id;
    private Map<Reference,Integer> itemMap;

    public Basket(String id) {
        this.setId(id);
        itemMap = new HashMap<Reference, Integer>();
    }

    public void add(Reference reference, int quantity) {
        if (reference == null) throw new OrderException("cannot add null reference to basket");
        if (quantity <= 0) throw new OrderException("cannot add 0 or less quantitty to basket");
        if (itemMap.containsKey(reference)) throw new OrderException("cannot add item to basket, its reference has been already added ");
        itemMap.put(reference, Integer.valueOf(quantity));
    }

    public void remove(Reference reference) {
        if (reference == null) throw new OrderException("cannot remove null reference to basket");
        itemMap.remove(reference);
    }

    public void increase(Reference reference, int quantity) {
        if (reference == null) throw new OrderException("cannot add null reference to basket");
        if (quantity <= 0) throw new OrderException("cannot increase 0 or less quantitty to basket");
        if (! itemMap.containsKey(reference)) throw new OrderException("cannot icrease quantity, no such reference ");
        Integer newQuantity = Integer.valueOf(itemMap.get(reference).intValue()+quantity);
        itemMap.put(reference, newQuantity);
    }

    public void decrease(Reference reference, int quantity) {
        if (reference == null) throw new OrderException("cannot add null reference to basket");
        if (quantity <= 0) throw new OrderException("cannot decrease 0 or less quantitty to basket");
        if (! itemMap.containsKey(reference)) throw new OrderException("cannot decrease quantity, no such reference ");
        int computedQuantity = itemMap.get(reference).intValue()+quantity;
        Integer newQuantity = Integer.valueOf(computedQuantity > 0 ? computedQuantity : 0);
        itemMap.put(reference, newQuantity);
    }

    public int getAmount() {
        int amount = 0;
        for (Map.Entry<Reference, Integer> entry : itemMap.entrySet()) {
            amount = amount + entry.getKey().getPrice().getPrice() *entry.getValue().intValue();
        }
        return amount;
    }
    
    private void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object other) {
        if (! (other instanceof Basket)) return false;

        return id.compareTo(((Basket)other).id) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
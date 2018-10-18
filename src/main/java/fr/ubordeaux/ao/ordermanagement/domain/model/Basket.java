package fr.ubordeaux.ao.ordermanagement.domain.model;

import java.util.HashSet;
import java.util.Set;

public class Basket {
    private String id;
    private Set<OrderLine> lines;

    public Basket(String id) {
        this.setId(id);
        lines = new HashSet<OrderLine>();
    }

    public void add(OrderLine line) {
        lines.add(line);
    }

    public void remove(OrderLine line) {
        lines.remove(line);
    }

    public int getAmount() {
        int amount = 0;
        for (OrderLine line : lines) {
            amount = amount + line.getAmount();
        }
        return amount;
    }
    
    private void setId(String id) {
        this.id = id;
    }
}
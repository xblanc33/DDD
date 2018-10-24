package fr.ubordeaux.ao.ordermanagement.application;

import fr.ubordeaux.ao.ordermanagement.domain.exception.OrderException;
import fr.ubordeaux.ao.ordermanagement.domain.model.Basket;
import fr.ubordeaux.ao.ordermanagement.domain.model.Event;
import fr.ubordeaux.ao.ordermanagement.domain.model.EventStore;

public class BasketQueryManager {
    private EventStore eventStore;

    public BasketQueryManager(EventStore eventStore) {
        this.setEventStore(eventStore);
    }

    public Basket buildBasketFromId(String basketId) {
        Basket basket = new Basket(basketId);
        for (Event ev : eventStore.getEventListByBasketId(basketId)) {
            ev.build(basket);
        }
        return basket;
    }

    private void setEventStore(EventStore eventStore) {
        if (eventStore == null) throw new OrderException("cannot create BasketQueryManager with null eventStore");
        this.eventStore = eventStore;
    }
}
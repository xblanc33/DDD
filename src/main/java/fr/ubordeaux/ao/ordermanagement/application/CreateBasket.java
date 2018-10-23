package fr.ubordeaux.ao.ordermanagement.application;

import fr.ubordeaux.ao.ordermanagement.domain.model.BasketCreated;
import fr.ubordeaux.ao.ordermanagement.domain.model.EventStore;

public class CreateBasket implements Command {
    private String basketId;

    public CreateBasket(String basketId) {
        this.basketId = basketId;
    }

	@Override
	public void execute(EventStore eventStore) {
        eventStore.add(new BasketCreated(basketId));
        
	}

}
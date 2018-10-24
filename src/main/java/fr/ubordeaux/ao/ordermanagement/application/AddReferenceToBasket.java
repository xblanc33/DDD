package fr.ubordeaux.ao.ordermanagement.application;

import fr.ubordeaux.ao.ordermanagement.domain.model.EventStore;
import fr.ubordeaux.ao.ordermanagement.domain.model.ReferenceAddedToBasket;

public class AddReferenceToBasket implements Command {
    private EventStore eventStore;
    private String basketId;
    private String referenceId;
    private int quantity;

    public AddReferenceToBasket(EventStore eventStore, String basketId, String referenceId, int quantity) {
        this.eventStore = eventStore;
        this.basketId = basketId;
        this.referenceId = referenceId;
        this.quantity = quantity;
    }

	@Override
	public void execute() {
        eventStore.add(new ReferenceAddedToBasket(basketId, referenceId, quantity));
        
	}

}
package fr.ubordeaux.ao.ordermanagement.application;

import fr.ubordeaux.ao.ordermanagement.domain.model.EventStore;
import fr.ubordeaux.ao.ordermanagement.domain.model.ReferenceAddedToBasket;

public class AddReferenceToBasket implements Command {
    private String basketId;
    private String referenceId;
    private int quantity;

    public AddReferenceToBasket(String basketId, String referenceId, int quantity) {
        this.basketId = basketId;
        this.referenceId = referenceId;
        this.quantity = quantity;
    }

	@Override
	public void execute(EventStore eventStore) {
        eventStore.add(new ReferenceAddedToBasket(basketId, referenceId, quantity));
        
	}

}
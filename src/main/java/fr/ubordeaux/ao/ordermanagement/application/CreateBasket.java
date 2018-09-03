package fr.ubordeaux.ao.ordermanagement.application;

import java.util.UUID;

import fr.ubordeaux.ao.ordermanagement.domain.model.Basket;

public class CreateBasket implements Command {

    public CreateBasket() {

    }

	@Override
	public void execute() {
        Basket basket = new Basket(UUID.randomUUID().toString());		
	}

}
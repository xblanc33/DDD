package fr.ubordeaux.ao.ordermanagement.application;

import fr.ubordeaux.ao.ordermanagement.domain.model.EventStore;

public interface Command {
    public void execute(EventStore eventStore);
}
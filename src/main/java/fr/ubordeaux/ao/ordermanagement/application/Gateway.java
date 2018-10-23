package fr.ubordeaux.ao.ordermanagement.application;

public interface Gateway {
    public void pushCommand(Command command);

    public void addCommandHandler(Handler handler);
}
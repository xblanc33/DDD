package fr.ubordeaux.ao.referencemanagement.application;

public interface Gateway {
    public void pushCommand(Command command);

    public void addCommandHandler(Handler handler);
}
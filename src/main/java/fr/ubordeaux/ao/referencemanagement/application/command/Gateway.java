package fr.ubordeaux.ao.referencemanagement.application.command;

public interface Gateway {
    public void pushCommand(Command command);

    public void addCommandHandler(Handler handler);
}
package fr.ubordeaux.ao.ordermanagement.domain.exception;

public class OrderException extends RuntimeException {
    public OrderException(String msg) {
        super(msg);
    }
}
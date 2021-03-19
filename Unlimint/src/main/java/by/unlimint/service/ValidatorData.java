package by.unlimint.service;

import by.unlimint.model.Order;
import by.unlimint.model.OrderEntry;
import org.springframework.stereotype.Component;

@Component
public class ValidatorData {

    public OrderEntry validate(Order order, OrderEntry orderEntry) {
        StringBuilder result = new StringBuilder();
        orderEntry.setOrder(order);
        try {
            Integer checkOrderId = Integer.parseInt(order.getOrderId());
        } catch (Exception e) {
            result.append(order.getOrderId() + " не число!");
        }

        try {
            Double checkAmount = new Double(order.getAmount());
        } catch (Exception e) {
            result.append(order.getAmount() + " не число!");
        }

        if (result.toString().isEmpty()) {
            orderEntry.setErrors("OK");
        } else {
            orderEntry.setErrors(result.toString());
        }

        return orderEntry;
    }
}

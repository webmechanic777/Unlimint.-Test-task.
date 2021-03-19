package by.unlimint.service.implProducer;

import by.unlimint.model.Order;
import by.unlimint.model.OrderEntry;
import by.unlimint.service.OrderEntryProducer;
import by.unlimint.service.ValidatorData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

@Component
public class CSVProducer implements OrderEntryProducer {

    private BlockingQueue<OrderEntry> ordersQueue;
    private String type = "csv";
    private ValidatorData validatorData;

    @Autowired
    public CSVProducer(@Qualifier("getOrdersQueue") BlockingQueue<OrderEntry> ordersQueue, ValidatorData validatorData) {
        this.ordersQueue = ordersQueue;
        this.validatorData = validatorData;
    }

    @Override
    public void readOrders(File file) {
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            int lineCount = 1;
            String nextLine;
            while ((nextLine = in.readLine()) != null) {
                OrderEntry orderEntry = new OrderEntry();
                try {
                    String[] arrStr = nextLine.split(",");
                    Order order = new Order((arrStr[0]), (arrStr[1]), arrStr[2], arrStr[3]);
                    orderEntry = validatorData.validate(order, orderEntry);
                    orderEntry.setOrder(order);
                } catch (Exception e) {
                    orderEntry.setErrors("Не коректная строка!");
                }
                orderEntry.setLine(lineCount);
                orderEntry.setFilename(file.getName());
                try {
                    ordersQueue.put(orderEntry);
                } catch (InterruptedException e) {
                    System.err.println("Не удалось положить в очередь строку № " + lineCount);
                }
                lineCount++;
            }
        } catch (IOException e) {
            System.err.println("Не удается найти указанный файл: " + file.toString());
        }
    }

    public String getType() {
        return type;
    }
}

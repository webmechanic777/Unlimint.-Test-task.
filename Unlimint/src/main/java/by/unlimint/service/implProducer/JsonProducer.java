package by.unlimint.service.implProducer;

import by.unlimint.model.Order;
import by.unlimint.model.OrderEntry;
import by.unlimint.service.OrderEntryProducer;
import by.unlimint.service.ValidatorData;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

@Component
public class JsonProducer implements OrderEntryProducer {
    private String type = "jsonl";
    private BlockingQueue<OrderEntry> ordersQueue;
    private Gson gson;
    private ValidatorData validatorData;

    @Autowired
    public JsonProducer(BlockingQueue<OrderEntry> ordersQueue, Gson gson, ValidatorData validatorData) {
        this.ordersQueue = ordersQueue;
        this.gson = gson;
        this.validatorData = validatorData;
    }

    @Override
    public void readOrders(File file) {
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            int lineCount = 1;
            String nextLine;
            while ((nextLine = in.readLine()) != null) {
                Order order = new Order();
                OrderEntry orderEntry = new OrderEntry();
                try {
                    order = gson.fromJson(nextLine, Order.class);
                    orderEntry = validatorData.validate(order, orderEntry);
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
            System.err.println("Не удается найти указанный файл: " + file.toString() + ". Проверьте верность пути.");
        }
    }

    public String getType() {
        return type;
    }
}


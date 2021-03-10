package by.unlimint.service.implProducer;

import by.unlimint.model.Order;
import by.unlimint.model.OrderEntry;
import by.unlimint.service.OrderEntryProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

@Component
public class CSVProducer implements OrderEntryProducer {//Производитель

//    private BlockingQueue<OrderEntry> ordersQueue;
    private String type = "csv";

//    @Autowired
//    public CSVProducer(BlockingQueue<OrderEntry> ordersQueue) {
//        this.ordersQueue = ordersQueue;
//    }

    @Override
    public void readOrders(File file) {
//        try (BufferedReader in = new BufferedReader(new FileReader(file))){
//            int lineCount = 0;
//            OrderEntry orderEntry = new OrderEntry();
//            Order order = null;
//            String[] arrStr = null;
//            String nextLine;
//            while ((nextLine = in.readLine()) != null) {
//                try {
//                    arrStr = nextLine.split(",");
//                    order = new Order(Integer.parseInt(arrStr[0]), Double.parseDouble(arrStr[1]), arrStr[2], arrStr[3]);
//                    orderEntry.setResult("ОК");
//                    orderEntry.setOrder(order);
//                    orderEntry.setFilename(file.getName());
//                    orderEntry.setLine(lineCount++);
//                    ordersQueue.put(orderEntry);
//                } catch (Exception e) {
//                    orderEntry.setResult(e.toString());
//                }
////offer() возвращает true, если вставка прошла успешно, иначе false.
//            }
//        } catch (IOException e) {
//            System.out.println("Не удалось открыть файл.");
//            e.printStackTrace();
//        }
    }

    public String getType() {
        return type;
    }
}

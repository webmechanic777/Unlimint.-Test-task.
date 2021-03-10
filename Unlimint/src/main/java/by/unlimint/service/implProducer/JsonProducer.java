package by.unlimint.service.implProducer;

import by.unlimint.model.Order;
import by.unlimint.model.OrderEntry;
import by.unlimint.service.OrderEntryProducer;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

@Component
@Scope("prototype")
public class JsonProducer implements OrderEntryProducer {
//    private BlockingQueue<OrderEntry> ordersQueue;
    private String type = "jsonl";
//    private Gson gson;
//
//    @Autowired
//    public JsonProducer(BlockingQueue<OrderEntry> ordersQueue, Gson gson) {
//        this.ordersQueue = ordersQueue;
//        this.gson = gson;
//    }


    @Override
    public void readOrders(File file) {
//        try (BufferedReader in = new BufferedReader(new FileReader(file))){
//            int lineCount = 0;
//            OrderEntry orderEntry = new OrderEntry();
//            String nextLine;
//            while ((nextLine = in.readLine()) != null) {
//                try {
//                    Order order = gson.fromJson(nextLine, Order.class);
////                    System.out.println(order.toString());
//                    orderEntry.setResult("OK");
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



package by.unlimint.service;

import by.unlimint.model.OrderEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;

@Component
public class Converter implements Runnable {
//    @Autowired
//    private BlockingQueue<OrderEntry> ordersQueue;
    private Printer printer;

//    @Autowired
//    public Converter(BlockingQueue<OrderEntry> ordersQueue, Printer printer) {
//        this.ordersQueue = ordersQueue;
//        this.printer = printer;
//    }

    public void convert(OrderEntry orderEntry) {//дол
//        String inPrint = "{\"orderId\":" + orderEntry.getOrder().getOrderId() +
//                ", \"amount\":" + orderEntry.getOrder().getAmount() +
//                ", \"currency\":\"" + orderEntry.getOrder().getOrderId() +
//                "\" ,\"comment\":\"" + orderEntry.getOrder().getComment() +
//                ", \"filename\":\"" + orderEntry.getFilename() +
//                "\", \"line\":" + orderEntry.getLine() +
//                "\", \"result\":\"" + orderEntry.getResult() + "\" }";
//        printer.printOrderEntry(inPrint);
    }

    @Override
    public void run() {
    //читать из очереди, как-то остановить ожидание

//        try {
//            while (true)
//                convert(ordersQueue.take());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//take() ожидает головного элемента очереди и удаляет его. Если очередь пуста,
// она блокируется и ожидает, пока элемент станет доступным.
    }
}

//{“id”:1, ”amount”:100, ”comment”:”оплата заказа”, ”filename”:”orders.csv”, ”line”:1, ”result”:”OK” }
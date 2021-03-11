package by.unlimint.service;

import by.unlimint.model.OrderEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;

@Component
public class Converter {
    private BlockingQueue<OrderEntry> ordersQueue;
    private Printer printer;

    @Autowired
    public Converter(@Qualifier("getOrdersQueue") BlockingQueue<OrderEntry> ordersQueue, Printer printer) {
        this.ordersQueue = ordersQueue;
        this.printer = printer;
    }

    public void runConverter() {
        try {
            while (true) convert(ordersQueue.take());
        } catch (InterruptedException e) {
            System.out.println("Исключение в Converter");
            e.printStackTrace();
        }


//take() ожидает головного элемента очереди и удаляет его. Если очередь пуста,
// она блокируется и ожидает, пока элемент станет доступным.
    }

    public void convert(OrderEntry orderEntry) {//дол
        String inPrint = "{\"orderId\":" + orderEntry.getOrder().getOrderId() +
                ", \"amount\":" + orderEntry.getOrder().getAmount() +
                ", \"currency\":\"" + orderEntry.getOrder().getOrderId() +
                "\" ,\"comment\":\"" + orderEntry.getOrder().getComment() +
                ", \"filename\":\"" + orderEntry.getFilename() +
                "\", \"line\":" + orderEntry.getLine() +
                "\", \"result\":\"" + orderEntry.getResult() + "\" }";
        printer.printOrderEntry(inPrint);
    }
}

//{“id”:1, ”amount”:100, ”comment”:”оплата заказа”, ”filename”:”orders.csv”, ”line”:1, ”result”:”OK” }
package by.unlimint.service;

import by.unlimint.model.ConversionResult;
import by.unlimint.model.OrderEntry;
import by.unlimint.service.implPrint.ConsolePrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;

@Component
public class Converter implements Runnable {
    private BlockingQueue<OrderEntry> ordersQueue;
    private ConsolePrinter consolePrinter;

    @Autowired
    public Converter(@Qualifier("getOrdersQueue") BlockingQueue<OrderEntry> ordersQueue, ConsolePrinter consolePrinter) {
        this.ordersQueue = ordersQueue;
        this.consolePrinter = consolePrinter;
    }

    @Override
    public void run() {
        while (true) {
            try {
                convert(ordersQueue.take());
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    public void convert(OrderEntry orderEntry) {
        ConversionResult conversionResult = new ConversionResult();
        try {
            conversionResult.setId(orderEntry.getOrder().getOrderId());
            conversionResult.setAmount(orderEntry.getOrder().getAmount());
            conversionResult.setComment(orderEntry.getOrder().getComment());
            conversionResult.setComment(orderEntry.getOrder().getComment());
            conversionResult.setResult(orderEntry.getErrors());
        } catch (Exception e) {
            conversionResult.setId("");
            conversionResult.setAmount("");
            conversionResult.setComment("");
            conversionResult.setComment("");
            conversionResult.setResult("Не коректная строка!");
        }
        conversionResult.setFilename(orderEntry.getFilename());
        conversionResult.setLine(orderEntry.getLine());
        consolePrinter.printConsole(conversionResult);
    }
}

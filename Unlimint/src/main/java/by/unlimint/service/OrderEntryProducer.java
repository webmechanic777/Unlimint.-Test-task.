package by.unlimint.service;

import java.io.File;

public interface OrderEntryProducer {
    void readOrders(File file);
    String getType();
}

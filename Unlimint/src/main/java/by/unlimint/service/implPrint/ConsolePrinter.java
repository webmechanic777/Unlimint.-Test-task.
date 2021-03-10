package by.unlimint.service.implPrint;

import by.unlimint.service.Printer;
import org.springframework.stereotype.Component;

@Component
public class ConsolePrinter implements Printer {

    public void printOrderEntry(String... inPrint) {
        for(String string: inPrint){
            System.out.println(string);
        }
    }
}

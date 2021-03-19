package by.unlimint.service.implPrint;

import by.unlimint.model.ConversionResult;
import by.unlimint.service.Printer;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsolePrinter implements Printer {
    private Gson gson;

    @Autowired
    public ConsolePrinter(Gson gson) {
        this.gson = gson;
    }

    public void printConsole(ConversionResult conversionResult) {
        String result = gson.toJson(conversionResult);
        if (conversionResult.getResult().equals("OK")) {
            System.out.println(result);
        } else {
            System.err.println(result);
        }
    }
}

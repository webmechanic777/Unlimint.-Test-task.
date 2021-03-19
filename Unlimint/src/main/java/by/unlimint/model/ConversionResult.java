package by.unlimint.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ConversionResult {
    private String Id;
    private String amount;
    private String comment;
    private String filename;
    private int line;
    private String result;//errors;
}


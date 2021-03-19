package by.unlimint.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String orderId;
    private String amount;
    private String currency;
    private String comment;
}



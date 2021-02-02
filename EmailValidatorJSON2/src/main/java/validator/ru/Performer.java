package validator.ru;

import java.io.IOException;

public class Performer {//Исполнитель
    public static void main(String[] args) throws IOException {
        DFAValidator dfaValidator = new DFAValidator("src/main/resources/transition2.json");
        dfaValidator.readStatesAndTransition();
        System.out.println(dfaValidator.validate("vito.sytin@tut.by"));
    }
}

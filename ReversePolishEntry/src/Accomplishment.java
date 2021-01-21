
public class Accomplishment {//выполнение
    public static void main(String[] args) {
        ReversePolishEntry reversePolishEntry = new ReversePolishEntry("12+22*(3+4/2-(1+2))*2+1");// 101.0

        MyStack<Lexem> stackLexem =  reversePolishEntry.getReversePolishEntry();/**Получить польскую запись.**/

        System.out.println(stackLexem);//   [12, 22, 3, 4, 2, /, +, 1, 2, +, -, *, 2, *, +, 1, +]

        Decision decision = new Decision(stackLexem);//decision - решение
        String answer = decision.calculate();//answer - ответ, calculate - вычислять
        System.out.println(answer);
    }
}

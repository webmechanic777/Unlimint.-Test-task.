import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Calculate {
    private List<Lexem> list = new ArrayList<>();
    private int x = -1;
    private double result = 0;

    public Calculate(MyStack<Lexem> stackLexem) {
        while (stackLexem.size() != 0) {
            list.add(stackLexem.pop());
        }
        Collections.reverse(list);
    }

    public String getAnswer() {
        while (list.size() != 1) {
            x++;
            switch ((LexemType) list.get(x).getLexemType()) {
                case PLUS:
                    result = Double.parseDouble((list.get(x-2).toString())) + Double.parseDouble((list.get(x-1).toString()));
                    calculate();
                    continue;
                case MINUS:
                    result = Double.parseDouble((list.get(x-2).toString())) - Double.parseDouble((list.get(x-1).toString()));
                    calculate();
                    continue;
                case MULTIPLY:
                    result = Double.parseDouble((list.get(x-2).toString())) * Double.parseDouble((list.get(x-1).toString()));
                    calculate();
                    continue;
                case DIVISION:
                    result = Double.parseDouble((list.get(x-2).toString())) / Double.parseDouble((list.get(x-1).toString()));
                    calculate();
                    continue;
                case DEGREE:
                    result = Math.pow(Double.parseDouble((list.get(x-2).toString())), Double.parseDouble((list.get(x-1).toString())));
                    calculate();
                    continue;
            }
        }
        return list.get(0).toString();
    }

    private void calculate() {
        list.set(x-2, new Lexem(String.valueOf(result), LexemType.NUMBER));
        list.remove(x);
        list.remove(x-1);
        x = x - 2;
    }
}

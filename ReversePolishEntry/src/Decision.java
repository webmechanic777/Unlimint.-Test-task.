
public class Decision {
    private MyStack<Lexem> stack = new StackArray<>(Lexem.class);;
    private MyStack<Lexem> answer = new StackArray<>(Lexem.class);;
    private double a = 0;
    private double b = 0;

    public Decision(MyStack<Lexem> stackLexem) {
        while (stackLexem.size() != 0) {
            stack.push(stackLexem.pop());
        }
    }

    public String calculate() {
        while (!(answer.size() == 1 && stack.size() == 0)) {
            switch (stack.peek().getLexemType()) {
                case NUMBER:
                    answer.push(stack.pop());
                    continue;
                case PLUS:
                    initializationAB();
                    answer.push(new Lexem(a + b, LexemType.NUMBER));
                    stack.pop();
                    continue;
                case MINUS:
                    initializationAB();
                    answer.push(new Lexem(a - b, LexemType.NUMBER));
                    stack.pop();
                    continue;
                case MULTIPLY:
                    initializationAB();
                    answer.push(new Lexem(a * b, LexemType.NUMBER));
                    stack.pop();
                    continue;
                case DIVISION:
                    initializationAB();
                    answer.push(new Lexem(a / b, LexemType.NUMBER));
                    stack.pop();
                    continue;
                case DEGREE:
                    initializationAB();
                    answer.push(new Lexem(Math.pow(b, a), LexemType.NUMBER));
                    stack.pop();
                    continue;
            }
        }
        return answer.peek().toString();
    }

    private void initializationAB() {
        b = answer.pop().getDoubleValue();
        a = answer.pop().getDoubleValue();
    }
}

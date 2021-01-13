
public class ReversePolishEntry {
    private static MyStack<Lexem> numbers = new StackArray<>(Lexem.class);//для чисел
    private static MyStack<Lexem> symbols = new StackArray<>(Lexem.class);//для знаков
    private static Lexer lexer;

    public ReversePolishEntry(String string) {
        lexer = new Lexer(string);
    }

    public MyStack<Lexem> getReversePolishEntry() {//Получение польской записи.
         while (!lexer.lookAhead().getLexemType().equals(LexemType.EOF)) {
             switch ((LexemType) lexer.lookAhead().getLexemType()) {
                 case NUMBER:
                     numbers.push(lexer.getNextToken());
                     continue;
                 case PLUS:
                     calculatePlus();
                     continue;
                 case MINUS:
                 case DIVISION:
                 case MULTIPLY:
                     calculateMultiply();
                     continue;
                 case DEGREE:
                 case LEFT_BRACKET:
                     symbols.push(lexer.getNextToken());
                     continue;
                 case RIGHT_BRACKET:
                     calculateRightBracket();
             }
         }
         while (symbols.size() != 0)
             numbers.push(symbols.pop());
         return numbers;
    }

    private void calculateMultiply() {
        if (symbols.size() != 0 &&
                symbols.peek().getLexemType() == LexemType.MULTIPLY ||
                symbols.peek().getLexemType() == LexemType.DIVISION) {
            numbers.push(symbols.pop());
        }
        symbols.push(lexer.getNextToken());
    }

    public void calculatePlus() {
        if (symbols.size() != 0) {
            if (symbols.peek().getLexemType() == LexemType.MULTIPLY ||
                    symbols.peek().getLexemType() == LexemType.DIVISION ||
                    symbols.peek().getLexemType() == LexemType.DEGREE) {
                numbers.push(symbols.pop());
            }
        }
        symbols.push(lexer.getNextToken());
    }

    public void calculateRightBracket() {
        while (symbols.peek().getLexemType() != LexemType.LEFT_BRACKET) {
            numbers.push(symbols.pop());
        }
        lexer.getNextToken();
        symbols.pop();
    }
}

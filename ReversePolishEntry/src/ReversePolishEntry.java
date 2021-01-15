
public class ReversePolishEntry {
    private static MyStack<Lexem> numbers = new StackArray<>(Lexem.class);//для чисел
    private static MyStack<Lexem> symbols = new StackArray<>(Lexem.class);//для знаков
    private static Lexer lexer;

    public ReversePolishEntry(String string) {
        lexer = new Lexer(string);
    }

    public MyStack<Lexem> getReversePolishEntry() {//Получение польской записи.
         while (!lexer.lookAhead().getLexemType().equals(LexemType.EOF)) {
             switch (lexer.lookAhead().getLexemType()) {
                 case NUMBER:
                     numbers.push(lexer.getNextToken());
                     continue;
                 case PLUS:
                 case MINUS:
                     parseExpression(2);
                     continue;
                 case DIVISION:
                 case MULTIPLY:
                     parseExpression(3);
                     continue;
                 case DEGREE:
                 case LEFT_BRACKET:
                     symbols.push(lexer.getNextToken());
                     continue;
                 case RIGHT_BRACKET:
                     parseRightBracket();
             }
         }
         while (symbols.size() != 0)
             numbers.push(symbols.pop());
         return numbers;
    }

    private void parseExpression(int priority) {
        while (symbols.size() != 0 && symbols.peek().getLexemType().getPriority() > priority) {
            numbers.push(symbols.pop());
        }
        symbols.push(lexer.getNextToken());
    }

    private void parseRightBracket() {
        while (symbols.peek().getLexemType() != LexemType.LEFT_BRACKET) {
            numbers.push(symbols.pop());
        }
        lexer.getNextToken();
        symbols.pop();
    }
}

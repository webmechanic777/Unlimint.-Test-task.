import static java.lang.Double.parseDouble;

public class Lexer {
    private final String expression;
    private int currentPosition = 0;

    public Lexer(String expression) {
        this.expression = expression;
    }

    public Lexem getNextToken() {//получить лексему, переключает currentPosition
        return accomplishment();
    }

    public Lexem lookAhead() {//посмотреть следующую лексему, не переключает currentPosition
        int IncrementOff = currentPosition;
        Lexem lexem = accomplishment();
        currentPosition = IncrementOff;
        return lexem;
    }

    private Lexem accomplishment() {
        Lexem lexem = new Lexem("", LexemType.EOF);
        if (currentPosition < expression.length()) {
            if (Character.isDigit(expression.charAt(currentPosition))) {
                lexem = parseNumber();
            } else {//если s +-/*^()
                lexem = parseNotANumber();
                currentPosition++;
            }
            return lexem;//возвращаем или цифры или +-/*^()
        }
        return lexem;//если currentPosition !< expression.length()
    }

    private Lexem parseNumber() {
        StringBuilder s = new StringBuilder();
        while (currentPosition < expression.length() && Character.isDigit(expression.charAt(currentPosition))) {
                s.append(expression.charAt(currentPosition));
                currentPosition++;
        }
        return new Lexem(parseDouble(s.toString()), LexemType.NUMBER);
    }

    private Lexem parseNotANumber() {
        switch (expression.charAt(currentPosition)) {
            case '+':
                return new Lexem("+", LexemType.PLUS);
            case '-':
                return new Lexem("-", LexemType.MINUS);
            case '*':
                return new Lexem("*", LexemType.MULTIPLY);
            case '/':
                return new Lexem("/", LexemType.DIVISION);
            case '^':
                return new Lexem("^", LexemType.DEGREE);
            case '(':
                return new Lexem("(", LexemType.LEFT_BRACKET);
            case ')':
                return new Lexem(")", LexemType.RIGHT_BRACKET);
            default:
                return new Lexem("", LexemType.EOF);
        }
    }
}


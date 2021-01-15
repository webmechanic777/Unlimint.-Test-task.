
public enum LexemType {
    PLUS(3),
    MINUS(3),
    MULTIPLY(4),// *
    DIVISION(4),// /
    DEGREE(5),// ^   всё выдавливает степень
    NUMBER(1),// число
    LEFT_BRACKET(2),
    RIGHT_BRACKET(2),
    EOF(1);

    private final int priority;

    LexemType(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}


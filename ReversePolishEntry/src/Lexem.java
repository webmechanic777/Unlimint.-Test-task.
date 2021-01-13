
public class Lexem<LexemType> {//возвращается Lexem типы разные value,
    private String value;
    private LexemType type;//Enumeration

    public Lexem(String value, LexemType type) {
        this.value = value;
        this.type = type;
    }

    public LexemType getLexemType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return value;
    }
}

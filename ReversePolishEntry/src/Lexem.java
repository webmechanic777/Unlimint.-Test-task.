
public class Lexem {//возвращается Lexem типы разные value,
    private String value = "";
    private double doubleValue;
    private LexemType type;//Enumeration

    public Lexem(String value, LexemType type) {
        this.value = value;
        this.type = type;
    }

    public Lexem(double doubleValue, LexemType type) {
        this.doubleValue = doubleValue;
        this.type = type;
    }

    public LexemType getLexemType() {
        return type;
    }

    public double getDoubleValue() {
        return doubleValue;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        if (value.equals("")) {
            return String.valueOf(doubleValue);
        }
        return value;
    }
}

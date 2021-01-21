
public class Email {
    private char[] arr;
    int currentPosition = 0;

    public Email(String str) {
        arr = str.toCharArray();
    }

    public static void main(String[] args) {
        Email email = new Email("viktorsytin@tut.by");
        System.out.println(email.q1());

    }

    public boolean q1() {
        if (currentPosition >= arr.length) {
            return false;
        } else if (((arr[currentPosition] >= 'a') && (arr[currentPosition] <= 'z')) ||
                ((arr[currentPosition] >= 'A') && (arr[currentPosition] <= 'Z'))) {
            currentPosition++;
            return q2();
        }
        return false;
    }

    private boolean q2() {
        while (currentPosition < arr.length) {
                if (((arr[currentPosition] >= 'a') && (arr[currentPosition] <= 'z')) ||//если это не буква
                    ((arr[currentPosition] >= 'A') && (arr[currentPosition] <= 'Z')) ||//и если это не цифра
                    Character.isDigit(arr[currentPosition])) {
                    currentPosition++;
                } else if (arr[currentPosition] == '@') {
                    currentPosition++;
                    return q3();
                } else if (arr[currentPosition] == '.') {
                    currentPosition++;
                    return q1();
                }
        }
        return false;
    }

    private boolean q3() {
        if (currentPosition >= arr.length) {
            return false;
        } else if (((arr[currentPosition] >= 'a') && (arr[currentPosition] <= 'z')) ||
                ((arr[currentPosition] >= 'A') && (arr[currentPosition] <= 'Z'))) {
            currentPosition++;
            return q4();
        }
        return false;
    }

    private boolean q4() {
        while (currentPosition < arr.length) {
            if (((arr[currentPosition] >= 'a') && (arr[currentPosition] <= 'z')) ||
                    ((arr[currentPosition] >= 'A') && (arr[currentPosition] <= 'Z'))) {
                currentPosition++;
            } else if (arr[currentPosition] == '.') {
                currentPosition++;
                return q5();
            } else {
                return false;
            }
        }
        return false;
    }

    private boolean q5() {
        if (currentPosition >= arr.length) {
            return false;
        } else if (((arr[currentPosition] >= 'a') && (arr[currentPosition] <= 'z')) ||
                ((arr[currentPosition] >= 'A') && (arr[currentPosition] <= 'Z'))) {
            currentPosition++;
            return q6();
        }
        return false;
    }

    private boolean q6() {
        if (currentPosition >= arr.length) {
            return false;
        } else if (((arr[currentPosition] >= 'a') && (arr[currentPosition] <= 'z')) ||
                ((arr[currentPosition] >= 'A') && (arr[currentPosition] <= 'Z'))) {
            currentPosition++;
            return q7();
        } else if (arr[currentPosition] == '.') {
            currentPosition++;
            return q5();
        }
        return false;
    }

    private boolean q7() {
        if (currentPosition >= arr.length) {
            return true;
        } else if (((arr[currentPosition] >= 'a') && (arr[currentPosition] <= 'z')) ||
                ((arr[currentPosition] >= 'A') && (arr[currentPosition] <= 'Z'))) {
            currentPosition++;
            return q8();
        } else if (arr[currentPosition] == '.') {
            currentPosition++;
            return q5();
        }
        return false;
    }

    private boolean q8() {
        if (currentPosition >= arr.length) {
            return true;
        } else if (((arr[currentPosition] >= 'a') && (arr[currentPosition] <= 'z')) ||
                ((arr[currentPosition] >= 'A') && (arr[currentPosition] <= 'Z'))) {
            currentPosition++;
            return q9();
        } else if (arr[currentPosition] >= '.') {
            currentPosition++;
            return q5();
        }
        return false;
    }

    private boolean q9() {
        if (currentPosition >= arr.length) {
            return true;
        } else if (((arr[currentPosition] >= 'a') && (arr[currentPosition] <= 'z')) ||
                ((arr[currentPosition] >= 'A') && (arr[currentPosition] <= 'Z'))) {
            currentPosition++;
            return q4();
        } else if (arr[currentPosition] == '.') {
            currentPosition++;
            return q5();
        }
        return false;
    }
}

//  Character.getNumericValue() проверка char на число

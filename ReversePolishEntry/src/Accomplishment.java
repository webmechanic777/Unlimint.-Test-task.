
public class Accomplishment {//выполнение
    public static void main(String[] args) {
//        ReversePolishEntry reversePolishEntry = new ReversePolishEntry("3+4*2/(1-5)^2");   // 3.5
//        ReversePolishEntry reversePolishEntry = new ReversePolishEntry("3+2*2");           // 7
//        ReversePolishEntry reversePolishEntry = new ReversePolishEntry("3+2*2*(1+1)^2");   // 19
//        ReversePolishEntry reversePolishEntry = new ReversePolishEntry("3*2^2");           // 12
//        ReversePolishEntry reversePolishEntry = new ReversePolishEntry("3^2+2");           // 11
        ReversePolishEntry reversePolishEntry = new ReversePolishEntry("12+22*(3+4/2-(1+2))*2+1");// 101.0

        MyStack<Lexem> stackLexem =  reversePolishEntry.getReversePolishEntry();//Получить польскую запись.
        System.out.println(stackLexem);     //[12, 22, 3, 4, 2, /, 1, 2, +, -, +, *, 2, *, 1, +, +]

        Calculate recordingSolution = new Calculate(stackLexem);//записывающее решение
        String answer = recordingSolution.getAnswer();//ответ
        System.out.println(answer);

    }
}

//-----------------------MyStack работает)
//        MenuMyStack<String> white = new MyStack<>();
//    E peek();//Смотрит на объект вверху этого стека, не удаляя его из стека.
//    E push(E item);//Помещает элемент в верхнюю часть этой стопки.
//    E pop();//Удаляет объект наверху этого стека и возвращает этот объект как значение этой функции.
//        white.push("1");
//        white.push("2");
//        white.push("3");
//        System.out.println(white.peek());//3
//        System.out.println(white.pop());//3
//        System.out.println(white.pop());//2
//        System.out.println(white.peek());//1
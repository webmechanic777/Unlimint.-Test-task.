import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class StackArray<E> implements MyStack<E> {
    private E[] arr;

    public StackArray(Class<E> type) {
        this.arr = (E[]) Array.newInstance(type, 0);
    }

    @Override
    public void push(E e) {//Помещает элемент в верхнюю часть этой стопки.
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = e;
    }

    @Override
    public E pop() {//Удаляет объект наверху этого стека и возвращает этот объект как значение этой функции.
        E e = arr[arr.length - 1];
        arr = Arrays.copyOf(arr, arr.length - 1);
        return e;
    }

    @Override
    public E peek() {//Смотрит на объект вверху этого стека, не удаляя его из стека.
        if (arr.length == 0)
            throw new NoSuchElementException();
        E e = arr[arr.length - 1];
        return e;
    }

    @Override
    public int size() {
        return arr.length;
    }

    public String toString() {
        return Arrays.toString(arr);
    }
}

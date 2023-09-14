public class ArrayDeque<T>{
    int size;
    int length;
    int front =4;
    int last = 4;
    public ArrayDeque(){
        T[] array = (T[]) new Object[8];
        length = 8;
        size = 0;
    }
    public boolean isEmpty(){
        return size ==0;
    }
    public int size(){
        return size;
    }
}


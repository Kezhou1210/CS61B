public class ArrayDeque<T> {
    private T[] items;
    private int size;

    public ArrayDeque(){
        items =(T[]) new Object[8];
        size = 0;
    }

    public ArrayDeque(ArrayDeque other){
        T[] temp = (T[]) new Object[other.items.length];
        for(int i = 0; i<=other.size; i++){
            temp[i] = (T) other.items[i];
        }
        items = temp;
        size = other.size;
    }

    private void resize(int capacity){
        T[] temp = (T[]) new Object[capacity];
        System.arraycopy(items, 0, temp, 0, size);
        items = temp;
    }

    public void addFirst(T item){
        if(size == items.length){
            resize(size * 2);
        }
        T[] temp = (T[]) new Object[items.length];
        System.arraycopy(items, 0, temp, 1,size);
        temp[0] = item;
        items = temp;
        size++;
    }

    public void addLast(T x){
        if(size == items.length){
            resize(size * 2);
        }
        items[size] = x;
        size++;
    }

    public boolean isEmpty(){
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        for(int i = 0; i<size; i++){
            System.out.print(items[i]+" ");
        }
        System.out.println(items[size]+" ");
    }

    public T removeFirst(){
        if(size == 0){
            return null;
        }
        T x = items[0];
        T[] temp = (T[]) new Object[items.length];
        System.arraycopy(items,1,temp,0,size -1);
        items = temp;
        size--;
        if(size/items.length <= 0.25){
            resize(items.length/2);
        }
        return x;
    }

    private T getLast(){
        return items[size-1];
    }

    public T removeLast(){
        if(size ==0){
            return null;
        }
        T x = getLast();
        size--;
        if(size/items.length <= 0.25){
            resize(items.length/2);
        }
        return x;
    }

    public T get(int index){
        if(index > size){
            return null;
        }
        return items[index];
    }

}

public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextLast;
    private int nextFirst;
    private int length;

    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
        nextLast = 5;
        nextFirst = 4;
        length = 8;
    }

    private void grow(){
        int capacity = length * 2;
        T[] temp = (T[]) new Object[capacity];
        System.arraycopy(items, 0 , temp, capacity/4, size);
        items = temp;
        nextFirst = capacity/4 -1;
        nextLast = 3*capacity/4;
        length = capacity;
    }

    private void shrink(){
        int capacity = length/2;
        T[] temp = (T[]) new Object[capacity];
        if(nextFirst > nextLast){
            System.arraycopy(items, nextFirst+1-length, temp, capacity/4,size);
        }else{
            System.arraycopy(items, nextFirst +1, temp, capacity/4 ,size);
        }
        items = temp;
        nextFirst = capacity/4 -1;
        nextLast = 3*capacity/4;
        length = capacity;
    }

    public void addFirst(T item){
        if(size == length){
            grow();
        }
        items[nextFirst] = item;
        size++;
        nextFirst--;
        if(nextFirst <0){
            nextFirst += length;
        }
    }

    public void addLast(T item){
        if(size == length){
            grow();
        }
        items[nextLast] = item;
        size++;
        nextLast++;
        if(nextLast > length-1){
            nextLast = length - nextLast;
        }
    }

    public boolean isEmpty(){
        return size==0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        int start;
        if(nextFirst > nextLast){
            start = nextFirst +1 -length;
        }else{
            start = nextFirst + 1;
        }
        for(int i = start; i<start+size;i++){
            System.out.print(items[i] + " ");
        }
        System.out.println("");
    }

    public T removeLast(){
        if(size/length <= 0.25 && length >= 16){
            shrink();
        }
        if(size ==0){
            return null;
        }
        size--;
        nextLast--;
        if(nextLast <0){
            nextLast = nextLast + length;
        }
        T item = items[nextLast];
        return item;
    }

    public T removeFirst(){
        if(size/length <= 0.25 && length >= 16){
            shrink();
        }
        if(size ==0){
            return null;
        }
        size--;
        nextFirst++;
        if(nextFirst > length - 1){
            nextFirst -= length;
        }
        T item = items[nextFirst];
        return item;
    }

    public T get(int index){
        if(index + nextFirst +1 >length){
            return items[index+nextFirst+1-length];
        }
        return items[index + nextFirst +1];
    }
}

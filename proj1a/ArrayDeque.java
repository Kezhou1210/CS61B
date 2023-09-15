import jdk.incubator.concurrent.ScopedValue;

import javax.management.remote.rmi.RMIJRMPServerImpl;
import java.lang.foreign.SymbolLookup;

public class ArrayDeque<item>{
    item [] array;
    int size;
    int front;
    int last;
    public ArrayDeque(){
        array = (item[]) new Object[8];
        size = 0;
        front = 0;
        last =1;
    }
    public boolean isEmpty(){
        return size ==0;
    }
    public int size(){
        return size;
    }
    public int minus(int index){
        return Math.floorMod(index - 1, array.length);
    }
    public int plus(int index){
        return Math.floorMod(index + 1, array.length);
    }
    public int plus(int index, int length){
        return Math.floorMod(index + 1, length);
    }
    public void addFirst(item x){
        resize();
        array[front] = x;
        size +=1;
        front = minus(front);
    }
    public item getFirst(){
        return (item) array[plus(front)];
    }
    public item removeFirst(){
        if(isEmpty()){
            return null;
        }
        resize();
        item r = getFirst();
        front = plus(front);
        array[front] = null;
        size --;
        return r;
    }
    public void addLast(item x){
        resize();
        array[last] =x;
        size ++;
        last = plus(last);
    }
    public item getLast(){
        return array[minus(last)];
    }
    public item removeLast(){
        if (isEmpty()){
            return null;
        }
        resize();
        item r = getLast();
        last = minus(last);
        array[last] = null;
        size --;
        return r;
    }
    public item get(int index){
        if(index <0 || index >= size|| isEmpty()){
            return null;
        }
        return array[Math.floorMod(plus(front)+index,array.length)];
    }
    public void resize(){
        if(size == array.length){
            grow();
        }
        if(array.length > 8 && size/ array.length < 0.25){
            shrink();
        }
    }
    public void grow() {
        resizeHelper(array.length * 2);
    }
    public void shrink(){
        resizeHelper(array.length/2);
    }
    public void resizeHelper(int capacity){
        item[] temp = array;
        array = (item[]) new Object[capacity];
        int start = plus(front);
        int end = minus(last);
        front = 0;
        last = 1;
        for(int i =start; i< end; i = plus(i, temp.length)){
            array[last] = temp[i];
            last = plus(last);
        }
        array[last] = temp[end];
        last = plus(last);
    }
    public void printDeque(){
        for(int i = plus(front);i!= last; i = plus(front)){
            System.out.println(array[i] + " ");
        }
    }
}


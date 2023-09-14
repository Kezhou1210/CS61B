import java.util.LinkedList;

public class LinkedListDeque<T>{

    public class TNode<T>{
        public T item;
        public TNode after;

        public TNode pre;
        public TNode(TNode pre, TNode after){
            this.pre= pre;
            this.after =after;
        }
        public TNode(T item, TNode pre, TNode after){
            this.item = item;
            this.pre = pre;
            this.after = after;
        }
    }
    public TNode first;
    public int size;
    public LinkedListDeque(){
        first = new TNode(null,null);
        first.after = first;
        first.pre = first;
        size =0;
    }
    public void addFirst(T item) {
        TNode node = new TNode(item, first, first.after);
        first.after.pre = node;
        first.after = node;
        size += 1;
    }
    public void addLast(T item){
        TNode node = new TNode(item, first.pre, first);
        first.pre.after = node;
        first.pre = node;
        size += 1;
    }
    public boolean isEmpty(){
        if(size ==0){
            return true;
        }
        return false;
    }
    public int size(){
        return size;
    }
    public T removeFirst(){
        if(size == 0){
            return  null;
        }
        T value = (T)first.after.item;
        TNode temp = first.after.after;
        first.after = temp;
        temp.pre = first;
        size -=1;
        return value;
    }
    public T removeLast(){
        if(size ==0){
            return null;
        }
        T value = (T)first.pre.item;
        first.pre = first.pre.pre;
        first.pre.after = first;
        size -= 1;
        return value;
    }
    public void printDeque(){
        TNode temp = first;
        while(temp != first){
            System.out.print(temp.item.toString() + ' ');
            temp = temp.after;
        }
    }
    public  T get(int index){
        if(index >= size){
            return null;
        }
        TNode temp = first;
        for(int i =0; i<=index;i+=1){
            temp = temp.after;
        }
        return (T)temp.item;
    }
    public T getRecursiveHelper(TNode new_list, int new_index){
        if(new_index == 0){
            return (T)new_list.item;
        }
        return (T)getRecursiveHelper(new_list.after,new_index -1);
    }
    public T getRecursive(int index){
        if(index >= size){
            return null;
        }
        return (T)getRecursiveHelper(first.after,index-1);
    }
}

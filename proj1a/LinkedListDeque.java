public class LinkedListDeque<T> {
        private class Node {
            public Node prev;
            public T item;
            public Node next;

            public Node(Node p, T i, Node n){
                prev = p;
                item = i;
                next = n;
            }

            public Node(Node p, Node n){
                prev = p;
                next = n;
            }
        }

        private Node sentinal;
        private int size;

        public LinkedListDeque(){
            sentinal = new Node(null,null);
            sentinal.next = sentinal;
            sentinal.prev = sentinal;
            size =0;
        }

        public void addFirst(T item){
            sentinal.next = new Node(sentinal, item, sentinal.next);
            sentinal.next.next.prev = sentinal.next;
            size ++;
        }

        public void addLast(T item) {
            sentinal.prev = new Node(sentinal.prev, item, sentinal);
            sentinal.prev.prev.next = sentinal.prev;
            size++;
        }

        public boolean isEmpty(){
            if(size == 0){
                return true;
            }else{
                return false;
            }
        }

        public int size(){
            return size;
        }

        public void printDeque(){
            Node temp = sentinal.next;
            while(temp.next != sentinal){
                System.out.print(temp.item+" ");
                temp = temp.next;
            }
            System.out.println(temp.item+" ");
        }

        public T removeFirst(){
            if(size == 0){
                return null;
            }
            T item = sentinal.next.item;
            sentinal.next = sentinal.next.next;
            sentinal.next.prev = sentinal;
            size--;
            return item;
        }

        public T removeLast(){
            if(size ==0){
                return null;
            }
            T item = sentinal.prev.item;
            sentinal.prev.prev.next = sentinal;
            sentinal.prev = sentinal.prev.prev;
            size--;
            return item;
        }

        public T get(int index){
            if(size < index+1){
                return null;
            }
            Node temp = sentinal.next;
            int i=0;
            while(i<index){
                temp = temp.next;
                i++;
            }
            return temp.item;
        }

        private T getRecursiveHelp(Node startNode, int index){
            if(index ==0){
                return startNode.item;
            }
            return getRecursiveHelp(startNode.next, index-1);
        }
        public T getRecursive(int index){
            if(size< index+1){
                return null;
            }
            return getRecursiveHelp(sentinal.next,index);
        }

}

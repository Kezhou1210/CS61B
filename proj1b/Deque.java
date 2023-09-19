public interface Deque<T> {
    public void addFirst(T item);

    public T removeFirst();

    public void addLast(T item);

    public T removeLast();

    public boolean isEmpty();

    public int size();

    public T get(int index);

    public void printDeque();
}

public class Tests {
    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.insert(new ListLink<Integer>(1) );
        list.insert(new ListLink<Integer>(2) );
        list.insert(new ListLink<Integer>(3) );
        list.insert(new ListLink<Integer>(4) );
        System.out.println(list.toString());
        list.reverse();
        System.out.println(list.toString());

    }
}

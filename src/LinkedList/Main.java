package LinkedList;

public class Main {
    public static void main(String[] args) {
        LL list = new LL();
        list.insertFirst(3);
        list.insertFirst(2);
        list.insertFirst(8);
        list.insertFirst(7);
        list.insertLast(10);
        list.insert(100, 3);
        list.insertRec(50, 3);
        list.display();
//        System.out.println(list.deleteFirst());
//        list.display();
//        System.out.println(list.deleteLast());
//        list.display();
//        System.out.println(list.delete(2));
//        list.display();
//        list.find(5);

//        DLL list = new DLL();
//        list.insertFirst(3);
//        list.insertFirst(2);
//        list.insertFirst(8);
//        list.insertFirst(7);
//        list.insertLast(10);
//        list.insert(3, 20);
//        list.display();

//        CLL list = new CLL();
//        list.insert(3);
//        list.insert(2);
//        list.insert(8);
//        list.insert(7);
//        list.insert(12);
//        list.insert(15);
//        list.delete(7);
//        list.display();
    }
}

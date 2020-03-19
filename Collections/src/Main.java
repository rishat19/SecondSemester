import java.util.ArrayList;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> myList1 = new ArrayList<>();
        myList1.add("A");
        myList1.add("B");
        myList1.add("C");
        myList1.add("C");

        ArrayList<String> myList2 = new ArrayList<>();
        myList2.add("C");
        myList2.add("B");
        myList2.add("C");
        myList2.add("A");

        ArrayList<String> myList3 = new ArrayList<>();
        myList3.add("A");
        myList3.add("B");
        myList3.add("C");
        myList3.add("A");

        SimpleUnmodifiableCollection<String> collection1 = new SimpleUnmodifiableCollection<>(myList1);
        System.out.println(collection1.size());
        for (String s : collection1) {
            System.out.println(s);
        }

        SimpleUnmodifiableCollection<String> collection2 = new SimpleUnmodifiableCollection<>();
        for (String s : collection2) {
            System.out.println(s);
        }

        SimpleUnmodifiableCollection<String> collection3 = new SimpleUnmodifiableCollection<>(myList2);
        System.out.println(collection1.equals(collection3));

        SimpleUnmodifiableCollection<String> collection4 = new SimpleUnmodifiableCollection<>(myList3);
        System.out.println(collection1.equals(collection4));

        SimpleModifiableCollection<String> collection5 = new SimpleModifiableCollection<>(myList1);
        System.out.println(collection5);
        collection5.add("D");
        collection5.add("E");
        collection5.add("E");
        collection5.add("F");
        collection5.add("G");
        System.out.println(collection5);
        Iterator<String> it = collection5.iterator();
        it.next();
        it.next();
        it.remove();
        System.out.println(collection5);
        it.next();
        it.remove();
        System.out.println(collection5);
    }
}

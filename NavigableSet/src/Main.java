import java.util.ArrayList;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> myList1 = new ArrayList<>();
        myList1.add("Ball");
        myList1.add("Cat");
        myList1.add("Apple");
        myList1.add("Apple");
        myList1.add("Apple");
        myList1.add("Ball");
        myList1.add("Cat");
        myList1.add("Cat");
        myList1.add("Ball");
        myList1.add("Apple");
        //NavSet<String> myNavSet = new NavSet<>(myList1, String::compareTo);
        NavSet<String> myNavSet = new NavSet<>(myList1);
        //NavSet<String> myNavSet = new NavSet<>();
        //NavSet<String> myNavSet = new NavSet<>(String::compareTo);
        //myNavSet.addAll(myList1);

        for (String s : myNavSet) {
            System.out.println(s);
        }
        System.out.println();

        System.out.println(myNavSet.size());
        System.out.println();

        System.out.println(myNavSet.lower("Ap"));
        System.out.println(myNavSet.lower("Ball"));
        System.out.println(myNavSet.lower("Cc"));
        System.out.println();

        System.out.println(myNavSet.floor("Ap"));
        System.out.println(myNavSet.floor("Ball"));
        System.out.println(myNavSet.floor("Cc"));
        System.out.println();

        System.out.println(myNavSet.ceiling("Ap"));
        System.out.println(myNavSet.ceiling("Ball"));
        System.out.println(myNavSet.ceiling("Cc"));
        System.out.println();

        System.out.println(myNavSet.higher("Ap"));
        System.out.println(myNavSet.higher("Ball"));
        System.out.println(myNavSet.higher("Cc"));
        System.out.println();

        System.out.println(myNavSet.pollFirst());
        System.out.println(myNavSet);
        System.out.println(myNavSet.size());
        System.out.println();

        System.out.println(myNavSet.add("Door"));
        System.out.println(myNavSet.add("Bird"));
        System.out.println(myNavSet.add("Apple"));
        System.out.println(myNavSet.add("Cat"));
        System.out.println(myNavSet);
        System.out.println(myNavSet.size());
        System.out.println();

        ArrayList<String> myList2 = new ArrayList<>();
        myList2.add("Elephant");
        myList2.add("Dog");
        myList2.add("Coach");
        System.out.println(myNavSet.addAll(myList2));
        System.out.println(myNavSet);
        System.out.println(myNavSet.addAll(myList2));
        System.out.println();

        System.out.println(myNavSet.remove("Coach"));
        System.out.println(myNavSet);
        System.out.println(myNavSet.remove("A"));
        System.out.println();

        System.out.println(myNavSet.pollFirst());
        System.out.println(myNavSet);
        System.out.println(myNavSet.pollLast());
        System.out.println(myNavSet);
        System.out.println();

        Iterator<String> i = myNavSet.descendingIterator();
        if (i.hasNext()) {
            i.next();
        }
        if (i.hasNext()) {
            i.next();
            i.remove();
        }
        if (i.hasNext()) {
            System.out.println(i.next());
        }
        System.out.println(myNavSet);
        System.out.println();

        System.out.println(myNavSet.descendingSet());
        System.out.println();

        System.out.println(myNavSet);
        System.out.println();
        System.out.println(myNavSet.subSet("Bird", true, "Cat", true));
        System.out.println(myNavSet.subSet("Bi", true, "Cct", true));
        System.out.println(myNavSet.subSet("Ba", true, "Dt", true));
        System.out.println(myNavSet.subSet("Cat", true, "Cat", true));
        System.out.println();
        System.out.println(myNavSet.subSet("Bird", false, "Cat", true));
        System.out.println(myNavSet.subSet("Bi", false, "Cct", true));
        System.out.println(myNavSet.subSet("Ba", false, "Dt", true));
        System.out.println(myNavSet.subSet("Cat", false, "Cat", true));
        System.out.println();
        System.out.println(myNavSet.subSet("Bird", true, "Cat", false));
        System.out.println(myNavSet.subSet("Bi", true, "Cct", false));
        System.out.println(myNavSet.subSet("Ba", true, "Dt", false));
        System.out.println(myNavSet.subSet("Cat", true, "Cat", false));
        System.out.println();
        System.out.println(myNavSet.subSet("Bird", false, "Cat", false));
        System.out.println(myNavSet.subSet("Bi", false, "Cct", false));
        System.out.println(myNavSet.subSet("Ba", false, "Dt", false));
        System.out.println(myNavSet.subSet("Cat", false, "Cat", false));
        System.out.println();

        System.out.println(myNavSet.headSet("Cat", true));
        System.out.println(myNavSet.headSet("Cat", false));
        System.out.println(myNavSet.headSet("Ball", true));
        System.out.println(myNavSet.headSet("Ball", false));
        System.out.println();

        System.out.println(myNavSet.tailSet("Bird", true));
        System.out.println(myNavSet.tailSet("Bird", false));
        System.out.println(myNavSet.tailSet("Door", true));
        System.out.println(myNavSet.tailSet("Door", false));
        System.out.println();

        System.out.println(myNavSet.first());
        System.out.println(myNavSet.last());
        System.out.println();
    }
}

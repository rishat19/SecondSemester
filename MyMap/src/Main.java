public class Main {
    public static void main(String[] args) {
        MyMap<Integer, String> m = new MyMap<>();
        System.out.println(m.put(5, "five"));
        System.out.println(m.put(3, "three"));
        System.out.println(m.put(11, "two"));
        System.out.println(m.put(29, "nine"));
        System.out.println(m.put(11, "one"));
        System.out.println(m.put(43, "three"));
        System.out.println(m.toString());

        MyMap<Integer, String> m2 = new MyMap<>(m);
        System.out.println(m2.toString());

        System.out.println(m.size());
        System.out.println(m.isEmpty());
        System.out.println();

        System.out.println(m.containsValue("two"));
        System.out.println(m.containsValue("three"));
        System.out.println(m.containsKey(7));
        System.out.println(m.containsKey(11));
        System.out.println(m.get(7));
        System.out.println(m.get(11));
        System.out.println();

        System.out.println(m.remove(7));
        System.out.println(m.remove(29));
        System.out.println(m.toString());

        m2.clear();
        m2.put(34, "four");
        m2.put(35, "five");
        m2.put(36, "six");
        m2.put(37, "seven");
        m.putAll(m2);
        System.out.println(m.toString());

        System.out.println(m.keySet());
        System.out.println(m.values());
        System.out.println();

        System.out.println(m.putIfAbsent(3, "3"));
        System.out.println(m.putIfAbsent(8, "eight"));
        System.out.println(m.toString());
    }
}

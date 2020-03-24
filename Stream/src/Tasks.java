import java.util.*;
import java.util.stream.Collectors;

public class Tasks {

    public static void main(String[] args) {
        System.out.println("Test 1:");
        useComparatorAsLambdaExpression();
        System.out.println("Test 2:");
        printListItemsLargerThanLargestItemInAnotherList();
        System.out.println("Test 3:");
        countNumberOfStringsWithMoreThanThreeVowels();
        System.out.println("Test 4:");
        concatenateAllMapKeys();
        System.out.println("Test 5:");
        sumCollectionStringLengthsLongerThanFiveCharacters();
    }

    public static void useComparatorAsLambdaExpression() {
        Comparator<String> myStringComparator = (firstString, secondString) -> {
            int result = Integer.compare(firstString.length(), secondString.length());
            if (result == 0) {
                result = firstString.compareTo(secondString);
            }
            return result;
        };
        //Also we can write like this:
        //Comparator<String> myStringComparator =
        //                      Comparator.comparingInt(String::length).thenComparing(firstString -> firstString);
        String[] array = new String[] {"ball", "apple", "cat", "food", "hat", "door", "element", "g"};
        Arrays.sort(array, myStringComparator);
        System.out.println(Arrays.toString(array));
    }

    public static void printListItemsLargerThanLargestItemInAnotherList() {
        List<Integer> firstList = Arrays.asList(3, 4, 5, 6, 7);
        List<Integer> secondList = Arrays.asList(1, 2, 3, 4, 5);
        int max = secondList.stream().max(Integer::compareTo).get();
        firstList.stream()
                .filter(element -> element.compareTo(max) > 0)
                .forEach(System.out::println);
    }

    public static void countNumberOfStringsWithMoreThanThreeVowels() {
        Set<String> set = new HashSet<>(Arrays.asList("Ball", "Apple", "cAAAt", "foOOod", "hat", "dooOor", "ElemEent"));
        Set<String> vowels = new HashSet<>(Arrays.asList("a", "e", "i", "o", "u", "y"));
        long count = set.stream()
                .filter(str -> Arrays.stream(str.toLowerCase().split(""))
                .filter(vowels::contains).count() > 3)
                .count();
        System.out.println(count);
    }

    public static void concatenateAllMapKeys() {
        Map<Integer, String> map = new HashMap<>();
        map.put(19, "Apple");
        map.put(30, "Ball");
        map.put(11, "Cat");
        map.put(3, "Door");
        String result = map.keySet().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
        System.out.println(result);
    }

    public static void sumCollectionStringLengthsLongerThanFiveCharacters () {
        Collection<String> collection = Arrays.asList("January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December");
        int sum = collection.stream()
                .mapToInt(String::length)
                .filter(length -> length > 5)
                .sum();
        System.out.println(sum);
    }
}

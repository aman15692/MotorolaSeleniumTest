import java.util.*;
import java.util.stream.Collectors;

public class removeDuplicateEntry {

    public static void main(String[] arg) {
        List<Integer> ActualList = new ArrayList<>(Arrays.asList(1, 10, 1, 2, 2, 3, 10, 3, 3, 4, 5, 5));
        System.out.println("Actual List with duplicates values: " + ActualList);
        List<Integer> finalList = ActualList.stream().distinct().collect(Collectors.toList());
        System.out.println("new List without duplicates values: " + finalList);

        ArrayList<String> list = new ArrayList<>(Arrays.asList("G", "e", "e", "k", "s"));
        System.out.println("List with duplicates elements: " + list);
        ArrayList<String> newList = removeDuplicateValues(list);
        System.out.println("List without duplicates elements: " + newList);

    }

    public static <String> ArrayList<String> removeDuplicateValues(ArrayList<String> list) {

        Set<String> set = new LinkedHashSet<>();
        set.addAll(list);
        list.clear();
        list.addAll(set);
        return list;
    }

}
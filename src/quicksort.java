import java.util.ArrayList;

public class quicksort {
    //quicksort arraylist string input
    public static void sort(ArrayList<String> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);
            sort(list, low, pi - 1);
            sort(list, pi + 1, high);
        }
    }
    //partition arraylist string input for quicksort
    public static int partition(ArrayList<String> list, int low, int high) {
        String pivot = list.get(high);
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (list.get(j).compareTo(pivot) < 0) {
                i++;
                String temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }
        String temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);
        return i + 1;
    }
    
}

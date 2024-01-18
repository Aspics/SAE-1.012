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

    private ArrayList<String> sort2(ArrayList<String> ar, int lo, int hi){
        if (lo < hi){
            int splitPoint = partition2(ar, lo, hi);
            sort2(ar, lo, splitPoint);
            sort2(ar, splitPoint +1, hi);
        }
        return ar;
    }

    private int partition2(ArrayList<String> ar, int lo, int hi){
        String pivot = ar.get(lo);
        lo--;
        hi++;
        while (true){
            lo++;
            while (lo<hi && ar.get(lo).compareTo(pivot) < 0){
                lo++;
            }
            hi--;
            while (hi>lo && ar.get(hi).compareTo(pivot) >= 0){
                hi--;
            }
            if (lo<hi){
                swap(ar, lo, hi);
            }else {
                return hi;
            }
        }
    }

    private ArrayList<String> swap(ArrayList<String> ar, int a, int b){
        String temp = ar.get(a);
        ar.set(a, ar.get(b));
        ar.set(b, temp);
        return ar;
    }
    
}

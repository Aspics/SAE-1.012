import java.util.ArrayList;

public class quicksort {
    //quicksort arraylist string input
    public static void sort(ArrayList<PaireChaineEntier> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);
            sort(list, low, pi - 1);
            sort(list, pi + 1, high);
        }
    }
    //partition arraylist string input for quicksort
    public static int partition(ArrayList<PaireChaineEntier> list, int low, int high) {
        PaireChaineEntier pivot = list.get(high);
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (list.get(j).getChaine().compareTo(pivot.getChaine()) < 0) {
                i++;
                PaireChaineEntier temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }
        PaireChaineEntier temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);
        return i + 1;
    }

    public static ArrayList<PaireChaineEntier> sort2(ArrayList<PaireChaineEntier> ar, int lo, int hi){
        if (lo < hi){
            int splitPoint = partition2(ar, lo, hi);
            sort2(ar, lo, splitPoint);
            sort2(ar, splitPoint +1, hi);
        }
        return ar;
    }

    private static int partition2(ArrayList<PaireChaineEntier> ar, int lo, int hi){
        String pivot = ar.get(lo).getChaine();
        lo--;
        hi++;
        while (true){
            lo++;
            while (lo<hi && ar.get(lo).getChaine().compareTo(pivot) < 0){
                lo++;
            }
            hi--;
            while (hi>lo && ar.get(hi).getChaine().compareTo(pivot) >= 0){
                hi--;
            }
            if (lo<hi){
                swap(ar, lo, hi);
            }else {
                return hi;
            }
        }
    }

    private static ArrayList<PaireChaineEntier> swap(ArrayList<PaireChaineEntier> ar, int a, int b){
        PaireChaineEntier temp = ar.get(a);
        ar.set(a, ar.get(b));
        ar.set(b, temp);
        return ar;
    }
    
}

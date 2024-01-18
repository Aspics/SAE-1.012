import java.util.ArrayList;

public class quicksort {

    public static ArrayList<PaireChaineEntier> sort(ArrayList<PaireChaineEntier> ar, int lo, int hi){
        if (lo < hi){
            int splitPoint = partition(ar, lo, hi);
            sort(ar, lo, splitPoint);
            sort(ar, splitPoint +1, hi);
        }
        return ar;
    }

    private static int partition(ArrayList<PaireChaineEntier> ar, int lo, int hi){
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

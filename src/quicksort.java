import java.util.ArrayList;

public class quicksort {



    /**
     * Sorts an ArrayList of PaireChaineEntier objects using the quicksort algorithm.
     * 
     * @param ar The ArrayList to be sorted.
     * @param lo The starting index of the range to be sorted.
     * @param hi The ending index of the range to be sorted.
     * @return The sorted ArrayList.
     */
    public static ArrayList<PaireChaineEntier> sort(ArrayList<PaireChaineEntier> ar, int lo, int hi){
        if (lo < hi){
            int splitPoint = partition(ar, lo, hi);
            sort(ar, lo, splitPoint);
            sort(ar, splitPoint +1, hi);
        }
        return ar;
    }

    /**
     * Partitions the given ArrayList of PaireChaineEntier objects based on a pivot element.
     * The elements smaller than the pivot are moved to the left side, and the elements greater than or equal to the pivot are moved to the right side.
     *
     * @param ar The ArrayList of PaireChaineEntier objects to be partitioned.
     * @param lo The lower index of the partition range.
     * @param hi The higher index of the partition range.
     * @return The index of the pivot element after partitioning.
     */
    private static int partition(ArrayList<PaireChaineEntier> ar, int lo, int hi){
        String pivot = ar.get(lo).getChaine();
        lo--;
        hi++;
        while (true){
            lo++;
            // find the first element greater than or equal to the pivot
            while (lo<hi && ar.get(lo).getChaine().compareTo(pivot) < 0){
                lo++;
            }
            hi--;
            // find the first element smaller than the pivot
            while (hi>lo && ar.get(hi).getChaine().compareTo(pivot) >= 0){
                hi--;
            }
            // swap the two elements
            if (lo<hi){
                swap(ar, lo, hi);
            }else {
                return hi;
            }
        }
    }

    /**
     * Swaps two elements in an ArrayList.
     * 
     * @param ar the ArrayList in which the elements are to be swapped
     * @param a the index of the first element to be swapped
     * @param b the index of the second element to be swapped
     * @return the ArrayList with the swapped elements
     */
    private static ArrayList<PaireChaineEntier> swap(ArrayList<PaireChaineEntier> ar, int a, int b){
        PaireChaineEntier temp = ar.get(a);
        ar.set(a, ar.get(b));
        ar.set(b, temp);
        return ar;
    }
    
}

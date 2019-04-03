import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

class IncrementTask extends RecursiveAction {
    final int THRESHOLD = 10;
    final long[] array; 
    final int lo; 
    final int hi;
    
    IncrementTask(long[] array, int lo, int hi) {
      this.array = array;
      this.lo = lo; 
      this.hi = hi;
    }

    @Override
    protected void compute() {
      if (hi - lo < THRESHOLD) {
        for (int i = lo; i < hi; ++i)
          array[i]++;
      }
      else {
        int mid = (lo + hi) >>> 1;
        invokeAll(new IncrementTask(array, lo, mid),
                  new IncrementTask(array, mid, hi));
      }
    }

    public long[] getArr(){
      return this.array;
    }
  }
 

public class Increment {

    public static void main(String[] args) {
        long [] arr = new long[]{1,2,3,4,5};
        int low = 0;
        int high = arr.length -1;
        System.out.printf("Before Increment : %s \n" , Arrays.toString(arr));
        IncrementTask i = new IncrementTask(arr, low, high);
        System.out.printf("After Increment : %s \n" , Arrays.toString(arr));
        

    }
}
import java.util.*;

class Compute{

    public static double seqArraySum(double[] X){
        long startTime = System.nanoTime();
        double sum1 = 0;
        double sum2 = 0;

        for(int i = 0 ; i < X.length /2 ;i++){
            sum1 += 1 / X[i];
        }

        for (int i = X.length /2 ; i < X.length ;i++){
            sum2 += 1 / X[i];
        }

        double sum = sum1 + sum2;

        long timeInNanos = System.nanoTime() - startTime;

        printResults("seqArraySum", timeInNanos , sum);

        return sum;
    }

    public static double parArraySum(double[] X){
        long startTime = System.nanoTime();
        double sum1 = 0;
        double sum2 = 0;

        async(()->{
        for(int i = 0 ; i < X.length /2 ;i++){
            sum1 += 1 / X[i];
        }});

        for (int i = X.length /2 ; i < X.length ;i++){
            sum2 += 1 / X[i];
        }

        double sum = sum1 + sum2;

        long timeInNanos = System.nanoTime() - startTime;

        printResults("seqArraySum", timeInNanos , sum);

        return sum;
    }


    private static void printResults(String name , long timeInNanos , double sum){
        System.out.printf(" %s Completed in %8.3f milliseconds, with sum = %8.5f\n",name,timeInNanos/1e6 ,sum);
    }

    

}



public class ReciprocalArraySumAsyncFinish{
    public static void main(String[] args) {

        double [] result = buildArray(50000);
        seqArraySum(result);
        parArraySum(result);
    }

    public static double [] buildArray(long number){
        Double [] arr = new double[number];

        for (int i = 0; i < number ; i++){
            arr[i] = i * Math.random();
        }

        return arr;
    }
    
}
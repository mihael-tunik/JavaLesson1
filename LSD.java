package LSD;
/**
 *
 * @author Mihael Tunik
 */
import java.util.Arrays;
import java.util.Random;

public class LSD {

 private static final int SIZE  = 1000000, RANGE = 1000000, TIMES = 10, BASE = 128;
 private static Random random = new Random();
 private static int[]C = new int[RANGE];
 private static int[]B = new int[SIZE];
 
 public static void main(String[] args) {
     
   int [] data  = generate(), data2 = data.clone(); 
   long start, stop, s;
   
   s = 0;
   for(int j = 0; j < TIMES; ++j)
   {
    start = System.nanoTime();
    java.util.Arrays.sort(data);
    stop = System.nanoTime();
    s += (stop - start);
   }
   
   System.out.println("Elapsed = " + s/TIMES);
   
   s = 0;
   for(int j = 0; j < TIMES; ++j)
   {
    start = System.nanoTime();
    LSDsort(data2);
    stop = System.nanoTime();
    s += (stop - start);
   }
   
   
   System.out.println("Elapsed = " + s/TIMES);
 }

 private static void LSDsort(int[] data) 
 {
     for(int i = 0; i < 3; ++i)
       CSort(data, i);
 }
 
 private static int  get_d(int INT_NUM, int byte_pos)
 {
    for(int i = 0; i < byte_pos; ++i)
        INT_NUM /= BASE;
    return INT_NUM % BASE;
 }
 
 private static void CSort(int[] data, int d_pos) 
 {
    int[]C = new int[RANGE];
    int[]B = new int[data.length];
    
    Arrays.fill(C, 0);        
    for(int j = 0; j < data.length; ++j)
        ++C[get_d(data[j], d_pos)];    
    for(int k = 1; k < RANGE; ++k)
        C[k] += C[k - 1];
    for(int j = (data.length - 1); j >= 0; --j)
    {
        B[C[get_d(data[j], d_pos)] - 1] = data[j];
        --C[get_d(data[j], d_pos)]; 
    }
    System.arraycopy(B, 0, data, 0, SIZE);
 }
 
 private static int[] generate() {
    int[] data = new int[SIZE];
    for (int k = 0; k < data.length; k++) 
      data[k] = random.nextInt(RANGE - 1);
    return data;
 }
 
 private static void showdata(int[] data)
 {
     for(int i = 0; i < SIZE; ++i)
         System.out.println(data[i]);
 }
}


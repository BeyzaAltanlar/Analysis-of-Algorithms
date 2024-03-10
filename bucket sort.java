package hw2;



import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.util.Comparator;
import java.util.List;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;
import java.util.LinkedList;


public class Main3 {

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskA solver = new TaskA();
        solver.solve(in, out);
        out.close();
    }
 
    static class TaskA { 
       
    	

    	public static double[] bucketSort(double[] array) {
    	    // Bucket olusturma
    	    int numberOfBuckets = array.length;
    	    List<List<Double>> buckets = new ArrayList<>(numberOfBuckets);
    	    for (int i = 0; i < numberOfBuckets; i++) {
    	        buckets.add(new ArrayList<>());
    	    }

    	    // Max ve Min bul
    	    double max = array[0];
    	    double min = array[0];
    	    for (double num : array) {
    	        max = Math.max(max, num);
    	        min = Math.min(min, num);
    	    }

    	    // Elemanları bucketlara dagit
    	    for (double num : array) {
    	        int bucketIndex = (int) ((num - min) / (max - min) * (numberOfBuckets - 1));
    	        buckets.get(bucketIndex).add(num); // bucketlara ekle
    	    }

    	    int index = 0;

    	    for (List<Double> bucket : buckets) {
    	        if (!bucket.isEmpty()) {
    	            // Her bir bucketi insertionSort kullanarak sirala
    	            insertionSort(bucket);

    	            // Siralanmis bucketlari ana diziye yerlestir
    	            for (double num : bucket) {
    	                array[index++] = num;
    	            }
    	        }
    	    }

    	    return array;
    	}
    	//insertion ile sirala bucketlar icin
    	private static void insertionSort(List<Double> bucket) {
    	    int n = bucket.size();
    	    for (int i = 1; i < n; ++i) {
    	        double key = bucket.get(i);
    	        int j = i - 1;

    	        // Buyuk olanlari kaydr
    	        while (j >= 0 && bucket.get(j) > key) {
    	            bucket.set(j + 1, bucket.get(j));
    	            j = j - 1;
    	        }

    	       
    	        bucket.set(j + 1, key);
    	    }
    	}

    	
        public void solve(InputReader in, PrintWriter out) {
            int t = in.nextInt();
            for (int testCase = 0 ; testCase < t ; testCase++) {
                int n = in.nextInt();
                double[] array = new double[n];
                for (int i = 0 ; i < n ; i++)
                    array[i] = in.nextDouble();
                array = bucketSort(array);
                for (int i = 0 ; i < n ; i++) {
                    out.print(array[i]);
                    out.print(' ');
                }
                out.println();
            }
        }
    }
 
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;
 
        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }
 
        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }
 
        public int nextInt() {
            return Integer.parseInt(next());
        }
 
        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }
 

    }
}
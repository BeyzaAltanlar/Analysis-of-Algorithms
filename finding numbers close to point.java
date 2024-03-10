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
import java.io.InputStream;
import java.util.Arrays;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class Main4 {

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
    	
    	public int[] findNumbers(int n, int k, int l, int[] arr) {
    		
    		int kthOrderStatistic = quickSelect(arr, 0, n - 1, k - 1);

 	        //her elemanin kth ile farkini bul 
 	        int[][] diffArr = new int[n][2]; 
 	        for (int i = 0; i < n; i++) {
 	            diffArr[i][0] = Math.abs(arr[i] - kthOrderStatistic);
 	            diffArr[i][1] = arr[i];
 	        }
 	        //farklari sirala
 	        Arrays.sort(diffArr, (a, b) -> Integer.compare(a[0], b[0]));

 	        //en yakin l sayi 
 	        int[] result = new int[l];
 	        for (int i = 0; i < l; i++) {
 	            result[i] = diffArr[i][1];
 	        }

 	        return result;
 	    }

 	    private int partition(int[] arr, int low, int high) {
 	    	
 	        int pivot = arr[high]; //pivot seciyorz rastgele 
 	        int i = low - 1;

 	        for (int j = low; j < high; j++) { // pivottan kucuklri sola
 	            if (arr[j] <= pivot) {
 	                i++;
 	                
 	                int temp = arr[i]; //swapping 
 	                arr[i] = arr[j];
 	                arr[j] = temp;
 	            }
 	        }

 	        int temp = arr[i + 1];
 	        arr[i + 1] = arr[high];
 	        arr[high] = temp;

 	        return i + 1; //pivot idnex 
 	    }

 	    private int quickSelect(int[] arr, int low, int high, int k) {
 	    	
 	        if (low < high) {
 	            int pivotIndex = partition(arr, low, high); 

 	            if (pivotIndex == k) { 
 	                return arr[pivotIndex]; 
 	            } else if (pivotIndex < k) { //pivot k den kucukse saga bak
 	                return quickSelect(arr, pivotIndex + 1, high, k);
 	            } else { //pivot k den buyukse sola bak
 	                return quickSelect(arr, low, pivotIndex - 1, k);
 	            }
 	        }

 	        return arr[low];
 	    }
 	
 
        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int k = in.nextInt();
            int l = in.nextInt();
            int[] arr = new int[n];
            for (int i = 0 ; i < n ; i++)
                arr[i] = in.nextInt();

            int []result;
            result = findNumbers(n,k,l,arr);
            
            for (int i = 0 ; i < l ; i++) {
                System.out.print(result[i]);
                System.out.print(" ");
            }
            System.out.println();
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
 

    }
}
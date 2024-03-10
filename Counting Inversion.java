 
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.*;
 
public class Q1 {
 
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
    	
	    public static long countingInversions(int[] arr) {
	        int n = arr.length;
	        int[] temp = new int[n];
	        return mergeSortAndCount(arr, temp, 0, n - 1);
	    }
	
	    private static long mergeSortAndCount(int[] arr, int[] temp, int left, int right) {
	        long count = 0;
	
	        if (left < right) {
	            int mid = (left + right) / 2;
	
	            count += mergeSortAndCount(arr, temp, left, mid);
	            count += mergeSortAndCount(arr, temp, mid + 1, right);
	            count += mergeAndCount(arr, temp, left, mid, right);
	        }
	
	        return count;
	    }
	
	    private static long mergeAndCount(int[] arr, int[] temp, int left, int mid, int right) {
	        long count = 0;
	
	        int i = left;
	        int j = mid + 1;
	        int k = left;
	
	        while (i <= mid && j <= right) {
	            if (arr[i] <= arr[j]) {
	                temp[k++] = arr[i++];
	            } else {
	                temp[k++] = arr[j++];
	                count += (mid - i + 1);
	            }
	        }
	
	        while (i <= mid) {
	            temp[k++] = arr[i++];
	        }
	
	        while (j <= right) {
	            temp[k++] = arr[j++];
	        }
	
	        System.arraycopy(temp, left, arr, left, right - left + 1);
	
	        return count;
	    }
	    
	   
    
     public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
 
            int [] arr = new int [n];
 
            for(int i = 0 ; i < n ; i++)
                arr[i] = in.nextInt();
 
            System.out.println(countingInversions(arr));
 
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
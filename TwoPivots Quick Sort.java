
import java.io.*;
import java.util.StringTokenizer;
 
public class Soru2 {
 
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
 
        public static double[] quickSort(double[] arr) {
            TwoPivotQuicksort(arr, 0, arr.length - 1);
            return arr;
        }
 
        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
 
            double[] arr = new double[n];
 
            for (int i = 0; i < n; i++)
                arr[i] = in.nextDouble();
 
            double[] result = quickSort(arr);
 
            for (int i = 0; i < result.length; i++) {
                System.out.print(result[i]);
                if (i == result.length - 1)
                    System.out.print("\n");
                else
                    System.out.print(" ");
            }
        }
 
        private static void TwoPivotQuicksort(double[] arr, int low, int high) {
            if (low >= high) {
                return;
            }
            int[] pivotIndices = partition(arr, low, high);
            TwoPivotQuicksort(arr, low, pivotIndices[0] - 1);
            TwoPivotQuicksort(arr, pivotIndices[0] + 1, pivotIndices[1] - 1);
            TwoPivotQuicksort(arr, pivotIndices[1] + 1, high);
        }
 
        private static int[] partition(double[] arr, int low, int high) {
            if (arr[low] > arr[high]) {
                swap(arr, low, high);
            }
 
            double pivot1 = arr[low];
            double pivot2 = arr[high];
            int leftPivotIndex = low + 1;
            int rightPivotIndex = high - 1;
            
            int i = low + 1;
            
            while (i <= rightPivotIndex) {
                if (arr[i] < pivot1) {
                    swap(arr, i++, leftPivotIndex++);
                } else if (arr[i] > pivot2) {
                    swap(arr, i, rightPivotIndex--);
                } else {
                    i++;
                }
            }
 
            swap(arr, low, --leftPivotIndex);
            swap(arr, high, ++rightPivotIndex);
 
            return new int[]{leftPivotIndex, rightPivotIndex};
        }
 
        private static void swap(double[] arr, int i, int j) {
	        double temp = arr[i];
	        arr[i] = arr[j];
	        arr[j] = temp;
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
 
        public double nextDouble() {
            return Double.parseDouble(next());
        }
 
        public long nextLong() {
            return Long.parseLong(next());
        }
    }
}
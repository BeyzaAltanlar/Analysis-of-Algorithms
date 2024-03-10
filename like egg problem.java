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
public class Main2 {


 
    static class TaskQ2 {

        static InputReader _in;
        
        public static int[] findAltitude(int maxAltitude) {
        	int n=2;
        	int k=maxAltitude;
        	int[][] dp = new int[n+1][k+1];
            
            
            for (int i = 1; i <= n; i++) {
                dp[i][0] = 0;
                dp[i][1] = 1;
            }
            
            
            for (int j = 1; j <= k; j++) {
                dp[1][j] = j;
            }
            
            // fill rest of the table
            int tmp;
            for (int i = 2; i <= n; i++) {
                for (int j = 2; j <= k; j++) {
                    dp[i][j] = Integer.MAX_VALUE;
                    for (int x = 1; x <= j; x++) {
                        tmp = 1 + Math.max(dp[i-1][x-1], dp[i][j-x]);
                        dp[i][j] = Math.min(dp[i][j], tmp);
                    }
                }
            }
            //int minExperiments=dp[n][k];

            return new int[]{dp[n][k],k};
        }

        
        	

        public static boolean isSurvived(int altitude) {
            System.out.println(1 + " " + altitude);
            System.out.flush();
            return _in.nextInt() == 0;
        }

        public static void checkAltitude(int altitude) {
            System.out.println(2 + " " + altitude);
            System.out.flush();
        }

       

        public void solve(InputReader in, PrintWriter out) {
            _in = in;
            int n = in.nextInt();
            int[] result;
            result = findAltitude(n);
            checkAltitude(result[0]);
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

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskQ2 solver = new TaskQ2();
        solver.solve(in, out);
        out.close();
    }
}
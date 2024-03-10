package odev3;

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
import java.util.Collections;
import java.util.ArrayList;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class Main2 {

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
    	
    	 public static double letsGetRich(double[] p, int k) {
    		 
 	        int n = p.length; // p[i ] denotes the price per share for the stock on the i. th day. i=0'dan n' e gun sayisi. yani pnin uzunlugu n 

 	        if (n < 2 || k == 0) { //hic islem yapmayacaksa veya n<2 , kar edemez
 	            return 0; 
 	        }

 	        if (k >= n / 2) {  //kari hesapla
 	           return calcProfitForConsecutiveDays(p) * 1000;
 	        }

 	        
 	        double[][] dp = new double[n][k + 1];

 	        for (int j = 1; j <= k; j++) {
 	            double bestProfit = -p[0]; //bestprofit herhangi bir gun icin en iyi kar durumu
 	            for (int i = 1; i < n; i++) {
 	            	//dp[i][j], i. gun ve en fazla j islem olan durumun maksimum kari
 	                dp[i][j] = Math.max(dp[i - 1][j], p[i] + bestProfit); // bi onceki gun ve o gunun karini (p[i] + bestProfit) karsilastir.
 	                bestProfit = Math.max(bestProfit, dp[i][j - 1] - p[i]); //  bi onceki gunun fiyat farkinin en iyisini (dp[i][j - 1] - p[i]) ve o gune kadar olan en iyi kari (bestProfit) karsilastir 
 	            }
 	        }

 	        return dp[n - 1][k] * 1000; // En fazla k islemle yapilan max karin degeri dondr
 	   
    	 }
    	 
    	// ardisik gunlerdeki islemlerin karini hesapla.
    	 private static double calcProfitForConsecutiveDays(double[] p) {
    		 
    		    double maxProfit = 0;

    		    for (int i = 1; i < p.length; i++) {
    		        if (p[i] > p[i - 1]) {
    		            maxProfit += p[i] - p[i - 1];
    		        }
    		    }

    		    return maxProfit;
    		}

 
        	   
        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int k = in.nextInt();
            double[] p = new double[n];
            for (int i = 0 ; i < n ; i++)
                p[i] = in.nextDouble();
            System.out.println(letsGetRich(p, k)); 
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
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
public class Main1 {

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
    	
        public static double bookPlacement(double[] thickness,double L) {
        	int n = thickness.length;
	        double[] dp = new double[n + 1]; //dinamik programlama
	        Arrays.fill(dp, Double.MAX_VALUE); // diziyi doldur 
	        dp[0] = 0;

	        for (int i = 1; i <= n; i++) {
	            double shelfWidth = 0; 
	            for (int j = i; j > 0; j--) { //kitaplari yerlestir 
	            	shelfWidth += thickness[j - 1];  //raf genisligine kitap kalinliklarini ekleyerek guncelle 
	                if (shelfWidth <= L) { //raf uzunlugu bitmediyse devam et
	                    dp[i] = Math.min(dp[i], Math.max(L - shelfWidth, dp[j - 1])); //maksimum kullanilmayan alani hesapla  
	                }
	            }
	        }
	        //L-shelfwidth=rafin kalan boslugu 
	        return dp[n];//en iyi duzen , minimum kullanilmayan alan
        }

        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            double L = in.nextDouble();
            double[] thickness = new double[n];
            for (int i = 0 ; i < n ; i++)
                thickness[i] = in.nextDouble();
            System.out.println(bookPlacement(thickness, L)); 
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

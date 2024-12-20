

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
import java.util.*;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class Soru3 {

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

	    public int findSink(int[][] adj)  {
	    	 
	    	int n = adj.length;
	        int candidate = 0;
	        
	        // her bir vertexi tek tek kontrol et
	        for (int i = 0; i < n; i++) {
	            if (adj[candidate][i] == 1) { //disa dogru kenarı varsa olamaz  
	                candidate = i; //bi sonraki dugume guncelle 
	            }
	        }
	        
	        for (int i = 0; i < n; i++) {
	            if (i != candidate && (adj[candidate][i] == 1 || adj[i][candidate] == 0)) {  //disa dogru bi kenar varsa veya herhangi bir dugume cikis kenari yoksa  
	                return -1; // global sink yok 
	            }
	        }
	        
	        return candidate; // global sink 
	        // Disa dogru bir kenari yoksa ve diger dugumlere bagliysa, global sink.
	     
    	}

        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int adj[][] = new int[n][n];
            for (int i = 0 ; i < n ; i++) {
		        for (int j = 0; j < n ; j++)
	        		adj[i][j] = in.nextInt();
	    	}
	        int result = findSink(adj);
	        System.out.println(result);
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
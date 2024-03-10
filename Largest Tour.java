package odev3;

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import java.io.BufferedReader;
import java.util.*;

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

    	public int largestTour(ArrayList<ArrayList<Integer>> adjList) { 
            int n = adjList.size(); 
            int[] dp = new int[n]; //her node icin en uzun path tutacak 
            boolean[] visited = new boolean[n];

            for (int i = 0; i < n; i++) { // her node u ziyaret edip 
                if (!visited[i]) {
                    dfs(i, adjList, dp, visited); //dfs le en uzun yollari hesapla
                }
            }

            int maxPathLength = 0;
            for (int length : dp) { //dp de tutulan en buyuk deger totalde en uzun pathi verir 
                maxPathLength = Math.max(maxPathLength, length);
            }

            return maxPathLength; //graf icindeki en uzun yolun uzunlugunu don
        }
    	
    	//DFS i longest path icin kullanalim
        private void dfs(int node, ArrayList<ArrayList<Integer>> adjList, int[] dp, boolean[] visited) {
            visited[node] = true;

            for (int neighbor : adjList.get(node)) { // node un komsulari gez 
                if (!visited[neighbor]) { // komsu ziyaret edilmediyse 
                    dfs(neighbor, adjList, dp, visited);  //dfs tekrar cagirarak devam et 
                }

                dp[node] = Math.max(dp[node], 1 + dp[neighbor]);  //nodeun en uzun pathini guncelle
            }
        }
        
        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();

            ArrayList<ArrayList<Integer> > adjList
            = new ArrayList<ArrayList<Integer> >(n);

            for (int i = 0; i < n; i++)
                adjList.add(new ArrayList<Integer>());

            for (int i = 0 ; i < m ; i++) {
                int u,v;
                u = in.nextInt();
                v = in.nextInt();
                u--;
                v--;
                adjList.get(u).add(v);
            }
            System.out.println(largestTour(adjList));
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
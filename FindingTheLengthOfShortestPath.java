
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

import java.util.ArrayList;
import java.util.List;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class Soru4 {

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


        static class Pair {
            int node;
            long cost;
    
            Pair(int node, long distances) {
                this.node = node;
                this.cost = distances;
            }
        }
        //djikstra algoritmasi 
        public long shortestPath(List<List<Pair>> adj, int s, int t) {
        	
            int n = adj.size(); //dugum sayisini al
            long[] distances = new long[n]; 
            Arrays.fill(distances, Long.MAX_VALUE);
            distances[s] = 0;
            
            PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingLong(p -> p.cost)); //dugumleri ve uzakliklari tutmak icin
            pq.offer(new Pair(s, 0)); // baslangic dugumunu ekle
            
            while (!pq.isEmpty()) {
                Pair current = pq.poll();
                int node = current.node; //dugumu al 
                long cost = current.cost; //costu al
                
                if (node == t) return distances[node];  // hedef dugume ulastiysa shortest path dondur
                
                if (cost > distances[node]) continue;  // cost, kaydedilmis en kisa uzaklik degerinden buyukse, devam et atla 
                
                for (Pair neighbor : adj.get(node)) { //dugumun komsularini gez 
                    int nextNode = neighbor.node;
                    long weight = neighbor.cost;
                    
                    if (distances[node] + weight < distances[nextNode]) { //yeni yol kaydedilmis uzakliktan daha kisaysa 
                        distances[nextNode] = distances[node] + weight;   //yeni uzakligi guncelle 
                        pq.offer(new Pair(nextNode, distances[nextNode])); // yeni dugumu queueya ekle 
                    }
                }
            }
            
            return -1; // If there is no path from s to t
        }
        public void solve(InputReader in, PrintWriter out) {

            int n = in.nextInt();
            int m = in.nextInt();
    
            // Initialize the adjacency list with pairs (node, cost)
            List<List<Pair>> adj = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                adj.add(new ArrayList<>());
            }
    
            // Read m lines with u, v, and cost, and construct the adjacency list
            for (int i = 0; i < m; i++) {
                int u = in.nextInt();
                int v = in.nextInt();
                u--;
                v--;
                int cost = in.nextInt(); // Assuming the cost of the edge is given
                adj.get(u).add(new Pair(v, cost)); // Assuming the graph is directed
            }
    
            // Now you can call shortestPath with the adjacency list and source and target nodes
            // For example:
            int s = in.nextInt();
            int t = in.nextInt();
            s--;
            t--;
            long shortestPathCost = shortestPath(adj, s, t);
            System.out.println(shortestPathCost);
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
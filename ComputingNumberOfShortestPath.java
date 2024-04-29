
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

public class Soru1 {

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
    	
        public int[] numberOfShortestPaths(List<List<Integer>> adj, int s) {
        	
        	int n = adj.size(); // Grafin dugum sayisini al 
            int[] shortestPaths = new int[n]; 
            Arrays.fill(shortestPaths, 0); //sifirla doldur
            Queue<Integer> queue = new LinkedList<>();
            boolean[] visited = new boolean[n]; // visited tut
            int[] distance = new int[n]; // baslangic dugumuyle olan mesafeleri tutacak
            int[] count = new int[n]; // farkli shortest pathlerin sayisi

           
            visited[s] = true; //baslangic dugumu isaretle 
            distance[s] = 0;
            count[s] = 1;
            queue.offer(s); //kuyruga ekle

            //kuyrugun basindan bir dugum alip onun komsularini gez
            while (!queue.isEmpty()) { 
                int current = queue.poll();

                
                for (int neighbor : adj.get(current)) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true; //isaretle
                        distance[neighbor] = distance[current] + 1; //mesafeyi guncelle
                        count[neighbor] = count[current];  // komsu dugume olan shortest path guncelle
                        queue.offer(neighbor); //komsu dugumu kuyruga ekle 
                    } else if (distance[neighbor] == distance[current] + 1) { //daha once ziyarret edilmisse 
                        count[neighbor] += count[current]; //shortest pathlerin sayisini guncelle 
                    }
                }
            }

            
            return count; //shortest pathlerin sayisini dondur 
        }

       
        public void solve(InputReader in, PrintWriter out) {

            int n = in.nextInt();
            int m = in.nextInt();
    
            // Initialize the adjacency list
            List<List<Integer>> adj = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                adj.add(new ArrayList<>());
            }
    
            // Read m lines with u and v, and construct the adjacency list
            for (int i = 0; i < m; i++) {
                int u = in.nextInt();
                int v = in.nextInt();
                u--;
                v--;
                adj.get(u).add(v);
                adj.get(v).add(u);
            }

            int s = in.nextInt();
            s--;

            int[] costs = numberOfShortestPaths(adj,s);

            for (int i = 0 ; i < costs.length ; i++) {
                System.out.print(costs[i] + " ");
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

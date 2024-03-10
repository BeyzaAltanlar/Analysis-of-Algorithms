package odev3;

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.util.*;


public class Main3 {

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

    	public static int switchCount(int[] entryTimes, int[] exitTimes) {
    		
            int n = entryTimes.length;
            State[] States = new State[2 * n];

            for (int i = 0; i < n; i++) { //giris ve cikislari tutan diziler 
                States[2 * i] = new State(entryTimes[i], true);
                States[2 * i + 1] = new State(exitTimes[i], false);
            }

            Arrays.sort(States); //O(nlgn) 

            int[] dp = new int[2 * n]; // Dinamik programlama tablosu
            int lightSwitchCount = 0; //toplam acilma sayisi

            for (int i = 0; i < 2 * n; i++) { 
            	State State = States[i];
                if (State.isEntry()) { // giris aktivitesi ise 
                    dp[i] = (i > 0 ? dp[i - 1] : 0) + 1; //1 ekleyerek guncelle ama i=0 sa dp[i]=1 olsun
                } else {  //cikis ise
                    dp[i] = dp[i - 1] - 1; //odadan bi kisi cikti
                    if (dp[i] == 0) {
                        lightSwitchCount++; //isik acilip kapanma sayisini artir
                    }
                }
            }

            return lightSwitchCount;
        }
    	
    	static class State implements Comparable<State> {
            int time;
            boolean entry;

            public State(int time, boolean entry) {
                this.time = time;
                this.entry = entry;
            }

            public boolean isEntry() {
                return entry;
            }

            @Override
            public int compareTo(State o) { //zaman karsilastirmasi yapan metot
                if (this.time != o.time) {
                    return Integer.compare(this.time, o.time);
                }
                return Boolean.compare(this.entry, o.entry);
            }
        }
   
        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int a[] = new int[n];
            int b[] = new int[n];
            for (int i = 0 ; i < n ; i++) {
                a[i] = in.nextInt();
                b[i] = in.nextInt();
            }
	        int result = switchCount(a,b);
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
package hw2;

/*Recall that at some point Utku had invented a time machine and went to Roman times. You
were at the beginning of your college life and most probably focused on decrypting texts encrypted with Ceaser cipher. Almost nobody shown interest in the details of the construction
of the time machine. Utku has also terminated his studies on the time machine since he was
quite scared of being trapped in a different time.
Mr. Bidik, however, was quite interested in the specifics of the time machine and since then
have been heavily working on constructing a more stable version. He has finally managed to
make the machine work properly. Recently, he traveled to year 4100AC and came back. He
has given a lot of information to us about the future.
He had the chance to go to some of the lectures at a university. He said that Artificial Intelligence Engineering Departments are quite popular in the future. In the finance classes he
attended, people were discussing the bankruptcy of Meta due to mis-speculation on the future of metaverse. He has also attended the algorithms classes while he was there. To his
surprise, people were still interested in algorithms that match men and women. This was
partly because a deadly world war, which put the future of humanity at risk.
The most fundamental issue -in the future- was to increase the size of the population and
thus, when matching men and women, they did not care about the individual preferences.
Rather, the problem they studied was as follows:
As input, we are given an integer array M of size n representing the ages of men in the society,
and an integer array W of size n representing the ages of women in the society, and an integer
k. The goal is to obtain a maximum sized matching between men and women such that no
person is matched with someone whose age differs by more than k. For instance, if k = 36,
1
somebody at the age of 1286 can be matched with someone whose age is at least 1250 and at
most 1322.
The output of your program should be a single integer: the size of the maximum matching.
PS: Due to advancement in the life sciences, people can live up to 231 −1 years.*/ 


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
    	
    	private int match(int [] M,int[] W,int k) {
    		Arrays.sort(M);
            Arrays.sort(W);

            int maleIndex = 0;
            int femaleIndex = 0;
            int matchingSize = 0;

            while (maleIndex < M.length && femaleIndex < W.length) {
   
                int ageDifference = Math.abs(M[maleIndex] - W[femaleIndex]); // yas fark

                if (ageDifference <= k) { // yas farki kucuk esitse eslesme olsun
                    matchingSize++;
                    maleIndex++;
                    femaleIndex++;
                } 
                else { //k den buyukse
             
                    if (M[maleIndex] < W[femaleIndex]) // daha kucuk bi snraki kisiyle eslesmeyi surdur
                        maleIndex++;
                    else 
                        femaleIndex++;
                }
            }

            return matchingSize;
            
        }
        
       
        
        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int k = in.nextInt();
            int[] M = new int[n];
            int[] W = new int[n];
            for (int i = 0 ; i < n ; i++)
                M[i] = in.nextInt();
            for (int i = 0 ; i < n ; i++)
                W[i] = in.nextInt();                
            int maxMatch = match(M,W,k);
           System.out.println(maxMatch);            
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
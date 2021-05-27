import java.io.*;
import java.math.*;
import java.util.*;

public class Main {
    static Map<String, Integer> boy = new HashMap<>();
    static Map<String, Integer> girl = new HashMap<>();
    static Map<Integer, String> boy1 = new HashMap<>();
    static Map<Integer, String> girl1 = new HashMap<>();

    public static void main(String[] args) {
        InputStream inputStream = System.in; // new FileInputStream("C:\\Users\\wavator\\Downloads\\test.in");
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);
        out.close();
    }

    static class Task {
        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            for (int i = 0; i < n; i++) {
                String str = in.next();
                boy.put(str, i);
                boy1.put(i, str);
            }
            for (int i = 0; i < n; i++) {
                String str = in.next();
                girl.put(str, i);
                girl1.put(i, str);
            }
            Woman[] woman = new Woman[n];
            Man[] man = new Man[n];
            for (int i = 0; i < n; i++) {
                man[i] = new Man(n);
                for (int j = 0; j < n; j++) {
                    String str = in.next();
                    man[i].prefer[j] = girl.get(str);
                }
            }
            for (int i = 0; i < n; i++) {
                woman[i] = new Woman(n);
                for (int j = 0; j < n; j++) {
                    String str = in.next();
                    woman[i].prefer[j] = boy.get(str);
                }
            }
            int[] preferNow = new int[n];
            for (int i = 0; i < n; i++) {
                preferNow[i] = 0;
            }
            int singleMan = n;
            while (singleMan > 0) {
                for (int i = 0; i < n;) {
                    int w = man[i].prefer[preferNow[i]];
                    if (man[i].state != -1) {
                        i++;
                    } else {
                        if (woman[w].state == -1) {
                            woman[w].state = i;
                            man[i].state = w;
                            i++;
                            singleMan--;
                        } else if (woman[w].index(woman[w].state) > woman[w].index(i)) {
                            int old = woman[w].state;
                            woman[w].state = i;
                            man[i].state = w;
                            man[old].state = -1;
                            preferNow[old]++;
                        } else if (woman[w].index(woman[w].state) < woman[w].index(i)) {
                            preferNow[i]++;
                        }
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                out.println(boy1.get(i) + " " + girl1.get(man[i].state));
            }
        }
    }
    static class Woman {
        int state;
        int[] prefer;
        public Woman(int n) {
            prefer = new int[n];
            this.state = -1;
        }
        public int index(int i) {
            int index;
            for (index = 0; prefer[index] != i; index++) {
            }
            return index;
        }
    }
    static class Man {
        int state;
        int[] prefer;
        public Man(int n) {
            prefer = new int[n];
            this.state = -1;
        }
        public int index(int i) {
            int index;
            for (index = 0; prefer[index] != i; index++) {
            }
            return index;
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

        public char[] nextCharArray() {
            return next().toCharArray();
        }

        //         public boolean hasNext() {
        //             try {
        //                 return reader.ready();
        //             } catch(IOException e) {
        //                 throw new RuntimeException(e);
        //             }
        //         }
        public boolean hasNext() {
            try {
                String string = reader.readLine();
                if (string == null) {
                    return false;
                }
                tokenizer = new StringTokenizer(string);
                return tokenizer.hasMoreTokens();
            } catch (IOException e) {
                return false;
            }
        }

        public BigInteger nextBigInteger() {
            return new BigInteger(next());
        }

        public BigDecimal nextBigDecinal() {
            return new BigDecimal(next());
        }
    }
}
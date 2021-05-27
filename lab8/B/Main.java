import java.io.*;
import java.math.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        InputStream inputStream = System.in; //
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
            int m = in.nextInt();
            int[] t = new int[n];
            String[] s = new String[n];
            for (int i = 0; i < n; i++) {
                s[i] = in.next();
                t[i] = in.nextInt();
            }
            int max = 0;
            for (int j = m; j >= m - 2; j--) {
                int[] binj = tran(j);
                int[] cur = binj;
                for (int p = 0; p < n; p++) {
                    int[] bint = tran(t[p]);
                    if (s[p].equals("AND")) {
                        for (int i = 0; i < 30; i++) {
                            if (bint[i] == 1 && cur[i] == 1) {
                                cur[i] = 1;
                            } else
                                cur[i] = 0;
                        }
                    } else if (s[p].equals("OR")) {
                        for (int i = 0; i < 30; i++) {
                            if (bint[i] == 0 && cur[i] == 0) {
                                cur[i] = 0;
                            } else
                                cur[i] = 1;
                        }
                    } else {
                        for (int i = 0; i < 30; i++) {
                            if (bint[i] != cur[i]) {
                                cur[i] = 1;
                            } else
                                cur[i] = 0;
                        }
                    }
                }
                int cst = 1;
                int deccur = 0;
                for (int i = 0; i < 30; i++) {
                    deccur += cst * cur[i];
                    cst = cst << 1;
                }
                if (deccur > max)
                    max = deccur;
            }
            out.print(max);
        }
    }
    public static int[] tran(int n) {
        int[] bin = new int[30];
        int i = 0;
        while (n > 0) {
            bin[i] = n % 2;
            n = n / 2;
            i++;
        }
        return bin;
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
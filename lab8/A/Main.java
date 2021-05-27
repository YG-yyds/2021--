import java.io.*;
import java.math.*;
import java.util.*;

public class Main {
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
            nd[] nds = new nd[26];
            for (int t = 0; t < n; t++) {
                for (int i = 0; i < 26; i++) {
                    nds[i] = new nd();
                }
                String s = in.next();
                int len = s.length();
                for (int i = 0; i < len; i++) {
                    int c = s.charAt(i) - 'a';
                    nds[c].val++;
                }
                PriorityQueue<nd> q = new PriorityQueue<>(new Comparator<nd>() {
                    @Override
                    public int compare(nd o1, nd o2) {
                        if (o1.val < o2.val)
                            return -1;
                        else if (o1.val > o2.val)
                            return 1;
                        else
                            return 0;
                    }
                });
                for (int i = 0; i < 26; i++) {
                    if (nds[i].val > 0) {
                        q.offer(nds[i]);
                    }
                }
                while (q.size() > 1) {
                    nd l = q.poll();
                    nd r = q.poll();
                    nd nd = new nd();
                    nd.val = l.val + r.val;
                    nd.left = l;
                    nd.right = r;
                    q.offer(nd);
                }
                nd head = q.poll();
                dfs(head, 0);
                long ans = 0;
                for (int i = 0; i < 26; i++) {
                    if (nds[i].val > 0) {
                        ans += nds[i].val * nds[i].height;
                    }
                }
                out.println(ans);
            }
        }
    }
    public static void dfs(nd nd, int h) {
        if (nd == null)
            return;
        nd.height = h;
        if (nd.left != null)
            dfs(nd.left, h + 1);
        if (nd.right != null)
            dfs(nd.right, h + 1);
    }
    static class nd {
        int val = 0;
        int height = 0;
        nd left = null;
        nd right = null;
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
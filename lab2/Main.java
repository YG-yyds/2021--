import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            int n = in.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
            }
            a = ms(a, n);
            int m1 = a[n - 1];
            int m2 = 0;
            int m3 = 0;
            int sum1 = 0;
            int n2 = 0;
            int n3 = 0;
            int n5 = 0;
            int sum2 = 0;
            for (int i = n - 2; i >= 0; i--) {
                if (a[i] == a[n - 1] / 2) {
                    n2 = 1;
                } else if (a[i] == a[n - 1] / 3) {
                    n3 = 1;
                } else if (a[i] == a[n - 1] / 5) {
                    n5 = 1;
                }
            }
            for (int i = n - 2; i >= 0; i--) {
                if (m1 % a[i] != 0) {
                    if (m2 == 0) {
                        m2 = a[i];
                    } else {
                        if (m2 % a[i] != 0) {
                            if (m3 == 0) {
                                m3 = a[i];
                                break;
                            }
                        }
                    }
                }
            }
            if (n2 == 1 && n3 == 1 && n5 == 1) {
                sum2 = a[n - 1] / 2 + a[n - 1] / 3 + a[n - 1] / 5;
            }
            sum1 = m1 + m2 + m3;
            if (sum1 > sum2) {
                out.println(sum1);
            } else {
                out.println(sum2);
            }
        }
        out.close();
    }
    public static int[] mer(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];
        int i = 0;
        int j = 0;
        for (int t = 0; t < a.length + b.length; t++) {
            if (i < a.length && (j > b.length - 1 || a[i] <= b[j])) {
                c[t] = a[i];
                i++;
            } else {
                c[t] = b[j];
                j++;
            }
        }
        return c;
    }
    public static int[] ms(int[] a, int n) {
        if (n > 1) {
            int p = n / 2;
            int[] b = new int[p];
            int[] c = new int[n - p];
            for (int i = 0; i < p; i++) {
                b[i] = a[i];
            }
            for (int i = 0; i < n - p; i++) {
                c[i] = a[p + i];
            }
            b = ms(b, p);
            c = ms(c, n - p);
            a = mer(b, c);
        }
        return a;
    }

    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ') c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ') c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }
}
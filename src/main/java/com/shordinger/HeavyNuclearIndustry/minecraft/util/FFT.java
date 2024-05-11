package com.shordinger.HeavyNuclearIndustry.minecraft.util;


public class FFT {
    private static final int SIZE = 64, l = 6;
    private static final int[] r = new int[SIZE * SIZE];

    static {
        for (int i = 0; i < SIZE; i++) {
            r[i] = (r[i >> 1] >> 1) | ((i & 1) << (l - 1));
        }
    }

    public static class Comp {
        public double x, y;

        public Comp(double x, double y) {
            this.x = x;
            this.y = y;
        }

    }

    static Comp add(Comp a, Comp b) {
        return new Comp(a.x + b.x, a.y + b.y);
    }

    static Comp decrease(Comp a, Comp b) {
        return new Comp(a.x - b.x, a.y - b.y);
    }

    static Comp multi(Comp a, Comp b) {
        return new Comp(a.x * b.x - a.y * b.y, a.x * b.y + a.y * b.x);
    }

    static Comp[] add(Comp[] a, Comp[] b) {
        Comp[] result = new Comp[2 * SIZE + 1];
        for (int i = 0; i <= 2 * SIZE; i++)
            result[i] = add(a[i], b[i]);
        return result;
    }

    static Comp[] decrease(Comp[] a, Comp[] b) {
        Comp[] result = new Comp[2 * SIZE + 1];
        for (int i = 0; i <= 2 * SIZE; i++)
            result[i] = decrease(a[i], b[i]);
        return result;
    }

    static Comp[][] add(Comp[][] a, Comp[][] b) {
        Comp[][] result = new Comp[2 * SIZE + 1][];
        for (int i = 0; i <= 2 * SIZE; i++)
            result[i] = add(a[i], b[i]);
        return result;
    }

    static Comp[][] decrease(Comp[][] a, Comp[][] b) {
        Comp[][] result = new Comp[2 * SIZE + 1][];
        for (int i = 0; i <= 2 * SIZE; i++)
            result[i] = decrease(a[i], b[i]);
        return result;
    }

    static Comp[] multi(Comp a, Comp[] b) {
        Comp[] result = new Comp[2 * SIZE + 1];
        for (int i = 0; i <= 2 * SIZE; i++)
            result[i] = multi(a, b[i]);
        return result;
    }

    static Comp[][] multi(Comp a, Comp[][] b) {
        Comp[][] result = new Comp[2 * SIZE + 1][];
        for (int i = 0; i <= 2 * SIZE; i++)
            result[i] = multi(a, b[i]);
        return result;
    }


    static Comp[] fft(Comp[] A, int type) {
        for (int i = 0; i < SIZE; ++i)
            if (i < r[i]) {
                Comp temp = A[i];
                A[i] = A[r[i]];
                A[r[i]] = temp;
            }
        for (int mid = 1; mid < SIZE; mid <<= 1) {
            Comp omg = new Comp(Math.cos(Math.PI / mid), type * Math.sin(Math.PI / mid));
            for (int R = mid << 1, j = 0; j < SIZE; j += R) {
                Comp w = new Comp(1, 0);
                for (int k = 0; k < mid; ++k, w = multi(w, omg)) {
                    Comp x = A[j + k], y = multi(w, A[j + mid + k]);
                    A[j + k] = add(x, y);
                    A[j + mid + k] = decrease(x, y);
                }
            }
        }
        return A;
    }

    static Comp[][] fft(Comp[][] A, int type) {
        for (int i = 0; i < SIZE; ++i)
            if (i < r[i]) {
                Comp[] temp = A[i];
                A[i] = A[r[i]];
                A[r[i]] = temp;
            }
        for (int mid = 1; mid < SIZE; mid <<= 1) {
            Comp omg = new Comp(Math.cos(Math.PI / mid), type * Math.sin(Math.PI / mid));
            for (int R = mid << 1, j = 0; j < SIZE; j += R) {
                Comp w = new Comp(1, 0);
                for (int k = 0; k < mid; ++k, w = multi(w, omg)) {
                    Comp[] x = A[j + k], y = multi(w, A[j + mid + k]);
                    // cout<<x<<' '<<y<<'\n'<<std::flush;
                    A[j + k] = add(x, y);
                    A[j + mid + k] = decrease(x, y);
                }
            }
        }
        return A;
    }

    static Comp[][][] fft(Comp[][][] A, int type) {
        for (int i = 0; i < SIZE; ++i)
            if (i < r[i]) {
                Comp[][] temp = A[i];
                A[i] = A[r[i]];
                A[r[i]] = temp;
            }
        for (int mid = 1; mid < SIZE; mid <<= 1) {
            Comp omg = new Comp(Math.cos(Math.PI / mid), type * Math.sin(Math.PI / mid));
            for (int R = mid << 1, j = 0; j < SIZE; j += R) {
                Comp w = new Comp(1, 0);
                for (int k = 0; k < mid; ++k, w = multi(w, omg)) {
                    Comp[][] x = A[j + k], y = multi(w, A[j + mid + k]);
                    A[j + k] = add(x, y);
                    A[j + mid + k] = decrease(x, y);
                }
            }
        }
        return A;
    }

    private static void decodeFFT(Comp[][][] result, int i, int j) {
        for (int k = 0; k <= 2 * SIZE; k++) {
            result[i][j][k].x = result[i][j][k].x / SIZE + 0.5;
            result[i][j][k].y = result[i][j][k].y / SIZE + 0.5;
        }
    }

    public static Comp[][][] multi(Comp[][][] a, Comp[][][] b, boolean flag) {
        Comp[][][] result = new Comp[2 * SIZE + 1][2 * SIZE + 1][2 * SIZE + 1];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                a[i][j] = fft(a[i][j], 1);
                if (flag) b[i][j] = fft(b[i][j], 1);
            }
            a[i] = fft(a[i], 1);
            if (flag) b[i] = fft(b[i], 1);
        }
        fft(a, 1);
        if (flag) fft(b, 1);
        for (int i = 0; i <= SIZE; ++i) {
            for (int j = 0; j <= SIZE; ++j) {
                for (int k = 0; k <= SIZE; k++) {
                    result[i][j][k] = multi(a[i][j][k], b[i][j][k]);
                }
            }
        }
        fft(result, -1);
        for (int i = 0; i <= 2 * SIZE; i++) {
            for (int j = 0; j <= 2 * SIZE; j++) {
                decodeFFT(result, i, j);
            }
        }
        for (int i = 0; i <= 2 * SIZE; i++) {
            result[i] = fft(result[i], -1);
            for (int j = 0; j <= 2 * SIZE; j++) {
                decodeFFT(result, i, j);
                result[i][j] = fft(result[i][j], -1);
                decodeFFT(result, i, j);
            }
        }
        return result;
    }

    double[][][] mergeBack(Comp[][][] a) {
        double[][][] result = new double[SIZE][SIZE][SIZE];
        for (int i = 0; i <= 2 * SIZE; i++) {
            for (int j = 0; j <= 2 * SIZE; j++) {
                for (int k = 0; k <= 2 * SIZE; k++) {

                }
            }
        }
        return result;
    }


}

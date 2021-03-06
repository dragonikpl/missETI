package misseti.generators;

//import java.util.ArrayList;
//import java.util.List;

public class InversiveGenerator implements GenericGenerator {

    private int a = 630360016;
    private int c = 23;
    private int M = (2 << 23) + 1;
    private int x;
    private int last;

    public InversiveGenerator() {
        x = (int) System.nanoTime();

        last = x;
    }
    

    @Override
    public long getRandom() {

        int next, previous;

        previous = last;

        if (previous != 0) {
            next = (a * modInverse(previous, M) + c) % M;

        } else {

            next = c;
        }

        last = Math.abs(next);
        return Math.abs(next);
    }

    int modInverse(int x, int m) {
        int s = m, t = x; /* set up for gcd(x,m) */
            int p = 0, b = 1; /* 0*x = s and 1*x = t */

            int q, r, temp;

            while(t != 0) 
            {
                q = s/t; r = s%t; /* quotient and remainder */
                s = t; t = r; /* push back */
                temp = (p-b*q) % m;
                p = b; b = temp; /* push back */
            }

            return p;
    }
}

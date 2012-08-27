
package misseti.generators;

import java.util.ArrayList;

public class InversiveGenerator extends Generator {
    
    private int a, c;

    
    public InversiveGenerator() {
        X = new ArrayList<Integer>();
    }
    
    public void Initialize(int a, int c, int m, int x0) {
        this.a = a;
        this.c = c;
        this.m = m;
        
        X.add(x0);
    }
    
    @Override
     public int Next() {
        int next, previous;
        previous = (Integer) X.get(X.size() - 1);
        
        if (previous != 0) {
            next = (a * modInverse(previous, m) + c) % m;
        } else {
            next = c;
        }
        
        
        X.add(next);
        
        return next;
    }

    int modInverse(int a, int n) {
        
        int i = n, v = 0, d = 1;
        while (a > 0) 
        {
            int t = i / a, x = a;
            a = i % x;
            i = x;
            x = d;
            d = v - t * x;
        }     
        
        v %= n;      
        if (v < 0) {
            v = (v + n) % n;
        }
        return v;
        
    }
}

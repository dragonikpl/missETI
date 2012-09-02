package misseti.generators;

import java.util.ArrayList;
import java.util.List;

public class MultiplyWithCarryGenerator implements GenericGenerator {

    /*
    private int a = 630360016;
    private int c = 23;
    private int M = (2 << 23) + 1;
    private int x;
    private List allValues;
    */
    
    private int r;
    private List<Integer> c;
    private List<Integer> a;
    private List<Integer> X;
    private int n;
    private int m = (2 << 23) + 1;


    public MultiplyWithCarryGenerator() 
    {
        
        c = new ArrayList<Integer>();
        c.add(23);

        a = new ArrayList<Integer>();
        a.add(9272);
        a.add(7777);
        a.add(6666);
        a.add(5555);
        a.add(4444);
        a.add(3333);
        a.add(2222);
        a.add(1111);
        r = a.size();
        
        X = new ArrayList<Integer>();
        for (int i = 0; i < 8; i++) {
            X.add((int) System.nanoTime());
        }

    }

    @Override
    public long getRandom() {

        int next = 0; // x_n
        int c_i = 0; // c_i+1

        for (int i = 0; i < r; i++) {
            next += a.get(i) * X.get(X.size() - i - 1); // -1 bo tablice indeksowane od 0
        }

        c_i = (next + c.get(c.size() - 1)) / m;
        next = (next + c_i) % m;
        X.add(next);

        return next;
    }

    
}

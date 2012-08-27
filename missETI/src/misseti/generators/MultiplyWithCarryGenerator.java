/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package misseti.generators;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author imac
 */
public class MultiplyWithCarryGenerator {
    /// <summary>
    /// ilosc parametrow a
    /// </summary>

    private int r;
    private List<Integer> c;
    private List<Integer> a;
    private List<Integer> X;
    private int m;
    private int n;

    public MultiplyWithCarryGenerator() {
        X = new ArrayList<Integer>();
        c = new ArrayList<Integer>();
    }

    /// <param name="a">ustalone parametry</param>
    /// <param name="initX">wartosci inicjalizujace</param>
    /// <param name="m">ustalona wartosc</param>
    /// <param name="c">wartosc inicjalizujaca</param>
    public void Initialize(List<Integer> a, List<Integer> initX, int m, int c) {
        this.m = m;
        this.c.add(c);
        this.a = a;
        r = a.size();

        X.addAll(initX);

    }

    public int Next() {
        int next = 0; // x_n
        int c_i = 0; // c_i+1

        for (int i = 0; i < r; i++) {
            next += a.get(i) * X.get(n - i - 1); // -1 bo tablice indeksowane od 0
        }

        c_i = (next + c.get(c.size() - 1)) / m;
        next = (next + c_i) % m;
        X.add(next);

        return next;
    }
}

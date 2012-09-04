/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package misseti.tests;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author akolassa
 */
public class KSTest
{
    double[] V;
    int eV;
    
    public KSTest()
    {
    }
    
    public double[] mMultiply(double[] A, double[] B, int m)
    {
        double s;
        double[] C = new double[m * m];
        
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < m; j++)
            {
                s = 0.0;
                for (int k = 0; k < m; k++)
                {
                    s += A[i * m + k] * B[k * m + j]; 
                }
                
                C[i * m + j] = s;
            }
        }
        
        return C;
    }
    
    public void mPower(double[] A, int eA, int m, int n)
    {
        double[] B;
        int eB;
        
        if (n == 1)
        {
            System.arraycopy(A, 0, V, 0, m * m);
            
            eV = eA;
            
            return;
        }
        
        mPower(A, eA, m, n / 2);
        B = mMultiply(V, V, m);
        eB = 2 * eV;
        
        if (n % 2 == 0)
        {
            System.arraycopy(B, 0, V, 0, m * m);
            eV = eB;
        }
        else
        {
            V = mMultiply(A, B, m);
            eV = eA + eB;
        }
        
        for (int i = 0; i < m * m; i++)
        {
            if (V[i] > 1.0e140)
            {
                for (int j = 0; j < m * m; j++)
                {
                    V[j] = V[j] * 1.0e-140;
                }
                
                eV += 140;
            }
        }
    }
    
    
    public boolean runTest(double[] data)
    { 
        if (data.length < 1)
        {
            System.out.println("KSTest: No P-value found ! ");
            return false;
        } 
        else if (data.length == 1)
        {
            System.out.println("KSTest: Only 1 P-value found !");
            return false;
        }
        
        ArrayList<Double> pValues = new ArrayList<Double>();
        
        for (double value : data)
        {
            pValues.add(value);
        }
        
        Collections.sort(pValues);
        
        System.out.println("SORTED PVALUES:");
        
        for (int i = 0; i < pValues.size(); i++)
        {
            System.out.println(pValues.get(i));
        }
        
        int valuesCount = pValues.size();
        double dmax = 0.0;
        double y, d;
        
        for (int i = 1; i <= valuesCount; i++)
        {
            y = Double.valueOf(i) / Double.valueOf(valuesCount + 1);
            d = (Double)pValues.get(i - 1) - y;
            d = Math.abs(d);
            
            if (d > dmax)
            {
                dmax = d;
            }
        }
        
        int k, m, h;
        
        k = (int)(Double.valueOf(valuesCount) * dmax) + 1;
        m = 2 * k - 1;
        h = k - (int)(Double.valueOf(valuesCount) * dmax);
        
        double[] H = new double[m * m];
        double[] Q = new double[m * m];
        
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < m; j++)
            {
                if (i - j + 1 < 0)
                {
                    H[i * m + j] = 0;
                }
                else
                {
                    H[i * m + j] = 1;
                }
            }
        }
        
        for (int i = 0; i < m; i++)
        {
            H[i * m] -= Math.pow(h, i + 1);
            H[(m - 1) * m + i] -= Math.pow(h, m - i);
        }
        
        H[(m - 1) * m] += (2 * h - 1 > 0 ? Math.pow(2 * h - 1, m) : 0);
        
        double[][] temp = new double[m][m];
        
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < m; j++)
            {
                if (i - j + 1 > 0)
                {
                    for (int g = 1; g <= i - j + 1; g++)
                    {
                        H[i * m + j] /= Double.valueOf(g);
                    }
                }
            }
        }
        
        V = new double[m * m];
        
        mPower(H, 0, m, valuesCount);
        double s = V[(k - 1) * m + k - 1];
        
        for (int i = 1; i <= valuesCount; i++)
        {
            s = s * Double.valueOf(i) / Double.valueOf(valuesCount);
            
            if (s < 1e-140)
            {
                s *= 1e140;
                eV -= 140;
            }
        }
        
        s *= Math.pow(10.0, eV);
        
        s = 1.0 - s;
        
        if (s >= 0.05 && s <= 0.95)
        {
            System.out.println("K-S Test passed: P = " + s);
            return true;
        }
        else
        {
            System.out.println("K-S Test failed: P = " + s);
            return false;
        }
    }
}

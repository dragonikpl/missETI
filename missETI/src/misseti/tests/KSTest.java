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
    
    int eV;
    
    public KSTest()
    {
    }
    
    public double[][] multiplyMatrix(double[][] matrix1, double[][] matrix2)
    {
        int matrix1Rows = matrix1.length;
        int matrix1Cols = matrix1[0].length;
        int matrix2Rows = matrix2.length;
        int matrix2Cols = matrix2[0].length;
        
        if (matrix1Cols != matrix2Rows)
        {
            System.out.println("multiplyMatrix: Matrix sizes are invalid");
            return null;
        }
        else
        {
            double[][] result = new double[matrix1Rows][matrix2Cols];
            
            for (int i = 0; i < matrix1Rows; i++)
            {
                for (int j = 0; j < matrix2Cols; j++)
                {
                    for (int k = 0; k < matrix1Cols; k++)
                    {
                        result[i][j] += matrix1[i][k] * matrix2[k][j];
                    }
                }
            }
            
            return result;
        }
    }
    
    public double[][] powMatrix(double[][] matrix, int power)
    {
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        double[][] result = new double[rows][cols];
        
        if (power == 0)
        {
            return null;
        }
        else if (power == 1)
        {
            return matrix;
        }
        
        for (int i = 0; i < rows; i++)
        {
            System.arraycopy(matrix[i], 0, result[i], 0, cols);
        }
        
        ArrayList<Integer> rest = new ArrayList<Integer>();
        
        int p = power;
        int c = 0;
        
        while (p != 1)
        {
            if (p % 2 == 0)
            {
                rest.add(c, (int)0);
            }
            else
            {
                rest.add(c, (int)1);
            }
            
            p = (int)(p / 2);
            c++;
        }
        
        int loopCount = c;
        eV = 0;
        
        for (int i = 0; i < loopCount; i++)
        {
            result = multiplyMatrix(result, result);

            if ((Integer)rest.get(i) == 1)
            {
                result = multiplyMatrix(result, matrix);  
            }
            
            eV = 2 * eV;
            
            for (int j = 0; j < rows; j++)
            {
                for (int k = 0; k < cols; k++)
                {
                    if (result[j][k] > 1.0e140)
                    {
                        for (int m = 0; m < rows; m++)
                        {
                            for (int n = 0; n < cols; n++)
                            {
                                result[m][n] = result[m][n] * 1.0e-140;
                            }
                            
                            eV += 140;
                        }
                    }
                }
            }       
        }
        
        return result;
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
                
                temp[i][j] = H[i * m + j];
            }
        }
        
        temp = powMatrix(temp, valuesCount);
        
        int index = (k - 1) * m + k - 1;
        int i1 = (int)(index / m);
        int i2 = index % m;
        
        double s = temp[i1][i2];
        
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

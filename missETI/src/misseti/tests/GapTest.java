/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package misseti.tests;

import java.util.ArrayList;
import java.util.Random;
import misseti.generators.GenericGenerator;

/**
 *
 * @author cheniuu
 */
public class GapTest 
{
    //deklaracja zmiennych        
    public class Point1D
    {
        public double x;
        public double distance;
    }

    public static final int loops = 1;
    public long edge;
    public long pointNumber;
    public GenericGenerator generator;
    public ArrayList<Point1D> points;
    
    public GapTest()
    {
    }
    
    public GapTest (GenericGenerator generator, long edge, long pointNumber)
    {
        this.generator = generator;
        this.edge = edge;
        this.pointNumber = pointNumber;
    }
    
    public boolean runTest()
    {
        double[] pValues = new double[loops];
        points = new ArrayList<Point1D>();
        Random gen = new Random();
        double distance = 0;
        double count = 0;
        double licznik = 0;
        double z;
                
        for (int loop = 0; loop < loops; loop++)
        {
            points.clear();
            
            for (int i = 0; i < pointNumber; i++)
            {
                Point1D p = new Point1D();
                            
                p.x = Double.valueOf(generator.getRandom() % edge);
                //p.x = Double.valueOf(gen.nextInt((int)edge));
                double w = p.x;
                System.out.println("px: " + w );
                
                if (p.x == 3)
                { 
                licznik = licznik +1;    
                distance = count;
                count =0;
                if (distance > 0)
                {
                if (licznik > 1)
                    {   
                    
                    z = 1.0 - Math.pow(0.9,distance+1);
    
                    pValues[loop] = z;
                    System.out.println("DISTANCE: " + distance);
                    System.out.println("[WARTOSC " + (licznik - 1) + "] P = " + z);
                    }
                }                
                
                
                }
                else 
                {
                count=count+1;
                }

                points.add(i, p);
            }

            
        }
        
        KSTest ks = new KSTest();
        
        boolean result;
        result = ks.runTest(pValues);
        
        if (result == true)
        {
            System.out.println("TEST PASSED");
            return true;
        }
        else
        {
            System.out.println("TEST FAILED");
            return false;
        }
    }
}
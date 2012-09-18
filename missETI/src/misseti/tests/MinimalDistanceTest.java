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
 * @author akolassa
 */
public class MinimalDistanceTest 
{
    //deklaracja zmiennych        
    public class Point2D
    {
        public double x;
        public double y;
        public double distance;
    }

    public static final double MAXDISTANCE = Double.MAX_VALUE;
    public static final int loops = 100;
    public long edge;
    public long pointNumber;
    public GenericGenerator generator;
    public ArrayList<Point2D> points;
    
    public MinimalDistanceTest()
    {
    }
    
    public MinimalDistanceTest(GenericGenerator generator, long edge, long pointNumber)
    {
        this.generator = generator;
        this.edge = edge;
        this.pointNumber = pointNumber;
    }
    
    public boolean runTest()
    {
        double[] pValues = new double[loops];
        points = new ArrayList<Point2D>();
        double minDistance, minDistance2;
        
        
        for (int loop = 0; loop < loops; loop++)
        {
            points.clear();
            minDistance = MAXDISTANCE;
            minDistance2 = 0.0;

            for (int i = 0; i < pointNumber; i++)
            {
                Point2D p = new Point2D();
                            
                p.x = Double.valueOf(generator.getRandom() % edge);
                p.y = Double.valueOf(generator.getRandom() % edge);
                p.distance = MAXDISTANCE;

                for (Point2D neighbour : points)
                { 
                    double dX = neighbour.x - p.x;
                    double dY = neighbour.y - p.y;
                    
                    double r2 = dX * dX + dY * dY;
                    double r1 = Math.sqrt(r2);
                    
                    if (r1 < minDistance)
                    {
                        minDistance = r1;
                        minDistance2 = r2;
                    }
                }
                
                //System.out.println(p.distance);
                
                points.add(i, p);
            }

            double p = 1.0 - Math.exp(-1.0 * minDistance2 / 0.995);
            
            pValues[loop] = p;

            System.out.println("MINDISTANCE: " + minDistance);
            System.out.println("MINDISTANCE2: " + minDistance2);
            System.out.println("[LOOP " + (loop + 1) + "] P = " + p);
        }
        
        KSTest ks = new KSTest();
        
        boolean result;3
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
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
public class RandomSpheresTest 
{
            
    public class Point3D
    {
        public double x;
        public double y;
        public double z;
        public double radius;
    }
    
    public static double exp(double val) {
    final long tmp = (long) (1512775 * val + (1072693248 - 60801));
    return Double.longBitsToDouble(tmp << 32);
}

    public static final double MAXRADIUS = Double.MAX_VALUE;
    public static final int loops = 20;
    public long edge;
    public long pointNumber;
    public GenericGenerator generator;
    public ArrayList<Point3D> points;
    
    public RandomSpheresTest()
    {
    }
    
    public RandomSpheresTest(GenericGenerator generator, long edge, long pointNumber)
    {
        this.generator = generator;
        this.edge = edge;
        this.pointNumber = pointNumber;
    }
    
    public boolean runTest()
    {
        double[] pValues = new double[loops];
        points = new ArrayList<Point3D>();
        double minRadius, minRadius3;
        
        
        for (int loop = 0; loop < loops; loop++)
        {
            points.clear();
            minRadius = MAXRADIUS;
            minRadius3 = 0.0;

            for (int i = 0; i < pointNumber; i++)
            {
                Point3D p = new Point3D();
                            
                p.x = Double.valueOf(generator.getRandom() % edge);
                p.y = Double.valueOf(generator.getRandom() % edge);
                p.z = Double.valueOf(generator.getRandom() % edge);
                p.radius = MAXRADIUS;

                for (Point3D neighbour : points)
                { 
                    double dX = neighbour.x - p.x;
                    double dY = neighbour.y - p.y;
                    double dZ = neighbour.z - p.z;
                    
                    double r2 = dX * dX + dY * dY + dZ * dZ;
                    double r1 = Math.sqrt(r2);
                    double r3 = r2 * r1;
                    
                    if (r1 < minRadius)
                    {
                        minRadius = r1;
                        minRadius3 = r3;
                    }
                }
                
                //System.out.println(p.radius);
                
                points.add(i, p);
            }

            double p = 1.0 - exp(-1.0 * minRadius3 / 30.0);
            
            pValues[loop] = p;

            System.out.println("MINRADIUS: " + minRadius);
            System.out.println("MINRADIUS3: " + minRadius3);
            System.out.println("[LOOP " + (loop + 1) + "] P = " + p);
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

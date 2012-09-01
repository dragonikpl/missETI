/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package misseti.tests;

import java.util.ArrayList;
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

    public static final double MAXRADIUS = Double.MAX_VALUE;
    public static final int loops = 1;
    public long edge;
    public long pointNumber;
    public double minRadius;
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
        int passes = 0;
        double result;
        
        for (int loop = 0; loop < loops; loop++)
        {
            points = new ArrayList<Point3D>();
            minRadius = MAXRADIUS;

            for (int i = 0; i < pointNumber; i++)
            {   
                Point3D p = new Point3D();
                
                p.x = Double.valueOf(generator.getRandom() % edge);
                p.y = Double.valueOf(generator.getRandom() % edge);
                p.z = Double.valueOf(generator.getRandom() % edge);
                p.radius = MAXRADIUS;

                for (Point3D neighbour : points)
                {
                    double dX = Math.pow(neighbour.x - p.x, 2);
                    double dY = Math.pow(neighbour.y - p.y, 2);
                    double dZ = Math.pow(neighbour.z - p.z, 2);
                    
                    double distance = Math.sqrt(dX + dY + dZ);

                    if (distance < p.radius) 
                    {
                        p.radius = distance;
                    }
                }

                if (p.radius < minRadius) 
                {
                    minRadius = p.radius;
                }
                
                points.add(i, p);
            }

            double mean = 0.0;

            for (int i = 0; i < pointNumber; i++)
            {
                Point3D p = points.get(i);
                //System.out.println(Math.pow(p.radius, 3.0));
                System.out.println(p.radius);
                mean += p.radius;
            }

            mean = mean / Double.valueOf(pointNumber);

            double p = 1.0 - Math.exp(-Math.pow(minRadius, 3.0) / 30);
            
            if (p >= 0.05 && p <= 0.95) 
            {
                passes++;
            }

            System.out.println("MEAN: " + mean);
            System.out.println("[LOOP " + (loop + 1) + "] P = " + p);
        }
        
        result = Double.valueOf(passes) / loops;
        
        if (result > 0.5)
        {
            System.out.println("TEST PASSED\nResult: " + String.valueOf(result));
            return true;
        }
        else
        {
            System.out.println("TEST FAILED\nResult: " + String.valueOf(result));
            return false;
        }
    }
}

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
        public long x;
        public long y;
        public long z;
        public double radius;
    }

    public static final double MAXRADIUS = Double.MAX_VALUE;
    public static final int loops = 20;
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
                p.x = generator.getRandom() % edge;
                p.y = generator.getRandom() % edge;
                p.z = generator.getRandom() % edge;
                p.radius = MAXRADIUS;

                points.add(i, p);
            }

            for (int i = 0; i < pointNumber; i++)
            {   
                Point3D p = points.get(i);

                for (int j = 0; j < pointNumber; j++)
                {
                    if (i == j) 
                    {
                        continue;
                    }

                    Point3D neighbour = points.get(j);
                    double temp = Math.pow(Double.valueOf(neighbour.x - p.x), 2) + Math.pow(Double.valueOf(neighbour.y - p.y), 2) + Math.pow(Double.valueOf(neighbour.z - p.z), 2);
                    double distance = Math.sqrt(temp);

                    if (distance < p.radius) 
                    {
                        p.radius = distance;
                    }
                }

                if (p.radius < minRadius) 
                {
                    minRadius = p.radius;
                }
            }

            double mean = 0.0;

            for (int i = 0; i < pointNumber; i++)
            {
                Point3D p = points.get(i);
                //System.out.println(p.radius);
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

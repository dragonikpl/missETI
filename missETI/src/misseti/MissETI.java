/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package misseti;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import misseti.generators.GenericGenerator;
import misseti.generators.InversiveGenerator;
import misseti.generators.MultiplyWithCarryGenerator;
import misseti.generators.XorShiftGenerator;
import misseti.tests.MinimalDistanceTest;
import misseti.tests.RandomSpheresTest;
import misseti.tests.GapTest;
import misseti.generators.RandomGenerator;


/**
 *
 * @author imac
 */
public class MissETI 
{


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        GenericGenerator generator = null;
        Random rand = null;
        boolean end = false;
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in), 1); 

        
        try 
        {
            while (!end)
            {
                System.out.println("PSEUDORANDOM TESTER");
                System.out.println("Dostępne generatory:");
                System.out.println("1. Inversive");
                System.out.println("2. MWC");
                System.out.println("3. Xorshift");
                System.out.println("4. Java Random");
                System.out.println("5. Koniec");
                System.out.print("Wybór: ");
                String gen = stdin.readLine();
                
                if (gen.compareTo("5") == 0)
                {
                    end = true;
                    continue;
                }

                System.out.println("\n\nDostępne testy:");
                System.out.println("1. Minimal Distance Test");
                System.out.println("2. Random Spheres Test");
                System.out.println("3. Gap Test");
                System.out.println("4. Brak testu (generacja liczb pseudolosowych)");
                System.out.print("Wybór: ");
                String test = stdin.readLine();

                switch (gen)
                {
                    case "1" : 
                    {
                        generator = new InversiveGenerator();
                        break;
                    }
                    case "2" : 
                    {
                        generator = new MultiplyWithCarryGenerator();
                        break;
                    }
                    case "3" : 
                    {
                        generator = new XorShiftGenerator();
                        break;
                    }
                    case "4" :
                    {
                        generator = new RandomGenerator();
                        break;
                    }
                    default : 
                    {
                        System.out.println("Zły numer generatora: " + gen);
                        continue;
                    }
                }

                if (test.compareTo("4") == 0)
                {
                    System.out.print("\nPodaj długość ciągu: ");
                    Integer length = Integer.parseInt(stdin.readLine());

                    System.out.print("\nPodaj zakres (od 0 do X): ");
                    Integer range = Integer.parseInt(stdin.readLine());

                    System.out.println("\nWygenerowany ciąg:");

                    for (int i = 0; i < length; i++)
                    {
                        System.out.println(Math.abs(generator.getRandom()) % range);
                    }
                }
                else
                {
                    switch (test)
                    {
                        case "1" :
                        {
                            MinimalDistanceTest testObj = new MinimalDistanceTest(generator, 1000, 8000);
                            testObj.runTest();
                            break;
                        }
                        case "2" :
                        {
                            RandomSpheresTest testObj = new RandomSpheresTest(generator, 1000, 4000);
                            testObj.runTest();
                            break;
                        }
                        case "3" :
                        {
                            GapTest testObj = new GapTest(generator, 1000, 4000);
                            testObj.runTest();
                            break;
                        }
                        default :
                        {
                            System.out.println("Zły numer testu: " + test);
                            continue;
                        }
                    }
                }
            }
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(MissETI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //RandomGenerator gen = new RandomGenerator();
        //InversiveGenerator gen = new InversiveGenerator();
        //MultiplyWithCarryGenerator gen = new MultiplyWithCarryGenerator();
        //XorShiftGenerator gen = new XorShiftGenerator(11, 8, 19);
        //for (int i = 0; i < 10000; i++) {
        //    System.out.println(gen.getRandom()%1000);
        //}
        
        //GapTest test = new GapTest(gen, 1000, 4000);
        //RandomSpheresTest test = new RandomSpheresTest(gen, 1000, 4000);
        //MinimalDistanceTest test2 = new MinimalDistanceTest(gen, 1000, 8000);
        
        
        
        //test.runTest();
    }
}

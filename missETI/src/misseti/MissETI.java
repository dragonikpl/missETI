/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package misseti;

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
public class MissETI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        
        
        //RandomGenerator gen = new RandomGenerator();
        //InversiveGenerator gen = new InversiveGenerator();
        //MultiplyWithCarryGenerator gen = new MultiplyWithCarryGenerator();
        XorShiftGenerator gen = new XorShiftGenerator(11, 8, 19);
        //for (int i = 0; i < 10000; i++) {
        //    System.out.println(gen.getRandom()%1000);
        //}
        
        GapTest test = new GapTest(gen, 1000, 4000);
        //RandomSpheresTest test = new RandomSpheresTest(gen, 1000, 4000);
        //MinimalDistanceTest test2 = new MinimalDistanceTest(gen, 1000, 8000);
        
        test.runTest();
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package misseti;

import misseti.generators.XorShiftGenerator;
import misseti.tests.RandomSpheresTest;


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
        XorShiftGenerator gen = new XorShiftGenerator();
        RandomSpheresTest test = new RandomSpheresTest(gen, 1000, 4000);
        
        test.runTest();
    }
}

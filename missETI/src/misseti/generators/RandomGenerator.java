
package misseti.generators;
import java.util.Random;

public class RandomGenerator implements GenericGenerator  {
    
    Random generator;
    
    public RandomGenerator() 
    {
        generator = new Random();
    }
    
    @Override
    public long getRandom() {

        return generator.nextInt(10000);
    }
    
}

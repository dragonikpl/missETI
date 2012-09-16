package misseti.generators;

import java.util.ArrayList;
import java.util.List;

public class MultiplyWithCarryGenerator implements GenericGenerator {

    // b = 2^32
    // http://www.javamex.com/tutorials/random_numbers/multiply_with_carry.shtml#footnote2
    
    final long a_ = 0xffffda61L;
    long x_ = System.nanoTime() & 0xffffffffL;
    
    public MultiplyWithCarryGenerator() 
    {
    }

    @Override
    public long getRandom() {

        x_ = Math.abs(a_ * (x_ & 0xffffffffL)) + (x_ >>> 32);
        return x_;
    }
}

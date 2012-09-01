/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package misseti.generators;

/**
 *
 * @author akolassa
 */
public class XorShiftGenerator implements GenericGenerator
{
    private long x = 123456789;
    private long y = 362436069;
    private long z = 521288629;
    private long w = 88675123;
    
    public XorShiftGenerator()
    {
        
    }
    
    public XorShiftGenerator(long x, long y, long z, long w)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }
    
    @Override
    public long getRandom()
    {
        long temp, result;
        
        temp = x ^ (x << 11);
        x = y;
        y = z;
        z = w;
        
        w = w ^ (w >> 19) ^ (temp ^ (temp >> 8));
        
        return w;
    }
}

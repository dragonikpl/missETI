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
    private int a;
    private int b;
    private int c;
    
    public XorShiftGenerator()
    {
        a = 11;
        b = 8;
        c = 19;
    }
    
    public XorShiftGenerator(int a, int b, int c)
    {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    @Override
    public long getRandom()
    {
        long temp;
        
        temp = x ^ (x << a);
        x = y;
        y = z;
        z = w;
        
        w = w ^ (w >> c) ^ (temp ^ (temp >> b));
        
        return w;
    }
}

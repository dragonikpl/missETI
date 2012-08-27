
package misseti.generators;

import java.util.List;

public abstract class Generator {
    public int m;
    public List<Integer> X;

    public int n()
        {
           // get
           // {
           return X.size();
           // }
        }

    public abstract int Next();
}


package misseti.shuffling;

import java.util.Arrays;
import java.util.List;
import misseti.generators.Generator;


public class Shuffler {
    public static List<int[]> ShuffleSingleSequence(Generator generator, int n)
        {
            int k = n / 2;  // ile liczb dostaniemy na wyjsciu
            int s = k; // dodatkowe liczby, ktore utracimy przy tasowaniu

            int []V = new int[k];

            for (int i = 0; i < k; i++) {
                V[i] = generator.X.get(i);
            }

            int y, Y = generator.X.get(k+1);

            for (int j = 0; j < s; j++)
            {
                y = Math.abs((k * Y) / generator.m);
                Y = V[y];
                V[y] = generator.X.get(j + k + 1);
            }

            return Arrays.asList(V);// V ToList();

        }
}

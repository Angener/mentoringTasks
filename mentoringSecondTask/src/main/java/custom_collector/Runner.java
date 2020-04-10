package custom_collector;

import java.util.Arrays;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        A a = new A("We All sCream   for ICe-cream.        ");
        A b = new A(" waTCh THREE swiSS    Swatch watch switches.");
        A c = new A(" We aLl scrEAm FOR   ice-CREAM.");
        A d = new A("Peter Piper picked a peck of pickled peppers.");
        A e = new A("   wE aLL   SCREAm foR       ice-cream.   ");
        A f = new A("   watch three swiss Swatch watch switches. ");
        A g = new A("WATch         three swiss SWATCH watch switCHES.");
        A h = new A("   Peter  PIPER picKeD A    peck of picklEd  pePPers.");
        A i = new A("   watch three swiss Swatch watch switches. ");
        A k = new A("WATch         three swiss SWATCH watch switCHES.");
        A l = new A("   wE aLL   SCREAm foR       ice-cream.   ");
        A m = new A(" PETER     pipER   pICKed a PECk OF   PICKled      PEPPERS.");

        List<A> aList = Arrays.asList(a, b, c, d, e, f, g, h, i, k, l, m);

        List<String> ls =  aList.parallelStream().collect(new CustomCollector());
        ls.forEach(System.out::println);
    }
}

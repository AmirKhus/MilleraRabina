import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

public class MillerRabin {
    public static boolean isPrime(String nunmber, int iteration) {
        BigInteger n = new BigInteger(nunmber);
        BigInteger one = new BigInteger("1");
        BigInteger two = new BigInteger("2");
        BigInteger nol = new BigInteger("0");

        if (n.equals(nol) || n.equals(one)) {
            return false;
        }
        if (n.equals(two)) {
            return true;
        }

        if (n.mod(two).equals(nol)) {
            return false;
        }

        BigInteger s = n.subtract(one);

        while (s.mod(two).equals(nol)) {
            s = s.divide(two);
        }

        for (int i = 0; i < iteration; i++) {
            double f = Math.random() / Math.nextDown(1.0);
            BigInteger rndBigInt = BigDecimal.valueOf(2).multiply(BigDecimal.valueOf(1.0 - f)).add(new BigDecimal(n.subtract(BigInteger.valueOf(1))).multiply(BigDecimal.valueOf(f))).toBigInteger();

            BigInteger a = rndBigInt.mod(n.subtract(one)).add(one), temp = s;

            BigInteger mod = modPow(a, temp, n);

            while (!temp.equals(n.subtract(one)) && !mod.equals(one) && !mod.equals(n.subtract(one))) {
                mod = mulMod(mod, mod, n);
                temp = temp.multiply(two);
            }
            if (!mod.equals(n.subtract(one)) && temp.mod(two).equals(nol)) {
                return false;
            }
        }
        return true;
    }

    private static BigInteger mulMod(BigInteger a, BigInteger b, BigInteger mod) {
        return a.multiply(b).mod(mod);

    }


    private static BigInteger modPow(BigInteger a, BigInteger b, BigInteger n){
        BigInteger res = BigInteger.ONE;
        BigInteger ai = a;
        ArrayList<BigInteger> n_bin = toBin(b);

        for (int i = 0; i < (n_bin.size()); i++) {
            if (n_bin.get(i).compareTo(BigInteger.valueOf(1)) == 0 ) {
                res = (res.multiply(ai)).mod(n);

            }
            ai = (ai.multiply(ai)).mod(n);
        }
        return res;
    }

    private static ArrayList toBin(BigInteger n){

        ArrayList<BigInteger> r1 = new ArrayList<BigInteger>();

        while (n.compareTo(BigInteger.ZERO)  > 0 ){
            r1.add(n.mod(BigInteger.valueOf(2)));
            n = n.divide(BigInteger.valueOf(2));
        }
        return r1;
    }

}
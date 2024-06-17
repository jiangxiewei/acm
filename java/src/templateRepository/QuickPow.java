package templateRepository;

public class QuickPow {


    public static long pow(long x, long y, long mod) {
        long result = 1;
        long base = x % mod;
        while (y > 0) {
            if ((y & 1) == 1) {
                result = (result * base) % mod;
            }
            base = (base * base) % mod;
            y >>= 1;
        }
        return result;
    }

}

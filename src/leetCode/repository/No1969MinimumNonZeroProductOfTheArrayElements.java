package leetCode.repository;

/**
 * 数组元素的最小非零乘积
 * <p>
 * S1= [1,2^n -1]  低位的数，会在 S2= [2^n ,  2^(n+1) -1] 里重新循环一次。故可以理解，
 * S2比S1引入了 2^(n+1)-1 -(2^n-1) 个  1<<(n+1) 。 通过这个递推，可以得到n=p的时候，任意位的1数量
 * </p>
 * <p> p=1 [ 001], p=2 [010, 011], p=3 [100, 101, 110, 111] </p>
 * <p> 每位含1总量数组 p=1 [ 0, 0 , 2^(1-1)=1 ], p=2 [ 0 , 2^(2-1)=2 , 1*2=2 ], p=3 [ 2^(3-1)=4 , 2*2=4 , 2*2=4 ] </p>
 * <p>
 * <p> 通过推到可得每一位都有 2^(p-1) 个，共有p位有数据. 元素总共有 2^p-1个 </p>
 * <p> 从规律上猜想，尽可能的制造1，然后其他位数的1都跑高位去 (根据周长相同情况下，正方形长宽差越大，面积越小的规律猜想。) </p>
 * <p> 根据数量会有一个满是1的值，就可以得到 (2^p-1)/2 个 1 * (2^p-2) 以及 1个 2^p-1
 * 将其相乘得到最终结果应为 (2^p-2)^(2^p-1) * (2^p-1) ,另外本题需要快速幂求解。 </p>
 */
public class No1969MinimumNonZeroProductOfTheArrayElements {

    public int minNonZeroProduct(int p) {
        long total = (1L << p) - 1;
        long mod = (long) (1e9 + 7);
        return (int) (pow(total - 1, total / 2, mod) * (total % mod) % mod);
    }

    public static long pow(long x, long y, long mod) {
        long result = 1;
        long base = x % mod;
        while (y != 0) {
            if ((y & 1) == 1) {
                result = (result * base) % mod;
            }
            base = (base * base) % mod;
            y >>= 1;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new No1969MinimumNonZeroProductOfTheArrayElements().minNonZeroProduct(35));
    }


}

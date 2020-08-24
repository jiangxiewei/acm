package leetCode.repository;


/**
 * 给你两个正整数 n 和 k，二进制字符串  Sn 的形成规则如下：
 * <p>
 * S1 = "0"
 * 当 i > 1 时，Si = Si-1 + "1" + reverse(invert(Si-1))
 * 其中 + 表示串联操作，reverse(x) 返回反转 x 后得到的字符串，而 invert(x) 则会翻转 x 中的每一位（0 变为 1，而 1 变为 0）
 * <p>
 * 例如，符合上述描述的序列的前 4 个字符串依次是：
 * <p>
 * S1 = "0"
 * S2 = "011"
 * S3 = "0111001"
 * S4 = "011100110110001"
 * 请你返回  Sn 的 第 k 位字符 ，题目数据保证 k 一定在 Sn 长度范围以内。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-kth-bit-in-nth-binary-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author jxw
 * @date 2020/8/25
 */
public class No1545FindKthBitInNthBinaryString {

    public static void main(String[] args) {
        No1545FindKthBitInNthBinaryString no = new No1545FindKthBitInNthBinaryString();
        //0
        System.out.println(no.findKthBit(3, 1));
        //1
        System.out.println(no.findKthBit(3, 2));
        //1
        System.out.println(no.findKthBit(2, 3));
        //1
        System.out.println(no.findKthBit(4, 4));
        //0
        System.out.println(no.findKthBit(3, 5));
        //1
        System.out.println(no.findKthBit(4, 11));
        //0
        System.out.println(no.findKthBit(4, 12));
        //1
        System.out.println(no.findKthBit(6, 58));
    }

    /**
     * 这题数字的起源就来自两个地方
     * 一个是一开始的k=1位置的0
     * 还有一个是每次在2^n位置新生成的1
     */
    public char findKthBit(int n, int k) {
        int count = 0, next2n;
        while ((next2n = findNext2n(k)) != k && k != 1) {
            k = next2n - k;
            count++;
        }
        if (k == 1) {
            return (count & 1) == 0 ? '0' : '1';
        }
        return (count & 1) == 0 ? '1' : '0';
    }

    public int findNext2n(int k) {
        int n = k - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n + 1;
    }

}

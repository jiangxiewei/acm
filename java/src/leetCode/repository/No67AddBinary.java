package leetCode.repository;

import java.math.BigInteger;

/**
 *
 * @author jiang
 * @date 2020/6/23
 */
public class No67AddBinary {

    public static void main(String[] args) {
        No67AddBinary no = new No67AddBinary();
        //"100"
        System.out.println(no.addBinary("11", "1"));
        //"10101"
        System.out.println(no.addBinary("1010", "1011"));
    }

    public String addBinary(String a, String b) {
        return new BigInteger(a, 2).add(new BigInteger(b, 2)).toString(2);
    }

}

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Test {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        w: while (in.hasNextLine()) { // 注意 while 处理多个 case
            String line = in.nextLine();
            if (line.length() <= 8) {
                System.out.println("NG");
                continue;
            }
            boolean[] checkList = new boolean[4];
            Set<String> hashset = new HashSet<>();
            char[] charArray = line.toCharArray();
            for (int i = 0; i < charArray.length; i++) {
                if (i + 2 < charArray.length) {
                    String subStr = new String(new char[]{charArray[i], charArray[i + 1], charArray[i + 2]});
                    if (hashset.contains(subStr)) {
                        System.out.println("NG");
                        continue w;
                    }
                    hashset.add(subStr);
                }
                if (charArray[i] >= '0' && charArray[i] <= '9') {
                    checkList[0] = true;
                }else if (charArray[i] >= 'a' && charArray[i] <= 'z') {
                    checkList[1] = true;
                }else if (charArray[i] >= 'A' && charArray[i] <= 'Z') {
                    checkList[2] = true;
                }else {
                    checkList[3] = true;
                }
            }
            int tc = 0;
            for (int i = 0; i < checkList.length; i++) {
                if (checkList[i]) {
                    tc++;
                }
            }
            if (tc >= 3) {
                System.out.println("OK");
            } else {
                System.out.println("NG");
            }
        }
    }


}

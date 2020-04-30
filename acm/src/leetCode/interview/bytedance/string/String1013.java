package leetCode.interview.bytedance.string;

import java.util.LinkedList;
import java.util.StringJoiner;

/**
 * 以 Unix 风格给出一个文件的绝对路径，你需要简化它。或者换句话说，将其转换为规范路径。<br/>
 * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。更多信息请参阅：Linux / Unix中的绝对路径 vs 相对路径 <br/>
 * 请注意，返回的规范路径必须始终以斜杠 / 开头，并且两个目录名之间必须只有一个斜杠 /。最后一个目录名（如果存在）不能以 / 结尾。此外，规范路径必须是表示绝对路径的最短字符串。<br/>
 * <p>
 * 示例 1：
 * <p>
 * 输入："/home/"
 * 输出："/home"
 * 解释：注意，最后一个目录名后面没有斜杠。
 * <br/>
 * 示例 2：
 * <p>
 * 输入："/../"
 * 输出："/"
 * 解释：从根目录向上一级是不可行的，因为根是你可以到达的最高级。
 * <br/>
 * 示例 3：
 * <p>
 * 输入："/home//foo/"
 * 输出："/home/foo"
 * 解释：在规范路径中，多个连续斜杠需要用一个斜杠替换。
 * <br/>
 * 示例 4：
 * <p>
 * 输入："/a/./b/../../c/"
 * 输出："/c"
 * <br/>
 * 示例 5：
 * <p>
 * 输入："/a/../../b/../c//.//"
 * 输出："/c"
 * <br/>
 * 示例 6：
 * <p>
 * 输入："/a//b////c/d//././/.."
 * 输出："/a/b/c"
 * <br/>
 *
 * @author jiang
 * @date 2020/4/30
 */
public class String1013 {

    public static void main(String[] args) {
        String1013 str = new String1013();
        System.out.println(str.simplifyPath("/home/"));// "/home"
        System.out.println(str.simplifyPath("/../"));// "/"
        System.out.println(str.simplifyPath("/home//foo/"));// "/home/foo"
        System.out.println(str.simplifyPath("/a/./b/../../c/"));// "/c"
        System.out.println(str.simplifyPath("/a/../../b/../c//.//"));// "/c"
        System.out.println(str.simplifyPath("/a//b////c/d//././/.."));// "/a/b/c"
    }

    public String simplifyPath(String path) {
        return firstWay(path);
    }

    private String firstWay(String s) {
        LinkedList<String> dirList = new LinkedList<>();
        String[] dirs = s.split("[/]+");
        for (String dir : dirs) {
            switch (dir) {
                case ".":
                    break;
                case "..":
                    if (dirList.size() > 0) {
                        dirList.removeLast();
                    }
                    break;
                case "":
                    break;
                default:
                    dirList.addLast(dir);
            }
        }
        StringJoiner sb = new StringJoiner("/", "/", "");
        dirList.forEach(sb::add);
        return sb.toString();
    }

}

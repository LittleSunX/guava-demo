import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * @author xys
 * @version 1.0.0
 */
public class Test {
    private static final int CONSTANT = 100;

    public static void main(String[] args) {
        //生成100个随机数存放到集合中
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= CONSTANT; i++) {
            int num = new Random().nextInt(99) + 1;
            list.add(num);
        }
        //集合转Integer数组
        Integer[] arr = list.toArray(new Integer[0]);
        //冒泡排序
        int temp;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j + 1] < arr[j]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        //打印结果
        for (Integer integer : arr) {
            System.out.println("冒泡排序打印： " + integer);
        }
        System.out.println("数组长度" + arr.length);
        String str = "计算机和骄傲的萨达是";
        String s = str.substring(0, 8);
        System.out.println(s);
        System.out.println("字符串长度为" + s.length());
    }
}

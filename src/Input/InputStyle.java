package Input;

import java.util.Scanner;

/**
 * 格式输入类
 */
public class InputStyle {
    public static Scanner src = new Scanner(System.in);

    /**
     * @param prompt   提示信息
     * @return       输入的整数
     */
    public static int readInt(String prompt) {
        int a;
        while (true) {
            try {
                a = src.nextInt();
                break;                                //如果是整数，则退出while循环
            } catch (Exception e) {                //这里用Exception来捕获这个异常
                System.out.print(prompt);
                src.next();
            }

        }
        return a;
    }

    /**
     * @return 输入的整数
     */
    public static int readInt() {
       return readInt("请输入整数:");
    }

    /**
     * @param prompt   提示信息
     * @return         输入的浮点数
     */
    public static double readDouble(String prompt){
        double a;
        while (true) {
            try {
                a = src.nextDouble();
                break;                                //如果是整数，则退出while循环
            } catch (Exception e) {                //这里用Exception来捕获这个异常
                System.out.print(prompt);
                src.next();
            }

        }
        return a;
    }

    /**
     * @return 输入的浮点数
     */
    public static double readDouble(){
        return readDouble("请输入浮点数:");
    }

    public static String readString(){
        return src.next();}
}

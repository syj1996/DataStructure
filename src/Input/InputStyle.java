package Input;

import java.util.Scanner;

/**
 * ��ʽ������
 */
public class InputStyle {
    public static Scanner src = new Scanner(System.in);

    /**
     * @param prompt   ��ʾ��Ϣ
     * @return       ���������
     */
    public static int readInt(String prompt) {
        int a;
        while (true) {
            try {
                a = src.nextInt();
                break;                                //��������������˳�whileѭ��
            } catch (Exception e) {                //������Exception����������쳣
                System.out.print(prompt);
                src.next();
            }

        }
        return a;
    }

    /**
     * @return ���������
     */
    public static int readInt() {
       return readInt("����������:");
    }

    /**
     * @param prompt   ��ʾ��Ϣ
     * @return         ����ĸ�����
     */
    public static double readDouble(String prompt){
        double a;
        while (true) {
            try {
                a = src.nextDouble();
                break;                                //��������������˳�whileѭ��
            } catch (Exception e) {                //������Exception����������쳣
                System.out.print(prompt);
                src.next();
            }

        }
        return a;
    }

    /**
     * @return ����ĸ�����
     */
    public static double readDouble(){
        return readDouble("�����븡����:");
    }

    public static String readString(){
        return src.next();}
}

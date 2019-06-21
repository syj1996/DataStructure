package Sort;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class CreateArray{
    public static int a=500000;
    /**
     * ���黻��  ���������������������
     * @param n ��Ҫ�õ�����ĳ���
     * @return   ��������
     */
    public static int[] arrayRandom(int n){
        int[] x = new int[n];
        for(int i = 0; i < n; i++)
        {
            x[i] = i;
        }
        Random r = new Random();
        for(int i = 0; i < n; i++)
        {
            int in = r.nextInt(n - i) + i;
            int t = x[in];
            x[in] = x[i];
            x[i] = t;
        }
        return x;
    }

    /**
     * ʹ��Set����
     * @param n   ��Ҫ���������ĳ���
     * @return    ����object��������Ҫ����ת��
     */
    public static Object[] setRandom(int n){
        Set r = new LinkedHashSet(n);
        Random random = new Random();

        while (r.size() < n ) {
            int i = random.nextInt(n );
            r.add(i);
        }
        return r.toArray();
    }

}

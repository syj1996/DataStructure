package Sort;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class CreateArray{
    public static int a=500000;
    /**
     * 数组换序  生成数组而后进行随机换序
     * @param n 需要得到数组的长度
     * @return   返回数组
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
     * 使用Set集合
     * @param n   需要输入的数组的长度
     * @return    返回object的数组需要进行转化
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

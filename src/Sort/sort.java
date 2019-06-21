package Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * �����㷨
 */
public class sort {
    public int[] array;

    /**
     * ��������LSD �ӵ�λ��ʼ��
     *
     * �������������ڣ�
     *  (1)���ݷ�Χ��С��������С��1000
     *  (2)ÿ����ֵ��Ҫ���ڵ���0
     *
     * ��. ȡ�������е����������ȡ��λ����
     * ��. arrΪԭʼ���飬�����λ��ʼȡÿ��λ���radix���飻
     * ��. ��radix���м����������ü�������������С��Χ�����ص㣩��
     * @param arr	 ����������
     */
    public static int[] radixSort(int[] arr){
        if(arr.length <= 1) return null;

        //ȡ�������е����������ȡ��λ��
        int max = 0;
        for(int i = 0; i < arr.length; i++){
            if(max < arr[i]){
                max = arr[i];
            }
        }
        int maxDigit = 1;
        while(max / 10 > 0){
            maxDigit++;
            max = max / 10;
        }
        //System.out.println("maxDigit: " + maxDigit);

        //����һ��Ͱ�ռ�
        int[][] buckets = new int[10][arr.length];
        int base = 10;

        //�ӵ�λ����λ����ÿһλ������������Ԫ�ط��䵽Ͱ��
        for(int i = 0; i < maxDigit; i++){
            int[] bktLen = new int[10];        //�洢����Ͱ�д洢Ԫ�ص�����

            //���䣺������Ԫ�ط��䵽Ͱ��
            for(int j = 0; j < arr.length; j++){
                int whichBucket = (arr[j] % base) / (base / 10);
                buckets[whichBucket][bktLen[whichBucket]] = arr[j];
                bktLen[whichBucket]++;
            }

            //�ռ�������ͬͰ�����ݰ����̳���,Ϊ��һ�ָ�λ������׼��,���ڿ���Ͱ�׵�Ԫ��������ǰ,��˴�Ͱ������
            int k = 0;
            for(int b = 0; b < buckets.length; b++){
                for(int p = 0; p < bktLen[b]; p++){
                    arr[k++] = buckets[b][p];
                }
            }

           // System.out.println("Sorting: " + Arrays.toString(arr));
            base *= 10;
        }
        return arr;
    }

    /**
     * ��������
     * @param array  ��Ҫ���������
     */
    public static int[] insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
        return array;
    } //����

    /**
     * Ͱ����
     * Ͱ����Bucket sort������ν����������һ�������㷨��
     * ������ԭ���ǽ�����ֵ�����������Ͱ�
     * ÿ��Ͱ�ٸ��������п�����ʹ�ñ�������㷨�����Եݹ鷽ʽ����ʹ��Ͱ����������򣩡�
     * Ͱ�����Ǹ볲�����һ�ֹ��ɽ������Ҫ������������ڵ���ֵ�Ǿ��ȷ����ʱ��
     * Ͱ����ʹ������ʱ��
     * �� {\displaystyle \Theta (n)}
     * {\displaystyle \Theta (n)}����O���ţ�����
     * ��Ͱ���򲢲��ǱȽ����������ܵ�
     * {\displaystyle O(n\log n)}
     * {\displaystyle O(n\log n)}���޵�Ӱ�졣
     *
     * Ͱ���������г�����У�
     *
     * ����һ�����������鵱����Ͱ�ӡ�
     * Ѱ�����У����Ұ���Ŀһ��һ���ŵ���Ӧ��Ͱ��ȥ��
     * ��ÿ�����ǿյ�Ͱ�ӽ�������
     * �Ӳ��ǿյ�Ͱ�������Ŀ�ٷŻ�ԭ���������С�
     * @param arr Ҫ�������������
     */
    public static int[] bucketSort(int[] arr) {

        int max = arr[0], min = arr[0];
        for (int a : arr) {
            if (max < a)
                max = a;
            if (min > a)
                min = a;
        }
        // ��ֵ����������ֵ����
        int bucketNum = max / 10 - min / 10 + 1;
        List buckList = new ArrayList<List<Integer>>();
        // create bucket
        for (int i = 1; i <= bucketNum; i++) {
            buckList.add(new ArrayList<Integer>());
        }
        // push into the bucket
        for (int i = 0; i < arr.length; i++) {
            int index = indexFor(arr[i], min, 10);
            ((ArrayList<Integer>) buckList.get(index)).add(arr[i]);
        }
        ArrayList<Integer> bucket = null;
        int index = 0;
        for (int i = 0; i < bucketNum; i++) {
            bucket = (ArrayList<Integer>) buckList.get(i);
            insertSort(bucket);
            for (int k : bucket) {
                arr[index++] = k;
            }
        }
        return arr;
    }  //Ͱ�������

    /**
     * @param a   Ҫ���������
     * @return    �����ź��������
     */
    public static int[] countSort(int []a){
        int b[] = new int[a.length];
        int max = a[0], min = a[0];
        for(int i : a){
            if(i > max){
                max = i;
            }
            if(i < min){
                min = i;
            }
        }
        //����k�Ĵ�С��Ҫ����������У�Ԫ�ش�С�ļ�ֵ��+1
        int k = max - min + 1;
        int c[] = new int[k];
        for(int i = 0; i < a.length; ++i){
            c[a[i]-min] += 1;//�Ż����ĵط�����С������c�Ĵ�С
        }
        for(int i = 1; i < c.length; ++i){
            c[i] = c[i] + c[i-1];
        }
        for(int i = a.length-1; i >= 0; --i){
            b[--c[a[i]-min]] = a[i];//����ȡ�ķ�ʽȡ��c��Ԫ��
        }
        return b;
    }  //����

    /**
     * ������������
     * @param a Ҫ���������
     * @return  ����õ�����
     */
    public static Object[] binaryTreeSort(int[] a){
        int randoms[] = a;

        Node roots = new Node();
        for (int number : randoms) {
            roots.add(number);
        }
        return roots.values().toArray();
    }//������

    /**
     * ϣ������
     *
     * 1. ѡ��һ����������t1��t2������tk������ti>tj��tk=1����һ�����ȡ����볤��֮��ÿ���ټ��룬ֱ������Ϊ1��
     * 2. ���������и���k�������н���k ������
     * 3. ÿ�����򣬸��ݶ�Ӧ������ti�����������зָ�����ɳ���Ϊm �������У��ֱ�Ը��ӱ����ֱ�Ӳ�������
     *    ����������Ϊ1 ʱ������������Ϊһ�������������ȼ�Ϊ�������еĳ��ȡ�
     * @param arr  ����������
     */
    public static int[] shellSort(int[] arr){
        int gap = arr.length / 2;
        for (; gap > 0; gap /= 2) {      //������Сgap��ֱ��1Ϊֹ
            for (int j = 0; (j+gap) < arr.length; j++){     //ʹ�õ�ǰgap�������ڲ�������
                for(int k = 0; (k+gap)< arr.length; k += gap){
                    if(arr[k] > arr[k+gap]) {
                        int temp = arr[k+gap];      //��������
                        arr[k+gap] = arr[k];
                        arr[k] = temp;
//                        System.out.println("    Sorting:  " + Arrays.toString(arr));
                    }
                }
            }
        }
        return arr;
    }//ϣ������

    /**
     * ѡ������
     *
     * 1. �Ӵ����������У��ҵ��ؼ�����С��Ԫ�أ�
     * 2. �����СԪ�ز��Ǵ��������еĵ�һ��Ԫ�أ�����͵�һ��Ԫ�ػ�����
     * 3. �����µ� N - 1 ��Ԫ���У��ҳ��ؼ�����С��Ԫ�أ��ظ��١��ڲ���ֱ�����������
     *    ����������Ϊ1 ʱ������������Ϊһ�������������ȼ�Ϊ�������еĳ��ȡ�
     * @param arr  ����������
     */
    public static int[] selectionSort(int[] arr){
        for(int i = 0; i < arr.length-1; i++){
            int min = i;
            for(int j = i+1; j < arr.length; j++){    //ѡ��֮���������ֵ��С��λ��
                if(arr[j] < arr[min]){
                    min = j;
                }
            }
            if(min != i){
                int temp = arr[min];      //��������
                arr[min] = arr[i];
                arr[i] = temp;
//                System.out.println("Sorting:  " + Arrays.toString(arr));
            }
        }
        return arr;
    } //ѡ������

    /**
     * ð������
     *
     * ��. �Ƚ����ڵ�Ԫ�ء������һ���ȵڶ����󣬾ͽ�������������
     * ��. ��ÿһ������Ԫ����ͬ���Ĺ������ӿ�ʼ��һ�Ե���β�����һ�ԡ��ⲽ���������Ԫ�ػ�����������
     * ��. ������е�Ԫ���ظ����ϵĲ��裬�������һ����
     * ��. ����ÿ�ζ�Խ��Խ�ٵ�Ԫ���ظ�����Ĳ����~�ۣ�ֱ��û���κ�һ��������Ҫ�Ƚϡ�
     * @param arr  ����������
     */
    public static int[] bubbleSort(int[] arr){
        for (int i = arr.length; i > 0; i--) {      //���ѭ���ƶ��α�
            for(int j = 0; j < i && (j+1) < i; j++){    //�ڲ�ѭ�������α꼰֮��(��֮ǰ)��Ԫ��
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
//                    System.out.println("Sorting: " + Arrays.toString(arr));
                }
            }
        }
        return arr;
    }//ð��

    /**
     * �������򣨵ݹ飩
     *
     * ��. ������������һ��Ԫ�أ���Ϊ"��׼"��pivot����
     * ��. �����������У����бȻ�׼ֵС��Ԫ�ذڷ��ڻ�׼ǰ�棬���бȻ�׼ֵ���Ԫ�ذ��ڻ�׼���棨��ͬ�������Ե���һ�ߣ����������������֮�󣬸û�׼�ʹ������е��м�λ�á������Ϊ������partition��������
     * ��. �ݹ�أ�recursively����С�ڻ�׼ֵԪ�ص������кʹ��ڻ�׼ֵԪ�ص�����������
     * @param arr   ����������
     * @param low   ��߽�
     * @param high  �ұ߽�
     */
    public static int[] quickSort(int[] arr, int low, int high){
        if(arr.length <= 0) return null;
        if(low >= high) return null;
        int left = low;
        int right = high;

        int temp = arr[left];   //�ڿ�1�������׼��ֵ
        while (left < right){
            while(left < right && arr[right] >= temp){  //��2���Ӻ���ǰ�ҵ��Ȼ�׼С��Ԫ�أ����뵽��׼λ�ÿ�1��
                right--;
            }
            arr[left] = arr[right];
            while(left < right && arr[left] <= temp){   //��3����ǰ�����ҵ��Ȼ�׼���Ԫ�أ��ŵ��ղ��ڵĿ�2��
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = temp;   //��׼ֵ�����3�У�׼�����εݹ����
       // System.out.println("Sorting: " + Arrays.toString(arr));
        quickSort(arr, low, left-1);
        quickSort(arr, left+1, high);
        return arr;
    }

    /**
     * �������򣨷ǵݹ飩
     *
     * ��. ������������һ��Ԫ�أ���Ϊ"��׼"��pivot����
     * ��. �����������У����бȻ�׼ֵС��Ԫ�ذڷ��ڻ�׼ǰ�棬���бȻ�׼ֵ���Ԫ�ذ��ڻ�׼���棨��ͬ�������Ե���һ�ߣ����������������֮�󣬸û�׼�ʹ������е��м�λ�á������Ϊ������partition��������
     * ��. �ѷ���֮����������ı߽磨low��high��ѹ��ջ���棬��ѭ���١��ڲ���
     * @param arr   ����������
     */
    public static Object[] quickSortByStack(int[] arr){
        if(arr.length <= 0) return null;
        Stack<Integer> stack = new Stack<Integer>();

        //��ʼ״̬������ָ����ջ
        stack.push(0);
        stack.push(arr.length - 1);
        while(!stack.isEmpty()){
            int high = stack.pop();     //��ջ���л���
            int low = stack.pop();

            int pivotIdx = partition(arr, low, high);

            //�����м����
            if(pivotIdx > low) {
                stack.push(low);
                stack.push(pivotIdx - 1);
            }
            if(pivotIdx < high && pivotIdx >= 0){
                stack.push(pivotIdx + 1);
                stack.push(high);
            }
        }
        return stack.toArray();
    }

    private static int partition(int[] arr, int low, int high){
        if(arr.length <= 0) return -1;
        if(low >= high) return -1;
        int l = low;
        int r = high;

        int pivot = arr[l];    //�ڿ�1�������׼��ֵ
        while(l < r){
            while(l < r && arr[r] >= pivot){  //��2���Ӻ���ǰ�ҵ��Ȼ�׼С��Ԫ�أ����뵽��׼λ�ÿ�1��
                r--;
            }
            arr[l] = arr[r];
            while(l < r && arr[l] <= pivot){   //��3����ǰ�����ҵ��Ȼ�׼���Ԫ�أ��ŵ��ղ��ڵĿ�2��
                l++;
            }
            arr[r] = arr[l];
        }
        arr[l] = pivot;   //��׼ֵ�����3�У�׼�����εݹ����
        return l;
    }
    // ��Ͱ��Ԫ�ز�������
    private static void insertSort(List<Integer> bucket) {
        for (int i = 1; i < bucket.size(); i++) {
            int temp = bucket.get(i);
            int j = i - 1;
            for (; j >= 0 && bucket.get(j) > temp; j--) {
                bucket.set(j + 1, bucket.get(j));
            }
            bucket.set(j + 1, temp);
        }
    }
    private static int indexFor(int a, int min, int step) {
        return (a - min) / step;
    }

}

/**
 * ����������ڵ���
 */
class Node {
    //��ڵ�
    public Node leftNode;
    // ���ӽڵ�
    public Node rightNode;

    // ֵ
    public Object value;

    // ���� ���ݵķ���
    public void add(Object v) {
        // �����ǰ�ڵ�û��ֵ���Ͱ����ݷ��ڵ�ǰ�ڵ���
        if (null == value)
            value = v;

            // �����ǰ�ڵ���ֵ���ͽ����жϣ�������ֵ�뵱ǰֵ�Ĵ�С��ϵ
        else {
            // ������ֵ���ȵ�ǰֵС������ͬ

            if ((Integer) v - ((Integer) value) <= 0) {
                if (null == leftNode)
                    leftNode = new Node();
                leftNode.add(v);
            }
            // ������ֵ���ȵ�ǰֵ��
            else {
                if (null == rightNode)
                    rightNode = new Node();
                rightNode.add(v);
            }

        }

    }

    // ����������еĽڵ�
    public List<Object> values() {
        List<Object> values = new ArrayList<>();

        // ��ڵ�ı������
        if (null != leftNode)
            values.addAll(leftNode.values());

        // ��ǰ�ڵ�
        values.add(value);

        // �ҽڵ�ı������
        if (null != rightNode)

            values.addAll(rightNode.values());

        return values;
    }
}
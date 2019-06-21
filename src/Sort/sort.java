package Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 排序算法
 */
public class sort {
    public int[] array;

    /**
     * 基数排序（LSD 从低位开始）
     *
     * 基数排序适用于：
     *  (1)数据范围较小，建议在小于1000
     *  (2)每个数值都要大于等于0
     *
     * ①. 取得数组中的最大数，并取得位数；
     * ②. arr为原始数组，从最低位开始取每个位组成radix数组；
     * ③. 对radix进行计数排序（利用计数排序适用于小范围数的特点）；
     * @param arr	 待排序数组
     */
    public static int[] radixSort(int[] arr){
        if(arr.length <= 1) return null;

        //取得数组中的最大数，并取得位数
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

        //申请一个桶空间
        int[][] buckets = new int[10][arr.length];
        int base = 10;

        //从低位到高位，对每一位遍历，将所有元素分配到桶中
        for(int i = 0; i < maxDigit; i++){
            int[] bktLen = new int[10];        //存储各个桶中存储元素的数量

            //分配：将所有元素分配到桶中
            for(int j = 0; j < arr.length; j++){
                int whichBucket = (arr[j] % base) / (base / 10);
                buckets[whichBucket][bktLen[whichBucket]] = arr[j];
                bktLen[whichBucket]++;
            }

            //收集：将不同桶里数据挨个捞出来,为下一轮高位排序做准备,由于靠近桶底的元素排名靠前,因此从桶底先捞
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
     * 插入排序
     * @param array  需要排序的数组
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
    } //插入

    /**
     * 桶排序
     * 桶排序（Bucket sort）或所谓的箱排序，是一个排序算法，
     * 工作的原理是将数组分到有限数量的桶里。
     * 每个桶再个别排序（有可能再使用别的排序算法或是以递归方式继续使用桶排序进行排序）。
     * 桶排序是鸽巢排序的一种归纳结果。当要被排序的数组内的数值是均匀分配的时候，
     * 桶排序使用线性时间
     * （ {\displaystyle \Theta (n)}
     * {\displaystyle \Theta (n)}（大O符号））。
     * 但桶排序并不是比较排序，他不受到
     * {\displaystyle O(n\log n)}
     * {\displaystyle O(n\log n)}下限的影响。
     *
     * 桶排序以下列程序进行：
     *
     * 设置一个定量的数组当作空桶子。
     * 寻访序列，并且把项目一个一个放到对应的桶子去。
     * 对每个不是空的桶子进行排序。
     * 从不是空的桶子里把项目再放回原来的序列中。
     * @param arr 要进行排序的数组
     */
    public static int[] bucketSort(int[] arr) {

        int max = arr[0], min = arr[0];
        for (int a : arr) {
            if (max < a)
                max = a;
            if (min > a)
                min = a;
        }
        // 该值可以用其他值代替
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
    }  //桶排序最快

    /**
     * @param a   要排序的数组
     * @return    返回排好序的数组
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
        //这里k的大小是要排序的数组中，元素大小的极值差+1
        int k = max - min + 1;
        int c[] = new int[k];
        for(int i = 0; i < a.length; ++i){
            c[a[i]-min] += 1;//优化过的地方，减小了数组c的大小
        }
        for(int i = 1; i < c.length; ++i){
            c[i] = c[i] + c[i-1];
        }
        for(int i = a.length-1; i >= 0; --i){
            b[--c[a[i]-min]] = a[i];//按存取的方式取出c的元素
        }
        return b;
    }  //计数

    /**
     * 二叉树堆排序
     * @param a 要排序的数组
     * @return  排序好的数组
     */
    public static Object[] binaryTreeSort(int[] a){
        int randoms[] = a;

        Node roots = new Node();
        for (int number : randoms) {
            roots.add(number);
        }
        return roots.values().toArray();
    }//二叉树

    /**
     * 希尔排序
     *
     * 1. 选择一个增量序列t1，t2，…，tk，其中ti>tj，tk=1；（一般初次取数组半长，之后每次再减半，直到增量为1）
     * 2. 按增量序列个数k，对序列进行k 趟排序；
     * 3. 每趟排序，根据对应的增量ti，将待排序列分割成若干长度为m 的子序列，分别对各子表进行直接插入排序。
     *    仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
     * @param arr  待排序数组
     */
    public static int[] shellSort(int[] arr){
        int gap = arr.length / 2;
        for (; gap > 0; gap /= 2) {      //不断缩小gap，直到1为止
            for (int j = 0; (j+gap) < arr.length; j++){     //使用当前gap进行组内插入排序
                for(int k = 0; (k+gap)< arr.length; k += gap){
                    if(arr[k] > arr[k+gap]) {
                        int temp = arr[k+gap];      //交换操作
                        arr[k+gap] = arr[k];
                        arr[k] = temp;
//                        System.out.println("    Sorting:  " + Arrays.toString(arr));
                    }
                }
            }
        }
        return arr;
    }//希尔排序

    /**
     * 选择排序
     *
     * 1. 从待排序序列中，找到关键字最小的元素；
     * 2. 如果最小元素不是待排序序列的第一个元素，将其和第一个元素互换；
     * 3. 从余下的 N - 1 个元素中，找出关键字最小的元素，重复①、②步，直到排序结束。
     *    仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
     * @param arr  待排序数组
     */
    public static int[] selectionSort(int[] arr){
        for(int i = 0; i < arr.length-1; i++){
            int min = i;
            for(int j = i+1; j < arr.length; j++){    //选出之后待排序中值最小的位置
                if(arr[j] < arr[min]){
                    min = j;
                }
            }
            if(min != i){
                int temp = arr[min];      //交换操作
                arr[min] = arr[i];
                arr[i] = temp;
//                System.out.println("Sorting:  " + Arrays.toString(arr));
            }
        }
        return arr;
    } //选择排序

    /**
     * 冒泡排序
     *
     * ①. 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
     * ②. 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。
     * ③. 针对所有的元素重复以上的步骤，除了最后一个。
     * ④. 持续每次对越来越少的元素重复上面的步骤①~③，直到没有任何一对数字需要比较。
     * @param arr  待排序数组
     */
    public static int[] bubbleSort(int[] arr){
        for (int i = arr.length; i > 0; i--) {      //外层循环移动游标
            for(int j = 0; j < i && (j+1) < i; j++){    //内层循环遍历游标及之后(或之前)的元素
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
//                    System.out.println("Sorting: " + Arrays.toString(arr));
                }
            }
        }
        return arr;
    }//冒泡

    /**
     * 快速排序（递归）
     *
     * ①. 从数列中挑出一个元素，称为"基准"（pivot）。
     * ②. 重新排序数列，所有比基准值小的元素摆放在基准前面，所有比基准值大的元素摆在基准后面（相同的数可以到任一边）。在这个分区结束之后，该基准就处于数列的中间位置。这个称为分区（partition）操作。
     * ③. 递归地（recursively）把小于基准值元素的子数列和大于基准值元素的子数列排序。
     * @param arr   待排序数组
     * @param low   左边界
     * @param high  右边界
     */
    public static int[] quickSort(int[] arr, int low, int high){
        if(arr.length <= 0) return null;
        if(low >= high) return null;
        int left = low;
        int right = high;

        int temp = arr[left];   //挖坑1：保存基准的值
        while (left < right){
            while(left < right && arr[right] >= temp){  //坑2：从后向前找到比基准小的元素，插入到基准位置坑1中
                right--;
            }
            arr[left] = arr[right];
            while(left < right && arr[left] <= temp){   //坑3：从前往后找到比基准大的元素，放到刚才挖的坑2中
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = temp;   //基准值填补到坑3中，准备分治递归快排
       // System.out.println("Sorting: " + Arrays.toString(arr));
        quickSort(arr, low, left-1);
        quickSort(arr, left+1, high);
        return arr;
    }

    /**
     * 快速排序（非递归）
     *
     * ①. 从数列中挑出一个元素，称为"基准"（pivot）。
     * ②. 重新排序数列，所有比基准值小的元素摆放在基准前面，所有比基准值大的元素摆在基准后面（相同的数可以到任一边）。在这个分区结束之后，该基准就处于数列的中间位置。这个称为分区（partition）操作。
     * ③. 把分区之后两个区间的边界（low和high）压入栈保存，并循环①、②步骤
     * @param arr   待排序数组
     */
    public static Object[] quickSortByStack(int[] arr){
        if(arr.length <= 0) return null;
        Stack<Integer> stack = new Stack<Integer>();

        //初始状态的左右指针入栈
        stack.push(0);
        stack.push(arr.length - 1);
        while(!stack.isEmpty()){
            int high = stack.pop();     //出栈进行划分
            int low = stack.pop();

            int pivotIdx = partition(arr, low, high);

            //保存中间变量
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

        int pivot = arr[l];    //挖坑1：保存基准的值
        while(l < r){
            while(l < r && arr[r] >= pivot){  //坑2：从后向前找到比基准小的元素，插入到基准位置坑1中
                r--;
            }
            arr[l] = arr[r];
            while(l < r && arr[l] <= pivot){   //坑3：从前往后找到比基准大的元素，放到刚才挖的坑2中
                l++;
            }
            arr[r] = arr[l];
        }
        arr[l] = pivot;   //基准值填补到坑3中，准备分治递归快排
        return l;
    }
    // 把桶內元素插入排序
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
 * 排序二叉树节点类
 */
class Node {
    //左节点
    public Node leftNode;
    // 右子节点
    public Node rightNode;

    // 值
    public Object value;

    // 插入 数据的方法
    public void add(Object v) {
        // 如果当前节点没有值，就把数据放在当前节点上
        if (null == value)
            value = v;

            // 如果当前节点有值，就进行判断，新增的值与当前值的大小关系
        else {
            // 新增的值，比当前值小或者相同

            if ((Integer) v - ((Integer) value) <= 0) {
                if (null == leftNode)
                    leftNode = new Node();
                leftNode.add(v);
            }
            // 新增的值，比当前值大
            else {
                if (null == rightNode)
                    rightNode = new Node();
                rightNode.add(v);
            }

        }

    }

    // 中序遍历所有的节点
    public List<Object> values() {
        List<Object> values = new ArrayList<>();

        // 左节点的遍历结果
        if (null != leftNode)
            values.addAll(leftNode.values());

        // 当前节点
        values.add(value);

        // 右节点的遍历结果
        if (null != rightNode)

            values.addAll(rightNode.values());

        return values;
    }
}
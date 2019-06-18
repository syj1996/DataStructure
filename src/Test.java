import Sort.printArray;
import Sort.sort;

public class Test {
    public long[] startTime;
    public long endTime;
    public static int[] a=new int[]{81,34,45,65,3,2,256,67,5,434,667,5466};
    public static void main(String[] args) {
        int[] k=sort.shellSort(a);
        printArray.printA(k);
    }
}

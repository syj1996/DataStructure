package Sort;

public class printArray {
    public static void printA(int[] a){
        for(int i:a){
            System.out.print(i+"\t");
        }
        System.out.println(" ");
    }
    public static void printO(Object[] a){
        for(Object i:a){
            System.out.print((int)i+"\t");
        }
        System.out.println(" ");
    }
}

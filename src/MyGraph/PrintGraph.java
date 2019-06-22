package MyGraph;

/**
 * ��ӡ���ṩ��ӡ����,ʹ�þ�̬������ͨ���������ܵ���
 */
public class PrintGraph {
    /**
     * ��ӡ��С������
     * @param MSTmatrix  ��С������ ����  ���վ������ʽ�����С������
     */
    public static void printMatrix(double[][] MSTmatrix){
        for (int i = 0; i < MSTmatrix[0].length; i++) {
            for ( int j = 0; j <MSTmatrix[0].length; j++) {
                System.out.print(MSTmatrix[i][j]+"\t");
            }
            System.out.println("\n");
        }
    }
    public static int M =100000000;
    /**
     * ���Ծ���1  ����ͼ
     */
    public static double[][] matrix1;
    /**
     * ���Ծ���2  ����ͼ
     */
    public static double[][] matrix2;
    static {
        
        matrix1 = new double[][]{
                {M, 6, 1, 5, M, M},
                {6, M, 5, M, 3, M},
                {1, 5, M, 7, 5, 4},
                {5, M, 7, M, M, 2},
                {M, 3, 5, M, M, 6},
                {M, M, 4, 2, 6, M}
        };
        matrix2= new double[][]{
                {M,6,4,5,M,M,M,M,M},
                {M,M,M,M,1,M,M,M,M},
                {M,M,M,M,1,M,M,M,M},
                {M,M,M,M,M,2,M,M,M},
                {M,M,M,M,M,M,9,7,M},
                {M,M,M,M,M,M,M,4,M},
                {M,M,M,M,M,M,M,M,2},
                {M,M,M,M,M,M,M,M,4},
                {M,M,M,M,M,M,M,M,M},
        };
    }
    
    /**
     * ��ӡ��� ·������
     * @param path  ·������
     */
    public static void printPath(int[][] path){
        int next=0;int vexnum=path[0].length;
        for ( int i = 0; i <vexnum ; i++) {
            for (int j = 0; j <vexnum ; j++) {
                System.out.println("���<"+i+","+j+">��·��");
                next=path[i][j];  //nextΪi�ĺ�̽ڵ�
                if(next==0)       //i�޺�̱�ʾPij������
                {
                    System.out.println(i+" to  "+j+"û��·��!");
                }
                else{
                    System.out.print(i);
                    while(next!=j){
                        System.out.print("-->"+next);
                        next=path[next][j];
                    }
                    System.out.println("-->"+j);
                }
            }
        }
    }

    public static void printArray(int[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+"\t");
        }
        System.out.print("\n");
    }

    public static void printArray(double[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+"\t");
        }
        System.out.print("\n");
    }
}

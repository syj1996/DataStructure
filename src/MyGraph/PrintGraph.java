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

    /**
     * ���Ծ���1
     */
    public static double[][] matrix1;
    static {
        matrix1 = new double[][]{
                {100000000, 6, 1, 5, 100000000, 100000000},
                {6, 100000000, 5, 100000000, 3, 100000000},
                {1, 5, 100000000, 7, 5, 4},
                {5, 100000000, 7, 100000000, 100000000, 2},
                {100000000, 3, 5, 100000000, 100000000, 6},
                {100000000, 100000000, 4, 2, 6, 100000000}
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
}

package MyGraph;

/**
 * ��ӡ���ṩ��ӡ����,ʹ�þ�̬������ͨ���������ܵ���
 */
public class PrintGraph {
    /**
     * ��ӡ��С������
     * @param MSTmatrix  ��С������ ����  ���վ������ʽ�����С������
     */
    public static void printMSTmatrix(double[][] MSTmatrix){
        for (int i = 0; i < MSTmatrix[0].length; i++) {
            for ( int j = 0; j <MSTmatrix[0].length; j++) {
                System.out.print(MSTmatrix[i][j]+"\t");
            }
            System.out.println("\n");
        }
    }
    public static double[][] matrix={
            {100000000,6,1,5,100000000,100000000},
            {6,100000000,5,100000000,3,100000000},
            {1,5,100000000,7,5,4},
            {5,100000000,7,100000000,100000000,2},
            {100000000,3,5,100000000,100000000,6},
            {100000000,100000000,4,2,6,100000000}
    };
}

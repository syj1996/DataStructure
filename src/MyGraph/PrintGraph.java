package MyGraph;

/**
 * 打印类提供打印功能,使用静态方法，通过类名就能调用
 */
public class PrintGraph {
    /**
     * 打印最小生成树
     * @param MSTmatrix  最小生成树 矩阵  按照矩阵的形式输出最小生成树
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
     * 测试矩阵1
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
     * 打印输出 路径矩阵
     * @param path  路径矩阵
     */
    public static void printPath(int[][] path){
        int next=0;int vexnum=path[0].length;
        for ( int i = 0; i <vexnum ; i++) {
            for (int j = 0; j <vexnum ; j++) {
                System.out.println("输出<"+i+","+j+">的路径");
                next=path[i][j];  //next为i的后继节点
                if(next==0)       //i无后继表示Pij不存在
                {
                    System.out.println(i+" to  "+j+"没有路径!");
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

package MyGraph;

import java.util.Scanner;

/**
 * ��������Ը��������Ĺ���
 */
public class MyGraph {
    public static void main(String[] agrs){
//       GraphMatrix graphMatrix=new GraphMatrix();
//       Graph graph=new Graph(graphMatrix.getGraph().weight);
//
//       graphMatrix.DFS(1); //  ����ӵڼ����������
//        graphMatrix.TRAVER(1);

//       graphMatrix.BFS(1);

            GraphList   graphList=new GraphList(PrintGraph.matrix2);
            AOE a=new AOE(PrintGraph.matrix2);
            a.VexElyTime();
            a.VexDelayTime();
            PrintGraph.printArray(a.vexETime);
            PrintGraph.printArray(a.vexDTime);
//        graphList.DFSL(1);
//        graphList.BFSL(1);
//          MinTree minTree=new MinTree();
//          minTree.Prime(1);

//        MinTree mintree=new MinTree();
//        mintree.Kruskal();

//        double[][] d=MinPath.MinPathMatrix(PrintGraph.matrix1);
//        Node  d1=(MinPath.Dijkstra(1, 5, PrintGraph.matrix1));
//        d=MinPath.Floyd(PrintGraph.matrix1);
//        PrintGraph.printMatrix(d);
//        System.out.println(d1.cost);
//        d1.getPath();
        Scanner src=new Scanner(System.in);
        src.next();
    }
}

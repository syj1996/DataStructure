package MyGraph;

import java.util.Iterator;
import java.util.Scanner;

/**
 * 测试类测试各类操作类的功能
 */
public class MyGraph {
    public static void main(String[] agrs){
    //   GraphMatrix graphMatrix=new GraphMatrix();
 //      Graph graph=new Graph(graphMatrix.getGraph().weight);

//       graphMatrix.DFS(1); //  输入从第几个顶点出发
//        graphMatrix.TRAVER(1);

//       graphMatrix.BFS(1);
//        GraphList   graphList=new GraphList();
//        graphList.DFSL(1);
//        graphList.BFSL(1);
//          MinTree minTree=new MinTree();
//          minTree.Prime(1);

//        MinTree mintree=new MinTree();
//        mintree.Kruskal();

        double[][] d=MinPath.MinPathMatrix(PrintGraph.matrix);
        Node  d1=(MinPath.Dijkstra(1, 5, PrintGraph.matrix));

        PrintGraph.printMSTmatrix(d);
        System.out.println(d1.cost);
        d1.getPath();
        Scanner src=new Scanner(System.in);
        src.next();
    }
}

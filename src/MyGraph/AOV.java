package MyGraph;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 在现代化管理中，人们常用有向图来描述和分析一项工程的计划和实施过程，一个工程常被分为多个小的子工程，这些子工程被称为活动（Activity)，在有向图中若以顶点表示活动，有向边表示活动之间的先后关系，这样的图简称为AOV网。
 */
public class AOV {
    /**
     * 邻接表
     */
    public GraphList G;
    /**
     * 拓扑序列
     */
    public int[] Tsort;

    public AOV() {
        G=new GraphList();
        Tsort=new int[G.vexnum];
    }

    public AOV(GraphList g) {
        G = g;
        Tsort=new int[G.vexnum];
    }

    public AOV(double[][] matrix){
        G=new GraphList(matrix);
        Tsort=new int[G.vexnum];
    }

    /**
     * 拓扑排序
     * 应用：AOV网 顶点活动顺序有先后 在只能进行串行工作下，完成整个工程的一种可行性方案
     * @return int
     *      -1 -- 失败(由于内存不足等原因导致)
     *      0 -- 成功排序，并输入结果
     *      1 -- 失败(该有向图是有环的)
     */
    public int TopologySort() {
        return TopologySort(this.G,this.Tsort);
    }

    /**
     * 静态方法
     * @param G   邻接表
     * @param Tsort   拓扑序列
     * @return    -1 -- 失败(由于内存不足等原因导致)
     *             0 -- 成功排序，并输入结果
     *             1 -- 失败(该有向图是有环的)
     */
    public static int TopologySort(GraphList G,int[] Tsort){
        EdgeNode p;
        Queue Q=new ArrayDeque();
        int m=0,k=0;
        //如果顶点的入度为零则先入队
        for (int i = 0; i <this.G.vexnum ; i++) {
            if(G.vexNodes[i].indegree==0) Q.add(i);
        }

        while(!Q.isEmpty()){
            int j=(int) ((ArrayDeque) Q).pop();
            Tsort[m]=j;     //点J入拓扑序列
            m++;
            p=G.vexNodes[j].firstEdge;  //指向当前输出节点的边表
            while(p!=null){
                k=p.number;
                G.vexNodes[k].indegree--;
                if(G.vexNodes[k].indegree==0){
                    Q.add(k);
                }
                p=p.next;
            }
        }
        if(m<G.vexnum){
            System.out.println("网络有回路!");
            return 0;
        }
        else return 1;
    }
}

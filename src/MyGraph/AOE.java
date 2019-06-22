package MyGraph;

import java.util.Stack;

/**
 * AOE网
 * 是一个带权的有向无环图。
 * 网中只有一个入度为零的点（称为源点）和一个出度为零的点（称为汇点）。
 * 其中，顶点表示事件（Event），弧表示活动，权表示活动持续的时间。
 * 通常，AOE网可用来估算工程的完成时间。
 * 假如汽车生产工厂要制造一辆汽车，制造过程的大概事件和活动时间如上图AOE网：
 * 我们把路径上各个活动所持续的时间之和称为路径长度，
 * 从源点到汇点具有最大长度的路径叫关键路径，在关键路径上的活动叫关键活动。
 *
 * （1）事件的最早发生时间etv(earliest time of vertex): 即顶点Vk的最早发生时间。
 * （2）事件的最晚发生时间ltv(latest time of vertex): 即顶点Vk的最晚发生时间。也就是每个顶点对应的事件最晚需要开始的时间，超出此时间将会延误整个工期。
 * （3）活动的最早开工时间ete(earliest time of edge): 即弧ak的最早发生时间。
 * （4）活动的最晚开工时间lte(latest time of edge): 即弧ak的最晚发生时间，
 * 也就是不推迟工期的最晚开工时间。
 * 
 */
public class AOE {
    /**
     * 邻接表图存储
     */
    public GraphList G;
    /**
     * 顶点数
     */
    public int vexnum;
    /**
     * 边数
     */
    public int edgenum;

    /**
     * 各顶点事件最早发生的时间
     */
    public int[] vexTime;
    /**
     * 拓扑序列数组栈
     */
    public  Stack<String>  TopologyArray=new Stack<String>();;

    public AOE(GraphList g) {
        G = g;
        this.vexnum=g.vexnum;
        this.edgenum=g.edgenum;
        this.vexTime=new int[g.vexnum];
    }

    public AOE(double[][] matrix) {
        G=new GraphList(matrix);
        this.vexnum=G.vexnum;
        this.edgenum=G.edgenum;
        this.vexTime=new int[G.vexnum];
    }

    public AOE(){
        G=new GraphList();
        vexnum=G.vexnum;
        edgenum=G.edgenum;
        this.vexTime=new int[G.vexnum];
    }

    public  int[] VxElyTime(){
        //0入度的顶点栈
        Stack<Integer> s=new Stack<Integer>();
        int count=0,j=0,k=0;
        //寻找0入度的顶点
        for(int i=0;i<vexnum;i++){
            if(G.vexNodes[i].indegree==0){
                s.push(i);
            }
        }
        while(!s.empty()){  //栈空时置空时循环结束
            j= s.pop();
            TopologyArray.push(G.vexNodes[j].vexterix);  //入拓扑序列栈
            count++;
            for(EdgeNode p=G.vexNodes[j].firstEdge;p!=null;p=p.next){
                k=p.number;
                G.vexNodes[j].indegree--;
                if(G.vexNodes[j].indegree==0){
                    s.push(j);
                }

            }
        }
     }
}

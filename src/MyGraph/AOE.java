package MyGraph;

import java.util.ArrayDeque;
import java.util.Queue;
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
 * 算法
 *   A、从开始顶点 v1?出发，令 ve(1)=0，按拓扑有序序列求其余各顶点的可能最早发生时间。?[3]?Ve(k)=max{ve(j)+dut(《j,k》)} , j ∈ T 。其中T是以顶点vk为尾的所有弧的头顶点的集合（2 《 k 《 n）。
 *
 * 如果得到的拓朴有序序列中顶点的个数小于网中顶点个数n，则说明网中有环，不能求出关键路径，算法结束。
 *
 * B、从完成顶点Vn  出发，令 Vl(n)=Ve(n) ，
 * 按逆拓扑有序求其余各顶点的允许的最晚发生时间：
 * vl(j)=min{vl(k)-dut(《j,k》)} ,k ∈ S 。其中 S 是以顶点vj是头的所有弧的
 * 尾顶点集合（1 《 j 《 n-1）。
 *
 * C、求每一项活动ai（1 《 i 《 m）的最早开始时间e(i)=ve(j)，最晚开始时间l(i)=vl(k)-dut(《j,k》) 。
 *
 * 若某条弧满足 e(i)=l(i) ，则它是关键活动。
 *
 */
public class AOE {
    /**
     * 关键节点
     */
    public ArrayDeque keyNode =new ArrayDeque<>();
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
    public double[] vexETime;
    /**
     * 事件节点发生最迟时间
     */
    public double[] vexDTime;
    /**
     * 拓扑序列数组栈
     */
    public  Stack<Integer> TopologyArray=new Stack<>();;

    public AOE(GraphList g) {
        G = g;
        this.vexnum=g.vexnum;
        this.edgenum=g.edgenum;
        this.vexETime =new double[g.vexnum];
        this.vexDTime =new double[g.vexnum];
    }

    public AOE(double[][] matrix) {
        G=new GraphList(matrix);
        this.vexnum=G.vexnum;
        this.edgenum=G.edgenum;
        this.vexETime =new double[G.vexnum];
        this.vexDTime =new double[G.vexnum];
    }

    public AOE(){
        G=new GraphList();
        vexnum=G.vexnum;
        edgenum=G.edgenum;
        this.vexETime =new double[G.vexnum];
        this.vexDTime =new double[G.vexnum];
    }

    /**
     * 节点最早发生时间
     * @return 0 AOV网有回路或不满足条件没有唯一的源点
     *         1 得到事件节点最早发生时间
     */
    public  int VexElyTime(){
        //0入度的顶点栈
        Stack<Integer> s=new Stack<Integer>();
        int i=0,count=0,j=0,k=0;
        //寻找0入度的顶点 压入栈
        boolean[] visit=new boolean[vexnum];
        //用于标记是否二次出栈 是否存在回路
        for( i=0;i<vexnum;i++) {
            if (G.vexNodes[i].indegree == 0) {
                s.push(i);
                break;//AOE 中只有一个源点入度为零
            }
        }
        if(i==vexnum) {
            System.out.println("图没有入度为零的点");
            return 0;}  //当图没有入度为零的点，不满足AOE网的条件
        //栈空时置空时循环结束
        while(!s.empty()) {
            j = s.pop();
            if (!visit[j]) {
                visit[j] = true;
                TopologyArray.push(j);  //入拓扑序列栈
                count++;
                //计数
                for (EdgeNode p = G.vexNodes[j].firstEdge; p != null; p = p.next) {
                    k = p.number;  //j,k为键值对时k为边的入点
                    G.vexNodes[k].indegree--;
                    if (G.vexNodes[k].indegree == 0) {
                        s.push(k);
                    }
                    if (vexETime[k] < (vexETime[j] + p.weight)) {
                        vexETime[k] = vexETime[j] + p.weight;
                        //选取最大的路径长度
                    }
                }
            }
            else break;
        }

        if(count<G.vexnum) {System.out.println("图有回路");return 0;}  //图有回路
        else {System.out.println("AOE网络满足条件最早时间计算操作成功！");return 1;}
     }

    /**
     * 节点最晚发生时间
     * @return  0  没有拓扑序列
     *          1  得到事件节点最晚发生时间
     */
    public  int VexDelayTime(){
        int j=0,k=0,i=0;
        if(TopologyArray.size()<vexnum) return 0;
        while(!TopologyArray.empty())
        {

            j=TopologyArray.pop();
            vexDTime[j]=vexETime[j]>vexDTime[i] ? vexETime[j] :vexDTime[i];
            //最晚发生时间一定大于最早发生时间
            //终点最晚时间等于最早发生时间
            //其他节点 初始最迟时间为前一个节点的最迟时间，取最小
            for (EdgeNode p = G.vexNodes[j].firstEdge; p != null; p = p.next) {
                k = p.number;  //j,k为键值对时k为边的入点
                if (vexDTime[j] > (vexDTime[k] - p.weight)) {
                    vexDTime[j] = vexDTime[k]  - p.weight;
                    //选取最迟的发生的时间
                }
            }
            i=j;
        }
        System.out.println("最晚时间计算操作成功");
        return 1;
    }

    /**
     * @return  事件节点最早发生时间和最迟发生时间差值最小的节点为关键节点
     *          控制不好会拖累整个工程
     */
    public ArrayDeque getKeyNode(){

        Queue<Integer> q=new ArrayDeque<Integer>();
        for(int i=0;i<vexnum;i++) {
            if ((vexDTime[i] - vexETime[i]) == 0) ;
            q.add(i);
        }
        return  (ArrayDeque)q;
    }

    /**
     * 解决AOE网问题得到每个节点的最早时间与最迟时间和关键节点
     */
    public void AOVSolve(){
        if(VexElyTime()==0)
            System.out.println("AOV网有回路或不满足条件没有唯一的源点");
        int a=VexDelayTime();
        keyNode =getKeyNode();
    }

}

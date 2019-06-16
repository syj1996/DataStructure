package MyGraph;

import java.util.*;

/**
 * 最短路径类  包括静态 Dijkstra算法和Floyd算法
 */
public class MinPath {

    public static double[][] pathMatrix;
    /**
     * Dijkstra 算法
     * 按路径长度递增次序产生算法：
     * 把顶点集合V分成两组：
     * （1）S：已求出的顶点的集合（初始时只含有源点V0）
     * （2）V-S=T：尚未确定的顶点集合
     * 将T中顶点按递增的次序加入到S中，保证：
     * （1）从源点V0到S中其他各顶点的长度都不大于从V0到T中任何顶点的最短路径长度
     * （2）每个顶点对应一个距离值
     * S中顶点：从V0到此顶点的长度
     * T中顶点：从V0到此顶点的只包括S中顶点作中间顶点的最短路径长度
     * 依据：可以证明V0到T中顶点Vk的，或是从V0到Vk的直接路径的权值；或是从V0经S中顶点到Vk的路径权值之和
     * （反证法可证）
     * 求最短路径步骤
     * 算法步骤如下：
     * G={V,E}
     * 1. 初始时令 S={V0},T=V-S={其余顶点}，T中顶点对应的距离值
     *      若存在《V0,Vi》《V0,Vi》《V0,Vi》，d(V0,Vi)为《V0,Vi》弧上的权值
     *      若不存在《V0,Vi》，d(V0，Vi)为∞
     * 2. 从T中选取一个与S中顶点有关联边且权值最小的顶点W，加入到S中
     * 3. 对其余T中顶点的距离值进行修改：若加进W作中间顶点，从V0到Vi的距离值缩短，则修改此距离值
     * 重复上述步骤2、3，直到S中包含所有顶点，即W=Vi为止
     * @param src    起点
     * @param dst    终点
     * @param graph  二维矩阵的形式存储  邻接矩阵
     * @return       返回两点之间的距离,和最短路径经过的节点
     * 若graph[i][j] == 100000000, 代表i,j不相连
     */
    public static Node Dijkstra(int src, int dst, double[][] graph){

        boolean[] visit=new boolean[graph[0].length];
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        //将起点加入pq
        Node a=new Node(new vexterix(String.valueOf(src),src), 0.0);
        pq.add(a);
        while(pq.size()>0){
            Node t = pq.poll();

            //当前节点是终点，即可返回最短路径  优先队列进行了自然的排序取出的值为当前最小，每次利用已知最小得到下一个最小
            if(t.node.number == dst) return t;
            //若当前节点已遍历过，跳过当前节点
            if(visit[t.node.number]) continue;
            //将当前节点标记成已遍历
            visit[t.node.number] = true;
            for(int i = 0; i < graph[0].length; i++){
                if(graph[t.node.number][i]!=100000000 && !visit[i]){
                    a=new Node(new vexterix(String.valueOf(i),i), t.cost + graph[t.node.number][i]);
                    a.formNode=t;
                    pq.add(a);
                }
            }
        }
        return null;
    }

    /**
     * 最短路径矩阵
     * @param graph    图存储的邻接矩阵
     * @return         返回二维矩阵 pathMatrix[i][j]表示i与j之间的最短距离
     */
    public static double[][] MinPathMatrix(double[][] graph){
        pathMatrix=new double[graph[0].length][graph[0].length];
        for (int i = 0; i <graph[0].length ; i++) {
            for (int j = 0; j <graph[0].length ; j++) {
                pathMatrix[i][j]=(Dijkstra(i,j,graph)).cost;
                pathMatrix[j][i]=pathMatrix[i][j];
            }
        }
        return pathMatrix;
    }

    public static void Floyd(double[][] graph){
        int i,j,k,next;
        int max=100000000;
        int vexnum=graph[0].length;
        double[][] path=new double[vexnum][vexnum];
        pathMatrix=new double[vexnum][vexnum];

        for ( i = 0; i <vexnum ; i++) {
            for ( j = 0; j <vexnum ; j++) {
                if(graph[i][j]!=100000000) path[i][j]=j;
                else path[i][j]=0;
                pathMatrix[i][j]=graph[i][j];
            }
        }
        
        for(k=0;k<vexnum;k++){
            for(i=0;i<vexnum;i++){
                for(j=0;j<vexnum;j++){
                    if(pathMatrix[i][j]>(pathMatrix[i][k]+pathMatrix[k][j])){
                        pathMatrix[i][j]=pathMatrix[i][k]+pathMatrix[k][j];
                    }

                }
            }
        }
    }
}

/**
 * 距起点的距离节点类 实现比较器
 */
class Node implements Comparable<Node> {
    /**
     * 终点序号
     */
    public vexterix node;

    /**
     * 最短路径上前一个顶点
     */
    public Node formNode;
    public String[] getPath(){
        Node a=this;
        Stack<String> path=new Stack<>();
        path.push(a.node.vex);
       while(a.formNode!=null) {
           a = a.formNode;
           path.push(a.node.vex);
       }
       String[] MinPath=new String[path.size()];
       int i=0;
       while(!path.empty()){
           MinPath[i]=path.pop();
           System.out.print(MinPath[i]+"-> \t");
       }
       return MinPath;
    }

    /**
     * 距离起点的距离
     */
    public double cost;

    public Node()
    {
    }

    public Node(vexterix node, double cost)
    {
        this.node = node;
        this.cost = cost;
        this.formNode=null;
    }

    @Override
    public int compareTo(Node o) {

        if(this.cost<o.cost)
            return -1;
        if(this.cost>o.cost)
            return 1;
        return 0;

    }
}
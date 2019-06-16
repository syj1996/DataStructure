package MyGraph;

import java.util.*;

/**
 * 生成最小生成树(Mininum Spanning Tree)
 * 生成树各边权值总和最小
 * MST最小生成树的性质
 * 设G(V,E) 是一个连通网络,U是顶点集V的一个真子集，若(u,v)是G中所有的(一个端点在U(u属于U), 另一个端点不在U)的边中，具有权值最小的一条边，则一定存在G的一颗最小生成树包括此边(u,v)
 * 利用两种算法
 * Prime算法
 * Kruskar算法
 */
public class MinTree {

    /**
     * 生成树的边集
     */
    private edge[] T;
    /**
     * 最小生成树基本图结构
     */
    private Graph minTree=new Graph();
    /**
     * 邻接矩阵储存图类
     */
    private GraphMatrix graphMatrix;
    /**
     * 邻接矩阵
     */
    private double[][] matrix;
    /**
     * 最小生成树的矩阵 0表示没有路径
     */
    private double[][] MSTmatrix;
    /**
     * 顶点数
     */
    private int vexnum;

    /**
     * 在U集合内的标志为true ，初始值都为false
     */
    private boolean[] flag;
    /**
     * 构造函数  初始化邻接矩阵存储的图得到邻接矩阵
     */
    public MinTree(){
        this.graphMatrix=new GraphMatrix();
        this.matrix=this.graphMatrix.getGraph().weight;
        //PrintGraph.printMSTmatrix(matrix);
        this.vexnum=this.graphMatrix.getVexnum();
        this.MSTmatrix=new double[this.vexnum][this.vexnum];
        this.T=new edge[this.vexnum];
        for (int i = 0; i < this.vexnum; i++){
            this.T[i]=new edge();
        }
        for (int i = 0; i < this.vexnum; i++) {
            for (int j = 0; j <this.vexnum ; j++) {
                this.MSTmatrix[i][j]=0;
            }
        }
//        PrintGraph.printMSTmatrix(this.MSTmatrix);

    }

    /**
     *
     *  prime算法简述
     *           1. 置T为任意一个顶点，初始化候选的边集
     *           2. while(T小于n)
     *             {从候选边集中选出最短的满足MST性质的边(u,v)
     *             将(u,v)的起点和终点扩充到T中
     *             调整候选边集
     *             }
     *           3满足条件退出循环得到最小生成树的边集T
     *
     * @param k  从第k个顶点开始出发生成最小生成树(从0开始)
     *
     * @return double[][]  返回最小生成树 矩阵
     */
    public double[][] Prime(int k){
        this.flag=new boolean[this.vexnum];
        int i=0,j=0,s=0,t=0,m=0,v=0;
        double d,min,max=100000000;
        this.flag[k]=true;
        edge e;
        /**
        * 初始化候选的边集 vexnum个节点生成最小生成树则有n-1条边
        * */
        for (  j = 0; j <this.vexnum; j++) {
            this.T[j].formvex=k;
            this.T[j].endvex=j;
            this.T[j].weight=this.matrix[k][j];
        }

        for ( i = 0; i <this.vexnum ; i++) {
            min=max;m=0;
            for(j=i;j<this.vexnum;j++){
                if(this.T[j].weight<min && !this.flag[this.T[j].endvex]){
                min=this.T[j].weight;
                m=j;}
            }
//            e=this.T[m];this.T[m]=this.T[i];this.T[i]=e;  //this.T[i]即为第i条最小生成树的边
            v=this.T[m].endvex;
            this.flag[v]=true;         //v节点加入U
            //调整候选集
            for(t=0;t<this.vexnum;t++){
                if(this.matrix[v][this.T[t].endvex]<this.T[t].weight && !this.flag[this.T[t].endvex]){
                    this.T[t].weight=this.matrix[v][this.T[t].endvex];
                    this.T[t].formvex=v;
                }
            }
        }

        for(i=0;i<this.vexnum;i++){
            if(i!=k)
            {s=this.T[i].formvex;
            v=this.T[i].endvex;
            this.MSTmatrix[s][v]=this.T[i].weight;
            this.MSTmatrix[v][s]=this.T[i].weight;}
        }
        //输出最小生成树矩阵
//        for ( i = 0; i < this.MSTmatrix[0].length; i++) {
//            for (  j = 0; j <this.MSTmatrix[0].length; j++) {
//                System.out.print(this.MSTmatrix[i][j]+"\t");
//            }
//            System.out.println("\n");
//        }
        return this.MSTmatrix;
    }  //prime算法

    /**
     * Kruskal 算法简述
     *         1.G=(V,E) 是连通网络，
     *         2.初状态 T(V,空) 只有n个顶点而且没有边 边集E为空 每个顶点自成一个连通分图,
     *         3.while(T.edgenum小于n-1)
     *             {
     *                 从E中选取最小的边(u,v);
     *                 从E中删去边(u,v);
     *                 if((u,v)在并入T之后不产生回路) 将边(u,v)并入T中)
     *             }
     *         4. 得到最小生成树
     *         5.关键在判断T(V,E)中是否有回路
     *         自己的思路
     *            通过遍历非连通图T(V,E)  得到连通分图的个数和顶点数,孤立点数，如果存在回路 边数和顶点数存在一定的数学关系
     *         count  表示连通分图的数量  通过int TRAVER(int type)获取
     *         v      表示顶点数
     *         e      表示边数
     *         当 count+e=v+1时 表示存在回路
     *         正常情况下  v=count+e-2
     * @return double[][]  返回最小生成树的邻接矩阵
     */
    public double[][]  Kruskal(){
        /**
         *   构造T 初始 n个节点 没有边 完全非连通图
         * */
        GraphMatrix Tgraph=new GraphMatrix(graphMatrix);
        while(Tgraph.getGraph().E.size()<this.vexnum-1){
            Iterator<edge> it= graphMatrix.getGraph().E.iterator();
            edge a=new edge();
            while(it.hasNext())   a=it.next();      //原图的E实现了自然排序，取出第最后一个即为最小的边
            ((TreeSet)graphMatrix.getGraph().E).pollLast();
            //从原图的E中删除最小的边
            Tgraph.getGraph().E.add(a);           //加入到T的边集中
            Tgraph.getGraph().weight[a.formvex][a.endvex]=a.weight;
            Tgraph.getGraph().weight[a.endvex][a.formvex]=a.weight;
            int count=Tgraph.TRAVER(1,false);     //得到T的连通分图的数量
            int e=Tgraph.getGraph().E.size();    // 边数
            int v=Tgraph.getGraph().V.size();    //顶点数
            if((e+count)==(v+1)){
                ((TreeSet)Tgraph.getGraph().E).remove(a);
            }                //判断是否存在回路
        }
        //  将最小生成树边集转化成邻接矩阵
        int s,v;
        Iterator<edge> it=Tgraph.getGraph().E.iterator();
        while(it.hasNext()){
            edge a=it.next();
            s=a.formvex;
            v=a.endvex;
            this.MSTmatrix[s][v]=a.weight;
            this.MSTmatrix[v][s]=a.weight;
        }
        //输出最小生成树矩阵
        for ( int i = 0; i < this.MSTmatrix[0].length; i++) {
            for (int  j = 0; j <this.MSTmatrix[0].length; j++) {
                System.out.print(this.MSTmatrix[i][j]+"\t");
            }
            System.out.println("\n");
        }
        return this.MSTmatrix;
    }

}



/**
 * 边类 实现Comparable接口降序  通过Arrays.sort(); 排序
 */
class edge implements Comparable<edge>{
    /**
     * 边的起点
     */
    int formvex;
    /**
     * 边的终点
     */
    int endvex;
    /**
     * 边的权值
     */
    double weight;

    public edge() {
        formvex=0;
        endvex=0;
        weight=100000000;
    }

    public edge(int formvex, int endvex, double weight) {
        this.formvex = formvex;
        this.endvex = endvex;
        this.weight = weight;
    }


    /**
     * 降序排序  用于最小生成树时求最小边
     */
    @Override
    public int compareTo(edge o) {
        if(this.weight<=o.weight)
        return 1;
        else return -1;
    }
}

/**
 * 顶点类 实现Comparable接口降序  通过Arrays.sort(); 排序
 */
class vexterix implements Comparable<vexterix>{
    /**
     * 顶点信息
     */
   public String vex;
    /**
     * 顶点序号
     */
   public int number;

    public vexterix(String vex, int number) {
        this.vex = vex;
        this.number = number;
    }

    @Override
    public int compareTo(vexterix o) {
        if(this.number<o.number)
        return -1;
        if(this.number>o.number)
            return 1;
        return 0;
    }
}

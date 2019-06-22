package MyGraph;

import Input.InputStyle;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * 邻接矩阵存储图类  通过邻接矩阵存储图，节点之间没有路径则赋值为无穷大100000000代替
 *
 */
public class GraphMatrix {
    /**
     * 图存储的对象
     */
   private Graph graph=new Graph();

    /**
     * 图的顶点数
     */
   private int vexnum=0;
    /**
     * 图的边数
     */
   private int edgenum=0;

    /**
     * 初始化图，使得访问visited[]置false;
     */
   public void initial() {
        this.visited =new boolean[this.vexnum];
    }

    /**
     * 标记节点是否已经被访问过
     */
    public boolean visited[];

    /**
     * 计数 ，提示文本控制标记
     */
   private int counter;//计数

    /**
     * 构造函数  创建邻接矩阵 存储图的信息
     */
   public GraphMatrix( ){   //默认无向图
       new GraphMatrix(false);
   }  //初始化构造图的邻接矩阵
   public GraphMatrix(boolean type){   //默认无向图
       int i,j,k,vexnum=0,edgenum=0;

       System.out.print("请输入顶点的数量：");
       vexnum = InputStyle.readInt("请重新输入顶点的数量:");

       System.out.print("请输入边的数量：");
       edgenum = InputStyle.readInt("请重新输入边的数量:");

       while(vexnum<=2 ||edgenum<=0 || edgenum>(vexnum*(vexnum-1)/2)){          System.out.println("输入的顶点数和边数不正确请重新输入！");
           System.out.print("请输入顶点的数量：");
           vexnum = InputStyle.readInt("请重新输入顶点的数量:");
           System.out.print("请输入边的数量：");
           edgenum = InputStyle.readInt("请重新输入边的数量:");
       }

       this.edgenum=edgenum;
       this.vexnum=vexnum;
       this.graph.vexterix=new String[vexnum];  //图顶点信息
       this.graph.weight=new double[vexnum][vexnum]; //图的键值对的权值
       this.counter=0;                    //用于计数
       this.graph.E=new TreeSet<edge>();
       this.graph.V=new TreeSet<vexterix>();

       System.out.println("请输入顶点的信息：");     //顶点信息
       for ( i = 0; i < vexnum; i++) {
           System.out.print("第"+(i+1)+"个顶点的信息:");
           graph.vexterix[i]=InputStyle.readString();
           this.graph.V.add(new vexterix(graph.vexterix[i],i));
       }
       for ( i = 0; i < vexnum; i++) {    // 权值矩阵初始化100000000表示无路径
           for ( j = 0; j < vexnum; j++) {
               this.graph.weight[i][j]=100000000;
           }
       }
       if(!type)
       for ( i = 0; i < edgenum; i++) {  //读入edgenum条边
           System.out.println("读取第"+(i+1)+"条边输入 键值对和 权值：");
           j=InputStyle.readInt();
           k=InputStyle.readInt();
           double weight=InputStyle.readDouble();
           if(j>=vexnum || k>=vexnum){
               System.out.println("输入键值对超过数组的下标,请重新输入!");
               i--;continue;
           }
           this.graph.E.add(new edge(j,k,weight));
           graph.weight[j][k]=weight;
           graph.weight[k][j]=weight;

       }
       else for ( i = 0; i < edgenum; i++) {  //读入edgenum条边
           System.out.println("读取第"+(i+1)+"条边输入 键值对和 权值：");
           j=InputStyle.readInt();
           k=InputStyle.readInt();
           double weight=InputStyle.readDouble();
           if(j>=vexnum || k>=vexnum){
               System.out.println("输入键值对超过数组的下标,请重新输入!");
               i--;continue;
           }
           this.graph.E.add(new edge(j,k,weight));
           graph.weight[j][k]=weight;
       }
   }
    /**
     * 利用原有的图邻接矩阵存储对象 构造 一个只有n个顶点的没有边的完全非连通图
     * @param graphMatrix   图邻接矩阵存储对象
     */
    public GraphMatrix(GraphMatrix graphMatrix) {
        this.vexnum=graphMatrix.getVexnum();
        this.edgenum=0;
        this.counter=0;
        this.graph=new Graph();
        this.graph.weight=new double[this.vexnum][this.vexnum];
        this.graph.vexterix=graphMatrix.graph.vexterix;
        this.graph.V=graphMatrix.graph.V;
        this.graph.E=new TreeSet<edge>();
        for(int i=0;i<this.vexnum;i++){
            for (int j = 0; j <this.vexnum ; j++) {
                this.graph.weight[i][j]=100000000;
            }
        }
    }

    /**
     * 通过邻接矩阵创造图结构类对象
     * @param matrix  邻接矩阵
     */
    public GraphMatrix(double[][] matrix){
        this.graph=new Graph(matrix);
        this.vexnum=matrix[0].length;
        int edge=0;
        for (int i = 0; i <this.vexnum ; i++) {
            for (int j = i+1; j < this.vexnum ; j++) {
                if(matrix[i][j]!=100000000.0){
                    edge++;
                }
            }
        } //得到边数
        this.edgenum=edge;
        this.counter=0;
    }

    /**
     * 深度搜索
     * @param i  输入i为开始搜索的位置
     */
    public void DFS(int i){  //深度搜索
        DFS(i,true);
    } //深度搜索
    /**
     * 深度搜索
     * @param i   输入i为开始搜索的位置
     * @param type   true 显示提示信息 false不提示文本作为调用函数
     */
    public void DFS(int i,boolean type){
        int count=this.counter;
        this.counter++;
        if(count==0){initial();}

        this.visited[i]=true;
        if(type)
            System.out.println("node:"+this.graph.vexterix[i]+"开始访问");
        for (int k = 0; k < vexnum; k++) {  //依次搜索vi+1的节点
            if((this.graph.weight[i][k]!=100000000) &&(!this.visited[k]))
            {
                //节点间有路径且没有被访问过  进行访问并对visited做标记
                if(type)
                    System.out.println(this.graph.vexterix[i]+"和"+this.graph.vexterix[k]+"的路径长度为"+this.graph.weight[i][k]);
                DFS(k,type);
            }
        }
        if(type)
        {System.out.println("node:"+this.graph.vexterix[i]+"访问结束!");
            if(count==0)System.out.println("访问结束!");}
    }

    /**广度搜索
     * @param i   输入i为开始搜索的位置
     */
    public void BFS(int i){
      BFS(i,true);
   }    //广度搜索

    /**
     * 广度搜索
     * @param i 输入i为开始搜索的位置
     * @param type true 显示提示文本  false不提示文本作为调用函数
     */
    public void BFS(int i,boolean type){
        int j,k;
        Queue<Integer> queue=new ArrayDeque<>(this.vexnum);
        if(type)
        System.out.println("node:"+this.graph.vexterix[i]+"开始访问");
        this.visited[i]=true;
        queue.add(new Integer(i));
        while(!queue.isEmpty()){
            j=(((ArrayDeque<Integer>) queue).pop()).intValue();
            for ( k = 0; k < this.vexnum; k++) {
                if(this.graph.weight[j][k]!=0 && (!this.visited[k])){
                    {
                        this.visited[k]=true;
                        queue.add(new Integer(k)); //把已经访问的节点入队
                    }
                }
            }
            if(type)
            System.out.println("node:"+this.graph.vexterix[k]+"访问结束");
        }
        if(type)
        System.out.println("访问结束!");
    }
    /**
     * 非连通图的遍历
     * @param type=1   type=1,基础算法为深度搜索 否则为广度搜索
     * @return count   返回非连通图的连通分图个数
     */
    public int TRAVER(int type){
        return TRAVER(type,true);
    }

    /**
     * @param type  type=1 深度搜索 反之为广度搜索
     * @param type1    type1=true 为显示提示文本 false不提示文本作为调用函数
     * @return       返回连通分图的个数
     */
    public int TRAVER(int type,boolean type1){
        initial();
        int count=0;
        for (int i = 0; i < this.vexnum ;i++) {
            if(!this.visited[i]){
                if(type==1){
                    DFS(i,type1);}
                else BFS(i,type1);
                if(type1)
                System.out.println("连通分图"+(count+1)+"遍历完成！");
                count++;
            }

        }
        return count;
    }
    /**
     * 得到图基本结构对象
     * @return 图基本结构对象
     */
    public Graph getGraph() {
        return graph;
    }

    /**
     * 得到顶点数
     * @return  顶点数
     */
    public int getVexnum() {
        return vexnum;
    }

    /**
     *得到边数
     * @return 边数
     */
    public int getEdgenum() {
        return edgenum;
    }

}

/**
 * 图基本结构类
 */
class Graph{
    /**
     * 顶点的信息
     */
    public  String[] vexterix;   //顶点的信息
    /**
     * 边的权值  0表示点与点之间没有路径
     */
    public  double weight[][];   //边的权值  0表示点与点之间没有路径

    public Set<edge> E;
    public Set<vexterix> V;
    /**
     * 通过邻接矩阵构造图基本结构
     * @param matrix   邻接矩阵
     */
    public Graph(double[][] matrix) {
        this.vexterix=new String[matrix[0].length];
        this.V=new TreeSet<vexterix>();
        this.E=new TreeSet<edge>();
        char t='a';
        for (int i = 0; i <matrix[0].length ; i++,t++) {
            Character k=new Character(t);
            this.vexterix[i]=k.toString();
            this.V.add(new vexterix(this.vexterix[i],i));
        }
        for (int i = 0; i <matrix[0].length ; i++) {
            for (int j = i; j <matrix[0].length  ; j++) {
                if(matrix[i][j]!=100000000)
                 this.E.add(new edge(i,j,matrix[i][j]));
            }
        }
        this.weight=matrix;
    }

    /**
     * 通过顶点数创造基本图结构 只有n个顶点和没有边的完全非连通图的结构
     * @param vexnum  顶点数
     */
    public Graph(int vexnum){
        this.vexterix=new String[vexnum];
        this.weight=new double[vexnum][vexnum];
        this.E=new TreeSet<edge>();
        this.V=new TreeSet<vexterix>();
        for (int i = 0; i <vexnum ; i++) {

            this.vexterix[i]=String.valueOf(i);
            this.V.add(new vexterix(this.vexterix[i],i));

            for (int j = 0; j <vexnum ; j++) {
                this.weight[i][j]=100000000;
            }
        }

    }

    /**
     * @param vexterix  顶点的信息
     * @param weight    边的权值  0表示点与点之间没有路径
     * @param e         边集
     * @param v         顶点集
     */
    public Graph(String[] vexterix, double[][] weight, Set<edge> e, Set<vexterix> v) {
        this.vexterix = vexterix;
        this.weight = weight;
        E = e;
        V = v;
    }

    /**
     * 通过顶点信息和邻接矩阵构造图基本结构对象
     * @param vexterix   顶点的信息
     * @param weight    边的权值  0表示点与点之间没有路径
     */
    public Graph(String[] vexterix, double[][] weight) {
        this.vexterix = vexterix;
        this.weight = weight;
    }

    /**
     *构造函数
     */
    public Graph() {
    }
}
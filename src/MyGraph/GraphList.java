package MyGraph;

import Input.InputStyle;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 邻接表存储图类   深度 广度 拓扑排序 关键路径
 */
public class GraphList {
    /**
     * 顶点的数组
     */
    public VexNode[] vexNodes;    //顶点的数组
    /**
     *顶点数
     */
    public int vexnum;           //顶点数
    /**
     * 边数
     */
    public int edgenum;          //边数

    /**
     * 初始化图的访问遍历标志   初始值为false
     */
    public void initial() {
        this.visited = new boolean[this.vexnum];
    }

    /**
     * 判断是否被访问,第二次访问时要先对其置0
     */
    public boolean visited[];    //判断是否被访问,第二次访问时要先对其置0
    /**
     * 计数，提示文本控制
     */
    private int counter;

    /**
     * 构造函数  创建默认无向邻接表存储图
     */
    public GraphList() {
        new GraphList(false);
    }     //构造函数，创建邻接表

    /**
     * @param type  true  有向   false 无向
     */
    public GraphList(boolean type){
        int i,j,k,vexnum,edgenum;
        double weight;
        EdgeNode s;

        Scanner src=new Scanner(System.in);
        System.out.print("请输入顶点数：");
        vexnum= InputStyle.readInt("请重新输入顶点的数量:");
        System.out.print("请输入边数：");
        edgenum=InputStyle.readInt("请重新输入边的数量:");

        while(vexnum<=2 ||edgenum<=0 || edgenum>(vexnum*(vexnum-1)/2)){
            System.out.println("输入的顶点数和边数不正确请重新输入！");
            System.out.print("请输入顶点的数量：");
            vexnum=src.nextInt();
            System.out.print("请输入边的数量：");
            edgenum=src.nextInt();
        }

        this.vexnum = vexnum;   //顶点数
        this.edgenum = edgenum;  //边数
        this.vexNodes=new VexNode[vexnum];  //顶点类数组
        this.counter=0;        //用于计数

        System.out.println("请输入顶点的信息：");
        for ( i = 0; i <vexnum; i++) {
            j=i+1;
            System.out.print("第"+j+"个节点信息：");
            this.vexNodes[i]=new VexNode(InputStyle.readString(),null);
        }
        if(!type)
        for ( k = 0; k <edgenum; k++) {
            System.out.println("输入"+(k+1)+"条边的键值对和权重:");
            i=InputStyle.readInt();   //邻接表的顶点序号
            j=InputStyle.readInt();  //依次输入e条边 用键值对 <i,j>表示
            weight=InputStyle.readDouble();  //输入的边的权值

            if(j>=vexnum || i>=vexnum){
                System.out.println("输入键值对超过数组的下标,请重新输入!");
                k--;continue;
            }

            s=new EdgeNode();
            s.number=j;     //用键值对的后一个点用作邻接点的序号
            s.weight=weight;  //表示 i，j 边的权值
            s.next=this.vexNodes[i].firstEdge;  //链表尾插法
            this.vexNodes[i].firstEdge=s;

            s=new EdgeNode();
            s.number=i;     //用键值对的后一个点用作邻接点的序号
            s.weight=weight;  //表示 i，j 边的权值
            s.next=this.vexNodes[j].firstEdge;  //链表尾插法
            this.vexNodes[j].firstEdge=s;
        }
        else  for ( k = 0; k <edgenum; k++) {
            System.out.println("输入"+(k+1)+"条边的键值对和权重:");
            i=InputStyle.readInt();   //邻接表的顶点序号
            j=InputStyle.readInt();  //依次输入e条边 用键值对 <i,j>表示
            weight=InputStyle.readDouble();  //输入的边的权值

            if(j>=vexnum || i>=vexnum){
                System.out.println("输入键值对超过数组的下标,请重新输入!");
                k--;continue;
            }
            this.vexNodes[j].indegree++;  //针对有向图记录每个点的入度
            this.vexNodes[i].outdegree++;  //记录出度
            s=new EdgeNode();
            s.number=j;     //用键值对的后一个点用作邻接点的序号
            s.weight=weight;  //表示 i，j 边的权值
            s.next=this.vexNodes[i].firstEdge;  //链表尾插法
            this.vexNodes[i].firstEdge=s;
        }

    }

    public GraphList(double[][] matrix){
        int i=0,j=0,vexnum=matrix[0].length,edgenum=0;
        EdgeNode s = null;
        this.vexnum = vexnum;   //顶点数
        for ( i = 0; i <this.vexnum ; i++) {
            for ( j = i+1; j < this.vexnum ; j++) {
                if(matrix[i][j]!=100000000.0){
                    edgenum++;
                }
            }
        } //得到边数
        this.edgenum = edgenum;  //边数


        this.vexNodes=new VexNode[vexnum];  //顶点类数组
        char k='a';
        for ( i =0; i <vexnum ; i++,k++) {
            Character a=new Character(k);
            this.vexNodes[i]=new VexNode(a.toString());  //顶点类数组
        }
        this.counter=0;        //用于计数

        for ( i = 0; i < vexnum; i++)
           for ( j = 0; j <vexnum; j++) {
               if (matrix[i][j] != 100000000) {
                   this.vexNodes[j].indegree++;
                   this.vexNodes[i].outdegree++;
                   s = new EdgeNode();
                   s.number = j;     //用键值对的后一个点用作邻接点的序号
                   s.weight = matrix[i][j];  //表示 i，j 边的权值
                   s.next = this.vexNodes[i].firstEdge;  //链表尾插法
                   this.vexNodes[i].firstEdge = s;
               }
           }
    }

    /**
     * 深度搜索
     * @param i   输入i为开始搜索的位置
     */
    public void DFSL(int i){
        int count=this.counter;
        this.counter++;
        if(count==0){initial();}  //当开始遍历时初始化visited数组

        EdgeNode p;
        this.visited[i]=true;

        System.out.println("node:"+this.vexNodes[i].vexterix+"开始访问");
        p=this.vexNodes[i].firstEdge;
        while(p!=null){
            if(!this.visited[p.number]){
                System.out.println(this.vexNodes[i].vexterix+"和"+this.vexNodes[p.number].vexterix+"的路径长度为"+p.weight);
                DFSL(p.number);
            }
            p=p.next;
        }
        System.out.println("node:"+this.vexNodes[i].vexterix+"访问结束!");
        if(count==0)System.out.println("访问结束!");
    }  //连通图深度搜索

    /**
     * 广度搜索
     * @param i  输入i为开始搜索的位置
     */
    public void BFSL(int i){
        initial();
        int k;
        EdgeNode p;
        Queue<Integer> queue=new ArrayDeque<>();
        this.visited[i]=true;
        queue.add(i);
        while(!queue.isEmpty()){
            k=((ArrayDeque<Integer>) queue).pop();
            System.out.println("node:"+this.vexNodes[k].vexterix+"开始访问");
            p=this.vexNodes[k].firstEdge;
            while(p!=null){
                if(!this.visited[p.number]){
                    System.out.println(this.vexNodes[k].vexterix+"和"+this.vexNodes[p.number].vexterix+"的路径长度为"+p.weight);
                    this.visited[p.number]=true;
                    queue.add(p.number);
                }
                p=p.next;
            }
            System.out.println("node:"+this.vexNodes[k].vexterix+"访问结束");
        }
        System.out.println("访问结束!");
    }//连通图广度搜索

    /**
     * 非连通图的遍历
     * @param type=1   type=1,基础算法为深度搜索 否则为广度搜索
     * @return count   返回非连通图的个数包括孤立的点
     */
    public int TRAVER(int type){
        initial();
        int count=0;
        for (int i = 0; i < this.vexnum ;i++) {
            if(!this.visited[i]){
                if(type==1)
                    DFSL(i);
                else BFSL(i);
                System.out.println("连通分图"+i+"遍历完成！");
                count++;
            }
        }
        return count;
    }
}

/**
 * 邻接表的边表节点的类
 */
class EdgeNode{
    /**
     * 存放邻接表的边表的序号
     */
    public int number;    //存放邻接点的序号
    /**
     * 指向边集的下一个节点
     */
    public EdgeNode next;  //指向下一个节点
    /**
     * 边上的权值
     */
    public double weight;   //边上的权值

    /**
     * 通过  存放邻接点的序号，指向下一个节点，边上的权值  构造邻接表边表上的节点
     * @param number   存放邻接点的序号
     * @param next     指向下一个节点
     * @param weight   边上的权值
     */
    public EdgeNode(int number, EdgeNode next, double weight) {
        this.number = number;
        this.next = next;
        this.weight = weight;
    }

    /**
     * 构造函数
     */
    public EdgeNode() {
    }
}

/**
 * 邻接表的顶点类
 */
class VexNode {
    /**
     * 顶点节点信息
     */
    public String vexterix;           //顶点节点信息
    /**
     * 顶点的头指针指向最后序号的一个边  从尾到顶点
     */
    public EdgeNode firstEdge;      //顶点的头指针指向最后序号的一个边  从尾到顶点
    /**
     * 存放的顶点入度
     */
    public int indegree=0;   //存放的顶点入度
    public int outdegree=0;  //出度
    /**
     * 构造函数
     * @param  vexterix 顶点信息
     */
    public VexNode(String vexterix) {
        this.vexterix=vexterix;
        this.firstEdge=null;
    }

    /**
     * 通过顶点节点信息和顶点头指针构造 邻接表顶点类
     * @param vexterix  顶点节点信息
     * @param firstEdge 顶点的头指针指向最后序号的一个边  从尾到顶点
     */
    public VexNode(String vexterix, EdgeNode firstEdge) {
        this.vexterix = vexterix;
        this.firstEdge = firstEdge;
    }
}

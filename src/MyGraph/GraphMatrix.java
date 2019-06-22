package MyGraph;

import Input.InputStyle;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * �ڽӾ���洢ͼ��  ͨ���ڽӾ���洢ͼ���ڵ�֮��û��·����ֵΪ�����100000000����
 *
 */
public class GraphMatrix {
    /**
     * ͼ�洢�Ķ���
     */
   private Graph graph=new Graph();

    /**
     * ͼ�Ķ�����
     */
   private int vexnum=0;
    /**
     * ͼ�ı���
     */
   private int edgenum=0;

    /**
     * ��ʼ��ͼ��ʹ�÷���visited[]��false;
     */
   public void initial() {
        this.visited =new boolean[this.vexnum];
    }

    /**
     * ��ǽڵ��Ƿ��Ѿ������ʹ�
     */
    public boolean visited[];

    /**
     * ���� ����ʾ�ı����Ʊ��
     */
   private int counter;//����

    /**
     * ���캯��  �����ڽӾ��� �洢ͼ����Ϣ
     */
   public GraphMatrix( ){   //Ĭ������ͼ
       new GraphMatrix(false);
   }  //��ʼ������ͼ���ڽӾ���
   public GraphMatrix(boolean type){   //Ĭ������ͼ
       int i,j,k,vexnum=0,edgenum=0;

       System.out.print("�����붥���������");
       vexnum = InputStyle.readInt("���������붥�������:");

       System.out.print("������ߵ�������");
       edgenum = InputStyle.readInt("����������ߵ�����:");

       while(vexnum<=2 ||edgenum<=0 || edgenum>(vexnum*(vexnum-1)/2)){          System.out.println("����Ķ������ͱ�������ȷ���������룡");
           System.out.print("�����붥���������");
           vexnum = InputStyle.readInt("���������붥�������:");
           System.out.print("������ߵ�������");
           edgenum = InputStyle.readInt("����������ߵ�����:");
       }

       this.edgenum=edgenum;
       this.vexnum=vexnum;
       this.graph.vexterix=new String[vexnum];  //ͼ������Ϣ
       this.graph.weight=new double[vexnum][vexnum]; //ͼ�ļ�ֵ�Ե�Ȩֵ
       this.counter=0;                    //���ڼ���
       this.graph.E=new TreeSet<edge>();
       this.graph.V=new TreeSet<vexterix>();

       System.out.println("�����붥�����Ϣ��");     //������Ϣ
       for ( i = 0; i < vexnum; i++) {
           System.out.print("��"+(i+1)+"���������Ϣ:");
           graph.vexterix[i]=InputStyle.readString();
           this.graph.V.add(new vexterix(graph.vexterix[i],i));
       }
       for ( i = 0; i < vexnum; i++) {    // Ȩֵ�����ʼ��100000000��ʾ��·��
           for ( j = 0; j < vexnum; j++) {
               this.graph.weight[i][j]=100000000;
           }
       }
       if(!type)
       for ( i = 0; i < edgenum; i++) {  //����edgenum����
           System.out.println("��ȡ��"+(i+1)+"�������� ��ֵ�Ժ� Ȩֵ��");
           j=InputStyle.readInt();
           k=InputStyle.readInt();
           double weight=InputStyle.readDouble();
           if(j>=vexnum || k>=vexnum){
               System.out.println("�����ֵ�Գ���������±�,����������!");
               i--;continue;
           }
           this.graph.E.add(new edge(j,k,weight));
           graph.weight[j][k]=weight;
           graph.weight[k][j]=weight;

       }
       else for ( i = 0; i < edgenum; i++) {  //����edgenum����
           System.out.println("��ȡ��"+(i+1)+"�������� ��ֵ�Ժ� Ȩֵ��");
           j=InputStyle.readInt();
           k=InputStyle.readInt();
           double weight=InputStyle.readDouble();
           if(j>=vexnum || k>=vexnum){
               System.out.println("�����ֵ�Գ���������±�,����������!");
               i--;continue;
           }
           this.graph.E.add(new edge(j,k,weight));
           graph.weight[j][k]=weight;
       }
   }
    /**
     * ����ԭ�е�ͼ�ڽӾ���洢���� ���� һ��ֻ��n�������û�бߵ���ȫ����ͨͼ
     * @param graphMatrix   ͼ�ڽӾ���洢����
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
     * ͨ���ڽӾ�����ͼ�ṹ�����
     * @param matrix  �ڽӾ���
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
        } //�õ�����
        this.edgenum=edge;
        this.counter=0;
    }

    /**
     * �������
     * @param i  ����iΪ��ʼ������λ��
     */
    public void DFS(int i){  //�������
        DFS(i,true);
    } //�������
    /**
     * �������
     * @param i   ����iΪ��ʼ������λ��
     * @param type   true ��ʾ��ʾ��Ϣ false����ʾ�ı���Ϊ���ú���
     */
    public void DFS(int i,boolean type){
        int count=this.counter;
        this.counter++;
        if(count==0){initial();}

        this.visited[i]=true;
        if(type)
            System.out.println("node:"+this.graph.vexterix[i]+"��ʼ����");
        for (int k = 0; k < vexnum; k++) {  //��������vi+1�Ľڵ�
            if((this.graph.weight[i][k]!=100000000) &&(!this.visited[k]))
            {
                //�ڵ����·����û�б����ʹ�  ���з��ʲ���visited�����
                if(type)
                    System.out.println(this.graph.vexterix[i]+"��"+this.graph.vexterix[k]+"��·������Ϊ"+this.graph.weight[i][k]);
                DFS(k,type);
            }
        }
        if(type)
        {System.out.println("node:"+this.graph.vexterix[i]+"���ʽ���!");
            if(count==0)System.out.println("���ʽ���!");}
    }

    /**�������
     * @param i   ����iΪ��ʼ������λ��
     */
    public void BFS(int i){
      BFS(i,true);
   }    //�������

    /**
     * �������
     * @param i ����iΪ��ʼ������λ��
     * @param type true ��ʾ��ʾ�ı�  false����ʾ�ı���Ϊ���ú���
     */
    public void BFS(int i,boolean type){
        int j,k;
        Queue<Integer> queue=new ArrayDeque<>(this.vexnum);
        if(type)
        System.out.println("node:"+this.graph.vexterix[i]+"��ʼ����");
        this.visited[i]=true;
        queue.add(new Integer(i));
        while(!queue.isEmpty()){
            j=(((ArrayDeque<Integer>) queue).pop()).intValue();
            for ( k = 0; k < this.vexnum; k++) {
                if(this.graph.weight[j][k]!=0 && (!this.visited[k])){
                    {
                        this.visited[k]=true;
                        queue.add(new Integer(k)); //���Ѿ����ʵĽڵ����
                    }
                }
            }
            if(type)
            System.out.println("node:"+this.graph.vexterix[k]+"���ʽ���");
        }
        if(type)
        System.out.println("���ʽ���!");
    }
    /**
     * ����ͨͼ�ı���
     * @param type=1   type=1,�����㷨Ϊ������� ����Ϊ�������
     * @return count   ���ط���ͨͼ����ͨ��ͼ����
     */
    public int TRAVER(int type){
        return TRAVER(type,true);
    }

    /**
     * @param type  type=1 ������� ��֮Ϊ�������
     * @param type1    type1=true Ϊ��ʾ��ʾ�ı� false����ʾ�ı���Ϊ���ú���
     * @return       ������ͨ��ͼ�ĸ���
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
                System.out.println("��ͨ��ͼ"+(count+1)+"������ɣ�");
                count++;
            }

        }
        return count;
    }
    /**
     * �õ�ͼ�����ṹ����
     * @return ͼ�����ṹ����
     */
    public Graph getGraph() {
        return graph;
    }

    /**
     * �õ�������
     * @return  ������
     */
    public int getVexnum() {
        return vexnum;
    }

    /**
     *�õ�����
     * @return ����
     */
    public int getEdgenum() {
        return edgenum;
    }

}

/**
 * ͼ�����ṹ��
 */
class Graph{
    /**
     * �������Ϣ
     */
    public  String[] vexterix;   //�������Ϣ
    /**
     * �ߵ�Ȩֵ  0��ʾ�����֮��û��·��
     */
    public  double weight[][];   //�ߵ�Ȩֵ  0��ʾ�����֮��û��·��

    public Set<edge> E;
    public Set<vexterix> V;
    /**
     * ͨ���ڽӾ�����ͼ�����ṹ
     * @param matrix   �ڽӾ���
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
     * ͨ���������������ͼ�ṹ ֻ��n�������û�бߵ���ȫ����ͨͼ�Ľṹ
     * @param vexnum  ������
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
     * @param vexterix  �������Ϣ
     * @param weight    �ߵ�Ȩֵ  0��ʾ�����֮��û��·��
     * @param e         �߼�
     * @param v         ���㼯
     */
    public Graph(String[] vexterix, double[][] weight, Set<edge> e, Set<vexterix> v) {
        this.vexterix = vexterix;
        this.weight = weight;
        E = e;
        V = v;
    }

    /**
     * ͨ��������Ϣ���ڽӾ�����ͼ�����ṹ����
     * @param vexterix   �������Ϣ
     * @param weight    �ߵ�Ȩֵ  0��ʾ�����֮��û��·��
     */
    public Graph(String[] vexterix, double[][] weight) {
        this.vexterix = vexterix;
        this.weight = weight;
    }

    /**
     *���캯��
     */
    public Graph() {
    }
}
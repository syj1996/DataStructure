package MyGraph;

import java.util.*;

/**
 * ������С������(Mininum Spanning Tree)
 * ����������Ȩֵ�ܺ���С
 * MST��С������������
 * ��G(V,E) ��һ����ͨ����,U�Ƕ��㼯V��һ�����Ӽ�����(u,v)��G�����е�(һ���˵���U(u����U), ��һ���˵㲻��U)�ı��У�����Ȩֵ��С��һ���ߣ���һ������G��һ����С�����������˱�(u,v)
 * ���������㷨
 * Prime�㷨
 * Kruskar�㷨
 */
public class MinTree {

    /**
     * �������ı߼�
     */
    private edge[] T;
    /**
     * ��С����������ͼ�ṹ
     */
    private Graph minTree=new Graph();
    /**
     * �ڽӾ��󴢴�ͼ��
     */
    private GraphMatrix graphMatrix;
    /**
     * �ڽӾ���
     */
    private double[][] matrix;
    /**
     * ��С�������ľ��� 0��ʾû��·��
     */
    private double[][] MSTmatrix;
    /**
     * ������
     */
    private int vexnum;

    /**
     * ��U�����ڵı�־Ϊtrue ����ʼֵ��Ϊfalse
     */
    private boolean[] flag;
    /**
     * ���캯��  ��ʼ���ڽӾ���洢��ͼ�õ��ڽӾ���
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
     *  prime�㷨����
     *           1. ��TΪ����һ�����㣬��ʼ����ѡ�ı߼�
     *           2. while(TС��n)
     *             {�Ӻ�ѡ�߼���ѡ����̵�����MST���ʵı�(u,v)
     *             ��(u,v)�������յ����䵽T��
     *             ������ѡ�߼�
     *             }
     *           3���������˳�ѭ���õ���С�������ı߼�T
     *
     * @param k  �ӵ�k�����㿪ʼ����������С������(��0��ʼ)
     *
     * @return double[][]  ������С������ ����
     */
    public double[][] Prime(int k){
        this.flag=new boolean[this.vexnum];
        int i=0,j=0,s=0,t=0,m=0,v=0;
        double d,min,max=100000000;
        this.flag[k]=true;
        edge e;
        /**
        * ��ʼ����ѡ�ı߼� vexnum���ڵ�������С����������n-1����
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
//            e=this.T[m];this.T[m]=this.T[i];this.T[i]=e;  //this.T[i]��Ϊ��i����С�������ı�
            v=this.T[m].endvex;
            this.flag[v]=true;         //v�ڵ����U
            //������ѡ��
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
        //�����С����������
//        for ( i = 0; i < this.MSTmatrix[0].length; i++) {
//            for (  j = 0; j <this.MSTmatrix[0].length; j++) {
//                System.out.print(this.MSTmatrix[i][j]+"\t");
//            }
//            System.out.println("\n");
//        }
        return this.MSTmatrix;
    }  //prime�㷨

    /**
     * Kruskal �㷨����
     *         1.G=(V,E) ����ͨ���磬
     *         2.��״̬ T(V,��) ֻ��n���������û�б� �߼�EΪ�� ÿ�������Գ�һ����ͨ��ͼ,
     *         3.while(T.edgenumС��n-1)
     *             {
     *                 ��E��ѡȡ��С�ı�(u,v);
     *                 ��E��ɾȥ��(u,v);
     *                 if((u,v)�ڲ���T֮�󲻲�����·) ����(u,v)����T��)
     *             }
     *         4. �õ���С������
     *         5.�ؼ����ж�T(V,E)���Ƿ��л�·
     *         �Լ���˼·
     *            ͨ����������ͨͼT(V,E)  �õ���ͨ��ͼ�ĸ����Ͷ�����,����������������ڻ�· �����Ͷ���������һ������ѧ��ϵ
     *         count  ��ʾ��ͨ��ͼ������  ͨ��int TRAVER(int type)��ȡ
     *         v      ��ʾ������
     *         e      ��ʾ����
     *         �� count+e=v+1ʱ ��ʾ���ڻ�·
     *         ���������  v=count+e-2
     * @return double[][]  ������С���������ڽӾ���
     */
    public double[][]  Kruskal(){
        /**
         *   ����T ��ʼ n���ڵ� û�б� ��ȫ����ͨͼ
         * */
        GraphMatrix Tgraph=new GraphMatrix(graphMatrix);
        while(Tgraph.getGraph().E.size()<this.vexnum-1){
            Iterator<edge> it= graphMatrix.getGraph().E.iterator();
            edge a=new edge();
            while(it.hasNext())   a=it.next();      //ԭͼ��Eʵ������Ȼ����ȡ�������һ����Ϊ��С�ı�
            ((TreeSet)graphMatrix.getGraph().E).pollLast();
            //��ԭͼ��E��ɾ����С�ı�
            Tgraph.getGraph().E.add(a);           //���뵽T�ı߼���
            Tgraph.getGraph().weight[a.formvex][a.endvex]=a.weight;
            Tgraph.getGraph().weight[a.endvex][a.formvex]=a.weight;
            int count=Tgraph.TRAVER(1,false);     //�õ�T����ͨ��ͼ������
            int e=Tgraph.getGraph().E.size();    // ����
            int v=Tgraph.getGraph().V.size();    //������
            if((e+count)==(v+1)){
                ((TreeSet)Tgraph.getGraph().E).remove(a);
            }                //�ж��Ƿ���ڻ�·
        }
        //  ����С�������߼�ת�����ڽӾ���
        int s,v;
        Iterator<edge> it=Tgraph.getGraph().E.iterator();
        while(it.hasNext()){
            edge a=it.next();
            s=a.formvex;
            v=a.endvex;
            this.MSTmatrix[s][v]=a.weight;
            this.MSTmatrix[v][s]=a.weight;
        }
        //�����С����������
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
 * ���� ʵ��Comparable�ӿڽ���  ͨ��Arrays.sort(); ����
 */
class edge implements Comparable<edge>{
    /**
     * �ߵ����
     */
    int formvex;
    /**
     * �ߵ��յ�
     */
    int endvex;
    /**
     * �ߵ�Ȩֵ
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
     * ��������  ������С������ʱ����С��
     */
    @Override
    public int compareTo(edge o) {
        if(this.weight<=o.weight)
        return 1;
        else return -1;
    }
}

/**
 * ������ ʵ��Comparable�ӿڽ���  ͨ��Arrays.sort(); ����
 */
class vexterix implements Comparable<vexterix>{
    /**
     * ������Ϣ
     */
   public String vex;
    /**
     * �������
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

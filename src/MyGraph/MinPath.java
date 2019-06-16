package MyGraph;

import java.util.*;

/**
 * ���·����  ������̬ Dijkstra�㷨��Floyd�㷨
 */
public class MinPath {

    public static double[][] pathMatrix;
    /**
     * Dijkstra �㷨
     * ��·�����ȵ�����������㷨��
     * �Ѷ��㼯��V�ֳ����飺
     * ��1��S��������Ķ���ļ��ϣ���ʼʱֻ����Դ��V0��
     * ��2��V-S=T����δȷ���Ķ��㼯��
     * ��T�ж��㰴�����Ĵ�����뵽S�У���֤��
     * ��1����Դ��V0��S������������ĳ��ȶ������ڴ�V0��T���κζ�������·������
     * ��2��ÿ�������Ӧһ������ֵ
     * S�ж��㣺��V0���˶���ĳ���
     * T�ж��㣺��V0���˶����ֻ����S�ж������м䶥������·������
     * ���ݣ�����֤��V0��T�ж���Vk�ģ����Ǵ�V0��Vk��ֱ��·����Ȩֵ�����Ǵ�V0��S�ж��㵽Vk��·��Ȩֵ֮��
     * ����֤����֤��
     * �����·������
     * �㷨�������£�
     * G={V,E}
     * 1. ��ʼʱ�� S={V0},T=V-S={���ඥ��}��T�ж����Ӧ�ľ���ֵ
     *      �����ڡ�V0,Vi����V0,Vi����V0,Vi����d(V0,Vi)Ϊ��V0,Vi�����ϵ�Ȩֵ
     *      �������ڡ�V0,Vi����d(V0��Vi)Ϊ��
     * 2. ��T��ѡȡһ����S�ж����й�������Ȩֵ��С�Ķ���W�����뵽S��
     * 3. ������T�ж���ľ���ֵ�����޸ģ����ӽ�W���м䶥�㣬��V0��Vi�ľ���ֵ���̣����޸Ĵ˾���ֵ
     * �ظ���������2��3��ֱ��S�а������ж��㣬��W=ViΪֹ
     * @param src    ���
     * @param dst    �յ�
     * @param graph  ��ά�������ʽ�洢  �ڽӾ���
     * @return       ��������֮��ľ���,�����·�������Ľڵ�
     * ��graph[i][j] == 100000000, ����i,j������
     */
    public static Node Dijkstra(int src, int dst, double[][] graph){

        boolean[] visit=new boolean[graph[0].length];
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        //��������pq
        Node a=new Node(new vexterix(String.valueOf(src),src), 0.0);
        pq.add(a);
        while(pq.size()>0){
            Node t = pq.poll();

            //��ǰ�ڵ����յ㣬���ɷ������·��  ���ȶ��н�������Ȼ������ȡ����ֵΪ��ǰ��С��ÿ��������֪��С�õ���һ����С
            if(t.node.number == dst) return t;
            //����ǰ�ڵ��ѱ�������������ǰ�ڵ�
            if(visit[t.node.number]) continue;
            //����ǰ�ڵ��ǳ��ѱ���
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
     * ���·������
     * @param graph    ͼ�洢���ڽӾ���
     * @return         ���ض�ά���� pathMatrix[i][j]��ʾi��j֮�����̾���
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
 * �����ľ���ڵ��� ʵ�ֱȽ���
 */
class Node implements Comparable<Node> {
    /**
     * �յ����
     */
    public vexterix node;

    /**
     * ���·����ǰһ������
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
     * �������ľ���
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
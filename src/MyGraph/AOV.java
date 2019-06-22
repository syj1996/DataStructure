package MyGraph;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * ���ִ��������У����ǳ�������ͼ�������ͷ���һ��̵ļƻ���ʵʩ���̣�һ�����̳�����Ϊ���С���ӹ��̣���Щ�ӹ��̱���Ϊ���Activity)��������ͼ�����Զ����ʾ�������߱�ʾ�֮����Ⱥ��ϵ��������ͼ���ΪAOV����
 */
public class AOV {
    /**
     * �ڽӱ�
     */
    public GraphList G;
    /**
     * ��������
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
     * ��������
     * Ӧ�ã�AOV�� ����˳�����Ⱥ� ��ֻ�ܽ��д��й����£�����������̵�һ�ֿ����Է���
     * @return int
     *      -1 -- ʧ��(�����ڴ治���ԭ����)
     *      0 -- �ɹ����򣬲�������
     *      1 -- ʧ��(������ͼ���л���)
     */
    public int TopologySort() {
        return TopologySort(this.G,this.Tsort);
    }

    /**
     * ��̬����
     * @param G   �ڽӱ�
     * @param Tsort   ��������
     * @return    -1 -- ʧ��(�����ڴ治���ԭ����)
     *             0 -- �ɹ����򣬲�������
     *             1 -- ʧ��(������ͼ���л���)
     */
    public static int TopologySort(GraphList G,int[] Tsort){
        EdgeNode p;
        Queue Q=new ArrayDeque();
        int m=0,k=0;
        //�����������Ϊ���������
        for (int i = 0; i <this.G.vexnum ; i++) {
            if(G.vexNodes[i].indegree==0) Q.add(i);
        }

        while(!Q.isEmpty()){
            int j=(int) ((ArrayDeque) Q).pop();
            Tsort[m]=j;     //��J����������
            m++;
            p=G.vexNodes[j].firstEdge;  //ָ��ǰ����ڵ�ı߱�
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
            System.out.println("�����л�·!");
            return 0;
        }
        else return 1;
    }
}

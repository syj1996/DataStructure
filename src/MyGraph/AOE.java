package MyGraph;

import java.util.Stack;

/**
 * AOE��
 * ��һ����Ȩ�������޻�ͼ��
 * ����ֻ��һ�����Ϊ��ĵ㣨��ΪԴ�㣩��һ������Ϊ��ĵ㣨��Ϊ��㣩��
 * ���У������ʾ�¼���Event��������ʾ���Ȩ��ʾ�������ʱ�䡣
 * ͨ����AOE�����������㹤�̵����ʱ�䡣
 * ����������������Ҫ����һ��������������̵Ĵ���¼��ͻʱ������ͼAOE����
 * ���ǰ�·���ϸ������������ʱ��֮�ͳ�Ϊ·�����ȣ�
 * ��Դ�㵽��������󳤶ȵ�·���йؼ�·�����ڹؼ�·���ϵĻ�йؼ����
 *
 * ��1���¼������緢��ʱ��etv(earliest time of vertex): ������Vk�����緢��ʱ�䡣
 * ��2���¼���������ʱ��ltv(latest time of vertex): ������Vk��������ʱ�䡣Ҳ����ÿ�������Ӧ���¼�������Ҫ��ʼ��ʱ�䣬������ʱ�佫�������������ڡ�
 * ��3��������翪��ʱ��ete(earliest time of edge): ����ak�����緢��ʱ�䡣
 * ��4�����������ʱ��lte(latest time of edge): ����ak��������ʱ�䣬
 * Ҳ���ǲ��Ƴٹ��ڵ�������ʱ�䡣
 * 
 */
public class AOE {
    /**
     * �ڽӱ�ͼ�洢
     */
    public GraphList G;
    /**
     * ������
     */
    public int vexnum;
    /**
     * ����
     */
    public int edgenum;

    /**
     * �������¼����緢����ʱ��
     */
    public int[] vexTime;
    /**
     * ������������ջ
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
        //0��ȵĶ���ջ
        Stack<Integer> s=new Stack<Integer>();
        int count=0,j=0,k=0;
        //Ѱ��0��ȵĶ���
        for(int i=0;i<vexnum;i++){
            if(G.vexNodes[i].indegree==0){
                s.push(i);
            }
        }
        while(!s.empty()){  //ջ��ʱ�ÿ�ʱѭ������
            j= s.pop();
            TopologyArray.push(G.vexNodes[j].vexterix);  //����������ջ
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

package MyGraph;

import java.util.ArrayDeque;
import java.util.Queue;
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
 * �㷨
 *   A���ӿ�ʼ���� v1?�������� ve(1)=0���������������������������Ŀ������緢��ʱ�䡣?[3]?Ve(k)=max{ve(j)+dut(��j,k��)} , j �� T ������T���Զ���vkΪβ�����л���ͷ����ļ��ϣ�2 �� k �� n����
 *
 * ����õ����������������ж���ĸ���С�����ж������n����˵�������л�����������ؼ�·�����㷨������
 *
 * B������ɶ���Vn  �������� Vl(n)=Ve(n) ��
 * �����������������������������������ʱ�䣺
 * vl(j)=min{vl(k)-dut(��j,k��)} ,k �� S ������ S ���Զ���vj��ͷ�����л���
 * β���㼯�ϣ�1 �� j �� n-1����
 *
 * C����ÿһ��ai��1 �� i �� m�������翪ʼʱ��e(i)=ve(j)������ʼʱ��l(i)=vl(k)-dut(��j,k��) ��
 *
 * ��ĳ�������� e(i)=l(i) �������ǹؼ����
 *
 */
public class AOE {
    /**
     * �ؼ��ڵ�
     */
    public ArrayDeque keyNode =new ArrayDeque<>();
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
    public double[] vexETime;
    /**
     * �¼��ڵ㷢�����ʱ��
     */
    public double[] vexDTime;
    /**
     * ������������ջ
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
     * �ڵ����緢��ʱ��
     * @return 0 AOV���л�·����������û��Ψһ��Դ��
     *         1 �õ��¼��ڵ����緢��ʱ��
     */
    public  int VexElyTime(){
        //0��ȵĶ���ջ
        Stack<Integer> s=new Stack<Integer>();
        int i=0,count=0,j=0,k=0;
        //Ѱ��0��ȵĶ��� ѹ��ջ
        boolean[] visit=new boolean[vexnum];
        //���ڱ���Ƿ���γ�ջ �Ƿ���ڻ�·
        for( i=0;i<vexnum;i++) {
            if (G.vexNodes[i].indegree == 0) {
                s.push(i);
                break;//AOE ��ֻ��һ��Դ�����Ϊ��
            }
        }
        if(i==vexnum) {
            System.out.println("ͼû�����Ϊ��ĵ�");
            return 0;}  //��ͼû�����Ϊ��ĵ㣬������AOE��������
        //ջ��ʱ�ÿ�ʱѭ������
        while(!s.empty()) {
            j = s.pop();
            if (!visit[j]) {
                visit[j] = true;
                TopologyArray.push(j);  //����������ջ
                count++;
                //����
                for (EdgeNode p = G.vexNodes[j].firstEdge; p != null; p = p.next) {
                    k = p.number;  //j,kΪ��ֵ��ʱkΪ�ߵ����
                    G.vexNodes[k].indegree--;
                    if (G.vexNodes[k].indegree == 0) {
                        s.push(k);
                    }
                    if (vexETime[k] < (vexETime[j] + p.weight)) {
                        vexETime[k] = vexETime[j] + p.weight;
                        //ѡȡ����·������
                    }
                }
            }
            else break;
        }

        if(count<G.vexnum) {System.out.println("ͼ�л�·");return 0;}  //ͼ�л�·
        else {System.out.println("AOE����������������ʱ���������ɹ���");return 1;}
     }

    /**
     * �ڵ�������ʱ��
     * @return  0  û����������
     *          1  �õ��¼��ڵ�������ʱ��
     */
    public  int VexDelayTime(){
        int j=0,k=0,i=0;
        if(TopologyArray.size()<vexnum) return 0;
        while(!TopologyArray.empty())
        {

            j=TopologyArray.pop();
            vexDTime[j]=vexETime[j]>vexDTime[i] ? vexETime[j] :vexDTime[i];
            //������ʱ��һ���������緢��ʱ��
            //�յ�����ʱ��������緢��ʱ��
            //�����ڵ� ��ʼ���ʱ��Ϊǰһ���ڵ�����ʱ�䣬ȡ��С
            for (EdgeNode p = G.vexNodes[j].firstEdge; p != null; p = p.next) {
                k = p.number;  //j,kΪ��ֵ��ʱkΪ�ߵ����
                if (vexDTime[j] > (vexDTime[k] - p.weight)) {
                    vexDTime[j] = vexDTime[k]  - p.weight;
                    //ѡȡ��ٵķ�����ʱ��
                }
            }
            i=j;
        }
        System.out.println("����ʱ���������ɹ�");
        return 1;
    }

    /**
     * @return  �¼��ڵ����緢��ʱ�����ٷ���ʱ���ֵ��С�Ľڵ�Ϊ�ؼ��ڵ�
     *          ���Ʋ��û�������������
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
     * ���AOE������õ�ÿ���ڵ������ʱ�������ʱ��͹ؼ��ڵ�
     */
    public void AOVSolve(){
        if(VexElyTime()==0)
            System.out.println("AOV���л�·����������û��Ψһ��Դ��");
        int a=VexDelayTime();
        keyNode =getKeyNode();
    }

}

package MyGraph;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/**
 * �ڽӱ�洢ͼ��   ͨ���� ÿ�����㣬���������ӵĽڵ㹹��һ������ÿ���ڵ�������ɿ��Դ洢ͼ�ṹ���ڽӱ�
 */
public class GraphList {
    /**
     * ���������
     */
    private VexNode[] vexNodes;    //���������
    /**
     *������
     */
    private int vexnum;           //������
    /**
     * ����
     */
    private int edgenum;          //����

    /**
     * ��ʼ��ͼ�ķ��ʱ�����־   ��ʼֵΪfalse
     */
    public void initial() {
        this.visited = new boolean[this.vexnum];
    }

    /**
     * �ж��Ƿ񱻷���,�ڶ��η���ʱҪ�ȶ�����0
     */
    private boolean visited[];    //�ж��Ƿ񱻷���,�ڶ��η���ʱҪ�ȶ�����0
    /**
     * ��������ʾ�ı�����
     */
    private int counter;

    /**
     * ���캯��  �����ڽӱ�洢ͼ
     */
    public GraphList() {
        int i,j,k,vexnum,edgenum;
        double weight;
        EdgeNode s;

        Scanner src=new Scanner(System.in);
        System.out.print("�����붥������");
        vexnum=src.nextInt();
        System.out.print("�����������");
        edgenum=src.nextInt();
        System.out.println("�����붥�����Ϣ��");

        while(vexnum<=2 ||edgenum<=0 || edgenum>(vexnum*(vexnum-1)/2)){
            System.out.println("����Ķ������ͱ�������ȷ���������룡");
            System.out.print("�����붥���������");
            vexnum=src.nextInt();
            System.out.print("������ߵ�������");
            edgenum=src.nextInt();
        }

        this.vexnum = vexnum;   //������
        this.edgenum = edgenum;  //����
        this.vexNodes=new VexNode[vexnum];  //����������
        this.counter=0;        //���ڼ���


        for ( i = 0; i <vexnum; i++) {
            j=i+1;
            System.out.print("��"+j+"���ڵ���Ϣ��");
            this.vexNodes[i]=new VexNode(src.next(),null);
        }
        for ( k = 0; k <edgenum; k++) {
            System.out.println("����"+(k+1)+"���ߵļ�ֵ�Ժ�Ȩ��:");
            i=src.nextInt();   //�ڽӱ�Ķ������
            j=src.nextInt();  //��������e���� �ü�ֵ�� <i,j>��ʾ
            weight=src.nextDouble();  //����ıߵ�Ȩֵ

            if(j>=vexnum || k>=vexnum){
                System.out.println("�����ֵ�Գ���������±�,����������!");
                i--;continue;
            }

            s=new EdgeNode();
            s.number=j;     //�ü�ֵ�Եĺ�һ���������ڽӵ�����
            s.weight=weight;  //��ʾ i��j �ߵ�Ȩֵ
            s.next=this.vexNodes[i].firstEdge;  //����β�巨
            this.vexNodes[i].firstEdge=s;

            s=new EdgeNode();
            s.number=i;     //�ü�ֵ�Եĺ�һ���������ڽӵ�����
            s.weight=weight;  //��ʾ i��j �ߵ�Ȩֵ
            s.next=this.vexNodes[j].firstEdge;  //����β�巨
            this.vexNodes[j].firstEdge=s;
        }
    }     //���캯���������ڽӱ�

    /**
     * �������
     * @param i   ����iΪ��ʼ������λ��
     */
    public void DFSL(int i){
        int count=this.counter;
        this.counter++;
        if(count==0){initial();}  //����ʼ����ʱ��ʼ��visited����

        EdgeNode p;
        this.visited[i]=true;

        System.out.println("node:"+this.vexNodes[i].vexterix+"��ʼ����");
        p=this.vexNodes[i].firstEdge;
        while(p!=null){
            if(!this.visited[p.number]){
                System.out.println(this.vexNodes[i].vexterix+"��"+this.vexNodes[p.number].vexterix+"��·������Ϊ"+p.weight);
                DFSL(p.number);
            }
            p=p.next;
        }
        System.out.println("node:"+this.vexNodes[i].vexterix+"���ʽ���!");
        if(count==0)System.out.println("���ʽ���!");
    }  //��ͨͼ�������

    /**
     * �������
     * @param i  ����iΪ��ʼ������λ��
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
            System.out.println("node:"+this.vexNodes[k].vexterix+"��ʼ����");
            p=this.vexNodes[k].firstEdge;
            while(p!=null){
                if(!this.visited[p.number]){
                    System.out.println(this.vexNodes[k].vexterix+"��"+this.vexNodes[p.number].vexterix+"��·������Ϊ"+p.weight);
                    this.visited[p.number]=true;
                    queue.add(p.number);
                }
                p=p.next;
            }
            System.out.println("node:"+this.vexNodes[k].vexterix+"���ʽ���");
        }
        System.out.println("���ʽ���!");
    }//��ͨͼ�������

    /**
     * ����ͨͼ�ı���
     * @param type=1   type=1,�����㷨Ϊ������� ����Ϊ�������
     * @return count   ���ط���ͨͼ�ĸ������������ĵ�
     */
    public int TRAVER(int type){
        initial();
        int count=0;
        for (int i = 0; i < this.vexnum ;i++) {
            if(!this.visited[i]){
                if(type==1)
                    DFSL(i);
                else BFSL(i);
                System.out.println("��ͨ��ͼ"+i+"������ɣ�");
                count++;
            }
        }
        return count;
    }
}

/**
 * �ڽӱ�ı߱�ڵ����
 */
class EdgeNode{
    /**
     * ����ڽӱ�ı߱�����
     */
    public int number;    //����ڽӵ�����
    /**
     * ָ��߼�����һ���ڵ�
     */
    public EdgeNode next;  //ָ����һ���ڵ�
    /**
     * ���ϵ�Ȩֵ
     */
    public double weight;   //���ϵ�Ȩֵ

    /**
     * ͨ��  ����ڽӵ����ţ�ָ����һ���ڵ㣬���ϵ�Ȩֵ  �����ڽӱ�߱��ϵĽڵ�
     * @param number   ����ڽӵ�����
     * @param next     ָ����һ���ڵ�
     * @param weight   ���ϵ�Ȩֵ
     */
    public EdgeNode(int number, EdgeNode next, double weight) {
        this.number = number;
        this.next = next;
        this.weight = weight;
    }

    /**
     * ���캯��
     */
    public EdgeNode() {
    }
}

/**
 * �ڽӱ�Ķ�����
 */
class VexNode {
    /**
     * ����ڵ���Ϣ
     */
    public String vexterix;           //����ڵ���Ϣ
    /**
     * �����ͷָ��ָ�������ŵ�һ����  ��β������
     */
    public EdgeNode firstEdge;      //�����ͷָ��ָ�������ŵ�һ����  ��β������

    /**
     * ���캯��
     */
    public VexNode() {
    }

    /**
     * ͨ������ڵ���Ϣ�Ͷ���ͷָ�빹�� �ڽӱ�����
     * @param vexterix  ����ڵ���Ϣ
     * @param firstEdge �����ͷָ��ָ�������ŵ�һ����  ��β������
     */
    public VexNode(String vexterix, EdgeNode firstEdge) {
        this.vexterix = vexterix;
        this.firstEdge = firstEdge;
    }
}

package MyGraph;

import Input.InputStyle;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * �ڽӱ�洢ͼ��   ��� ��� �������� �ؼ�·��
 */
public class GraphList {
    /**
     * ���������
     */
    public VexNode[] vexNodes;    //���������
    /**
     *������
     */
    public int vexnum;           //������
    /**
     * ����
     */
    public int edgenum;          //����

    /**
     * ��ʼ��ͼ�ķ��ʱ�����־   ��ʼֵΪfalse
     */
    public void initial() {
        this.visited = new boolean[this.vexnum];
    }

    /**
     * �ж��Ƿ񱻷���,�ڶ��η���ʱҪ�ȶ�����0
     */
    public boolean visited[];    //�ж��Ƿ񱻷���,�ڶ��η���ʱҪ�ȶ�����0
    /**
     * ��������ʾ�ı�����
     */
    private int counter;

    /**
     * ���캯��  ����Ĭ�������ڽӱ�洢ͼ
     */
    public GraphList() {
        new GraphList(false);
    }     //���캯���������ڽӱ�

    /**
     * @param type  true  ����   false ����
     */
    public GraphList(boolean type){
        int i,j,k,vexnum,edgenum;
        double weight;
        EdgeNode s;

        Scanner src=new Scanner(System.in);
        System.out.print("�����붥������");
        vexnum= InputStyle.readInt("���������붥�������:");
        System.out.print("�����������");
        edgenum=InputStyle.readInt("����������ߵ�����:");

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

        System.out.println("�����붥�����Ϣ��");
        for ( i = 0; i <vexnum; i++) {
            j=i+1;
            System.out.print("��"+j+"���ڵ���Ϣ��");
            this.vexNodes[i]=new VexNode(InputStyle.readString(),null);
        }
        if(!type)
        for ( k = 0; k <edgenum; k++) {
            System.out.println("����"+(k+1)+"���ߵļ�ֵ�Ժ�Ȩ��:");
            i=InputStyle.readInt();   //�ڽӱ�Ķ������
            j=InputStyle.readInt();  //��������e���� �ü�ֵ�� <i,j>��ʾ
            weight=InputStyle.readDouble();  //����ıߵ�Ȩֵ

            if(j>=vexnum || i>=vexnum){
                System.out.println("�����ֵ�Գ���������±�,����������!");
                k--;continue;
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
        else  for ( k = 0; k <edgenum; k++) {
            System.out.println("����"+(k+1)+"���ߵļ�ֵ�Ժ�Ȩ��:");
            i=InputStyle.readInt();   //�ڽӱ�Ķ������
            j=InputStyle.readInt();  //��������e���� �ü�ֵ�� <i,j>��ʾ
            weight=InputStyle.readDouble();  //����ıߵ�Ȩֵ

            if(j>=vexnum || i>=vexnum){
                System.out.println("�����ֵ�Գ���������±�,����������!");
                k--;continue;
            }
            this.vexNodes[j].indegree++;  //�������ͼ��¼ÿ��������
            this.vexNodes[i].outdegree++;  //��¼����
            s=new EdgeNode();
            s.number=j;     //�ü�ֵ�Եĺ�һ���������ڽӵ�����
            s.weight=weight;  //��ʾ i��j �ߵ�Ȩֵ
            s.next=this.vexNodes[i].firstEdge;  //����β�巨
            this.vexNodes[i].firstEdge=s;
        }

    }

    public GraphList(double[][] matrix){
        int i=0,j=0,vexnum=matrix[0].length,edgenum=0;
        EdgeNode s = null;
        this.vexnum = vexnum;   //������
        for ( i = 0; i <this.vexnum ; i++) {
            for ( j = i+1; j < this.vexnum ; j++) {
                if(matrix[i][j]!=100000000.0){
                    edgenum++;
                }
            }
        } //�õ�����
        this.edgenum = edgenum;  //����


        this.vexNodes=new VexNode[vexnum];  //����������
        char k='a';
        for ( i =0; i <vexnum ; i++,k++) {
            Character a=new Character(k);
            this.vexNodes[i]=new VexNode(a.toString());  //����������
        }
        this.counter=0;        //���ڼ���

        for ( i = 0; i < vexnum; i++)
           for ( j = 0; j <vexnum; j++) {
               if (matrix[i][j] != 100000000) {
                   this.vexNodes[j].indegree++;
                   this.vexNodes[i].outdegree++;
                   s = new EdgeNode();
                   s.number = j;     //�ü�ֵ�Եĺ�һ���������ڽӵ�����
                   s.weight = matrix[i][j];  //��ʾ i��j �ߵ�Ȩֵ
                   s.next = this.vexNodes[i].firstEdge;  //����β�巨
                   this.vexNodes[i].firstEdge = s;
               }
           }
    }

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
     * ��ŵĶ������
     */
    public int indegree=0;   //��ŵĶ������
    public int outdegree=0;  //����
    /**
     * ���캯��
     * @param  vexterix ������Ϣ
     */
    public VexNode(String vexterix) {
        this.vexterix=vexterix;
        this.firstEdge=null;
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

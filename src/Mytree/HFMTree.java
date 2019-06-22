package Mytree;

import java.util.Scanner;

/**
 * ��������
 * ���ڹ�����ĵı����ܳ���̵ķ���
 * �����������������Ĵ�Ȩ·�����ȱ�ʾ�����ַ����볤������ִ�����Ƶ�ʵĳ˻�
 * ��Ϊ�����ַ����Ǳ������ϵ�Ҷ�ӽڵ㣬�����ڱ���Ķ���������
 * ����������Ҷ�ӵ����ڵ���볤Ϊ0���ڵ㵽���ӽڵ���볤Ϊ1
 */
public class HFMTree {
    /**
     * ���ڵ�����
     */
    public HFMTreeNode[] hfmTreeNode;
    /**
     * ��������������
     */
    public HFMCodeNode[] HuffCode;
    /**
     * Ҷ������
     */
    public int leafNum;
    /**
     * ���캯�� �����������
     * @param n  Ҷ�ӽڵ�ĸ���
     */
    public HFMTree(int n) {
        int m1=0,x1=0,m2=0,x2=0;
        int i,j;
        hfmTreeNode=new HFMTreeNode[2*n-1];
        Scanner src=new Scanner(System.in);
        for(i=0;i<n;i++) {
            hfmTreeNode[i].weight=src.nextInt();
        }
        for ( i = 0; i < n - 1; i++) {
            x1=x2=10000;
            m1=m2=0;
            for(j=0;j<n+i;j++){
                if( hfmTreeNode[j].parent==-1 && hfmTreeNode[j].weight< x1){
                    //�ҳ�����Ȩֵ��С����С����
                    x2=x1;m2=m1;
                    x1=hfmTreeNode[j].weight;m1=j;
                }
                else if(hfmTreeNode[j].parent==-1&& hfmTreeNode[j].weight<x2){
                    x2=hfmTreeNode[j].weight;
                    m2=j;
                }
            }
        }
        hfmTreeNode[m1].parent=n+i;hfmTreeNode[m2].parent=n+i;
        hfmTreeNode[n+i].weight=hfmTreeNode[m1].weight+hfmTreeNode[m2].weight;
        hfmTreeNode[n+i].lchild=m1;
        hfmTreeNode[n+i].rchild=m2;
        leafNum=n;
        HuffCode=new HFMCodeNode[n];
    }

    /**
     * ����������
     * @param hfmTree   ��������
     * @param HuffCode  ��������������
     * @param n         Ҷ�ӽڵ�����
     */
    public void HaffmanCode(HFMTreeNode[] hfmTree,HFMCodeNode[] HuffCode,int n){
        HFMCodeNode cd=new HFMCodeNode(n);
        int i=0,j=0,c=0,p=0;
        for ( i = 0; i < n ; i++) {
            cd.start = n - 1;
            c = i;
            p = hfmTree[c].parent;
            while (p != -1) {
                if (hfmTree[p].lchild == c) cd.bit[cd.start] = 0;
                else cd.bit[cd.start] = 1;
                cd.start--;
                c = p;
                p = hfmTree[c].parent;
            }
            for(j=cd.start+1;j<n;j++){
                HuffCode[i].bit[j]=cd.bit[j];
            }
            HuffCode[i].start=cd.start+1;
        }
    }
    /**
     * ����������
     */
    public void HaffmanCode(){
        HaffmanCode(hfmTreeNode, HuffCode,leafNum);

    }
}

/**
 * ���������ڵ�
 */
class HFMTreeNode{
    public int weight;
    public int parent;
    public int lchild;
    public int rchild;

    public HFMTreeNode() {
        this.weight = 0;
        this.parent = -1;
        this.lchild = -1;
        this.rchild = -1;
    }
}

/**
 * ����������ڵ�
 */
class HFMCodeNode{
    /**
     * һά�������ڴ�Ź���������
     */
    public int[] bit;
    /**
     * ����bit�еĿ�ʼλ��
     */
    public int start;

    public HFMCodeNode(int n) {
        bit=new int[n];
    }
}
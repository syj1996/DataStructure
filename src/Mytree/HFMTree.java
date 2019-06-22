package Mytree;

import java.util.Scanner;

/**
 * 哈夫曼树
 * 用于构造电文的编码总长最短的方案
 * 哈夫曼编码树中树的带权路径长度表示各个字符的码长与其出现次数或频率的乘积
 * 因为编码字符都是编码树上的叶子节点，不存在编码的二义性问题
 * 所有左子树叶子到父节点的码长为0，节点到右子节点的码长为1
 */
public class HFMTree {
    /**
     * 树节点数组
     */
    public HFMTreeNode[] hfmTreeNode;
    /**
     * 哈夫曼编码数组
     */
    public HFMCodeNode[] HuffCode;
    /**
     * 叶子数量
     */
    public int leafNum;
    /**
     * 构造函数 构造哈夫曼树
     * @param n  叶子节点的个数
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
                    //找出两颗权值最小，次小的树
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
     * 哈夫曼编码
     * @param hfmTree   哈夫曼树
     * @param HuffCode  哈夫曼编码数组
     * @param n         叶子节点数量
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
     * 哈夫曼编码
     */
    public void HaffmanCode(){
        HaffmanCode(hfmTreeNode, HuffCode,leafNum);

    }
}

/**
 * 哈夫曼树节点
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
 * 哈夫曼编码节点
 */
class HFMCodeNode{
    /**
     * 一维数组用于存放哈夫曼编码
     */
    public int[] bit;
    /**
     * 数组bit中的开始位置
     */
    public int start;

    public HFMCodeNode(int n) {
        bit=new int[n];
    }
}
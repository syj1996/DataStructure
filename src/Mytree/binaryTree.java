package Mytree;

import java.util.Scanner;

/**
 * ������
 */
public class binaryTree {
    /**
     * ���ڵ㼯
     */
    public treeNode[] treeNode;
    /**
     * ����
     */
    public treeNode root;

    /**
     * ���ڵ������
     */
    public int treeNum;

    /**
     * ���캯������ ������
     */
    public binaryTree() {

        int front=1,rear=0;
        Scanner src=new Scanner(System.in);
        root=null;

        System.out.println("���������Ľڵ������");
        treeNum=src.nextInt();
        while(treeNum<3)
        {
            System.out.println("�������������ڵ�");
        }
        treeNode=new treeNode[treeNum];
        for(int i=0;i<treeNum;i++)
        System.out.print("������"+i+"�����ڵ���Ϣ");


    }
}

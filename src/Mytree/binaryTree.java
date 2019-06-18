package Mytree;

import java.util.Scanner;

/**
 * 二叉树
 */
public class binaryTree {
    /**
     * 树节点集
     */
    public treeNode[] treeNode;
    /**
     * 树根
     */
    public treeNode root;

    /**
     * 树节点的数量
     */
    public int treeNum;

    /**
     * 构造函数创建 二叉树
     */
    public binaryTree() {

        int front=1,rear=0;
        Scanner src=new Scanner(System.in);
        root=null;

        System.out.println("请输入树的节点个数：");
        treeNum=src.nextInt();
        while(treeNum<3)
        {
            System.out.println("请重新输入树节点");
        }
        treeNode=new treeNode[treeNum];
        for(int i=0;i<treeNum;i++)
        System.out.print("请输入"+i+"个树节点信息");


    }
}

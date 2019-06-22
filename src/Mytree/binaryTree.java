package Mytree;

import Input.InputStyle;
import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.Scanner;

/**
 * 二叉树
 */
public class binaryTree {
    /**
     * 缺少孩子节点 顺序存放
     */
    private ArrayQueue<bitNode> lackChild=new ArrayQueue<>(10000);
    /**
     * 二叉树节点数组
     */
    private bitNode[] node=new bitNode[10000];
    /**
     * 树根
     */
    private bitNode root;

    public ArrayQueue<bitNode> getLackChild() {
        return lackChild;
    }

    public bitNode[] getNode() {
        return node;
    }

    public bitNode getRoot() {
        return root;
    }

    public int getTreeNum() {
        return treeNum;
    }

    /**
     * 树节点的数量
     */
    private int treeNum;

    /**
     * 构造函数创建 二叉树
     */
    public binaryTree() {

        int front=1,rear=0;
        //front  队头，rear 队尾
        Scanner src=new Scanner(System.in);
        root=null;

        System.out.print("请输入树的节点个数：");
        treeNum = InputStyle.readInt();
         while(treeNum<3) {
               System.out.print("请重新输入树节点：");
               treeNum=InputStyle.readInt();
             }

        bitNode[] bitN=new bitNode[treeNum+1];
        //node=new bitNode[treeNum];
        for(int i=0;i<treeNum;i++) {
            System.out.print("请输入"+(i+1)+"个树节点信息:");
            rear++;
            bitNode s=new bitNode(InputStyle.readString());
            node[i]=s;
            bitN[rear]=s;
            if(rear==1) root=s;
            else {
                //孩子和父节点均不是虚节点
                if(s!=null && bitN[front]!=null) {
                    //偶数
                    if (rear % 2 == 0) {
                        bitN[front].lchild = s;
                        s.parent = bitN[front];
                    }
                    //奇数
                    else {
                        bitN[front].rchild = s;
                        s.parent = bitN[front];
                    }
                    if (rear % 2 == 1) front++;
                }
            }
        }
        for(int i = front; i<=rear; i++){
           lackChild.add(bitN[i]);
        }
    }

    /**
     * 构造函数
     * @param Node   树节点的数组
     */
    public binaryTree(bitNode[] Node){
        int front=1,rear=0;
        //front  队头，rear 队尾
        root=null;
        treeNum=Node.length;
        bitNode[] bitN=new bitNode[treeNum+1];
        this.node=Node;
        for(int i=0;i<treeNum;i++) {
            rear++;
            bitNode s=Node[i];
            node[i]=s;
            bitN[rear]=s;
            if(rear==1) root=s;
            else {
                //孩子和父节点均不是虚节点
                if(s!=null && bitN[front]!=null) {
                    //偶数
                    if(rear%2==0) {bitN[front].lchild=s;s.parent=bitN[front];}
                        //奇数
                    else {bitN[front].rchild=s;s.parent=bitN[front];}
                    if(rear%2==1)  front++;
                }
            }
        }
        for(int i = front; i<=rear; i++){
            lackChild.add(bitN[i]);
        }
    }

    /**
     * 在parent 中插入x,使得x为parent的左节点，如果parent已经有左节点则将parent原来的左节点作为x的左节点
     * @param x    插入的节点
     * @param parent   插入节点的父节点
     */
    public void InsertL(bitNode x,bitNode parent){
        int i;
        for(i=0;i<treeNum;i++){
            if(parent==node[i]) break;
        }
        if(i==treeNum){System.out.println("插入出错!");return;}
        else {
            if(parent.lchild==null){
                parent.lchild=x;
            }
            else {
                x.lchild=parent.lchild;
                parent.lchild=x;
            }
        }
    }


    /**
     * 添加
     * @param x  添加的树节点
     */
    public void add(bitNode x){
      node[treeNum++]=x;
      if(lackChild.get(0).lchild==null){
          lackChild.get(0).lchild=x;
          x.parent=lackChild.get(0);
      }
      else {
          lackChild.get(0).rchild =x;
          x.parent=lackChild.get(0);
          lackChild.remove(0);
          lackChild.add(x);
      }
//      bitNode node=search(this.root,lackChild.get(0));
//      if(node!=null) {
//          if(node.lchild==null){
//              node.lchild=x;
//          }
//          else {
//              node.rchild =x;
//          }
//      }
    }

    /**
     * 查找节点
     * @param x     查找的节点的信息
     * @return       查找的节点
     */
    public bitNode search( String x){
        for(int i=0;i<treeNum;i++){
            if(node[i].info.equals(x)) return node[i];
        }
        return null;
    }

    /**
     * 查找
     * @param root  根节点
     * @param x    节点信息
     * @return  返回查找到的节点
     */
    public bitNode search(bitNode root,String x){
        bitNode p=new bitNode();
        if(root!=null){
            if(root.info.equals(x)) return root;
            if(root.lchild!=null) p=search(root.lchild,x);
            if(p!=null) return p;
            if(p.rchild!=null) p=search(root.rchild,x);
            if(p!=null) return p;
        }
        return null;
    }
    /**
     * 删除节点
     * @param x 根据节点信息删除该节点
     * @return 删除成功
     */
    public boolean remove(String x){
        if(x!=null)
        {
            for (int i = 0; i < treeNum; i++) {
            if(node[i].info.equals(x)){

                if(node[i].parent.lchild!=null&&node[i].parent.lchild.info.equals(x)){
                    node[i].parent.lchild=null;
                    if(node[i].lchild!=null)
                    remove(node[i].lchild.info);
                    if(node[i].rchild!=null)
                    remove(node[i].rchild.info);
                }
                else {

                    if(node[i].lchild!=null)
                    remove(node[i].lchild.info);
                    if(node[i].rchild!=null)
                    remove(node[i].rchild.info);
                    node[i].parent.rchild=null;
                }
                for(int j=i;j<treeNum-1;j++){
                    node[j]=node[j+1];
                }
                treeNum--;
                return true;
            }
            }
            return false;
        }
        else
        return false;
    }

    /**
     * 子节点数量及其子节点的子节点数
     * @param root 根节点
     * @return   子节点数量
     */
    public int NodeNum(bitNode root){
        if(root!=null)
        {
            if(root.lchild==null && root.rchild==null) return 0;
            else if(root.lchild!=null &&root.rchild==null ) return 1;
            else if(root.lchild==null &&root.rchild!=null) return 1;
            else return NodeNum(root.lchild)+NodeNum(root.rchild);
        }
        else return 0;
    }

    /**
     * 叶子节点数
     * @param root  根节点
     * @return   叶子树
     */
    public int countLeaf(bitNode root){
        if(root==null)return 0;
        if(root.lchild==null && root.rchild==null ) return 1;
        return countLeaf(root.lchild)+countLeaf(root.rchild);
    }

    /**
     * 中序遍历
     * @param t  根节点
     */
    public void Inorder(bitNode t){
        if(t!=null){

            Inorder(t.lchild);
            System.out.println(t.info);
            Inorder(t.rchild);
        }
    }

    /**
     * 前序遍历
     * @param t 根节点
     */
    public void Preorder(bitNode t){
        System.out.println("前序遍历：");
        if(t!=null){
            System.out.println(t.info);
            Inorder(t.lchild);
            Inorder(t.rchild);
        }
    }

    /**
     * 非递归遍历
     * @param root 根节点
     */
    public void NRPreOrder(bitNode root){
        bitNode[] stack=new bitNode[10000];
        bitNode p;
        int top=-1;
        if(root==null) return;
        p=root;
        while(!(p==null && top==-1)){
            while(p!=null){
                System.out.println(p.info);
                top++;
                stack[top]=p;
                p=p.lchild;
            }
            if(top<0) return ;
            else {
                p=stack[top];
                top--;
                p=p.rchild;
            }
        }
    }
    /**
     * 后序遍历
     * @param t 根节点
     */
    public void Postorder(bitNode t){
        System.out.println("后序遍历：");
        if(t!=null){
            Inorder(t.lchild);
            Inorder(t.rchild);
            System.out.println(t.info);
        }
    }

}

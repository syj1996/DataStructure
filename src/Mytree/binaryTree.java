package Mytree;

import Input.InputStyle;
import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.Scanner;

/**
 * ������
 */
public class binaryTree {
    /**
     * ȱ�ٺ��ӽڵ� ˳����
     */
    private ArrayQueue<bitNode> lackChild=new ArrayQueue<>(10000);
    /**
     * �������ڵ�����
     */
    private bitNode[] node=new bitNode[10000];
    /**
     * ����
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
     * ���ڵ������
     */
    private int treeNum;

    /**
     * ���캯������ ������
     */
    public binaryTree() {

        int front=1,rear=0;
        //front  ��ͷ��rear ��β
        Scanner src=new Scanner(System.in);
        root=null;

        System.out.print("���������Ľڵ������");
        treeNum = InputStyle.readInt();
         while(treeNum<3) {
               System.out.print("�������������ڵ㣺");
               treeNum=InputStyle.readInt();
             }

        bitNode[] bitN=new bitNode[treeNum+1];
        //node=new bitNode[treeNum];
        for(int i=0;i<treeNum;i++) {
            System.out.print("������"+(i+1)+"�����ڵ���Ϣ:");
            rear++;
            bitNode s=new bitNode(InputStyle.readString());
            node[i]=s;
            bitN[rear]=s;
            if(rear==1) root=s;
            else {
                //���Ӻ͸��ڵ��������ڵ�
                if(s!=null && bitN[front]!=null) {
                    //ż��
                    if (rear % 2 == 0) {
                        bitN[front].lchild = s;
                        s.parent = bitN[front];
                    }
                    //����
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
     * ���캯��
     * @param Node   ���ڵ������
     */
    public binaryTree(bitNode[] Node){
        int front=1,rear=0;
        //front  ��ͷ��rear ��β
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
                //���Ӻ͸��ڵ��������ڵ�
                if(s!=null && bitN[front]!=null) {
                    //ż��
                    if(rear%2==0) {bitN[front].lchild=s;s.parent=bitN[front];}
                        //����
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
     * ��parent �в���x,ʹ��xΪparent����ڵ㣬���parent�Ѿ�����ڵ���parentԭ������ڵ���Ϊx����ڵ�
     * @param x    ����Ľڵ�
     * @param parent   ����ڵ�ĸ��ڵ�
     */
    public void InsertL(bitNode x,bitNode parent){
        int i;
        for(i=0;i<treeNum;i++){
            if(parent==node[i]) break;
        }
        if(i==treeNum){System.out.println("�������!");return;}
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
     * ���
     * @param x  ��ӵ����ڵ�
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
     * ���ҽڵ�
     * @param x     ���ҵĽڵ����Ϣ
     * @return       ���ҵĽڵ�
     */
    public bitNode search( String x){
        for(int i=0;i<treeNum;i++){
            if(node[i].info.equals(x)) return node[i];
        }
        return null;
    }

    /**
     * ����
     * @param root  ���ڵ�
     * @param x    �ڵ���Ϣ
     * @return  ���ز��ҵ��Ľڵ�
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
     * ɾ���ڵ�
     * @param x ���ݽڵ���Ϣɾ���ýڵ�
     * @return ɾ���ɹ�
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
     * �ӽڵ����������ӽڵ���ӽڵ���
     * @param root ���ڵ�
     * @return   �ӽڵ�����
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
     * Ҷ�ӽڵ���
     * @param root  ���ڵ�
     * @return   Ҷ����
     */
    public int countLeaf(bitNode root){
        if(root==null)return 0;
        if(root.lchild==null && root.rchild==null ) return 1;
        return countLeaf(root.lchild)+countLeaf(root.rchild);
    }

    /**
     * �������
     * @param t  ���ڵ�
     */
    public void Inorder(bitNode t){
        if(t!=null){

            Inorder(t.lchild);
            System.out.println(t.info);
            Inorder(t.rchild);
        }
    }

    /**
     * ǰ�����
     * @param t ���ڵ�
     */
    public void Preorder(bitNode t){
        System.out.println("ǰ�������");
        if(t!=null){
            System.out.println(t.info);
            Inorder(t.lchild);
            Inorder(t.rchild);
        }
    }

    /**
     * �ǵݹ����
     * @param root ���ڵ�
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
     * �������
     * @param t ���ڵ�
     */
    public void Postorder(bitNode t){
        System.out.println("���������");
        if(t!=null){
            Inorder(t.lchild);
            Inorder(t.rchild);
            System.out.println(t.info);
        }
    }

}

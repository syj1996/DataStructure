package Mytree;

/**
 * ���ڵ���
 * ÿһ���ڵ�����в�����һ�����ڵ�
 * �������ж������
 * */
public class treeNode {
    /**
     * ���ڵ�
     */
    public treeNode parent;
    /**
     * �ӽڵ�����
     */
    public treeNode[] child;
    /**
     * �ڵ㵽���ڵ�֮��ľ���Ȩֵ
     */
    public double weight;

    /**
     * ���ڵ����Ϣ
     */
    public String nodeInfo;
}

/**
 * ��������ڵ���
 */
class bitNode{
    public String node;
    public bitNode  lchild;
    public bitNode  rchild;
}
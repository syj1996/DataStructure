package Mytree;
/**
 * ��������ڵ���
 */
public class bitNode{
    /**
     * �������ڵ���Ϣ
     */
    public String info;
    /**
     * ��ڵ�
     */
    public bitNode  lchild;
    /**
     * �ҽڵ�
     */
    public bitNode  rchild;
    /**
     * ���ڵ�
     */
    public bitNode  parent;
    /**
     * �븸�ڵ��Ȩֵ
     */
    public double weight;

    public bitNode(String node) {
        this.info = node;
    }

    public bitNode() {
    }

}

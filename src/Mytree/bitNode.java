package Mytree;
/**
 * 二叉树类节点类
 */
public class bitNode{
    /**
     * 二叉树节点信息
     */
    public String info;
    /**
     * 左节点
     */
    public bitNode  lchild;
    /**
     * 右节点
     */
    public bitNode  rchild;
    /**
     * 父节点
     */
    public bitNode  parent;
    /**
     * 与父节点的权值
     */
    public double weight;

    public bitNode(String node) {
        this.info = node;
    }

    public bitNode() {
    }

}

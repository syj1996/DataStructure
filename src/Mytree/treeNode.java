package Mytree;

/**
 * 树节点类
 * 每一树节点可以有不超过一个父节点
 * 但可以有多个孩子
 * */
public class treeNode {
    /**
     * 父节点
     */
    public treeNode parent;
    /**
     * 子节点数组
     */
    public treeNode[] child;
    /**
     * 节点到父节点之间的距离权值
     */
    public double weight;

    /**
     * 树节点的信息
     */
    public String nodeInfo;
}


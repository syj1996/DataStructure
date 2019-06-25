import Mytree.binaryTree;
import Mytree.bitNode;
import Sort.CreateArray;
import Sort.printArray;
import Sort.runTime;
import Sort.sort;

public class Test {
    public static void main(String[] args) {
        int[] a=CreateArray.arrayRandom(CreateArray.a);
        printArray.printA(a);
//        runTime pro1=new runTime("≤Â»Î≈≈–Ú");
//        pro1.Start();
//        sort.insertionSort(a);
//        pro1.End();
//        pro1.result();

//        runTime pro2=new runTime("œ£∂˚≈≈–Ú");
//        pro2.Start();
//        sort.shellSort(a);
//        pro2.End();
//        pro2.result();

//        runTime pro3=new runTime("∂˛≤Ê≈≈–Ú");
//        pro3.Start();
//        sort.binaryTreeSort(a);
//        pro3.End();
//        pro3.result();

//         runTime pro4=new runTime("øÏÀŸ≈≈–Ú");
//         pro4.Start();
//         sort.quickSort(a,0,CreateArray.a-1);
//         pro4.End();
//         pro4.result();

//         runTime pro5=new runTime("Õ∞≈≈–Ú");
//         pro5.Start();
//         sort.bucketSort(a);
//         pro5.End();
//         pro5.result();

//         runTime pro6=new runTime("øÏÀŸ≈≈–Úª˘”⁄’ª");
//         pro6.Start();
//         sort.quickSortByStack(a);
//         pro6.End();
//         pro6.result();

//         runTime pro7=new runTime("º∆ ˝≈≈–Ú");
//         pro7.Start();
//         sort.countSort(a);
//         pro7.End();
//         pro7.result();

        binaryTree binTree=new binaryTree();
        binTree.NRPreOrder(binTree.getRoot());
//        binTree.add(new bitNode("sd"));
//        binTree.Inorder(binTree.getRoot());
//        bitNode a=binTree.search("a");
//        System.out.println(a.info);
        boolean ab=binTree.remove("c");
        System.out.println(ab);
        binTree.Inorder(binTree.getRoot());
        System.out.println(binTree.getTreeNum());
    }
}

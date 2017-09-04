import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by singh0777 on 3/29/17.
 */

class BinaryNode {
    private BinaryNode parent;
    private BinaryNode leftchild;
    private BinaryNode rightchild;
    private int key = -1;
    private int value = -1;
    private boolean isLeaf = false;

    public BinaryNode(){

    }

    public BinaryNode(int k, int v){
        this.key = k;
        this.value = v;
    }

    public BinaryNode getParent() {
        return parent;
    }

    public void setParent(BinaryNode parent) {
        this.parent = parent;
    }

    public BinaryNode getLeftchild() {
        return leftchild;
    }

    public void setLeftchild(BinaryNode leftchild) {
        this.leftchild = leftchild;
    }

    public BinaryNode getRightchild() {
        return rightchild;
    }

    public void setRightchild(BinaryNode rightchild) {
        this.rightchild = rightchild;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setToLeaf() {
        isLeaf = true;
    }

}

class DNode {
    private DNode parent;
    private DNode leftchild;
    private DNode rightchild;
    private int key = -1;
    private int value = -2;
    private boolean isleaf = false;

    public DNode(int k, int v){
        this.key = k;
        this.value = v;
    }

    public DNode(){

    }

    public DNode getParent() {
        return parent;
    }

    public void setParent(DNode parent) {
        this.parent = parent;
    }

    public DNode getLeftchild() {
        return leftchild;
    }

    public void setLeftchild(DNode leftchild) {
        this.leftchild = leftchild;
    }

    public DNode getRightchild() {
        return rightchild;
    }

    public void setRightchild(DNode rightchild) {
        this.rightchild = rightchild;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isIsleaf() {
        return isleaf;
    }

    public void setToLeaf() {
        isleaf = true;
    }
}


class PNode
{
    int value;
    private int key;
    private boolean isLeaf = false;
    PNode lChild;
    PNode nextSibling;
    PNode backLink;
    PNode leftChild;
    PNode rightChild;

    public PNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(PNode leftChild) {
        this.leftChild = leftChild;
    }

    public PNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(PNode rightChild) {
        this.rightChild = rightChild;
    }

    /* Constructor */
    public PNode(){

    }

    public PNode(int x, int k)
    {
        value = x;
        key = k;
        lChild = null;
        nextSibling = null;
        backLink = null;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setToLeaf() {
        isLeaf = true;
    }

    public PNode getlChild() {
        return lChild;
    }

    public void setlChild(PNode lChild) {
        this.lChild = lChild;
    }

    public PNode getNextSibling() {
        return nextSibling;
    }

    public void setNextSibling(PNode nextSibling) {
        this.nextSibling = nextSibling;
    }

    public PNode getBackLink() {
        return backLink;
    }

    public void setBackLink(PNode backLink) {
        this.backLink = backLink;
    }
}


class BinaryHeap {
    private BinaryNode[] heap;
    private int currentheapsize = 0;

    public BinaryNode[] getHeap() {
        return heap;
    }

    public BinaryHeap(int maxSize){
        heap = new BinaryNode[maxSize];
    }

    public void swap(int p, int l){
        BinaryNode temp = heap[p];
        heap[p] = heap[l];
        heap[l] = temp;
    }

    public void insert(BinaryNode n){

        int i = currentheapsize;
        int parent = (i-1)/2;

        while( (i>0) && (heap[parent].getValue() > n.getValue())){

            heap[i] = heap[parent];
            i = parent;
            parent = (parent-1)/2;

        }
        heap[i] = n;
        currentheapsize = currentheapsize + 1;
    }

/*    public void insert(BinaryNode n){

        currentheapsize = currentheapsize + 1;

        int i = currentheapsize;

        while( i > 1 && heap[i/2].getValue() > n.getValue()){

            heap[i] = heap[i/2];
            i = i/2;

        }
        heap[i] = n;
    }*/


    public int findSmallestChild(int s, int e){
        int minChild = s;
        for(int i = s+1; (i<=e && i<currentheapsize); i++){
            if(heap[minChild].getValue() > heap[i].getValue()){
                minChild = i;
            }
        }
        return minChild;
    }


    public void restoreHeap(int i){

        int one = 4*i+1;
        int two = 4*i+2;
        int minchild = findSmallestChild(one,two);
        BinaryNode currentRoot = heap[i];
        while ((minchild < currentheapsize) && (heap[minchild].getValue() < currentRoot.getValue())){
            heap[i] = heap[minchild];
            i = minchild;
            minchild = findSmallestChild(minchild*4+1, minchild*4+4);
        }
        heap[i] = currentRoot;
    }
/*    public void restoreHeap(int i){

        int lc = 2*i;
        int rc = 2*i+1;
        int s;

        if(rc <= currentheapsize){

            if(heap[lc].getValue() < heap[rc].getValue())
                s = lc;
            else
                s = rc;

            if(heap[i].getValue() > heap[s].getValue()){
                swap( i, s );
                restoreHeap( s );
            }

        }else if(lc == currentheapsize && heap[i].getValue() > heap[lc].getValue()){
            swap( i , lc );
        }
    }*/


    public BinaryNode removeMin(){
        if(currentheapsize!=0){
            BinaryNode min = heap[0];
            currentheapsize = currentheapsize - 1;
            heap[0] = heap[currentheapsize];
            restoreHeap(0);
            return min;
        }else{
            return null;
        }
    }

/*    public BinaryNode removeMin(){
        if(currentheapsize!=0){
            BinaryNode min = heap[1];
            heap[1] = heap[currentheapsize];
            currentheapsize = currentheapsize -1;
            restoreHeap(1);
            return min;
        }
        return null;
    }*/

    public int getSize(){
        return currentheapsize;
    }





    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/*    public static void main(String[] args) throws  Exception{

        BinaryHeap bh =  new BinaryHeap(20);
        HashMap<Integer, Integer>  m = new FrequencyGenerator().makeFrequencyTable("/home/singh0777/myspace/ADS_project_question/Project/sample1/sample_input_small.txt");

        for (Map.Entry<Integer, Integer> entry : m.entrySet()) {
            //System.out.println(entry.getKey()+" : "+entry.getValue());
            BinaryNode n = new BinaryNode(entry.getKey(),entry.getValue());
            bh.insert(n);
        }

        bh.removeMin();

        BinaryNode[] h=bh.getHeap();
        for(int i=1; i <= bh.getSize(); i++){
            System.out.println(h[i].getValue());
        }

    }*/
}

class DHeap {
    private DNode[] dheap;
    private int currentheapsize = 0;

    public DHeap(int maxSize){
        dheap = new DNode[maxSize];
    }

    public int getSize(){
        return currentheapsize;
    }

    public DNode[] getdHeap() {
        return dheap;
    }

    public void insert(DNode n){

        int i = currentheapsize;
        int parentIndex = (i-1)/4;

        while( (i>0) && (dheap[parentIndex].getValue() > n.getValue())){

            dheap[i] = dheap[parentIndex];
            i = parentIndex;
            parentIndex = (parentIndex-1)/4;

        }
        dheap[i] = n;
        currentheapsize = currentheapsize + 1;
    }

    public DNode removeMin(){
        if(currentheapsize!=0){
            DNode min = dheap[0];
            currentheapsize = currentheapsize - 1;
            dheap[0] = dheap[currentheapsize];
            restoreHeap(0);
            return min;
        }else{
            return null;
        }
    }

    public int findSmallestChild(int s, int e){
        int minChild = s;
        for(int i = s+1; (i<=e && i<currentheapsize); i++){
            if(dheap[minChild].getValue() > dheap[i].getValue()){
                minChild = i;
            }
        }
        return minChild;
    }


    public void restoreHeap(int i){

        int first = 4*i+1;
        int second = 4*i+2;
        int third = 4*i+3;
        int fourth = 4*i+4;
        int minchild = findSmallestChild(first,fourth);
        DNode currentRoot = dheap[i];
        while ((minchild < currentheapsize) && (dheap[minchild].getValue() < currentRoot.getValue())){
            dheap[i] = dheap[minchild];
            i = minchild;
            minchild = findSmallestChild(minchild*4+1, minchild*4+4);
        }
        dheap[i] = currentRoot;

        //System.out.println("hi");



/*        if(fourth <= currentheapsize){

            minchild = first;
            for(int c = first+1; (c<=fourth && c<currentheapsize); c++){
                if(dheap[minchild].getValue() > dheap[c].getValue()){
                    minchild = c;
                }
            }

            if(dheap[i].getValue() > dheap[minchild].getValue()){
                swap( i, minchild );
                restoreHeap( minchild );
            }

        }else{
            minchild = first;
            int flag = 0;
            if(first==currentheapsize)
                flag =1;
            if(second==currentheapsize)
                flag =2;
            if(third==currentheapsize)
                flag =3;
            if(flag!=0){
                    for(int c = first + 1; c<= first + flag; c++){
                    if(dheap[minchild].getValue() > dheap[c].getValue()){
                        minchild = c;
                    }
                }
                if(dheap[i].getValue() > dheap[minchild].getValue())
                    swap(i,minchild);
            }


*//*            int numleafs = 4 - (fourth-currentheapsize);
            if(numleafs!=0){
                minchild = first;
                for(int c = first+1; c<=numleafs+1; c++){
                    if(dheap[minchild].getValue() > dheap[c].getValue()){
                        minchild = c;
                    }
                }
                if(dheap[i].getValue() > dheap[minchild].getValue()){
                    swap(i,minchild);
                }
            }*//*
        }*/

    }


/*
    public static void main(String[] args) throws Exception {


        DHeap dh = new DHeap(20);
        HashMap<Integer, Integer> m = new FrequencyGenerator().makeFrequencyTable("/home/singh0777/myspace/ADS_project_question/Project/sample1/sample_input_small.txt");

        for (Map.Entry<Integer, Integer> entry : m.entrySet()) {
            //System.out.println(entry.getKey()+" : "+entry.getValue());
            DNode n = new DNode(entry.getKey(), entry.getValue());
            dh.insert(n);
        }

        DNode[] h = dh.getdHeap();

        for (int i = 1; i <= dh.getSize(); i++) {
            System.out.println(h[i].getValue());
        }

        dh.removeMin();

        System.out.println("\n");

        h = dh.getdHeap();

        for (int i = 1; i <= dh.getSize(); i++) {
            System.out.println(h[i].getValue());
        }

        System.out.println("\n");

        dh.removeMin();

        h = dh.getdHeap();

        for (int i = 1; i <= dh.getSize(); i++) {
            System.out.println(h[i].getValue());
        }


        System.out.println("\n");

        dh.removeMin();

        h = dh.getdHeap();

        for (int i = 1; i <= dh.getSize(); i++) {
            System.out.println(h[i].getValue());
        }


        System.out.println("\n");

        dh.removeMin();

        h = dh.getdHeap();

        for (int i = 1; i <= dh.getSize(); i++) {
            System.out.println(h[i].getValue());
        }



*//*
        DHeap dh = new DHeap(20);
        DNode n = new DNode(1,4);
        dh.insert(n);
        n = new DNode(2,5);
        dh.insert(n);
        n = new DNode(3,6);
        dh.insert(n);
        n = new DNode(4,7);
        dh.insert(n);
        n = new DNode(5,8);
        dh.insert(n);
        n = new DNode(6,1);
        dh.insert(n);
        n = new DNode(7,2);
        dh.insert(n);
        n = new DNode(8,3);
        dh.insert(n);
        n = new DNode(8,3);
        dh.insert(n);
        n = new DNode(8,3);
        dh.insert(n);
        n = new DNode(8,1);
        dh.insert(n);
        n = new DNode(8,1);
        dh.insert(n);
        n = new DNode(8,1);
        dh.insert(n);
        n = new DNode(8,1);
        dh.insert(n);
*//*
    }*/

}

class PairingHeap {

    private PNode root;
    private int currentHeapSize = 0;
    private PNode[] minTrees = new PNode[ 5 ];


    public PairingHeap( ) {
        root = null;
    }

    public int getSize(){
        return currentHeapSize;
    }


    public PNode insert(PNode newNode) {
        currentHeapSize = currentHeapSize + 1;
        if (root == null)
            root = newNode;
        else
            root = meldOperation(root, newNode);
        return newNode;
    }

    private PNode meldOperation(PNode n1, PNode n2) {
        if (n2 == null)
            return n1;

        if (n2.value < n1.value) {
            n2.backLink = n1.backLink;
            n1.backLink = n2;
            n1.nextSibling = n2.lChild;
            if (n1.nextSibling != null)
                n1.nextSibling.backLink = n1;
            n2.lChild = n1;
            return n2;
        }
        else {
            n2.backLink = n1;
            n1.nextSibling = n2.nextSibling;
            if (n1.nextSibling != null)
                n1.nextSibling.backLink = n1;
            n2.nextSibling = n1.lChild;
            if (n2.nextSibling != null)
                n2.nextSibling.backLink = n2;
            n1.lChild = n2;
            return n1;
        }
    }



    private PNode twoPassSchemeJoin(PNode sibling) {
        int totalSiblings = 0;
        if( sibling.nextSibling == null ){
            return sibling;
        }
        for ( ; sibling != null; totalSiblings++) {
            minTrees = increaseMinTreeArraySize( minTrees, totalSiblings );
            minTrees[totalSiblings] = sibling;
            sibling.backLink.nextSibling = null;
            sibling = sibling.nextSibling;
        }
        minTrees = increaseMinTreeArraySize( minTrees, totalSiblings );
        minTrees[ totalSiblings ] = null;
        int i = 0;
        for ( ; i + 1 < totalSiblings; i = i + 2){
            minTrees[i] = meldOperation(minTrees[i], minTrees[i + 1]);
        }
        int j = i - 2;
        if (j == totalSiblings - 3){
            minTrees[j] = meldOperation(minTrees[j], minTrees[j+2]);
        }
        for ( ; j >= 2; j = j -2){
            minTrees[j - 2] = meldOperation(minTrees[j-2], minTrees[j]);
        }
        return minTrees[0];
    }


    private PNode[] increaseMinTreeArraySize(PNode[ ] arr, int pos) {
        if (pos == arr.length) {
            PNode[ ] oArray = arr;
            arr = new PNode[pos * 2];
            for( int i = 0; i < pos; i++ )
                arr[i] = oArray[i];
        }
        return arr;
    }

    public PNode removeMin( ) {
        if (root==null)
            return null;
        PNode r = root;
        currentHeapSize = currentHeapSize - 1;
        if (root.lChild == null)
            root = null;
        else
            root = twoPassSchemeJoin( root.lChild);
        return r;
    }



/*

    public static void main(String[] args) throws Exception{
        HashMap<Integer, Integer> m = new FrequencyGenerator().makeFrequencyTable("/home/singh0777/myspace/ADS_project_question/Project/sample1/sample_input_small.txt");

        PairingHeap ph = new PairingHeap();
        for (Map.Entry<Integer, Integer> entry : m.entrySet()) {
            System.out.println(entry.getKey()+" : "+entry.getValue());
            int k = entry.getKey();
            int v = entry.getValue();
            PNode newNode = new PNode(v,k);
            ph.insert(newNode);
        }
*//*
        PNode h = ph.getRoot();
        System.out.println(h.getValue());
        System.out.println(h.getlChild().getValue());
        System.out.println(h.getlChild().getNextSibling().getValue());
        System.out.println(h.getlChild().getNextSibling().getNextSibling().getValue());

        System.out.println(h.getlChild().getNextSibling().getNextSibling().getlChild().getValue());
        System.out.println(h.getlChild().getNextSibling().getNextSibling().getlChild().getNextSibling().getValue());

        PNode a = ph.removeMin();
        h = ph.getRoot();
        System.out.println("\n");
        System.out.println(h.getValue());
        System.out.println(h.getlChild().getValue());
        System.out.println(h.getlChild().getNextSibling().getValue());

        System.out.println(h.getlChild().getlChild().getValue());
        System.out.println(h.getlChild().getlChild().getNextSibling().getValue());

        PNode b = ph.removeMin();
        h = ph.getRoot();
        System.out.println("\n");
        System.out.println(h.getValue());
        System.out.println(h.getlChild().getValue());
        System.out.println(h.getlChild().getNextSibling().getValue());
        System.out.println(h.getlChild().getNextSibling().getNextSibling().getValue());

        int c = a.getValue() + b.getValue();

        PNode n1 = new PNode(c, 0);

        ph.insert(n1);
        h = ph.getRoot();
        System.out.println("\n");
        System.out.println(h.getValue());
        System.out.println(h.getlChild().getValue());
        System.out.println(h.getlChild().getNextSibling().getValue());
        System.out.println(h.getlChild().getNextSibling().getNextSibling().getValue());
        System.out.println(h.getlChild().getNextSibling().getNextSibling().getNextSibling().getValue());*//*

        int n = ph.getSize() - 1;

        for(int i = 1; i<=n; i++){
            PNode newNode = new PNode();
            PNode a1 = ph.removeMin();
            PNode b1 = ph.removeMin();
            newNode.setLeftChild(a1);
            newNode.setRightChild(b1);
            newNode.setValue(newNode.leftChild.getValue() + newNode.rightChild.getValue());
            newNode.setKey(0);
            ph.insert(newNode);

        }



    }*/

}

class FrequencyGenerator {

    public static LinkedHashMap<Integer, Integer> makeFrequencyTable(String path) throws Exception{
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        LinkedHashMap<Integer,Integer> m = new LinkedHashMap<>();
        while( (st = br.readLine()) != null ){
            //System.out.println(st);
            Integer k = Integer.parseInt(st);
            if(m.containsKey(k)){
                Integer v = m.get(k) + 1;
                m.remove(k);
                m.put(k,v);
            }else{
                m.put(k,1);
            }

        }
        return m;
    }

/*    public static void main(String[] args) throws Exception{
        String inputPath = "/home/singh0777/myspace/ADS_project_question/Project/sample1/sample_input_small.txt";
        HashMap<Integer, Integer> f = makeFrequencyTable(inputPath);
        for (Map.Entry<Integer, Integer> entry : f.entrySet()) {
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }
    }*/
}

class HuffmanGenerator {

    public static BinaryHeap build_tree_using_binary_heap(LinkedHashMap<Integer, Integer> m) throws Exception{

        BinaryHeap bh = new BinaryHeap(1000001);

        for (Map.Entry<Integer, Integer> entry : m.entrySet()) {
            BinaryNode n = new BinaryNode(entry.getKey(),entry.getValue());
            n.setToLeaf();
            bh.insert(n);
        }

        int n = bh.getSize()-1;

        //System.out.println("start\n");

        for (int i = 1; i <= n; i++){

/*            BinaryNode[] h=bh.getHeap();
            for(int i1=1; i1 <= bh.getSize(); i1++){
                System.out.println(h[i1].getValue());
            }
            System.out.println("\n");*/

            BinaryNode newBinaryNode = new BinaryNode();
            newBinaryNode.setLeftchild(bh.removeMin());

/*            BinaryNode[] h1=bh.getHeap();
            for(int i1=1; i1 <= bh.getSize(); i1++){
                System.out.println(h1[i1].getValue());
            }
            System.out.println("\n");*/

            newBinaryNode.setRightchild(bh.removeMin());
            newBinaryNode.setValue(newBinaryNode.getLeftchild().getValue() + newBinaryNode.getRightchild().getValue());

/*            BinaryNode[] h2=bh.getHeap();
            for(int i1=1; i1 <= bh.getSize(); i1++){
                System.out.println(h2[i1].getValue());
            }
            System.out.println("end\n");*/

            bh.insert(newBinaryNode);
        }

        return bh;

    }

    public static DHeap build_tree_using_4way_dheap(LinkedHashMap<Integer, Integer> m) throws Exception{

        DHeap dh = new DHeap(1000001);

        for (Map.Entry<Integer, Integer> entry : m.entrySet()) {
            DNode n = new DNode(entry.getKey(),entry.getValue());
            n.setToLeaf();
            dh.insert(n);
        }

        int n = dh.getSize()-1;

        //System.out.println("start\n");

        for (int i = 1; i <= n; i++){

            /*DNode[] h=dh.getdHeap();
            for(int i1=1; i1 <= dh.getSize(); i1++){
                System.out.println(h[i1].getValue());
            }
            System.out.println("\n");*/

            DNode newNode = new DNode();
            newNode.setLeftchild(dh.removeMin());

           /* DNode[] h1=dh.getdHeap();
            for(int i1=1; i1 <= dh.getSize(); i1++){
                System.out.println(h1[i1].getValue());
            }
            System.out.println("\n");*/

            newNode.setRightchild(dh.removeMin());
            newNode.setValue(newNode.getLeftchild().getValue() + newNode.getRightchild().getValue());

            /*DNode[] h2=dh.getdHeap();
            for(int i1=1; i1 <= dh.getSize(); i1++){
                System.out.println(h2[i1].getValue());
            }
            System.out.println("end\n");*/

            dh.insert(newNode);
        }

        return dh;

    }

    public static PairingHeap build_tree_using_pairing_heap(LinkedHashMap<Integer, Integer> m) throws Exception{

        PairingHeap ph = new PairingHeap();

        for (Map.Entry<Integer, Integer> entry : m.entrySet()) {
            int k = entry.getKey();
            int v = entry.getValue();
            PNode newNode = new PNode(v,k);
            newNode.setToLeaf();
            ph.insert(newNode);
        }


        int n = ph.getSize() - 1;

        for(int i = 1; i<=n; i++){
            PNode newNode = new PNode();
            PNode a1 = ph.removeMin();
            PNode b1 = ph.removeMin();
            newNode.setLeftChild(a1);
            newNode.setRightChild(b1);
            newNode.setValue(newNode.leftChild.getValue() + newNode.rightChild.getValue());
            newNode.setKey(0);
            ph.insert(newNode);
        }

        return ph;
    }

/*    public static void main(String[] args) throws Exception{

        //LinkedHashMap<Integer, Integer>  m = new FrequencyGenerator().makeFrequencyTable("/home/singh0777/myspace/ADS/Project/sample1/sample_input_small.txt");

        LinkedHashMap<Integer, Integer>  m = new FrequencyGenerator().makeFrequencyTable(args[0]);

        int iterations = 10;

        long startTime = System.currentTimeMillis();
        for(int i=0; i<iterations; i++){
            BinaryHeap tree_using_binary_heap = HuffmanGenerator.build_tree_using_binary_heap(m);
        }
        long stopTime = System.currentTimeMillis();
        System.out.println("Binary Heap Time ===> " + (stopTime - startTime)*100 + " microseconds.");


        startTime = System.currentTimeMillis();
        for(int i=0; i<iterations; i++){
            DHeap tree_using_4way_dheap = HuffmanGenerator.build_tree_using_4way_dheap(m);
        }
        stopTime = System.currentTimeMillis();
        System.out.println("4 way Heap Time ===> " + (stopTime - startTime)*100 + " microseconds.");


        startTime = System.currentTimeMillis();
        for(int i=0; i<iterations; i++){
            PairingHeap tree_using_pairing_heap = HuffmanGenerator.build_tree_using_pairing_heap(m);
        }
        stopTime = System.currentTimeMillis();
        System.out.println("Pairing Heap Time ===> " + (stopTime - startTime)*100 + " microseconds.");


    }*/

}

public class encoder {

    public static void encodeInput(LinkedHashMap<Integer, String> codeMap, File file) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        OutputStream output = null;
        String aOutputFileName="encoded.bin";
        output = new BufferedOutputStream(new FileOutputStream(aOutputFileName));
        String a = "";
        while ((st = br.readLine()) != null) {
            Integer k = Integer.parseInt(st);
            String msg = codeMap.get(k);
            a = a + msg;
            if(a.length() % 8 == 0){
                for (int i = 0; i < a.length(); i += 8) {
                    String byteX = a.substring(i, i + 8);
                    int byteToInt = 0xFF & Integer.parseInt(byteX, 2);
                    output.write(byteToInt);
                }
                a = "";
            }
        }
        output.close();
    }

    public static LinkedHashMap<Integer,String> huffmanCodeGenerator(DNode n, String hCode, LinkedHashMap m, BufferedWriter w){

        if(n!=null){
            if(n.getKey()!=-1){
                String codePair = n.getKey() + " " + hCode + "\n";
                m.put(n.getKey(), hCode.trim());
                try{
                    w.write(codePair);
                }catch(IOException i){
                    System.out.println("Error");
                }

            }else{
                huffmanCodeGenerator(n.getLeftchild(),hCode + "0", m, w);
                huffmanCodeGenerator(n.getRightchild(),hCode + "1", m, w);
            }
        }
        return m;
    }

    public static void runEncoder(String path) throws Exception{
        LinkedHashMap<Integer, Integer>  m = new FrequencyGenerator().makeFrequencyTable(path);

        //Part-A: Record average time taken by each heap
        int iterations = 10;
        long startTime = System.currentTimeMillis();
        BinaryHeap tree_using_binary_heap = null;
        for(int i=0; i<iterations; i++){
            tree_using_binary_heap = HuffmanGenerator.build_tree_using_binary_heap(m);
        }
        long stopTime = System.currentTimeMillis();
        System.out.println("Binary Heap Time ===> " + (stopTime - startTime)/10 + " milliseconds.");

        DHeap tree_using_4way_dheap = null;
        startTime = System.currentTimeMillis();
        for(int i=0; i<iterations; i++){
            tree_using_4way_dheap = HuffmanGenerator.build_tree_using_4way_dheap(m);
        }
        stopTime = System.currentTimeMillis();
        System.out.println("4 way Heap Time ===> " + (stopTime - startTime)/10 + " milliseconds.");


        startTime = System.currentTimeMillis();
        for(int i=0; i<iterations; i++){
            HuffmanGenerator.build_tree_using_pairing_heap(m);
        }
        stopTime = System.currentTimeMillis();
        System.out.println("Pairing Heap Time ===> " + (stopTime - startTime)/10 + " milliseconds.");

        //Part-B : Encoding using the fastest heap i.e, 4-way cache optimized heap
        System.out.println("Storing data into 4-Way Cache Optimized Heap");
        //DHeap tree_using_4way_dheap = HuffmanGenerator.build_tree_using_4way_dheap(m);
        System.out.println("Encoding started using 4-way cache optimized heap");
        File file2 = new File(path);
        File file = new File("code_table.txt");
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        String code = "";
        LinkedHashMap<Integer,String> codeMap = new LinkedHashMap<>();
        huffmanCodeGenerator(tree_using_4way_dheap.getdHeap()[0], code, codeMap, bw);
        System.out.println("code_table.txt generated");
        bw.close();
        encodeInput(codeMap, file2);
        System.out.println("encoded.bin generated");

    }

    public static void main(String[] args)throws Exception{

        long startTime = System.currentTimeMillis();
        String path = args[0];
        encoder encoder = new encoder();
        encoder.runEncoder(path);
        long stopTime = System.currentTimeMillis();
        System.out.println("Time Taken (encoder): " + (stopTime - startTime)/1000 + " seconds.");
    }
}

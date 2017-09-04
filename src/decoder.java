import java.io.*;

/**
 * Created by singh0777 on 3/29/17.
 */

class Node {
    private Node parent;
    private Node leftchild;
    private Node rightchild;
    private int key = -1;
    private int value = -2;
    private boolean isleaf = false;

    public Node(int k, int v){
        this.key = k;
        this.value = v;
    }

    public Node(){

    }

    public Node getLeftchild() {
        return leftchild;
    }

    public void setLeftchild(Node leftchild) {
        this.leftchild = leftchild;
    }

    public Node getRightchild() {
        return rightchild;
    }

    public void setRightchild(Node rightchild) {
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


public class decoder {

    public static Node generateTree(File file) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        Node root = new Node();
        while( (st = br.readLine()) != null){
            int l = st.split(" ")[1].length();
            root = generateTreefromCode(root, st, 0, l);
        }
        return root;
    }

    public static Node generateTreefromCode(Node root, String codePair, int pos, int posMax) throws Exception{
        if(pos!=posMax){
            if(codePair.split(" ")[1].charAt(pos)=='0'){
                if(root.getLeftchild()==null){
                    Node newNode = new Node();
                    root.setLeftchild(generateTreefromCode(newNode,codePair, pos+1, posMax));
                }else{
                    root.setLeftchild(generateTreefromCode(root.getLeftchild(),codePair, pos+1, posMax));
                }
            }else if(codePair.split(" ")[1].charAt(pos)=='1'){
                if(root.getRightchild()==null){
                    Node newNode = new Node();
                    root.setRightchild(generateTreefromCode(newNode,codePair, pos+1, posMax));
                }else{
                    root.setRightchild(generateTreefromCode(root.getRightchild(),codePair, pos+1, posMax));
                }
            }
        }else{
            root.setKey(Integer.parseInt(codePair.split(" ")[0]));
            root.setToLeaf();
            root.setValue(31116116);
        }
        return root;
    }

    public static void getDecodedMessageString(File file2, File file3, Node htree) throws Exception{
        InputStream input = new BufferedInputStream(new FileInputStream(file2));
        byte[] byteArray = new byte[(int)file2.length()];
        int readCount = 0;
        while(readCount < byteArray.length){
            int bytesGrabbed = input.read(byteArray, readCount, byteArray.length - readCount);
            if (bytesGrabbed > 0){
                readCount = readCount + bytesGrabbed;
            }
        }
        input.close();
        FileWriter fw = new FileWriter(file3.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        String r = "";
        for(int i=0; i<byteArray.length;i++){
            int a = byteArray[i] & 0xFF;
            String s = String.format("%08d", Integer.parseInt(Integer.toBinaryString(a)));
            r = decodeData(htree, r+s, bw);
        }
        bw.close();
    }

    public static String decodeData(Node htree, String message, BufferedWriter bw)throws Exception{
        Node utree = htree;
        Node n = null;
        String residual = "";
        while(message.length()>0){
            for(int i=0; i<message.length();i++){
                residual = residual + message.charAt(i);
                if(message.charAt(i)=='0'){
                    n = htree.getLeftchild();
                    htree = n;
                    if(n.isIsleaf()){
                        bw.write(n.getKey() + "\n");
                        message = message.substring(i+1);
                        htree = utree;
                        residual = "";
                        break;
                    }
                }else if(message.charAt(i)=='1'){
                    n = htree.getRightchild();
                    htree = n;
                    if(n.isIsleaf()){
                        bw.write(n.getKey() + "\n");
                        message = message.substring(i+1);
                        htree=utree;
                        residual = "";
                        break;
                    }
                }
            }
            if(residual.trim().length()>0){
                break;
            }
        }
        return residual;
    }

    public static void runDecoder(String[] arr) throws Exception{
        File file1 = new File(arr[0]);
        File file2 = new File(arr[1]);
        File file3 = new File("decoded.txt");
        System.out.println("Decoding process started");
        Node htree = generateTree(file2);
        System.out.println("Huffman tree generated using code table.");
        System.out.println("Message decoding begins");
        getDecodedMessageString(file1, file3,htree);
        System.out.println("decoded.txt generated");
    }

    public static void main(String[] args) throws Exception{

        long startTime = System.currentTimeMillis();
        String path1 = args[0];
        String path2 = args[1];
        decoder decoder = new decoder();
        decoder.runDecoder(args);
        long stopTime = System.currentTimeMillis();
        System.out.println("Time Taken (Decoder): " + (stopTime - startTime)/1000 + " seconds.");
    }
}

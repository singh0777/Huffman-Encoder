/**
 * Created by Batman on 01-08-2017.
 */
public class testing {
    public static String convert(String s, int numRows) {
        int m = 2*numRows-2;
        String sn = "";
        for(int i=0; i<numRows; i++){
            for(int j = 0; j<s.length(); j=j+m){
                if(i==0){
                    sn = sn + s.charAt(j);
                }else if(i==numRows-1){
                    try{
                        sn = sn + s.charAt(j+i);
                    }catch(StringIndexOutOfBoundsException exp){
                    }
                }else if (j==0 && i>0 && i<(numRows-1)){
                    sn = sn + s.charAt(j+i);
                }else if(j==(s.length()-s.length()%m) && j+i>s.length()){
                    sn = sn;
                }else{
                    System.out.println(i + " " + j);
                    sn = sn + s.charAt(j-i) + s.charAt(j+i);
                }
            }
        }
        return sn;
    }

    public static void main(String[] args){
        String ss = convert("abcdefgh", 4);
        System.out.println(ss);
    }
}

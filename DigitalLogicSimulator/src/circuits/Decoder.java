package circuits;
public class Decoder {
    public int[] evaluate(int a,int b) { int[] y=new int[4]; y[(a<<1)|b]=1; return y; }
}

package circuits;
public class Multiplexer {
    public int evaluate2(int i0,int i1,int s) { return s==0?i0:i1; }
    public int evaluate4(int[] data,int s1,int s0) { return data[(s1<<1)|s0]; }
}

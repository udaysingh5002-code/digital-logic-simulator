package circuits;
public class FullAdder {
    public int[] evaluate(int a,int b,int cin) { return new int[]{a^b^cin,(a&b)|(cin&(a^b))}; }
}

package circuits;
public class Comparator {
    public int[] evaluate(int a,int b) { return new int[]{a>b?1:0,a==b?1:0,a<b?1:0}; }
}

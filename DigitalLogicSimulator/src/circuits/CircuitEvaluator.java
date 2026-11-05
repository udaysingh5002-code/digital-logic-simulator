package circuits;
import gates.BasicGate;
public final class CircuitEvaluator {
    private CircuitEvaluator() {}
    public static final String[] CIRCUITS={"AND","OR","NOT","NAND","NOR","XOR","XNOR","Half Adder","Full Adder","2:1 Multiplexer","4:1 Multiplexer","2:4 Decoder","1-bit Comparator"};
    public static String[] inputNames(String c) {
        switch(c) {
            case "NOT": return new String[]{"A"};
            case "Full Adder": return new String[]{"A","B","Cin"};
            case "2:1 Multiplexer": return new String[]{"I0","I1","S"};
            case "4:1 Multiplexer": return new String[]{"I0","I1","I2","I3","S1","S0"};
            default: return new String[]{"A","B"};
        }
    }
    public static String[] outputNames(String c) {
        switch(c) {
            case "Half Adder": case "Full Adder": return new String[]{"Sum","Carry"};
            case "2:4 Decoder": return new String[]{"Y0","Y1","Y2","Y3"};
            case "1-bit Comparator": return new String[]{"A>B","A=B","A<B"};
            default: return new String[]{"Y"};
        }
    }
    public static int[] evaluate(String c,int[] x) {
        int a=x[0],b=x.length>1?x[1]:0;
        switch(c) {
            case "AND": case "OR": case "NOT": case "NAND": case "NOR": case "XOR": case "XNOR": return new int[]{new BasicGate(c).evaluate(x)};
            case "Half Adder": return new HalfAdder().evaluate(a,b);
            case "Full Adder": return new FullAdder().evaluate(a,b,x[2]);
            case "2:1 Multiplexer": return new int[]{new Multiplexer().evaluate2(a,b,x[2])};
            case "4:1 Multiplexer": return new int[]{new Multiplexer().evaluate4(new int[]{x[0],x[1],x[2],x[3]},x[4],x[5])};
            case "2:4 Decoder": return new Decoder().evaluate(a,b);
            case "1-bit Comparator": return new Comparator().evaluate(a,b);
            default: throw new IllegalArgumentException("Unknown circuit: "+c);
        }
    }
    public static String expression(String c) {
        switch(c) {
            case "AND": return "Y = A · B"; case "OR": return "Y = A + B"; case "NOT": return "Y = ¬A";
            case "NAND": return "Y = ¬(A · B)"; case "NOR": return "Y = ¬(A + B)";
            case "XOR": return "Y = A ⊕ B"; case "XNOR": return "Y = ¬(A ⊕ B)";
            case "Half Adder": return "Sum = A ⊕ B; Carry = A · B";
            case "Full Adder": return "Sum = A ⊕ B ⊕ Cin; Carry = AB + Cin(A ⊕ B)";
            case "2:1 Multiplexer": return "Y = ¬S·I0 + S·I1";
            case "4:1 Multiplexer": return "Y = ¬S1¬S0I0 + ¬S1S0I1 + S1¬S0I2 + S1S0I3";
            case "2:4 Decoder": return "Y0=¬A¬B, Y1=¬AB, Y2=A¬B, Y3=AB";
            case "1-bit Comparator": return "A>B=A¬B; A=B=¬(A⊕B); A<B=¬AB";
            default: return "";
        }
    }
}

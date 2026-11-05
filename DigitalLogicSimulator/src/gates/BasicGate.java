package gates;
public class BasicGate implements LogicGate {
    private final String type;
    public BasicGate(String type) { this.type = type; }
    @Override public int evaluate(int... x) {
        int a=x[0], b=x.length>1?x[1]:0;
        switch(type) {
            case "AND": return a&b;
            case "OR": return a|b;
            case "NOT": return 1-a;
            case "NAND": return 1-(a&b);
            case "NOR": return 1-(a|b);
            case "XOR": return a^b;
            case "XNOR": return 1-(a^b);
            default: throw new IllegalArgumentException("Unknown gate: "+type);
        }
    }
}

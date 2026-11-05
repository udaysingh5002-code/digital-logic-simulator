package gates;
public class NANDGate implements LogicGate {
    @Override public int evaluate(int... inputs) {
        return new BasicGate("NAND").evaluate(inputs);
    }
}

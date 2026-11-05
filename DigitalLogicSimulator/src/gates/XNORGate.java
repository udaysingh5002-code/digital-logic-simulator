package gates;
public class XNORGate implements LogicGate {
    @Override public int evaluate(int... inputs) {
        return new BasicGate("XNOR").evaluate(inputs);
    }
}

package gates;
public class XORGate implements LogicGate {
    @Override public int evaluate(int... inputs) {
        return new BasicGate("XOR").evaluate(inputs);
    }
}

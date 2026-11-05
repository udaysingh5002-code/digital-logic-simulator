package gates;
public class ORGate implements LogicGate {
    @Override public int evaluate(int... inputs) {
        return new BasicGate("OR").evaluate(inputs);
    }
}

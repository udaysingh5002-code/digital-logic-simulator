package gates;
public class ANDGate implements LogicGate {
    @Override public int evaluate(int... inputs) {
        return new BasicGate("AND").evaluate(inputs);
    }
}

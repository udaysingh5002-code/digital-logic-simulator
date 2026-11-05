package gates;
public class NOTGate implements LogicGate {
    @Override public int evaluate(int... inputs) {
        return new BasicGate("NOT").evaluate(inputs);
    }
}

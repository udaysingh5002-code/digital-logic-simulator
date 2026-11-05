package gates;
public class NORGate implements LogicGate {
    @Override public int evaluate(int... inputs) {
        return new BasicGate("NOR").evaluate(inputs);
    }
}

package gui;
import circuits.CircuitEvaluator;
import javax.swing.*;
import java.awt.*;
public class MainFrame extends JFrame {
    private final JComboBox<String> circuitSelector=new JComboBox<>(CircuitEvaluator.CIRCUITS);
    private final JPanel inputPanel=new JPanel();
    private final JLabel resultLabel=new JLabel();
    private final JLabel expressionLabel=new JLabel();
    private final CircuitPanel circuitPanel=new CircuitPanel();
    private final TruthTablePanel truthTable=new TruthTablePanel();
    private JComboBox<Integer>[] selectors;
    public MainFrame() {
        super("Digital Logic Circuit Simulator");setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100,780);setMinimumSize(new Dimension(820,600));setLocationRelativeTo(null);
        JPanel root=new JPanel(new BorderLayout(12,12));root.setBackground(Theme.BACKGROUND);
        root.setBorder(BorderFactory.createEmptyBorder(14,14,14,14));setContentPane(root);
        JLabel title=label("DIGITAL LOGIC CIRCUIT SIMULATOR",24);title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(Theme.ACCENT);root.add(title,BorderLayout.NORTH);
        JPanel middle=new JPanel(new BorderLayout(12,12));middle.setBackground(Theme.BACKGROUND);root.add(middle,BorderLayout.CENTER);
        JPanel controls=new JPanel();controls.setBackground(Theme.PANEL);controls.setLayout(new BoxLayout(controls,BoxLayout.Y_AXIS));
        controls.setBorder(BorderFactory.createEmptyBorder(16,12,16,12));controls.setPreferredSize(new Dimension(260,300));
        controls.add(label("SELECT CIRCUIT",15));controls.add(Box.createVerticalStrut(8));
        circuitSelector.setMaximumSize(new Dimension(230,35));circuitSelector.setAlignmentX(Component.LEFT_ALIGNMENT);controls.add(circuitSelector);
        controls.add(Box.createVerticalStrut(18));controls.add(label("BINARY INPUTS",15));
        inputPanel.setLayout(new BoxLayout(inputPanel,BoxLayout.Y_AXIS));inputPanel.setBackground(Theme.PANEL);
        controls.add(inputPanel);controls.add(Box.createVerticalStrut(10));
        JButton simulate=button("SIMULATE"),reset=button("RESET");
        controls.add(simulate);controls.add(Box.createVerticalStrut(8));controls.add(reset);controls.add(Box.createVerticalStrut(18));
        resultLabel.setForeground(Theme.TEXT);resultLabel.setFont(new Font("SansSerif",Font.BOLD,16));
        resultLabel.setAlignmentX(Component.LEFT_ALIGNMENT);controls.add(resultLabel);
        middle.add(controls,BorderLayout.WEST);middle.add(circuitPanel,BorderLayout.CENTER);
        JPanel bottom=new JPanel(new BorderLayout(6,6));bottom.setBackground(Theme.PANEL);bottom.setPreferredSize(new Dimension(900,270));
        bottom.add(truthTable,BorderLayout.CENTER);expressionLabel.setForeground(Theme.TEXT);
        expressionLabel.setBorder(BorderFactory.createEmptyBorder(6,8,6,8));
        bottom.add(expressionLabel,BorderLayout.SOUTH);root.add(bottom,BorderLayout.SOUTH);
        circuitSelector.addActionListener(e->updateCircuit());simulate.addActionListener(e->simulate());
        reset.addActionListener(e -> {
    for (JComboBox<Integer> s : selectors) {
        s.setSelectedIndex(0);
    }

    resultLabel.setText("OUTPUT: Click SIMULATE");
    circuitPanel.repaint();
});
        updateCircuit();
    }
    private JLabel label(String s,int size) {JLabel l=new JLabel(s);l.setForeground(Theme.TEXT);l.setFont(new Font("SansSerif",Font.BOLD,size));l.setAlignmentX(Component.LEFT_ALIGNMENT);return l;}
    private JButton button(String s) {JButton b=new JButton(s);b.setAlignmentX(Component.LEFT_ALIGNMENT);b.setMaximumSize(new Dimension(230,38));b.setBackground(Theme.ACCENT);b.setForeground(Color.BLACK);b.setFocusPainted(false);return b;}
   @SuppressWarnings("unchecked")
private void updateCircuit() {

    inputPanel.removeAll();

    String c = (String) circuitSelector.getSelectedItem();
    String[] names = CircuitEvaluator.inputNames(c);

    selectors = (JComboBox<Integer>[]) new JComboBox<?>[names.length];

    for (int i = 0; i < names.length; i++) {

        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 3));
        row.setBackground(Theme.PANEL);

        JLabel name = label(names[i] + ":", 14);
        name.setPreferredSize(new Dimension(50, 25));

        JComboBox<Integer> combo =
                new JComboBox<>(new Integer[]{0, 1});

        // Do not calculate automatically when input changes
        combo.addActionListener(e -> {
            resultLabel.setText("OUTPUT: Click SIMULATE");
            circuitPanel.repaint();
        });

        selectors[i] = combo;

        row.add(name);
        row.add(combo);
        inputPanel.add(row);
    }

    inputPanel.revalidate();
    inputPanel.repaint();

    truthTable.showCircuit(c);

    resultLabel.setText("OUTPUT: Click SIMULATE");
}
    private void simulate() {
        if(selectors==null)return;String c=(String)circuitSelector.getSelectedItem();int[] bits=new int[selectors.length];
        for(int i=0;i<bits.length;i++)bits[i]=(Integer)selectors[i].getSelectedItem();
        int[] out=CircuitEvaluator.evaluate(c,bits);String[] names=CircuitEvaluator.outputNames(c);
        StringBuilder text=new StringBuilder("<html>OUTPUT:<br>");
        for(int i=0;i<out.length;i++)text.append(names[i]).append(" = ").append(out[i]).append("<br>");
        resultLabel.setText(text.append("</html>").toString());
        expressionLabel.setText("BOOLEAN EXPRESSION: "+CircuitEvaluator.expression(c));
        circuitPanel.showCircuit(c,bits,out);
    }
}

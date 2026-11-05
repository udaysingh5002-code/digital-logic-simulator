package gui;
import circuits.CircuitEvaluator;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
public class TruthTablePanel extends JPanel {
    private final JTable table=new JTable();
    public TruthTablePanel() {
        super(new BorderLayout()); setBackground(Theme.PANEL);
        JLabel title=new JLabel("TRUTH TABLE",SwingConstants.CENTER); title.setForeground(Theme.TEXT);
        title.setFont(new Font("SansSerif",Font.BOLD,16)); add(title,BorderLayout.NORTH);
        table.setRowHeight(25); table.setBackground(Theme.PANEL); table.setForeground(Theme.TEXT);
        table.setGridColor(new Color(70,75,90)); table.setSelectionBackground(new Color(65,100,150));
        add(new JScrollPane(table),BorderLayout.CENTER);
    }
    public void showCircuit(String circuit) {
        String[] in=CircuitEvaluator.inputNames(circuit),out=CircuitEvaluator.outputNames(circuit);
        String[] cols=new String[in.length+out.length];
        System.arraycopy(in,0,cols,0,in.length); System.arraycopy(out,0,cols,in.length,out.length);
        int rows=1<<in.length; Object[][] data=new Object[rows][cols.length];
        for(int r=0;r<rows;r++) {
            int[] bits=new int[in.length];
            for(int j=0;j<in.length;j++) { bits[j]=(r>>(in.length-j-1))&1; data[r][j]=bits[j]; }
            int[] result=CircuitEvaluator.evaluate(circuit,bits);
            for(int j=0;j<result.length;j++) data[r][in.length+j]=result[j];
        }
        table.setModel(new DefaultTableModel(data,cols) {
            @Override public boolean isCellEditable(int r,int c) { return false; }
        });
    }
}

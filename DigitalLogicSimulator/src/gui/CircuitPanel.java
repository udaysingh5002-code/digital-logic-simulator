package gui;
import circuits.CircuitEvaluator;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
public class CircuitPanel extends JPanel {
    private String circuit="AND";
    private int[] inputs={0,0},outputs={0};
    public CircuitPanel() { setBackground(Theme.PANEL); setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Theme.ACCENT),"CIRCUIT DIAGRAM",0,0,new Font("SansSerif",Font.BOLD,15),Theme.TEXT)); }
    public void showCircuit(String c,int[] in,int[] out) { circuit=c;inputs=in.clone();outputs=out.clone();repaint(); }
    @Override protected void paintComponent(Graphics g) {
        super.paintComponent(g); Graphics2D g2=(Graphics2D)g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        int w=getWidth(),h=getHeight();
        if(Arrays.asList("AND","OR","NOT","NAND","NOR","XOR","XNOR").contains(circuit)) drawGate(g2,w,h);
        else drawBlock(g2,w,h);
        g2.dispose();
    }
    private void drawGate(Graphics2D g,int w,int h) {
        int cx=w/2,cy=h/2,x=cx-60,y=cy-45;
        g.setStroke(new BasicStroke(3));g.setColor(Theme.ACCENT);
        boolean not=circuit.equals("NOT"),and=circuit.equals("AND")||circuit.equals("NAND");
        if(not) { Polygon p=new Polygon(new int[]{x,x,x+105},new int[]{y,y+90,cy},3);g.drawPolygon(p); }
        else if(and) {g.drawLine(x,y,x,y+90);g.drawLine(x,y,x+40,y);g.drawLine(x,y+90,x+40,y+90);g.drawArc(x-5,y,90,90,-90,180);}
        else {
            // Simplified OR-family outline; labels distinguish XOR/XNOR.
            g.drawArc(x-50,y-10,100,110,-65,130);
            g.drawArc(x-55,y-55,190,145,-72,62);
            g.drawArc(x-55,y,190,145,10,62);
        }
        boolean bubble=not||circuit.equals("NAND")||circuit.equals("NOR")||circuit.equals("XNOR");
        int ox=x+120;if(bubble){g.drawOval(ox-12,cy-7,14,14);ox+=6;}
        g.drawLine(ox,cy,ox+45,cy);
        g.setColor(Theme.TEXT);g.setFont(new Font("SansSerif",Font.BOLD,15));
        g.drawString("Y="+outputs[0],ox+48,cy+5);
        if(not){g.setColor(Theme.ACCENT);g.drawLine(x-75,cy,x,cy);g.setColor(Theme.TEXT);g.drawString("A="+inputs[0],x-115,cy+5);}
        else {g.setColor(Theme.ACCENT);g.drawLine(x-75,cy-25,x,cy-25);g.drawLine(x-75,cy+25,x,cy+25);g.setColor(Theme.TEXT);g.drawString("A="+inputs[0],x-115,cy-20);g.drawString("B="+inputs[1],x-115,cy+30);}
        g.setFont(new Font("SansSerif",Font.BOLD,19));g.drawString(circuit+" GATE",cx-65,cy-110);
    }
    private void drawBlock(Graphics2D g,int w,int h) {
        int bw=155,bh=Math.max(125,inputs.length*32),x=w/2-bw/2,y=h/2-bh/2;
        g.setColor(Theme.ACCENT);g.setStroke(new BasicStroke(2));g.drawRoundRect(x,y,bw,bh,12,12);
        g.setColor(Theme.TEXT);g.setFont(new Font("SansSerif",Font.BOLD,13));
        FontMetrics fm=g.getFontMetrics();g.drawString(circuit,x+(bw-fm.stringWidth(circuit))/2,y+bh/2);
        String[] ins=CircuitEvaluator.inputNames(circuit),outs=CircuitEvaluator.outputNames(circuit);
        g.setFont(new Font("SansSerif",Font.PLAIN,13));
        for(int i=0;i<inputs.length;i++) {int py=y+(i+1)*bh/(inputs.length+1);g.setColor(Theme.ACCENT);g.drawLine(x-35,py,x,py);g.setColor(Theme.TEXT);g.drawString(ins[i]+"="+inputs[i],x-90,py+4);}
        for(int i=0;i<outputs.length;i++) {int py=y+(i+1)*bh/(outputs.length+1);g.setColor(Theme.ACCENT);g.drawLine(x+bw,py,x+bw+30,py);g.setColor(Theme.TEXT);g.drawString(outs[i]+"="+outputs[i],x+bw+33,py+4);}
    }
}

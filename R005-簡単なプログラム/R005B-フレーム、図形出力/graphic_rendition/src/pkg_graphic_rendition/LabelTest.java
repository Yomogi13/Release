package pkg_graphic_rendition;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;

public class LabelTest extends Frame {
    public static void main(String [] args) {
        new LabelTest();
    }
    LabelTest() {
        super("LabelTest");
        setSize(200, 100);
        setLayout(new FlowLayout());
        //ラベルの名前
        Label l1 = new Label("HelloWorld!!");
        add(l1);
        show();
    }
}
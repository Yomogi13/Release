package pkg_graphic_rendition;

import java.awt.Checkbox;
import java.awt.FlowLayout;
import java.awt.Frame;

public class CheckboxTest extends Frame {
    public static void main(String [] args) {
        new CheckboxTest();
    }
    CheckboxTest() {
        super("CheckboxTest");
        setSize(200, 100);
        setLayout(new FlowLayout());
        //チェックボックス
        Checkbox c1 = new Checkbox("OK?");
        add(c1);
        show();
    }
}
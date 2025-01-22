package pkg_graphic_rendition;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Scrollbar;

public class ScrollbarTest extends Frame {
    public static void main(String [] args) {
        new ScrollbarTest();
    }
    ScrollbarTest() {
        super("ScrollbarTest");
        setSize(200, 100);
        setLayout(new BorderLayout());
        Scrollbar sb1 = new Scrollbar(Scrollbar.HORIZONTAL);
        add(sb1, BorderLayout.SOUTH);
        show();
    }
}
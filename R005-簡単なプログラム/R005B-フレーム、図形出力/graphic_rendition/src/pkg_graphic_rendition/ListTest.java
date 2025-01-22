package pkg_graphic_rendition;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.List;

public class ListTest extends Frame {
    public static void main(String [] args) {
        new ListTest();
    }
    ListTest() {
        super("ListTest");
        setSize(200, 100);
        setLayout(new FlowLayout());
        List list1 = new List();
        list1.add("ListA");
        list1.add("ListB");
        add(list1);
        show();
    }
}
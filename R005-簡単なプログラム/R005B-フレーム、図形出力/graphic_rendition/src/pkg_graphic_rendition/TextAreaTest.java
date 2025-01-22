package pkg_graphic_rendition;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextArea;

public class TextAreaTest extends Frame {
    public static void main(String [] args) {
        new TextAreaTest();
    }
    TextAreaTest() {
        super("TextAreaTest");
        setSize(200, 100);
        setLayout(new FlowLayout());
        //テキストエリア
        TextArea b1 = new TextArea("Hello World!!", 3, 20);
        add(b1);
        show();
    }
}

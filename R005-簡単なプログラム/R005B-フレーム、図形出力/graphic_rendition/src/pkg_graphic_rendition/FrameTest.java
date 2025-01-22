package pkg_graphic_rendition;

import java.awt.Frame;

public class FrameTest extends Frame {
    public static void main(String [] args) {
        new FrameTest();
    }
    //フレーム本体
    FrameTest() { 
    	//フレームの名前
        super("FrameTest");
        //フレームサイズ
        setSize(200, 100);
        //フレームを出力
        show();
    }
}

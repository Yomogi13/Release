package pkg_graphic_rendition;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class SimpleDrawing extends JFrame {
    // コンストラクタでフレームを設定
    public SimpleDrawing() {
        setTitle("Canvasを使った簡単な絵");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // ウィンドウを中央に配置
        add(new DrawingCanvas()); // カスタムCanvasを追加
    }

    // メインメソッド
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SimpleDrawing frame = new SimpleDrawing();
            frame.setVisible(true);
        });
    }

    // カスタムCanvasクラス
    static class DrawingCanvas extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            // 背景色を設定
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, getWidth(), getHeight());

            // 線を描画
            g2d.setColor(Color.BLUE);
            g2d.drawLine(50, 50, 200, 50);

            // 長方形を描画
            g2d.setColor(Color.RED);
            g2d.fillRect(100, 100, 150, 100);

            // 円を描画
            g2d.setColor(Color.GREEN);
            g2d.fillOval(300, 150, 100, 100);

            // テキストを描画
            g2d.setColor(Color.BLACK);
            g2d.drawString("Java Canvas Drawing Example", 150, 30);
        }
    }
}

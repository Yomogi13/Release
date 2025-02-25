package pkg_graphic_rendition;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GiraffeDrawing extends JFrame {
    // コンストラクタでフレームを設定
    public GiraffeDrawing() {
        setTitle("キリンの絵 - Canvas");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // ウィンドウを中央に配置
        add(new GiraffeCanvas()); // カスタムCanvasを追加
    }

    // メインメソッド
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GiraffeDrawing frame = new GiraffeDrawing();
            frame.setVisible(true);
        });
    }

    // カスタムCanvasクラス
    static class GiraffeCanvas extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            // 背景を塗りつぶす
            g2d.setColor(Color.CYAN);
            g2d.fillRect(0, 0, getWidth(), getHeight());

            // キリンの胴体（長方形）
            g2d.setColor(new Color(255, 204, 102)); // キリンの黄色
            g2d.fillRect(200, 250, 150, 200); // x=200, y=250, 幅=150, 高さ=200

            // キリンの首（細長い長方形）
            g2d.fillRect(260, 100, 30, 150); // x=260, y=100, 幅=30, 高さ=150

            // キリンの頭（楕円）
            g2d.fillOval(245, 60, 60, 50); // x=245, y=60, 幅=60, 高さ=50

            // キリンの耳（小さな楕円）
            g2d.fillOval(235, 65, 20, 30); // 左耳
            g2d.fillOval(305, 65, 20, 30); // 右耳

            // キリンの足（4本の細長い長方形）
            g2d.fillRect(210, 450, 20, 100); // 左前足
            g2d.fillRect(270, 450, 20, 100); // 左後足
            g2d.fillRect(300, 450, 20, 100); // 右後足
            g2d.fillRect(360, 450, 20, 100); // 右前足

            // キリンのしっぽ
            g2d.fillRect(330, 400, 10, 50);

            // キリンの模様（茶色の円を胴体に配置）
            g2d.setColor(new Color(153, 102, 51)); // 茶色
            g2d.fillOval(210, 260, 30, 30); // 模様1
            g2d.fillOval(250, 300, 40, 40); // 模様2
            g2d.fillOval(230, 360, 35, 35); // 模様3
            g2d.fillOval(280, 280, 30, 30); // 模様4
            g2d.fillOval(320, 340, 35, 35); // 模様5

            // キリンの目（黒い小さな円）
            g2d.setColor(Color.BLACK);
            g2d.fillOval(265, 80, 10, 10); // 左目
            g2d.fillOval(285, 80, 10, 10); // 右目

            // キリンの口（小さな楕円）
            g2d.setColor(Color.PINK);
            g2d.fillOval(270, 100, 20, 10); // 口
        }
    }
}
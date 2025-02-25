package pkg_graphic_rendition;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class StarryNight extends JFrame {
    public StarryNight() {
        setTitle("星空 - Canvas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new StarryCanvas()); // カスタムCanvasを追加
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StarryNight frame = new StarryNight();
            frame.setVisible(true);
        });
    }

    static class StarryCanvas extends JPanel {
        private final Random random = new Random();

        // 星をランダムに配置
        private final int[][] stars = new int[100][2]; // 星の座標（100個）

        public StarryCanvas() {
            // 星の初期位置をランダムに設定
            for (int i = 0; i < stars.length; i++) {
                stars[i][0] = random.nextInt(800); // X座標
                stars[i][1] = random.nextInt(600); // Y座標
            }

            // アニメーションのためにタイマーを設定
            Timer timer = new Timer(500, e -> {
                // 星の輝きをランダムに変更
                for (int i = 0; i < stars.length; i++) {
                    stars[i][0] = (stars[i][0] + random.nextInt(3) - 1 + 800) % 800;
                    stars[i][1] = (stars[i][1] + random.nextInt(3) - 1 + 600) % 600;
                }
                repaint();
            });
            timer.start();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            // 背景を夜空に設定（濃い青色）
            g2d.setColor(new Color(10, 10, 50)); // 夜空の色
            g2d.fillRect(0, 0, getWidth(), getHeight());

            // 星を描画（ランダムな白い点）
            for (int[] star : stars) {
                int starSize = random.nextInt(3) + 2; // 星の大きさ（ランダム）
                g2d.setColor(new Color(255, 255, 255, random.nextInt(256))); // 輝き具合（透明度）
                g2d.fillOval(star[0], star[1], starSize, starSize);
            }

            // 地平線を描画（黒の長方形）
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0, getHeight() - 50, getWidth(), 50);

            // 月を描画（黄色い円）
            g2d.setColor(Color.YELLOW);
            g2d.fillOval(600, 50, 100, 100); // x=600, y=50, 直径=100
        }
    }
}
package pkg_graphic_rendition;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
	
	public class Test_graphic_outpt {
		public static void main (String[] args)
		{
			Win win = new Win("描画例１");
		}
	}
	
	class Win extends JFrame
	{
		/******************/
		/* コンストラクタ */
		/******************/
		Win(String name)
		{
						// JFrameクラスのコンストラクタ（Windowのタイトルを引き渡す）
			super(name);
						// Windowの大きさ
			setSize(310, 200);   // +40, +70
						// ContentPane の取得と MainPanel の追加
			MainPanel pn = new MainPanel();   // MainPanel オブジェクトの生成
			getContentPane().add(pn);   // MainPanel オブジェクトを ContentPane に追加
			pn.setSize(270, 130);
						// ウィンドウを表示
			setVisible(true);
						// イベントアダプタ
			addWindowListener(new WinEnd());
		}
	
		/******************************/
		/* 上，左，下，右の余白の設定 */
		/******************************/
		public Insets getInsets()
		{
			return new Insets(50, 20, 20, 20);
		}
	
		/************/
		/* 終了処理 */
		/************/
		class WinEnd extends WindowAdapter
		{
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		}
	}
	
	class MainPanel extends JPanel
	{
		MainPanel()
		{
			setBackground(new Color(238, 255, 238));   // 背景色の設定
		}
						// 描画
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);   // 親クラスの描画
								// Graphics2Dの取得
			Graphics2D g2 = (Graphics2D)g;
								// 線幅が5ピクセルの矩形
			g2.setStroke(new BasicStroke(5.0f));
			g2.draw(new Rectangle(20, 15, 100, 100));
								// 塗りつぶされた円
			g.setColor(Color.green);
			g.fillOval(30, 25, 80, 80);
								// 線幅が5ピクセルの矩形
			g.setColor(Color.red);
			g2.draw(new Rectangle(150, 15, 100, 100));
		}
	}
# PDF to Images Converter
シンプルで使いやすいPDF変換ツールです。
複数のPDFファイルをドラッグ＆ドロップまたはファイル選択で指定するだけで、各PDFごとに専用フォルダを作成し、全ページを高品質な画像（PNG/JPG）に自動変換します。

## 機能概要

### 複数PDFの一括処理
ドラッグ＆ドロップまたはファイル選択で複数のPDFを同時に指定可能
ドラッグ＆ドロップ対応
ウィンドウ全体またはリストボックスにPDFをドラッグするだけで追加

### 自動フォルダ生成
各PDFファイル名に基づいて専用フォルダを作成（例: report.pdf → report/ フォルダ）

### 出力形式選択
PNG（透明対応）またはJPGを選択可能
解像度（DPI）調整
72～600dpiの範囲でスライダー調整（デフォルト300dpi）

### リアルタイム進捗表示
進捗バーとパーセント表示で変換状況を視覚的に確認
エラー処理
変換失敗時も他のファイルは続行、詳細なエラーメッセージを表示
スタンドアロンexe対応
Python環境不要で配布可能（PyInstallerでビルド）

## 動作環境

Windows 10/11（64bit推奨）
Python 3.8以上（開発時：3.14使用）
必要ライブラリ：
PyMuPDF (fitz)
tkinterdnd2（ドラッグ＆ドロップ用）


## インストール・ビルド方法

### 開発環境での実行
Bashpip install pymupdf tkinterdnd2
python pdf_converter_gui_dragdrop_progress.py
exeファイルの作成（PyInstaller使用）
Bashpy
```
-m pip install pyinstaller
-m PyInstaller --onefile --windowed --hidden-import=fitz --clean pdf_converter_gui_dragdrop_progress.py
```
生成されたdist/pdf_converter_gui_dragdrop_progress.exeをダブルクリックで起動。

## 使用方法

### アプリを起動
PDFファイルをドラッグ＆ドロップ（または「PDFファイルを選択」ボタンで複数選択）
出力親フォルダを選択（デフォルトはアプリ起動フォルダ）
出力形式（PNG/JPG）と解像度（dpi）を設定
「変換開始」をクリック
進捗バーが動き、完了後に保存先が表示される

### 保存先の例
入力PDF: meeting_notes.pdf
出力フォルダ: meeting_notes/
内部ファイル: page_001.png, page_002.png, ...

## 注意事項

大量のPDFや高DPI設定では処理時間が長くなる場合があります。
非常に大きなPDF（数百ページ以上）はメモリ使用量が増加する可能性があります。
アンチウイルスソフトがexeを誤検知する場合があります（PyInstaller製の一般的な挙動）。
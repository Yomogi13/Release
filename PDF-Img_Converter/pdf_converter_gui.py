# pdf_converter_gui_dragdrop_fixed.py
import tkinter as tk
from tkinter import filedialog, messagebox, ttk
from tkinterdnd2 import *
import fitz
import os
import threading

class PDFConverterApp(TkinterDnD.Tk):
    def __init__(self):
        super().__init__()  # 親クラス初期化
        self.title("PDF to Images Converter (Drag & Drop + Multi)")
        self.geometry("600x500")
        self.resizable(False, False)
        
        # 複数PDFのパスを保持する
        self.pdf_paths = []  
        self.output_dir = tk.StringVar(value=os.getcwd())
        self.format_var = tk.StringVar(value="png")
        self.dpi_var = tk.IntVar(value=300)

        self.create_widgets()
        self.register_drop_target()

    def create_widgets(self):
        tk.Label(self, text="ここにPDFファイルをドラッグ＆ドロップ（複数可）またはボタンで選択").pack(pady=10)
        self.pdf_listbox = tk.Listbox(self, height=8, width=70)
        self.pdf_listbox.pack(pady=5)
        self.pdf_listbox.drop_target_register(DND_FILES)
        self.pdf_listbox.dnd_bind('<<Drop>>', self.on_drop)

        btn_frame = tk.Frame(self)
        btn_frame.pack(pady=5)
        tk.Button(btn_frame, text="PDFファイルを選択（複数可）", command=self.select_pdfs).pack(side=tk.LEFT, padx=10)
        tk.Button(btn_frame, text="選択解除", command=self.clear_pdfs).pack(side=tk.LEFT, padx=10)

        tk.Label(self, text="出力親フォルダ:").pack(pady=10)
        tk.Entry(self, textvariable=self.output_dir, width=60).pack()
        tk.Button(self, text="フォルダを選択", command=self.select_output_dir).pack(pady=5)

        tk.Label(self, text="出力形式:").pack(pady=5)
        ttk.Combobox(self, textvariable=self.format_var, values=["png", "jpg"], state="readonly").pack()

        tk.Label(self, text="解像度 (dpi):").pack(pady=5)
        tk.Scale(self, from_=72, to=600, orient=tk.HORIZONTAL, variable=self.dpi_var, length=400).pack()

        # 変換ボタン
        tk.Button(
            self,
            text="変換開始",
            command=self.start_conversion,
            bg="#2E7D32",  
            fg="white",
            font=("Arial", 12, "bold"),  
            width=15, height=6,  
            relief="raised",  
            padx=10, pady=10     
        ).pack(pady=20)
        self.status_label = tk.Label(self, text="待機中...（PDFをドラッグ＆ドロップまたは選択してください）", fg="blue")
        self.status_label.pack(pady=10)

    def register_drop_target(self):
        self.drop_target_register(DND_FILES)
        self.dnd_bind('<<Drop>>', self.on_drop_window)

    def on_drop(self, event):
        files = self.tk.splitlist(event.data)
        self.add_files(files)

    def on_drop_window(self, event):
        files = self.tk.splitlist(event.data)
        self.add_files(files)

    def add_files(self, files):
        added = False
        for file in files:
            if file.lower().endswith('.pdf') and file not in self.pdf_paths:
                self.pdf_paths.append(file)
                self.pdf_listbox.insert(tk.END, os.path.basename(file))
                added = True
        if added:
            self.status_label.config(text=f"{len(self.pdf_paths)} 個のPDFが追加されました", fg="green")

    def select_pdfs(self):
        files = filedialog.askopenfilenames(filetypes=[("PDF files", "*.pdf")])
        if files:
            self.add_files(files)

    def clear_pdfs(self):
        self.pdf_paths = []
        self.pdf_listbox.delete(0, tk.END)
        self.status_label.config(text="選択解除しました", fg="blue")

    def select_output_dir(self):
        dir_path = filedialog.askdirectory()
        if dir_path:
            self.output_dir.set(dir_path)

    def start_conversion(self):
        if not self.pdf_paths:
            messagebox.showerror("エラー", "PDFファイルを選択またはドラッグ＆ドロップしてください")
            return

        output_parent_dir = self.output_dir.get()
        if not os.path.exists(output_parent_dir):
            os.makedirs(output_parent_dir)

        dpi = self.dpi_var.get()
        fmt = self.format_var.get()

        threading.Thread(target=self.convert_multiple_pdfs, args=(dpi, fmt), daemon=True).start()

    def convert_multiple_pdfs(self, dpi, fmt):
        self.status_label.config(text="変換開始...", fg="orange")
        self.update()

        total_files = len(self.pdf_paths)
        processed = 0

        for pdf_path in self.pdf_paths:
            processed += 1
            pdf_name = os.path.splitext(os.path.basename(pdf_path))[0]
            output_dir = os.path.join(self.output_dir.get(), pdf_name)

            if not os.path.exists(output_dir):
                os.makedirs(output_dir)

            self.status_label.config(text=f"処理中: {processed}/{total_files} ({pdf_name})", fg="orange")
            self.update()

            try:
                doc = fitz.open(pdf_path)
                for i, page in enumerate(doc):
                    pix = page.get_pixmap(dpi=dpi, alpha=(fmt == "png"))
                    output_file = os.path.join(output_dir, f"page_{i+1:03d}.{fmt}")
                    pix.save(output_file)

                doc.close()

            except Exception as e:
                self.status_label.config(text=f"エラー: {pdf_name}", fg="red")
                messagebox.showerror("エラー", f"{pdf_name} の変換に失敗:\n{str(e)}")
                return

        self.status_label.config(text=f"完了！ {total_files} ファイルを変換しました", fg="green")
        messagebox.showinfo("完了", f"{total_files} ファイルを変換しました\n保存先: {self.output_dir.get()}")

if __name__ == "__main__":
    app = PDFConverterApp()
    app.mainloop()
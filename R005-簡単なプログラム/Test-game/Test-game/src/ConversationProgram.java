import java.util.Scanner;

public class ConversationProgram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("選択肢によってフラグが成立する会話プログラムです");
        System.out.println("1: 否定的な回答");
        System.out.println("2: 肯定的な回答");
        System.out.println("3: 中立的な回答");
        System.out.println("選択肢を入力してください (1〜3):");

        // ユーザー入力を取得
        int choice = 0;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("無効な入力です。プログラムを終了します。");
            return;
        }

        // 入力による分岐
        switch (choice) {
            case 1:
                System.out.println("否定的な回答をされました！あなたの名前は何ですか？");
                String userName = scanner.nextLine();
                System.out.println("君の名前は" + userName + "これは入力された文字列を出力しています");
                break;

            case 2:
                System.out.println("肯定的な回答をしました");
                break;

            case 3:
                System.out.println("中立的な回答をしました");
                break;

            default:
                System.out.println("無効な選択肢です。1〜3を選んでください。");
                break;
        }

        System.out.println("ではでは～");
        scanner.close();
    }
}

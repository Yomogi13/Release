/**
 * 
 */// 会話メッセージと選択肢を管理
function handleChoice(choice) {
  const messageDiv = document.getElementById('message'); // メッセージを表示する部分
  const optionsDiv = document.getElementById('options'); // 選択肢のボタンを表示する部分

  // 選択肢に応じた処理
  switch (choice) {
    case 1:
      messageDiv.innerHTML = `
        <p>否定的な回答を選択しました、名称認識のテストをします、名前を入力してください</p>
        <input type="text" id="userName" placeholder="名前を入力してください" />
        <button onclick="greetUser()">送信</button>
      `;
      optionsDiv.innerHTML = ''; // 選択肢を消去
      break;

    case 2:
      messageDiv.innerHTML = `
        <p>肯定的な回答を選択しました</p>
      `;
      optionsDiv.innerHTML = '<button onclick="restart()">もう一度選択する</button>';
      break;

    case 3:
      messageDiv.innerHTML = `
        <p>中立的な回答をしました</p>
      `;
      optionsDiv.innerHTML = '<button onclick="restart()">もう一度選択する</button>';
      break;

    default:
      messageDiv.innerHTML = `
        <p>無効な選択肢です。1〜3を選んでください。</p>
      `;
      optionsDiv.innerHTML = '<button onclick="restart()">もう一度選択する</button>';
      break;
  }
}

// 名前を入力後、挨拶メッセージを表示
function greetUser() {
  const userName = document.getElementById('userName').value;
  const messageDiv = document.getElementById('message');
  const optionsDiv = document.getElementById('options');

  if (userName.trim() !== '') {
    messageDiv.innerHTML = `<p>こんにちは、${userName}さん！お会いできて嬉しいです！</p>`;
  } else {
    messageDiv.innerHTML = `<p>名前が入力されていません。もう一度試してください。</p>`;
  }
  optionsDiv.innerHTML = '<button onclick="restart()">もう一度話す</button>';
}

// 会話を最初に戻す
function restart() {
  const messageDiv = document.getElementById('message');
  const optionsDiv = document.getElementById('options');

  messageDiv.innerHTML = `<p>回答を選択してください</p>`;
  optionsDiv.innerHTML = `
    <button onclick="handleChoice(1)">否定的な回答</button>
    <button onclick="handleChoice(2)">肯定的な回答</button>
    <button onclick="handleChoice(3)">中立的な回答</button>
  `;
}

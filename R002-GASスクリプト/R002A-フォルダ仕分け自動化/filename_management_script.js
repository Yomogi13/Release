function sortFiles() {
  // 仕分け対象のフォルダIDを指定
  const targetFolderId = 'main_folderID'; // ここにメインフォルダIDを入れる
  const targetFolder = DriveApp.getFolderById(targetFolderId);

  // サブフォルダのIDを指定
  const subFolder1Id = 'sub_folderID1'; // ここにサブフォルダのIDを入れる（例：任意のフォルダA）
  const subFolder2Id = 'sub_folderID2'; // ここにサブフォルダのIDを入れる（例：任意のフォルダB）
  //追加したいフォルダーの数分追加していく、’subFolder1Id’’sub_folderID1’は任意の命名にしておく
 
  const subFolder1 = DriveApp.getFolderById(subFolder1Id); //任意のフォルダA
  const subFolder2 = DriveApp.getFolderById(subFolder2Id); //任意のフォルダB

  // 仕分け対象フォルダ内のファイルを取得
  const files = targetFolder.getFiles();

  while (files.hasNext()) {
    const file = files.next();
    const fileName = file.getName();

    // ファイル名に分岐させるファイル名が含まれているかをチェックしてフォルダに移動
    if (fileName.includes('任意のファイル名')) {
      //任意のフォルダA
      file.moveTo(subFolder1);
    } else if (fileName.includes('任意のファイル名')) {
      //任意のフォルダB
      file.moveTo(subFolder2);
    //サブフォルダを追加した分だけ条件分岐を追加していく
    }
  }
}
Attribute VB_Name = "Module1"
Public strName As String

Sub AddOutlineToSpecificText()
    Dim ws As Worksheet
    Dim targetStrings As Variant
    Dim rng As Range, cell As Range
    Dim targetrange As Range
    Dim colC As Range, colB As Range
    Dim i As Long

    ' 実行対象シートを指定
    Set ws = Worksheets("収集要素")

    ' 対象文字列を指定する
    targetStrings = Array("チェック", "種類", "詳細", "タイトル:", "効果：", "フィギュア：", "タイトル", "効果", "所在")
    
    ' C列で「場所」を含むセルを検出し外枠を追加する
    Set colC = ws.Columns("C")
    For Each cell In colC
        If Not IsEmpty(cell.Value) And VarType(cell.Value) = vbString Then
            If InStr(cell.Value, "場所") > 0 Then
                Set targetrange = GetMergedRange(cell)
                If Not targetrange Is Nothing Then
                    targetrange.BorderAround LineStyle:=xlContinuous, Weight:=xlThick
                End If
            End If
        End If
    Next cell
    
    ' B列で「タイトル」を含むセルを検出し、外枠を追加する
    Set colB = ws.Columns("B")
    For Each cell In colB
        If Not IsEmpty(cell.Value) And VarType(cell.Value) = vbString Then
            If InStr(cell.Value, "タイトル") > 0 Then
                Set targetrange = GetMergedRange(cell)
                If Not targetrange Is Nothing Then
                    targetrange.BorderAround LineStyle:=xlContinuous, Weight:=xlThick
                End If
            End If
        End If
    Next cell
    
    ' 対象文字列を検出し、外枠を追加する
    For i = LBound(targetStrings) To UBound(targetStrings)
        Set rng = ws.UsedRange.Find(What:=targetStrings(i), LookIn:=xlValues, LookAt:=xlPart)
        Do Until rng Is Nothing
            Set targetrange = GetMergedRange(rng)
            If Not targetrange Is Nothing Then
                targetrange.BorderAround LineStyle:=xlContinuous, Weight:=xlThick
            End If
            Set rng = ws.UsedRange.FindNext(rng)
            If rng.Address = ws.UsedRange.Find(What:=targetStrings(i)).Address Then Exit Do
        Loop
    Next i
End Sub

' 結合セルを取得する関数
Function GetMergedRange(targetcell As Range) As Range
    If targetcell.MergeCells Then
        Set GetMergedRange = targetcell.MergeArea
    Else
        Set GetMergedRange = targetcell
    End If
End Function


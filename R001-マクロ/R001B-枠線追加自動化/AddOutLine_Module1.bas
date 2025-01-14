Attribute VB_Name = "Module1"
Public strName As String

Sub AddOutlineToSpecificText()
    Dim ws As Worksheet
    Dim targetStrings As Variant
    Dim rng As Range, cell As Range
    Dim targetrange As Range
    Dim colC As Range, colB As Range
    Dim i As Long

    ' ���s�ΏۃV�[�g���w��
    Set ws = Worksheets("���W�v�f")

    ' �Ώە�������w�肷��
    targetStrings = Array("�`�F�b�N", "���", "�ڍ�", "�^�C�g��:", "���ʁF", "�t�B�M���A�F", "�^�C�g��", "����", "����")
    
    ' C��Łu�ꏊ�v���܂ރZ�������o���O�g��ǉ�����
    Set colC = ws.Columns("C")
    For Each cell In colC
        If Not IsEmpty(cell.Value) And VarType(cell.Value) = vbString Then
            If InStr(cell.Value, "�ꏊ") > 0 Then
                Set targetrange = GetMergedRange(cell)
                If Not targetrange Is Nothing Then
                    targetrange.BorderAround LineStyle:=xlContinuous, Weight:=xlThick
                End If
            End If
        End If
    Next cell
    
    ' B��Łu�^�C�g���v���܂ރZ�������o���A�O�g��ǉ�����
    Set colB = ws.Columns("B")
    For Each cell In colB
        If Not IsEmpty(cell.Value) And VarType(cell.Value) = vbString Then
            If InStr(cell.Value, "�^�C�g��") > 0 Then
                Set targetrange = GetMergedRange(cell)
                If Not targetrange Is Nothing Then
                    targetrange.BorderAround LineStyle:=xlContinuous, Weight:=xlThick
                End If
            End If
        End If
    Next cell
    
    ' �Ώە���������o���A�O�g��ǉ�����
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

' �����Z�����擾����֐�
Function GetMergedRange(targetcell As Range) As Range
    If targetcell.MergeCells Then
        Set GetMergedRange = targetcell.MergeArea
    Else
        Set GetMergedRange = targetcell
    End If
End Function


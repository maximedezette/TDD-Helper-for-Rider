package com.github.maximedezette.tddhelperforrider.actions

import com.github.maximedezette.tddhelperforrider.actions.service.NamespaceExtractor
import com.intellij.codeInsight.intention.IntentionAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.OpenFileDescriptor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.impl.source.tree.LeafPsiElement
import com.jetbrains.rider.languages.fileTypes.csharp.CSharpLanguage


private const val CSHARP_FILE_EXTENSION = ".cs"

class GenerateImplementationAction : IntentionAction {

    override fun startInWriteAction(): Boolean {
        return true
    }

    override fun getFamilyName(): String {

        return "Create class in new file"
    }

    override fun getText(): String {
        return "Create class in new file"
    }

    override fun isAvailable(project: Project, editor: Editor?, file: PsiFile?): Boolean {

        if (editor != null && file != null) {
            if (file.language != CSharpLanguage)
                return false;

            val caretOffset = editor.caretModel.offset
            val elementAtCaret = file.findElementAt(caretOffset)
            if ((elementAtCaret as LeafPsiElement).treeParent.elementType.toString() == "cs:type-usage-role") {
                return true
            }
        }

        return false
    }

    override fun invoke(project: Project, editor: Editor?, file: PsiFile?) {
        if (file != null && editor != null) {
            val caretOffset = editor.caretModel.offset
            val elementAtCaret = file.findElementAt(caretOffset)
            if (elementAtCaret != null) {
                val fileName = getFileName(elementAtCaret)
                val newFile = PsiFileFactory.getInstance(project).createFileFromText(fileName, CSharpLanguage, getFileContent(elementAtCaret))
                val added = file.containingDirectory.add(newFile)

                val document = FileDocumentManager.getInstance().getDocument(added.containingFile.virtualFile)
                if (document != null) {
                    PsiDocumentManager.getInstance(project).commitDocument(document)
                }
                val descriptor = OpenFileDescriptor(project, added.containingFile.virtualFile)
                FileEditorManager.getInstance(project).openTextEditor(descriptor, true)
            }

        }
    }

    private fun getFileContent(elementAtCaret: PsiElement): String {
        val namespace = NamespaceExtractor.extract(elementAtCaret.containingFile.text)
        return "$namespace\n\npublic class ${elementAtCaret.text}() {\n}"
    }

    private fun getFileName(elementAtCaret: PsiElement) = elementAtCaret.text + CSHARP_FILE_EXTENSION
}
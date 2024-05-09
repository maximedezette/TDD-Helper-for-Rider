package com.github.maximedezette.tddhelperforrider.actions

import com.intellij.ide.DataManager
import com.intellij.openapi.actionSystem.*
import com.jetbrains.rdclient.daemon.util.presentableName


internal class ShowPopupAction : AnAction() {
    override fun actionPerformed(originalEvent: AnActionEvent) {
        val actionManager = ActionManager.getInstance()
        val fileName = getFileName(originalEvent)
        if (!isTestFile(fileName)) {
            val actionGroup = getActionGroup()
            showPopup(actionGroup, originalEvent)
        } else {
            val goToTestSubjectAction = actionManager.getAction("com.github.maximedezette.tddhelperforrider.actions.GoToTestAction")
            if (goToTestSubjectAction != null) {
                val e = AnActionEvent.createFromDataContext(ActionPlaces.UNKNOWN, goToTestSubjectAction.templatePresentation, DataManager.getInstance().getDataContext())
                goToTestSubjectAction.actionPerformed(e)
            }
        }
    }

    private fun getFileName(originalEvent: AnActionEvent) =
            originalEvent.getRequiredData(CommonDataKeys.EDITOR).document.presentableName()

    private fun isTestFile(fileName: String) = fileName.contains("Test")

    private fun showPopup(group: DefaultActionGroup, e: AnActionEvent) {
        val popupMenu = ActionManager.getInstance().createActionPopupMenu(ActionPlaces.UNKNOWN, group)
        val editor = e.getRequiredData(CommonDataKeys.EDITOR)
        val component = editor.caretModel.primaryCaret.editor.contentComponent;

        val x = editor.caretModel.visualPosition.column * editor.offsetToXY(1).x
        val y = (editor.caretModel.visualPosition.line + 2) * editor.lineHeight

        popupMenu.component.show(component, x, y);
    }

    private fun getActionGroup(): DefaultActionGroup {
        val group = DefaultActionGroup("TDDHelper", true)
        val actionManager = ActionManager.getInstance()

        val createTestAction = actionManager.getAction("RiderGenerateUnitTestAction")
        val goToTestAction = actionManager.getAction("com.github.maximedezette.tddhelperforrider.actions.GoToTestAction")

        group.add(goToTestAction)
        group.add(createTestAction)

        return group
    }
}

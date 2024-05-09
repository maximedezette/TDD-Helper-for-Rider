package com.github.maximedezette.tddhelperforrider.actions

import com.intellij.ide.DataManager
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.ActionPlaces
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent


internal class GoToTestSubjectAction : AnAction() {
    override fun actionPerformed(originalEvent: AnActionEvent) {

        val action = ActionManager.getInstance().getAction("RiderGoToLinkedTypesAction")

        if (action != null) {
            val e = AnActionEvent.createFromDataContext(ActionPlaces.UNKNOWN, action.templatePresentation, DataManager.getInstance().getDataContext())
            action.actionPerformed(e)
        }
    }
}

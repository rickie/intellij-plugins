package com.intellij.dts.lang.psi.mixin

import com.intellij.dts.lang.psi.DtsPHandle
import com.intellij.dts.lang.psi.DtsPropertyContent
import com.intellij.dts.lang.psi.DtsTypes
import com.intellij.dts.lang.resolve.DtsLabelReference
import com.intellij.dts.lang.resolve.DtsPathReference
import com.intellij.dts.api.DtsPath
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.util.PsiTreeUtil

abstract class DtsPHandleMixin(node: ASTNode) : ASTWrapperPsiElement(node), DtsPHandle {
    override val dtsPHandleLabel: PsiElement?
        get() = findChildByType(DtsTypes.NAME)

    override val dtsPHandlePath: PsiElement?
        get() = findChildByType(DtsTypes.PATH)

    override fun getReference(): PsiReference? {
        val propertyContent = PsiTreeUtil.findFirstParent(this) { it is DtsPropertyContent }
        val isValue = propertyContent != null

        dtsPHandleLabel?.let {
            return DtsLabelReference(
                this,
                it.textRangeInParent,
                it.text,
                isValue,
            )
        }

        dtsPHandlePath?.let {
            return DtsPathReference(
                this,
                it.textRangeInParent,
                DtsPath.from(it.text),
                isValue,
            )
        }

        return null
    }
}
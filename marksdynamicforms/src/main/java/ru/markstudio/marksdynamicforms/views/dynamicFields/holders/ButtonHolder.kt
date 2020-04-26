package ru.mysmartflat.kortros.view.customViews.dynamic

import android.view.View
import ru.markstudio.marksdynamicforms.views.DynamicBackgroundButton
import ru.markstudio.marksdynamicforms.views.dynamicFields.ButtonField
import ru.markstudio.marksdynamicforms.views.dynamicFields.Field


class ButtonHolder(itemView: View) : BaseViewHolder(itemView) {

    override fun bind(item: Field) {
        (itemView as DynamicBackgroundButton).apply {
            (item as ButtonField).also {
                text = it.text
            }
        }
    }

}
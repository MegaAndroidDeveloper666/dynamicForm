package ru.mysmartflat.kortros.view.customViews.dynamic

import android.view.View
import android.widget.TextView
import ru.markstudio.marksdynamicforms.views.dynamicFields.Field
import ru.markstudio.marksdynamicforms.views.dynamicFields.TitleField

class TitleViewHolder(itemView: View) : BaseViewHolder(itemView) {

    override fun bind(item: Field) {
        (itemView as TextView).apply {
            text = (item as TitleField).text
        }
    }

}
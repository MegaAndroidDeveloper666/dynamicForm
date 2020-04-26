package ru.mysmartflat.kortros.view.customViews.dynamic

import android.view.View
import android.widget.TextView
import ru.markstudio.marksdynamicforms.views.dynamicFields.Field
import ru.markstudio.marksdynamicforms.views.dynamicFields.TextField


class TextViewHolder(itemView: View) : BaseViewHolder(itemView) {

    override fun bind(item: Field) {
        (itemView as TextView).apply {
            text = (item as TextField).text
        }
    }

}
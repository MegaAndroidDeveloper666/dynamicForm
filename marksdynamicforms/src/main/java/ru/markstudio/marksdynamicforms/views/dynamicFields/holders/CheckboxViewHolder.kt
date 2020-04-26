package ru.mysmartflat.kortros.view.customViews.dynamic

import android.view.View
import android.widget.CheckBox
import ru.markstudio.marksdynamicforms.views.dynamicFields.CheckboxField
import ru.markstudio.marksdynamicforms.views.dynamicFields.Field


class CheckboxViewHolder(itemView: View) : BaseViewHolder(itemView) {

    private var isRequired = false

    override fun bind(item: Field) {
        isRequired = item.required
        val checkboxField = item as CheckboxField

        (itemView as CheckBox).apply {
            text = checkboxField.text
            setOnCheckedChangeListener { checkBox, isChecked ->
                onValueChanged(isChecked.toString())
            }
        }
    }

}
package ru.mysmartflat.kortros.view.customViews.dynamic

import android.view.View
import android.widget.RadioButton
import kotlinx.android.synthetic.main.field_dialog_radio_group.view.*
import ru.markstudio.marksdynamicforms.R
import ru.markstudio.marksdynamicforms.views.dynamicFields.Field
import ru.markstudio.marksdynamicforms.views.dynamicFields.RadioButtonsField

class RadioGroupViewHolder(itemView: View) : BaseViewHolder(itemView) {

    private var isRequired = false

    override fun bind(item: Field) {
        isRequired = item.required
        val radioField = item as RadioButtonsField
        if (radioField.title?.isEmpty() == true) {
            itemView.titleTextView.visibility = View.GONE
        } else {
            setTitle(radioField.title)
        }

        val selectValues = radioField.selectValues

        var id = 100
        selectValues.map { selectValue ->
            val selectButton = View.inflate(itemView.context, R.layout.field_radiobutton, null) as RadioButton
            selectButton.tag = selectValue.value
            selectButton.id = id++
            selectButton.text = selectValue.text

            if (item.defaultValue == selectValue.value) {
                selectButton.isChecked = true
                onValueChanged(selectValue.value)
                setValidFlag(isValueValid(selectValue.value))
            }

            itemView.radioGroup.addView(selectButton)
        }

        itemView.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val value = group.findViewById<View>(checkedId).tag.toString()
            onValueChanged(value)
            setValidFlag(isValueValid(value))
        }
    }

    private fun setTitle(label: String?) {
        itemView.titleTextView.text = if (isRequired) "$label*" else label
    }

    private fun isValueValid(value: String) = !isRequired || value.isNotBlank()
}

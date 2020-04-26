package ru.markstudio.marksdynamicforms.views.dynamicFields.holders

import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import kotlinx.android.synthetic.main.field_edit_text.view.*
import ru.markstudio.marksdynamicforms.databinding.FieldEditTextBinding
import ru.markstudio.marksdynamicforms.views.Settings
import ru.markstudio.marksdynamicforms.views.dynamicFields.EditField
import ru.markstudio.marksdynamicforms.views.dynamicFields.Field
import ru.mysmartflat.kortros.view.customViews.dynamic.BaseViewHolder


class EditViewHolder(val binding: FieldEditTextBinding, val settings: Settings) :
    BaseViewHolder(binding.root) {

    private var isRequired = false

    override fun bind(item: Field) {
        binding.settings = settings
        binding.executePendingBindings()

        itemView.editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val value = s.toString()
                onValueChanged(value)
                setValidFlag(isValueValid(value))
            }
        })


        if (item is EditField) {
            item.also {
                isRequired = item.required
                setTitle(item.hint)
                when (it.inputTypeString) {
                    "phone" -> itemView.editText.inputType = InputType.TYPE_CLASS_PHONE
                    "name" -> itemView.editText.inputType = InputType.TYPE_TEXT_FLAG_CAP_WORDS
                    "text" -> itemView.editText.inputType = InputType.TYPE_TEXT_FLAG_CAP_SENTENCES
                }
                itemView.editText.setText(it.value)
                onValueChanged(it.value ?: "")
                setValidFlag(isValueValid(it.value ?: ""))
            }
        }
    }

    private fun setTitle(label: String?) {
        itemView.editLayout.hint = if (isRequired) "$label*" else label
    }

    private fun isValueValid(value: String) = !isRequired || value.isNotBlank()
}
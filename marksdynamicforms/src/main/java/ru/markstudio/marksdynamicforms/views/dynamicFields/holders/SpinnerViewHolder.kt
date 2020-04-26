package ru.mysmartflat.kortros.view.customViews.dynamic

import android.view.View
import kotlinx.android.synthetic.main.field_dialog_spinner.view.*
import ru.markstudio.marksdynamicforms.views.dynamicFields.Field


class SpinnerViewHolder(itemView: View) : BaseViewHolder(itemView) {

    private var isRequired = false

    override fun bind(item: Field) {
        isRequired = item.required
//        setTitle(item.text)

        //FIXME переделать на новую динамику
//        val selectValues = item.content
//        val spinnerValues = ArrayList<String>()
//        var defaultValue = ""
//
//        selectValues.isNotEmpty().let {
//            spinnerValues.add(0, itemView.resources.getString(R.string.all))
//
//            for (selectValue in selectValues) {
//                selectValue.value?.let { value ->
//                    spinnerValues.add(value)
//                    if (item.defaultValue == selectValue.id.toString()) {
//                        defaultValue = value
//                    }
//                }
//            }
//        }

//        val spinnerAdapter = ArrayAdapter(itemView.context, android.R.layout.simple_spinner_item, spinnerValues)
//        spinnerAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice)
//        itemView.spinner.adapter = spinnerAdapter

//        itemView.spinner.setSelection(spinnerAdapter.getPosition(defaultValue))
//        onValueChanged(defaultValue)
//        setValidFlag(isValueValid(defaultValue))

//        itemView.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
//                onValueChanged(spinnerValues[pos])
//                setValidFlag(isValueValid(spinnerValues[pos]))
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>) {}
//        }
//
//        itemView.spinner.setSelection(spinnerAdapter.getPosition(defaultValue))
    }

    private fun setTitle(label: String?) {
        itemView.titleTextView.hint = if (isRequired) "$label*" else label
    }

    private fun isValueValid(value: String) = !isRequired || value.isNotEmpty()
}
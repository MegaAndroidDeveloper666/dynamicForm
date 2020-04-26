package ru.mysmartflat.kortros.view.customViews.dynamic

import android.app.Activity
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.markstudio.marksdynamicforms.views.Settings
import ru.markstudio.marksdynamicforms.views.dynamicFields.Field


abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var onValueChanged: (String) -> Unit = {}
    var setValidFlag: (Boolean) -> Unit = {}

    abstract fun bind(item: Field)
}
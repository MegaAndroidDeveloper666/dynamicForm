package ru.markstudio.marksdynamicforms.views

import android.content.Context
import android.graphics.Typeface
import androidx.core.content.ContextCompat
import ru.markstudio.marksdynamicforms.R

data class Settings(
    val context: Context
) {
    var primaryTextColor: Int
    var colorAccent: Int
    var colorAccentDark: Int
    var colorAccentDisabled: Int
    var colorButtonText: Int
    var colorButtonTextStroke: Int
    var primaryHintColor: Int
    var viewPadding: Int
    var typeface: Typeface
    var textSize: Int
    var titleSize: Int
    var smallTextSize: Int

    init {
        primaryTextColor = R.color.defaultText
        colorAccent = R.color.defaultColorAccent
        colorButtonText = R.color.defaultButtonText
        colorButtonTextStroke = R.color.defaultButtonTextStroke
        colorAccentDark = R.color.defaultColorAccentDark
        colorAccentDisabled = R.color.defaultColorAccentDisabled
        primaryHintColor = R.color.defaultHint
        viewPadding = context.resources.getDimension(R.dimen.defaultPadding).toInt()
        typeface = Typeface.DEFAULT
        textSize = context.resources.getDimension(R.dimen.defaultTextSize).toInt()
        smallTextSize = context.resources.getDimension(R.dimen.defaultTitleSize).toInt()
        titleSize = context.resources.getDimension(R.dimen.defaultSmallTextSize).toInt()
    }
}
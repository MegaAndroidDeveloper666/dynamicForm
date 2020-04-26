package ru.markstudio.marksdynamicforms.views

import android.content.Context
import android.graphics.Typeface

class DynamicForm (val settings: Settings) {

    class Builder(val context: Context) {
        private val settings = Settings(context)

        fun setPrimaryTextColor(color: Int): Builder {
            settings.primaryTextColor = color
            return this
        }

        fun setPrimaryHintColor(color: Int): Builder {
            settings.primaryHintColor = color
            return this
        }

        fun setViewPadding(viewPadding: Int): Builder {
            settings.viewPadding = viewPadding
            return this
        }

        fun setTypeface(typeface: Typeface): Builder {
            settings.typeface = typeface
            return this
        }

        fun setTextSize(size: Int): Builder {
            settings.textSize = size
            return this
        }

        fun setTitleSize(size: Int): Builder {
            settings.titleSize = size
            return this
        }

        fun setSmallTextSize(size: Int): Builder {
            settings.smallTextSize = size
            return this
        }

        fun setColorAccent(color: Int): Builder {
            settings.colorAccent = color
            return this
        }

        fun setColorAccentDark(color: Int): Builder {
            settings.colorAccentDark = color
            return this
        }

        fun setColorAccentDisabled(color: Int): Builder {
            settings.colorAccentDisabled = color
            return this
        }

        fun setButtonTextColor(color: Int): Builder {
            settings.colorButtonText = color
            return this
        }

        fun setButtonTextStokeColor(color: Int): Builder {
            settings.colorButtonTextStroke = color
            return this
        }

        fun build() = DynamicForm(settings)
    }

}
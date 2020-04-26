package ru.markstudio.marksdynamicforms.views.dynamicFields

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.markstudio.marksdynamicforms.R
import ru.markstudio.marksdynamicforms.databinding.FieldEditTextBinding
import ru.markstudio.marksdynamicforms.views.Settings
import ru.markstudio.marksdynamicforms.views.dynamicFields.holders.EditViewHolder
import ru.mysmartflat.kortros.view.customViews.dynamic.*

enum class FieldInputType(val holderType: Int) {
    EDIT_TEXT(1) {
        override fun getViewHolder(parent: ViewGroup) : BaseViewHolder {
            FieldEditTextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return EditViewHolder(
                FieldEditTextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    },
    SELECT(2) {
        override fun getViewHolder(parent: ViewGroup) : BaseViewHolder {
            return SpinnerViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.field_dialog_spinner, parent, false))
        }
    },
    RADIO(3) {
        override fun getViewHolder(parent: ViewGroup) : BaseViewHolder {
            return RadioGroupViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.field_dialog_radio_group, parent, false))
        }
    },
    CHECKBOX(4) {
        override fun getViewHolder(parent: ViewGroup) : BaseViewHolder {
            return CheckboxViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.field_checkbox, parent, false))
        }
    },
    TEXT(6) {
        override fun getViewHolder(parent: ViewGroup): BaseViewHolder {
            return TextViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.field_dialog_text_view, parent, false))
        }
    },
    BUTTON(7) {
        override fun getViewHolder(parent: ViewGroup): BaseViewHolder {
            return ButtonHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.field_dialog_button, parent, false))
        }
    },
    GALLERY(8) {
        override fun getViewHolder(parent: ViewGroup): BaseViewHolder {
            return ImageGalleryHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.field_image_gallery, parent, false))
        }
    },
    TITLE(9) {
        override fun getViewHolder(parent: ViewGroup): BaseViewHolder {
            return TitleViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.field_title, parent, false))
        }
    },
    MAP(10) {
        override fun getViewHolder(parent: ViewGroup): BaseViewHolder {
            return MapViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.field_map, parent, false))
        }
    },
    CONTACTS(11) {
        override fun getViewHolder(parent: ViewGroup): BaseViewHolder {
            return ContactsViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.field_contact_list, parent, false))
        }
    },
    DIVIDER(12) {
        override fun getViewHolder(parent: ViewGroup): BaseViewHolder {
            return DividerViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.field_divider, parent, false))
        }
    },
    VIDEO(13) {
        override fun getViewHolder(parent: ViewGroup): BaseViewHolder {
            return VideoViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.field_video, parent, false))
        }
    },
    KORTROS_CAROUSEL(14) {
        override fun getViewHolder(parent: ViewGroup): BaseViewHolder {
            return KortrosViewPagerHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.field_kortros_viewpager, parent, false))
        }
    }
    ;

    abstract fun getViewHolder(parent: ViewGroup) : BaseViewHolder

    companion object {
        fun getType(field: Field) =
            when (field) {
                is TitleField -> TITLE
                is TextField -> TEXT
                is EditField -> EDIT_TEXT
                is GalleryField -> GALLERY
                is ContactsField -> CONTACTS
                is MapField -> MAP
                is ButtonField -> BUTTON
                is RadioButtonsField -> RADIO
                is CheckboxField -> CHECKBOX
                is VideoField -> VIDEO
                is KortrosCarouselField -> KORTROS_CAROUSEL
                is DividerField -> DIVIDER
                else -> DIVIDER
            }

        fun getViewHolder(type: Int, parent: ViewGroup): BaseViewHolder {
            values().map {
                if (it.holderType == type) {
                    return it.getViewHolder(parent)
                }
            }
            return DIVIDER.getViewHolder(parent)
        }
    }
}
package ru.markstudio.marksdynamicforms.views.dynamicFields

open class Field(
    var name: String = "",
    var required: Boolean = false
) {
    object JsonFields {
        val name = "name"
        val required = "required"
    }
}

class GalleryField(
    var imageList: ArrayList<ImageGallery>
) : Field() {
    object JsonFields {
        val fieldName = "gallery"
        val imageList = "images"
    }
}

class ImageGallery(
    var url: String?
) {
    object  JsonFields {
        val url = "url"
    }
}

class ContactsField(
    var contactList: ArrayList<Contact>
) : Field() {
    object JsonFields {
        val fieldName = "contacts"
        val contactList = "contacts"
    }
}

class Contact(
    var type: String?,
    var data: String?
) {
    object JsonFields {
        val type = "type"
        val data = "data"
    }
}

class TitleField(
    var text: String
): Field() {
    object  JsonFields {
        val fieldName = "title"
        val text = "text"
    }
}

class TextField(
    var text: String
): Field() {
    object  JsonFields {
        val fieldName = "text"
        val text = "text"
    }
}

class EditField(
    var inputTypeString: String?,
    var hint: String?,
    var value: String?,
    var defaultValue: String
): Field() {
    object JsonFields {
        val fieldName = "edit_text"
        val type = "type"
        val hint = "hint"
        val value = "value"
        val defaultValue = "default_value"
    }
}

class MapField(
    var center: MapPoint?,
    var pointList: ArrayList<MapPoint>?
): Field() {
    object  JsonFields {
        val fieldName = "map"
        val center = "center"
        val pointList = "points"
    }
}

class MapPoint(
    var x: Double,
    var y: Double,
    var zoom: Float
) {
    object  JsonFields {
        val x = "x"
        val y = "y"
        val zoom = "zoom"
    }
}

class ButtonField(
    var text: String?,
    var actions: Actions
): Field() {
    object  JsonFields {
        val fieldName = "button"
        val text = "text"
        val actions = "actions"
    }
}

class Actions(
    var click: ClickAction
) {
    object  JsonFields {
        val click = "click"
    }
}

class ClickAction(
    var type: String?,
    var data: ClickActionData?
) {
    object  JsonFields {
        val type = "type"
        val data = "data"
    }
}

class ClickActionData(
    var layoutId: String?,
    var fields: ArrayList<String>?,
    var url: String?,
//        var data: HashMap<String, String>?,
    var params: HashMap<String, String>
//        var headers: HashMap<String, String>?
) {
    object  JsonFields {
        val layoutId = "layout_id"
        val fields = "fields"
        val method = "method"
        val url = "url"
        val data = "data"
        val params = "params"
    }
}

class RadioButtonsField(
    var title: String?,
    var defaultValue: String?,
    var selectValues: ArrayList<SelectValue>
): Field() {
    object  JsonFields {
        val fieldName = "radio_group"
        val title = "title"
        val defaultValue = "default_value"
        val selectValues = "radio_buttons"
    }
}

class SelectValue (
    var value: String,
    var text: String
) {
    object JsonFields {
        val value = "value"
        val text = "text"
    }
}

class CheckboxField(
    var text: String
): Field() {
    object JsonFields {
        val fieldName = "checkbox"
        val text = "text"
    }
}

class VideoField(
    var preview: String,
    var url: String
): Field() {
    object JsonFields {
        val fieldName = "video"
        val preview = "preview"
        val url = "url"
    }
}

class KortrosCarouselField(
): Field() {
    object JsonFields {
        val fieldName = "kortros_viewpager"
    }
}

class DividerField(
): Field() {
    object JsonFields {
        val fieldName = "divider"
    }
}
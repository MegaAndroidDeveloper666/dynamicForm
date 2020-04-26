package ru.markstudio.marksdynamicforms.views

import android.app.Activity
import android.util.Log
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.internal.LinkedTreeMap
import ru.markstudio.marksdynamicforms.views.dynamicFields.*
import ru.mysmartflat.kortros.view.customViews.dynamic.BaseViewHolder
import java.lang.Exception

class DynamicAdapter(
    private val dataEntryList: List<Field>,
    private val settings: Settings
) : RecyclerView.Adapter<BaseViewHolder>() {

    private var values = Array(dataEntryList.size) { "" }
    private var validValues = Array(dataEntryList.size) { false }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return FieldInputType.getViewHolder(viewType, parent, settings)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onValueChanged = { value -> setValue(position, value) }
        holder.setValidFlag = { flag -> setValidFlag(position, flag) }
        holder.bind(dataEntryList[position])
    }

    override fun getItemViewType(position: Int): Int =
        FieldInputType.getType(dataEntryList[position]).holderType

    override fun getItemCount() = dataEntryList.size

    /**
     * Список параметров для отправки значений формы на почту
     */
    fun getParamsMap(position: Int): Map<String, String> {
        val params = LinkedHashMap<String, String>()
        for (i in position until dataEntryList.size) {
            params[dataEntryList[i].name] = values[i]
        }
        return params
    }

    fun getValuesList() = values

    private fun setValue(position: Int, value: String) {
        values[position] = value
    }

    private fun setValidFlag(position: Int, flag: Boolean) {
        validValues[position] = flag
    }

    fun isFormValid() = validValues.all { it }

    companion object {
        @JvmStatic
        fun parseHashMap(fieldsList: ArrayList<HashMap<String, Any>>, settings: Settings): DynamicAdapter {
            val dataEntryList = ArrayList<Field>()
            fieldsList.map {
                var map: Map<String, Any> = HashMap()
                it.keys.map { key ->
                    map = it[key] as Map<String, Any>
                }
                var field: Field = DividerField()
                try {
                    when (it.keys) {
                        setOf(EditField.JsonFields.fieldName) ->
                            field = EditField(
                                (map[EditField.JsonFields.type] ?: "") as String,
                                (map[EditField.JsonFields.hint] ?: "") as String,
                                (map[EditField.JsonFields.value] ?: "") as String,
                                (map[EditField.JsonFields.defaultValue] ?: "") as String
                            )
                        setOf(TitleField.JsonFields.fieldName) ->
                            field = TitleField(
                                (map[TitleField.JsonFields.text] ?: "") as String
                            )
                        setOf(TextField.JsonFields.fieldName) ->
                            field = TextField(
                                (map[TextField.JsonFields.text] ?: "") as String
                            )
                        setOf(GalleryField.JsonFields.fieldName) ->
                            field = GalleryField(
                                ArrayList<ImageGallery>().apply {
                                    (map[GalleryField.JsonFields.imageList] as ArrayList<LinkedTreeMap<String, String>>).map { image ->
                                        this.add(ImageGallery(image[ImageGallery.JsonFields.url]))
                                    }
                                }
                            )
                        setOf(ContactsField.JsonFields.fieldName) ->
                            field = ContactsField(
                                ArrayList<Contact>().apply {
                                    (map[ContactsField.JsonFields.contactList] as ArrayList<LinkedTreeMap<String, String>>).map { contact ->
                                        this.add(
                                            Contact(
                                                contact[Contact.JsonFields.type],
                                                contact[Contact.JsonFields.data]
                                            )
                                        )

                                    }
                                }
                            )
                        setOf(MapField.JsonFields.fieldName) -> {
                            val mapPointMap = (map[MapField.JsonFields.center] as LinkedTreeMap<String, Any>)
                            field = MapField(
                                MapPoint(
                                    (mapPointMap[MapPoint.JsonFields.x]
                                        ?: 0.0) as Double,
                                    (mapPointMap[MapPoint.JsonFields.y]
                                        ?: 0.0) as Double,
                                    ((mapPointMap[MapPoint.JsonFields.zoom]
                                        ?: 0.0) as Double).toFloat()
                                ),
                                ArrayList<MapPoint>().apply {
                                    (map[MapField.JsonFields.pointList] as ArrayList<LinkedTreeMap<String, Any>>).map { point ->
                                        this.add(
                                            MapPoint(
                                                (point[MapPoint.JsonFields.x]
                                                    ?: 0.0) as Double,
                                                (point[MapPoint.JsonFields.y]
                                                    ?: 0.0) as Double,
                                                ((point[MapPoint.JsonFields.zoom]
                                                    ?: 0.0) as Double).toFloat()
                                            )
                                        )

                                    }
                                }
                            )
                        }
                        setOf(ButtonField.JsonFields.fieldName) -> {
                            val mapActions =
                                if (map.keys.contains(ButtonField.JsonFields.actions))
                                    map[ButtonField.JsonFields.actions] as Map<String, Any>
                                else
                                    null
                            val mapActionClick =
                                if (mapActions != null)
                                    mapActions.get(Actions.JsonFields.click) as Map<String, Any>
                                else
                                    null
                            val mapClickData =
                                if (mapActionClick != null)
                                    mapActionClick.get(ClickAction.JsonFields.data) as Map<String, Any>
                                else
                                    null
                            val hashMapActionParams = HashMap<String, String>()

                            val mapActionParams =
                                if (mapClickData != null)
                                    mapClickData[ClickActionData.JsonFields.params] as Map<String, String>
                                else
                                    null
                            mapActionParams?.keys?.map {
                                hashMapActionParams.put(it, mapActionParams[it].toString())
                            }
                            field = ButtonField(
                                (map[ButtonField.JsonFields.text] ?: "").toString(),
                                Actions(
                                    ClickAction(
                                        if (mapActionClick != null)
                                            (mapActionClick[ClickAction.JsonFields.type]
                                                ?: "").toString()
                                        else "",
                                        ClickActionData(
                                            null,
                                            if (mapClickData?.keys?.contains(ClickActionData.JsonFields.fields) == true)
                                                mapClickData[ClickActionData.JsonFields.fields] as ArrayList<String>
                                            else null,
                                            if (mapClickData?.keys?.contains(ClickActionData.JsonFields.url) == true)
                                                (mapClickData[ClickActionData.JsonFields.url]
                                                    ?: "").toString()
                                            else null,
                                            hashMapActionParams
                                        )
                                    )
                                )
                            )
                        }
                        setOf(DividerField.JsonFields.fieldName) ->
                            field = DividerField()
                        setOf(KortrosCarouselField.JsonFields.fieldName) ->
                            field = KortrosCarouselField()
                        setOf(RadioButtonsField.JsonFields.fieldName) -> {
                            val selectValues = ArrayList<SelectValue>()
                            val mapSelectValues =
                                if (map.keys.contains(RadioButtonsField.JsonFields.selectValues))
                                    map[RadioButtonsField.JsonFields.selectValues] as ArrayList<Map<String, String>>
                                else
                                    null
                            mapSelectValues?.map {
                                selectValues.add(
                                    SelectValue(
                                        it[SelectValue.JsonFields.value] ?: "",
                                        it[SelectValue.JsonFields.text] ?: ""
                                    )
                                )
                            }
                            field = RadioButtonsField(
                                (map[RadioButtonsField.JsonFields.title] ?: "").toString(),
                                (map[RadioButtonsField.JsonFields.defaultValue]
                                    ?: "").toString(),
                                selectValues
                            )
                        }
                        setOf(CheckboxField.JsonFields.fieldName) ->
                            field = CheckboxField(
                                (map[CheckboxField.JsonFields.text] ?: "").toString()
                            )
                        setOf(VideoField.JsonFields.fieldName) ->
                            field = VideoField(
                                (map[VideoField.JsonFields.preview] ?: "").toString(),
                                (map[VideoField.JsonFields.url] ?: "").toString()
                            )
                        else -> DividerField()
                    }
                    field.apply {
                        name = (map[Field.JsonFields.name] ?: "") as String
                        required = (map[Field.JsonFields.required]
                            ?: false) as Boolean
                    }
                } catch (e: Exception) {
                    Log.d("Dynamic Field Parser", "Cannot parce map: $map")
                }
                dataEntryList.add(field)
            }
            return DynamicAdapter(dataEntryList, settings)
        }
    }
}

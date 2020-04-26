package ru.markstudio.marksdynamicforms.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_dynamic.*
import ru.markstudio.marksdynamicforms.BR
import ru.markstudio.marksdynamicforms.R
import ru.markstudio.marksdynamicforms.databinding.FragmentDynamicBinding
import ru.markstudio.marksdynamicforms.views.dynamicFields.Field

class DynamicFragment: Fragment() {

    lateinit var fields: ArrayList<Field>
    lateinit var binding: FragmentDynamicBinding
    lateinit var settings: Settings

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dynamic, container, false)
        val view = binding.root
//        binding.setVariable(BR.settings, settings)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvDynamic.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = DynamicAdapter(fields, settings)
        }
//        TextView(context).apply {
//            setHintTextColor(resources.)
//        }
    }

    companion object {
        @JvmStatic
        fun newInstance(fields: ArrayList<Field>, settings: Settings): DynamicFragment {
            return DynamicFragment().apply {
                this.fields = fields
                this.settings = settings
            }
        }
    }
}
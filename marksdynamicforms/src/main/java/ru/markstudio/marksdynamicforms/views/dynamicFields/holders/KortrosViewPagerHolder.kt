package ru.mysmartflat.kortros.view.customViews.dynamic

import android.view.View
import androidx.viewpager.widget.ViewPager
import ru.markstudio.marksdynamicforms.views.AppUtils
import ru.markstudio.marksdynamicforms.views.ViewPagerBuilderAdapter
import ru.markstudio.marksdynamicforms.views.dynamicFields.Field


class KortrosViewPagerHolder(itemView: View) : BaseViewHolder(itemView) {

    override fun bind(item: Field) {
        (itemView as ViewPager).apply {
            clipToPadding = false
            offscreenPageLimit = 2
            pageMargin = AppUtils.dpToPx(itemView.context, 10)
            val adapterBuilder = ViewPagerBuilderAdapter(itemView.context)
            adapter = adapterBuilder
        }
    }

}
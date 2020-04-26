package ru.mysmartflat.kortros.view.customViews.dynamic

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.field_image_gallery.view.*
import ru.markstudio.marksdynamicforms.R
import ru.markstudio.marksdynamicforms.views.dynamicFields.Field
import ru.markstudio.marksdynamicforms.views.dynamicFields.GalleryField
import ru.markstudio.marksdynamicforms.views.dynamicFields.ImageGallery


class ImageGalleryHolder(itemView: View) : BaseViewHolder(itemView) {

    override fun bind(item: Field) {
        var field = item as GalleryField
        itemView.vpImageGallery.adapter = ImageGalleryAdapter(field.imageList)
        if (field.imageList.size ?: 0 > 1) {
            itemView.ciViewPager.setViewPager(itemView.vpImageGallery)
        } else {
            itemView.ciViewPager.visibility = View.GONE
        }
    }

}

class ImageGalleryAdapter(val gallery: ArrayList<ImageGallery>) : PagerAdapter() {
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount() = gallery.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = LayoutInflater.from(container.context).inflate(R.layout.field_image, container, false) as ImageView
        Glide.with(imageView)
                .load(gallery[position]?.url)
                .into(imageView)
        container.addView(imageView, 0)
        imageView.setOnClickListener {
//            UrlCarouselViewActivity.start(
//                    imageView.context,
//                    ArrayList<CarouselPhoto>().apply {
//                        gallery.map { imageGallery -> add(CarouselPhoto(imageGallery?.url, "")) }
//                    },
//                    null,
//                    "",
//                    position,
//                    false
//            )
        }
        return imageView
    }

}

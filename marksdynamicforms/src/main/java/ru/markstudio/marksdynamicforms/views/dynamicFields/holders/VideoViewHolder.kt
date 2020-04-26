package ru.mysmartflat.kortros.view.customViews.dynamic

import android.content.Intent
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.field_video.view.*
import ru.markstudio.marksdynamicforms.views.dynamicFields.Field
import ru.markstudio.marksdynamicforms.views.dynamicFields.VideoField

class VideoViewHolder(itemView: View) : BaseViewHolder(itemView) {

    override fun bind(item: Field) {
        Glide.with(itemView.ivPreview)
                .load((item as VideoField).url)
                .into(itemView.ivPreview)
        itemView.setOnClickListener {
//            val intent = Intent(itemView.context, VideoPlayerActivity::class.java)
//            item.url.let {
//                intent.putExtra(VideoPlayerActivity.VIDEO_URL, it)
//                itemView.context.startActivity(intent)
//            }
        }
    }

}
package com.alexos.heartstonecardsinfo.presentation.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.alexos.heartstonecardsinfo.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("bindFlavor")
fun bindFlavor(textView: TextView, flavor: String) {
    textView.text = flavor.let { it1 ->
        HtmlCompat.fromHtml(
            it1,
            HtmlCompat.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH
        )
    }
}

@BindingAdapter("cardImage")
fun setCardImage(imageView: ImageView, url: String?){
    Glide.with(imageView.context).load(url)
        .placeholder(R.drawable.no_image_svgrepo_com)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .apply(RequestOptions().skipMemoryCache(false))
        .fitCenter()
        .into(imageView)
}

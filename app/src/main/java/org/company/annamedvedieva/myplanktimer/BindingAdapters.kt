package org.company.annamedvedieva.myplanktimer

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("app:image")
fun setImage(imageView: ImageView, plankType: String?){

    //TODO: add plank images
    val image = when(plankType){
        "Low plank" -> R.drawable.ic_banner_foreground
        "High plank" -> R.drawable.ic_launcher_background
        "Side plank" -> R.drawable.ic_baseline_assignment_turned_in_24
        else -> R.drawable.ic_baseline_av_timer_24
    }
    imageView.load(image) {
        crossfade(true)
        placeholder(R.drawable.ic_banner_foreground)
    }

}
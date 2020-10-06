package org.company.annamedvedieva.myplanktimer

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("app:image")
fun setImage(imageView: ImageView, plankType: String?){

    val image = when(plankType){
        "Low plank" -> R.drawable.low_plank
        "High plank" -> R.drawable.high_plank
        "Side plank" -> R.drawable.side_plank
        else -> R.drawable.low_plank
    }
    imageView.load(image) {
        crossfade(true)
        placeholder(R.drawable.low_plank)
    }

}
package raul.aguilar.projects.myfilms

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import com.bumptech.glide.Glide

fun ImageView.load(itemUrl:String){

    Glide.with(this).load(itemUrl).into(this)
}

fun ViewGroup.inflate(@LayoutRes layoutRes:Int): View {

    return  LayoutInflater.from(context).inflate(layoutRes, this,false)

}
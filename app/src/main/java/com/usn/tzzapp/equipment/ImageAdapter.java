package com.usn.tzzapp.equipment;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;
import com.usn.tzzapp.R;

public class ImageAdapter {

    /**
     * @param i the imageView that the app:image call will get
     * @param src the source of the image that will be displayed
     */
    @BindingAdapter({ "app:image"})
    public static void loadImage(ImageView i, String src) {

        Picasso.get().load("file:"+src).placeholder(R.drawable.ic_tzzapp).fit().into(i);

    }

}

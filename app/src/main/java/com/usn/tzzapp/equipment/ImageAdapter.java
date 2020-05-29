package com.usn.tzzapp.equipment;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;
import com.usn.tzzapp.R;

public class ImageAdapter {

    @BindingAdapter({ "app:image"})
    public static void loadImage(ImageView i, String src) {

        Picasso.get().load("file:"+src).placeholder(R.drawable.ic_tzzapp).fit().into(i);

    }

}

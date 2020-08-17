package com.github.mohamedwael.moviessignature.modules.moviedetails.adapter

import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginTop
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.mohamedwael.moviessignature.applevel.utils.createImageURL
import com.github.mohamedwael.moviessignature.modules.moviedetails.repo.dto.PhotoItem

class ImagePosterAdapter() :
    RecyclerView.Adapter<ImagePosterAdapter.ImagePosterViewHolder>() {
    private val imageList = mutableListOf<PhotoItem>()

    fun updateImageList(images: List<PhotoItem>) {
        imageList.clear()
        imageList.addAll(images)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagePosterViewHolder {
        return ImagePosterViewHolder(ImageView(parent.context).apply {
            val layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(0, 4, 0, 4)
            this.layoutParams = layoutParams
            scaleType = ImageView.ScaleType.CENTER_INSIDE
        })
    }

    override fun getItemCount(): Int = imageList.size


    override fun onBindViewHolder(holder: ImagePosterViewHolder, position: Int) {
        holder.bindImage(imageList[position])
    }

    class ImagePosterViewHolder(view: ImageView) : RecyclerView.ViewHolder(view) {
        fun bindImage(photoItem: PhotoItem) {
            Glide.with(itemView).load(createImageURL(photoItem)).into(itemView as ImageView);
        }
    }
}
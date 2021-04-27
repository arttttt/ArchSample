package com.arttttt.archsample.ui.fullscreenpicture

import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.fragment.app.Fragment
import com.arttttt.archsample.R
import com.arttttt.archsample.SharedElementTransitionOwner
import com.arttttt.archsample.base.SharedElementTransitionInfo
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import kotlinx.parcelize.Parcelize

class FullscreenPictureFragment : Fragment(R.layout.fragment_fullscreen_picture),
    SharedElementTransitionOwner {

    @Parcelize
    data class Arguments(
        val pictureUri: Uri
    ) : Parcelable

    companion object {
        const val ARGUMENTS_KEY = "ARGUMENTS_KEY"
    }

    private var sharedElementTransitionInfo: SharedElementTransitionInfo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        postponeEnterTransition()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedElementTransitionInfo?.sharedElements?.forEach { sharedView ->
            view.findViewById<View>(sharedView.id).transitionName = sharedView.transitionName
        }

        Glide
            .with(requireView())
            .load(requireArguments().getParcelable<Arguments>(ARGUMENTS_KEY)!!.pictureUri)
            .listener(
                object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: com.bumptech.glide.request.target.Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        startPostponedEnterTransition()
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: com.bumptech.glide.request.target.Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        startPostponedEnterTransition()
                        return false
                    }
                }
            )
            .into(view.findViewById(R.id.image_view))
    }

    override fun setSharedElementTransitionInfo(sharedElementTransitionInfo: SharedElementTransitionInfo) {
        this.sharedElementTransitionInfo = sharedElementTransitionInfo
    }
}

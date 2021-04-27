package com.arttttt.archsample.ui.breedpictures

import android.os.Bundle
import android.view.View
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.arttttt.archsample.R
import com.arttttt.archsample.Screens
import com.arttttt.archsample.base.EqualsDiffCallback
import com.arttttt.archsample.base.ListDifferAdapter
import com.arttttt.archsample.base.SharedElementTransitionInfo
import com.arttttt.archsample.ui.breedpictures.adapter.delegates.BreedPictureAdapterDelegate
import com.arttttt.archsample.ui.breedpictures.di.BreedPicturesDependencies
import com.arttttt.archsample.ui.breedpictures.di.DaggerBreedPicturesComponent
import com.arttttt.archsample.utils.findAppFragment
import com.arttttt.archsample.utils.requireAppFragment
import javax.inject.Inject

class BreedPicturesFragment(
    private val dependencies: BreedPicturesDependencies
) : Fragment(R.layout.fragment_breed_pictures) {

    companion object {
        const val BREED_ARG = "BREED_ARG"
    }

    val adapter by lazy {
        ListDifferAdapter(
            diffCallback = EqualsDiffCallback(),
            delegates = setOf(
                BreedPictureAdapterDelegate(
                    onClick = { pictureUri, view ->
                        requireAppFragment().router.navigateTo(
                            Screens.FullscreenPictureScreen(
                                pictureUri = pictureUri,
                                sharedElementTransitionInfo = SharedElementTransitionInfo(
                                    sharedElements = listOf(
                                        view
                                    )
                                )
                            )
                        )
                    }
                )
            )
        )
    }

    @Inject
    lateinit var bindings: BreedPicturesFragmentBindings

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerBreedPicturesComponent
            .factory()
            .create(
                dependencies = dependencies,
                breed = requireArguments().getParcelable(BREED_ARG)!!
            )
            .inject(this)

        super.onCreate(savedInstanceState)
        bindings.attachTo(this)
        findAppFragment()?.postponeEnterTransition()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.recycler_view)?.let { recyclerView ->
            recyclerView.adapter = adapter

            adapter.registerAdapterDataObserver(
                object : RecyclerView.AdapterDataObserver() {
                    override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                        findAppFragment()?.startPostponedEnterTransition()
                    }
                }
            )
        }
    }

}

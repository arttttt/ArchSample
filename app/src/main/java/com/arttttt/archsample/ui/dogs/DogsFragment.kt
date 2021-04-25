package com.arttttt.archsample.ui.dogs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.arttttt.archsample.R
import com.arttttt.archsample.base.EqualsDiffCallback
import com.arttttt.archsample.base.ListDifferAdapter
import com.arttttt.archsample.ui.dogs.adapter.delegates.BreedAdapterDelegate
import com.arttttt.archsample.ui.dogs.di.DaggerDogsComponent
import com.arttttt.archsample.ui.dogs.di.DogsDependencies
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class DogsFragment(
    private val dependencies: DogsDependencies
) : Fragment(R.layout.fragment_dogs) {

    sealed class UiEvent {
        data class BreedClicked(val index: Int) : UiEvent()
    }

    val uiEvents = PublishSubject.create<UiEvent>()

    val adapter by lazy {
        ListDifferAdapter(
            diffCallback = EqualsDiffCallback(),
            delegates = setOf(
                BreedAdapterDelegate(
                    onClick = { position -> uiEvents.onNext(UiEvent.BreedClicked(position)) }
                )
            )
        )
    }

    @Inject
    lateinit var bindings: DogsFragmentBindings

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerDogsComponent
            .factory()
            .create(dependencies)
            .inject(this)

        super.onCreate(savedInstanceState)

        bindings.attachTo(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.recycler_view)?.let { recyclerView ->
            recyclerView.adapter = adapter
        }
    }

}

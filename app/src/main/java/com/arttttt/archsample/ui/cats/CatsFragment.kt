package com.arttttt.archsample.ui.cats

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.arttttt.archsample.R
import com.arttttt.archsample.ui.cats.di.CatsDependencies
import com.arttttt.archsample.ui.cats.di.DaggerCatsComponent

class CatsFragment(
    private val dependencies: CatsDependencies
) : Fragment(R.layout.fragment_cats) {

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerCatsComponent
            .factory()
            .create(dependencies)
            .inject(this)

        super.onCreate(savedInstanceState)
    }

}

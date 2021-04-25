package com.arttttt.archsample

import androidx.core.os.bundleOf
import com.arttttt.archsample.domain.entity.Breed
import com.arttttt.archsample.ui.bottomnavigation.BottomNavigationFragment
import com.arttttt.archsample.ui.breedpictures.BreedPicturesFragment
import com.arttttt.archsample.ui.chooser.ChooserFragment
import com.arttttt.archsample.ui.cats.CatsFragment
import com.arttttt.archsample.ui.dogs.DogsFragment
import com.arttttt.archsample.utils.instantiate
import com.arttttt.archsample.utils.instantiateWithArguments
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {

    fun ChooserScreen() = FragmentScreen { fragmentFactory ->
        fragmentFactory.instantiate<ChooserFragment>()
    }

    fun BottomNavigationScreen() = FragmentScreen { fragmentFactory ->
        fragmentFactory.instantiate<BottomNavigationFragment>()
    }

    fun CatsScreen() = FragmentScreen { fragmentFactory ->
        fragmentFactory.instantiate<CatsFragment>()
    }

    fun DogsScreen() = FragmentScreen { fragmentFactory ->
        fragmentFactory.instantiate<DogsFragment>()
    }

    fun BreedPicturesScreen(
        breed: Breed
    ) = FragmentScreen { fragmentFactory ->
        fragmentFactory.instantiateWithArguments<BreedPicturesFragment>(
            arguments = bundleOf(
                BreedPicturesFragment.BREED_ARG to breed
            )
        )
    }

}

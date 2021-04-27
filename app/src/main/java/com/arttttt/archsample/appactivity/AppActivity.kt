package com.arttttt.archsample.appactivity

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.commit
import com.arttttt.archsample.appactivity.di.AppActivityDependencies
import com.arttttt.archsample.appactivity.di.DaggerAppActivityComponent
import com.arttttt.archsample.appfragment.AppFragment
import com.arttttt.archsample.base.BackPressedHandler
import com.arttttt.archsample.base.FragmentFactoryImpl
import com.arttttt.archsample.utils.instantiate
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

class AppActivity : AppCompatActivity() {

    @Parcelize
    private data class SaveState(
        val containerId: Int
    ) : Parcelable

    companion object {
        private const val PRIMARY_NAVIGATION_FRAGMENT_TAG = "PRIMARY_NAVIGATION_FRAGMENT_TAG"

        private const val APP_ACTIVITY_STATE = "APP_ACTIVITY_STATE"
    }

    @Inject
    lateinit var fragmentFactory: FragmentFactoryImpl

    private var containerId: Int = View.NO_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerAppActivityComponent
            .factory()
            .create(
                dependencies = object : AppActivityDependencies {}
            )
            .inject(this)

        containerId = savedInstanceState
            ?.getParcelable<SaveState>(APP_ACTIVITY_STATE)
            ?.containerId
            ?: View.generateViewId()

        supportFragmentManager.fragmentFactory = fragmentFactory

        super.onCreate(savedInstanceState)

        setContentView(
            FragmentContainerView(this).apply {
                id = containerId
                layoutParams = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT
                )
            }
        )

        if (supportFragmentManager.fragments.isEmpty()) {
            supportFragmentManager.commit {
                val fragment = supportFragmentManager.fragmentFactory.instantiate<AppFragment>()
                setPrimaryNavigationFragment(fragment)
                replace(containerId, fragment, PRIMARY_NAVIGATION_FRAGMENT_TAG)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putParcelable(
            APP_ACTIVITY_STATE, SaveState(
                containerId = containerId
            )
        )
    }

    override fun onBackPressed() {
        supportFragmentManager
            .primaryNavigationFragment
            ?.let { fragment -> fragment as? BackPressedHandler }
            ?.onBackPressed()
            ?.takeIf { it }
            ?: super.onBackPressed()
    }

}

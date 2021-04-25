package com.arttttt.archsample.appactivity

import android.os.Bundle
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
import javax.inject.Inject

class AppActivity : AppCompatActivity() {

    companion object {
        private const val PRIMARY_NAVIGATION_FRAGMENT_TAG = "PRIMARY_NAVIGATION_FRAGMENT_TAG"
    }

    @Inject
    lateinit var fragmentFactory: FragmentFactoryImpl

    private val containerId: Int = View.generateViewId()

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerAppActivityComponent
            .factory()
            .create(
                dependencies = object : AppActivityDependencies {}
            )
            .inject(this)

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

    override fun onBackPressed() {
        supportFragmentManager
            .primaryNavigationFragment
            ?.let { fragment -> fragment as? BackPressedHandler }
            ?.onBackPressed()
            ?.takeIf { it }
            ?: super.onBackPressed()
    }

}

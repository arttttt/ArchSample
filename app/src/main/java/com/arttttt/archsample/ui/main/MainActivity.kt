package com.arttttt.archsample.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.arttttt.archsample.R
import com.arttttt.archsample.base.FragmentFactoryImpl
import com.arttttt.archsample.ui.container.ContainerFragment
import com.arttttt.archsample.ui.main.di.DaggerMainActivityComponent
import com.arttttt.archsample.ui.main.di.MainActivityDependencies
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var fragmentFactory: FragmentFactoryImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerMainActivityComponent
            .factory()
            .create(
                dependencies = object : MainActivityDependencies {}
            )
            .inject(this)

        supportFragmentManager.fragmentFactory = fragmentFactory

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        savedInstanceState ?: supportFragmentManager.commit {
            replace(R.id.fragment_container, ContainerFragment::class.java, null)
        }
    }

}

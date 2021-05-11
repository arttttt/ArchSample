package com.arttttt.mainscreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arttttt.chooserscreen.NavigationSelection
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

internal class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mainNavigatorFactory: MainNavigator.Factory

    @Inject
    lateinit var navigationSelection: NavigationSelection

    private val compositeDisposable = CompositeDisposable()

    private val mainNavigator by lazy {
        mainNavigatorFactory.create(R.id.container, supportFragmentManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        MainScreenComponentHolder
            .getComponent()
            .inject(this)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        savedInstanceState ?: mainNavigator.openRootScreen()

        navigationSelection
            .navigationSelectionObservable
            .subscribeBy(
                onNext = { event ->
                    when (event) {
                        is NavigationSelection.Event.BottomNavigationSelected -> {}
                        is NavigationSelection.Event.OrdinaryNavigationSelected -> mainNavigator.openOrdinaryNavigationFlow()
                    }
                }
            )
            .let(compositeDisposable::add)
    }

    override fun onDestroy() {
        super.onDestroy()

        compositeDisposable.clear()
    }

}

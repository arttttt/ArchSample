package com.arttttt.archsample.di.modules

import com.arttttt.archsample.di.scopes.NavigationScope
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides

@Module
object NavigationModule {

    @JvmStatic
    @Provides
    @NavigationScope
    fun provideCicerone(): Cicerone<Router> {
        return Cicerone.create()
    }

    @JvmStatic
    @Provides
    @NavigationScope
    fun provideRouter(cicerone: Cicerone<Router>): Router {
        return cicerone.router
    }

    @JvmStatic
    @Provides
    @NavigationScope
    fun provide(cicerone: Cicerone<Router>): NavigatorHolder {
        return cicerone.getNavigatorHolder()
    }

}

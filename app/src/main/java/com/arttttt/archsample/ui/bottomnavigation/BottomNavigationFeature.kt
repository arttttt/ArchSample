package com.arttttt.archsample.ui.bottomnavigation

import com.arttttt.archsample.ui.tabs.Tab
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ReducerFeature

class BottomNavigationFeature : ReducerFeature<BottomNavigationFeature.Wish, BottomNavigationFeature.State, BottomNavigationFeature.News>(
    initialState = State(
        tabs = emptyList(),
        selectedTab = null
    ),
    reducer = ReducerImpl()
) {

    data class State(
        val tabs: List<Tab>,
        val selectedTab: Tab?
    )

    sealed class Wish {
        data class SetTabs(val tabs: List<Tab>) : Wish()
        data class SelectTabAt(val position: Int) : Wish()
    }

    sealed class News

    class ReducerImpl : Reducer<State, Wish> {
        override fun invoke(state: State, wish: Wish): State {
            return when (wish) {
                is Wish.SetTabs -> state.copy(
                    tabs = wish.tabs
                )
                is Wish.SelectTabAt -> state.copy(
                    selectedTab = state.tabs.elementAt(wish.position)
                )
            }
        }
    }

    class NewsPublisher : SimpleNewsPublisher<Wish, State, News>() {
        override fun invoke(wish: Wish, state: State): News? {
            return null
        }
    }
}

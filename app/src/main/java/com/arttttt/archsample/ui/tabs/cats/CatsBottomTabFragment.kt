package com.arttttt.archsample.ui.tabs.cats

import android.os.Bundle
import androidx.fragment.app.commit
import com.arttttt.archsample.R
import com.arttttt.archsample.ui.fragment1.Fragment1
import com.arttttt.archsample.ui.tabs.container.TabContainerFragment

class CatsBottomTabFragment : TabContainerFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        savedInstanceState ?: childFragmentManager.commit {
            replace(R.id.tab_content_container, Fragment1(), null)
        }
    }

}

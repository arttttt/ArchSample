package com.arttttt.archsample.ui.tabs.dogs

import android.os.Bundle
import androidx.fragment.app.commit
import com.arttttt.archsample.R
import com.arttttt.archsample.ui.fragment2.Fragment2
import com.arttttt.archsample.ui.tabs.container.TabContainerFragment

class DogsBottomTabFragment : TabContainerFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        savedInstanceState ?: childFragmentManager.commit {
            replace(R.id.tab_content_container, Fragment2(), null)
        }
    }

}

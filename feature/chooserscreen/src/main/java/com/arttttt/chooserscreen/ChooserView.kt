package com.arttttt.chooserscreen

import android.view.View
import com.arttttt.android.BaseMviView
import com.google.android.material.button.MaterialButton

internal class ChooserView(
    root: View
) : BaseMviView<Nothing, ChooserView.UiEvent>() {

    sealed class UiEvent {
        object OrdinaryClicked : UiEvent()
        object BottomNavigationClicked : UiEvent()
    }

    init {
        root.findViewById<MaterialButton>(R.id.button_ordinary)?.setOnClickListener {
            emitUiEvent(UiEvent.OrdinaryClicked)
        }
        root.findViewById<MaterialButton>(R.id.button_bottomnavigation)?.setOnClickListener {
            emitUiEvent(UiEvent.BottomNavigationClicked)
        }
    }

}

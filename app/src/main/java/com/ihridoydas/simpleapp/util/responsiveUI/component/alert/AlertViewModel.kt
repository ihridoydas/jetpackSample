
package com.ihridoydas.simpleapp.util.responsiveUI.component.alert

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.ihridoydas.simpleapp.util.common.EmptyParamsNoReturnFun
import com.ihridoydas.simpleapp.util.common.EmptyParamsNoReturnNullableFun
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

enum class ButtonActions {
    Button1, Button2, Button3
}

/**
 * ViewModel for Alert
 * UI-> Alert()
 */
@HiltViewModel
class AlertViewModel @Inject constructor() : ViewModel() {

    var title by mutableStateOf("")
    var text by mutableStateOf("")
    var button1Text by mutableStateOf("")
    var button1TextColor : Color = Color.Unspecified
    var button1Action: EmptyParamsNoReturnFun = {}
    var button2Text by mutableStateOf("")
    var button2TextColor : Color = Color.Unspecified
    var button2Action: EmptyParamsNoReturnFun = {}
    var button3Text by mutableStateOf("")
    var button3TextColor : Color = Color.Unspecified
    var button3Action: EmptyParamsNoReturnFun = {}

    /** Called before showing dialog */
    private var showHook: EmptyParamsNoReturnNullableFun = null
    /** Called before hiding dialog */
    private var hideHook: EmptyParamsNoReturnNullableFun = null
    /** Button Action to call on Android back button press*/
    var backAction: ButtonActions? = null

    /**
     * Show alert, button1 is not optional, other buttons optional
     * @param title Title
     * @param text Text shown under title
     * @param button1Text Text to show on button1
     * @param button1Action Lambda Action to perform when clicking button 1
     * @param button2Text Text to show on button2
     * @param button2Action Lambda Action to perform when clicking button 2
     * @param button3Text Text to show on button3
     * @param button3Action Lambda Action to perform when clicking button 3
     * @param backAction Existing button action to call when back button pressed
     */
    fun show(
        title: String,
        text: String,
        button1Text: String,
        button1Action: () -> Unit,
        button2Text: String = "",
        button2Action: () -> Unit = {},
        button3Text: String = "",
        button3Action: () -> Unit = {},
        backAction: ButtonActions? = null
    ) {
        showHook?.let { it() }
        this.title = title
        this.text = text
        this.button1Text = button1Text
        this.button1Action = button1Action
        this.button2Text = button2Text
        this.button2Action = button2Action
        this.button3Text = button3Text
        this.button3Action = button3Action
        this.backAction = backAction
    }

    /** * Hide the alert */
    fun hide(runHook: Boolean = true) {
        hideHook?.let { it() }
    }

    fun callDismissAction() {
        when (backAction) {
            ButtonActions.Button1 -> button1Action.invoke()
            ButtonActions.Button2 -> button2Action.invoke()
            ButtonActions.Button3 -> button3Action.invoke()
            else -> {}
        }
    }

    /**
     *  UI→　NumberPlateAlertDialog()
     */
    fun showConfirmationDialog(
        title: String,
        text: String,
        button1Text: String,
        button1Action: () -> Unit,
        button2Text: String = "",
        button2Action: () -> Unit = {},
        button3Text: String = "",
        button3Action: () -> Unit = {},
        backAction: ButtonActions? = null
    ){
        showHook?.let { it() }
        this.title = title
        this.text = text
        this.button1Text = button1Text
        this.button1Action = button1Action
        this.button2Text = button2Text
        this.button2Action = button2Action
        this.button3Text = button3Text
        this.button3Action = button3Action
        this.backAction = backAction
    }

    /**
     * タイトルと一つボタンだけ表示の場合
     * UI→　SingleButtonAlertDialog()
     */
    fun showDialogWithTitle(
        title: String,
        button1Text: String,
        button1Action: () -> Unit,
        backAction: ButtonActions? = null
    ){
        showHook?.let { it() }
        this.title = title
        this.button1Text = button1Text
        this.button1Action = button1Action
        this.backAction = backAction
    }


    /**
     * ボタンのテキストカラー設定可能
     */
    fun showWithButtonTextColor(
        title: String,
        text: String,
        button1Text: String,
        button1TextColor: Color = Color.Unspecified,
        button1Action: () -> Unit,
        button2Text: String = "",
        button2TextColor: Color = Color.Unspecified,
        button2Action: () -> Unit = {},
        button3Text: String = "",
        button3TextColor: Color = Color.Unspecified,
        button3Action: () -> Unit = {},
        backAction: ButtonActions? = null
    ) {
        showHook?.let { it() }

        this.title = title
        this.text = text
        this.button1Text = button1Text
        this.button1TextColor = button1TextColor
        this.button1Action = button1Action
        this.button2Text = button2Text
        this.button2TextColor = button2TextColor
        this.button2Action = button2Action
        this.button3Text = button3Text
        this.button3TextColor = button3TextColor
        this.button3Action = button3Action
        this.backAction = backAction
    }
}

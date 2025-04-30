package com.kobe.mimaa.ui.view.communityScreen.aiOption.chat

import androidx.lifecycle.ViewModel
import com.google.ai.client.generativeai.GenerativeModel
import dagger.hilt.android.lifecycle.HiltViewModel


@HiltViewModel
class ChatViewModel(
    private val generativeModel: GenerativeModel
) : ViewModel(){
    //
}
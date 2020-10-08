package com.resocoder.databinding

import android.graphics.Insets.add
import android.system.Os.remove
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MainViewModel : ViewModel(), Observable {
    //viewModelをインポート

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    //LiveDataから値を取得する
    val currentRandomFruitName: LiveData<String>
        get() = FakeRepository.currentRandomFruitName

    //ボタンをクリックしたらLiveDataからString値を取得する
    fun onChangeRandomFruitClick() = FakeRepository.changeCurrentRandomFruitName()

    //viewModelとfakeRepositoryのどちらかで値が書き変われば値が変化するようにする
    //dataBindingさせるときは@bindableと書く
    @Bindable
    val editTextContent = MutableLiveData<String>()

    //よくわからない
    //displayedEditTextContent は LiveData<String> を継承する
    //displayedEditTextContentをgetする
    private val _displayedEditTextContent = MutableLiveData<String>()
    val displayedEditTextContent: LiveData<String>
        get() = _displayedEditTextContent

    //ボタンがクリックされると、_displayedEditTextContent.value　に　editTextContent.value　が代入される
    fun onDisplayEditTextContentClick() {
        _displayedEditTextContent.value = editTextContent.value
    }

    //ランダムなString値を取得してEditTextに表示させる
    fun onSelectRandomEditTextFruit() {
        editTextContent.value = FakeRepository.getRandomFruitName()
    }
}
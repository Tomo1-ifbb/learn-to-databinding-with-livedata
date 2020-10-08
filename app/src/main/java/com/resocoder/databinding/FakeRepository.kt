package com.resocoder.databinding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*
//random関数を使うためのパッケージ


object FakeRepository {

    // 画面で使うString Data一覧、これらのみを使うということでprivateと書いてる
    private val fruitNames: List<String> = listOf(
            "Apple", "Banana", "Orange", "Kiwi", "Grapes", "Fig",
            "Pear", "Strawberry", "Gooseberry", "Raspberry"
    )

    //インスタンス化、Live Dataを値を更新するときに使う、呼びだすのは_currentRandomFruitName
    //Live Dataを画面上で呼び出す時に使うのがviewModel
    //eg. viewModel.currentRandomFruitName　これを呼び出して表示させたいxmlに "@"{}"の形式で表示させる
    //MutableLiva dataとLiveDataを別々に定義する理由は、
    private val _currentRandomFruitName = MutableLiveData<String>()
    val currentRandomFruitName: LiveData<String>
        get() = _currentRandomFruitName

    //初期値の入力を定義しているだけ。init
    init {
        _currentRandomFruitName.value = fruitNames.first()
    }

    //String Dataのフルーツ名をランダムに表示させたいので、ランダムに表示させる関数を設定する
    //返す値はfruitNames、randomをnextIntと一緒に使う的な感じでいい。fruitNames.size（fruitNamesの中身全体）
    fun getRandomFruitName(): String {
        val random = Random()
        return fruitNames[random.nextInt(fruitNames.size)]
    }

    //_currentRandomFruitName.valueが↑の関数だけだと初期値しか表示されないので、getRandomFruitName()関数で呼び出されるfruitNamesを表示させる
    fun changeCurrentRandomFruitName() {
        _currentRandomFruitName.value = getRandomFruitName()
    }
}
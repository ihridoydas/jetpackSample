package com.ihridoydas.simpleapp.util.responsiveUI.component.animations.dragAndDrop.simpleDragAndDrop

import androidx.annotation.DrawableRes
import com.ihridoydas.simpleapp.R

data class FoodItem(val id: Int, val name: String, val price: Double, @DrawableRes val image: Int)

val foodList = listOf(
    FoodItem(1, "Pizza", 20.0, R.drawable.food_cake),
    FoodItem(2, "French toast", 10.05, R.drawable.food_cake),
    FoodItem(3, "Chocolate cake", 12.99, R.drawable.food_cake),
)

data class Person(val id: Int, val name: String, @DrawableRes val profile: Int)

val persons = listOf(
    Person(1, "Jhone", R.drawable.user_one),
    Person(2, "Eyle", R.drawable.user_one),
    Person(3, "Tommy", R.drawable.user_one),
)
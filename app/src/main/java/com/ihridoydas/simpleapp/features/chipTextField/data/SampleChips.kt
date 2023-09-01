package com.ihridoydas.simpleapp.features.chipTextField.data

import com.ihridoydas.simpleapp.features.chipTextField.chipStyle.Chip
import kotlin.random.Random

object SampleChips {
    private const val LOREM_IPSUM = "Lorem ipsum dolor sit amet, consectetur adipisicing elit"

    private const val PICSUM_SEED_URL = "https://picsum.photos/seed/{seed}/100/100"

    fun getTextChips() = LOREM_IPSUM.split(" ").map(::Chip)

    fun getCheckableChips() = LOREM_IPSUM.split(" ").map {
        CheckableChip(text = it, isChecked = Random.nextBoolean())
    }

    fun getAvatarChips(): List<AvatarChip> {
        var millis = System.currentTimeMillis()
        return LOREM_IPSUM.split(" ")
            .map { AvatarChip(it, randomAvatarUrl(millis++)) }
    }

    fun randomAvatarUrl(seed: Long = System.currentTimeMillis()): String {
        return PICSUM_SEED_URL.replace("{seed}", seed.toString())
    }
}
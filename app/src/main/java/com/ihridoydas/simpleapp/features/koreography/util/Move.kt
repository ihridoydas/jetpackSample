package com.ihridoydas.simpleapp.features.koreography.util

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.AnimationVector
import androidx.compose.animation.core.spring

sealed interface Move<T, V : AnimationVector> {
    class SingleAnimatableMove<T, V : AnimationVector>(
        val animatable: Animatable<T, V>,
        val targetValue: T,
        val initialVelocity: T = animatable.velocity,
        val animationSpec: AnimationSpec<T> = spring(),
        internal val initialValue: T = animatable.value
    ) : Move<T, V>

    class SequentialMoves<T, V : AnimationVector> : Move<T, V> {
        private val _moves = mutableListOf<Move<T, V>>()
        internal val moves: List<Move<T, V>>
            get() = _moves.toList()

        fun move(
            animatable: Animatable<T, V>,
            targetValue: T,
            initialVelocity: T = animatable.velocity,
            animationSpec: AnimationSpec<T> = spring()
        ) {
            _moves += SingleAnimatableMove(animatable, targetValue, initialVelocity, animationSpec)
        }

        fun parallelMoves(parallelMoves: ParallelMoves<T, V>.() -> Unit) {
            _moves += ParallelMoves<T, V>().apply(parallelMoves)
        }

        fun sequentialMoves(sequentialMoves: SequentialMoves<T, V>.() -> Unit) {
            _moves += this.apply(sequentialMoves)
        }
    }

    class ParallelMoves<T, V : AnimationVector> : Move<T, V> {
        private val _moves = mutableListOf<Move<T, V>>()
        internal val moves: List<Move<T, V>>
            get() = _moves.toList()

        fun move(
            animatable: Animatable<T, V>,
            targetValue: T,
            initialVelocity: T = animatable.velocity,
            animationSpec: AnimationSpec<T> = spring(),
        ) {
            _moves += SingleAnimatableMove(animatable, targetValue, initialVelocity, animationSpec)
        }

        fun sequentialMoves(sequentialMoves: SequentialMoves<T, V>.() -> Unit) {
            _moves += SequentialMoves<T, V>().apply(sequentialMoves)
        }

        fun parallelMoves(parallelMoves: ParallelMoves<T, V>.() -> Unit) {
            _moves += this.apply(parallelMoves)
        }
    }
}
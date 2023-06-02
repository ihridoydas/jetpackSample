package com.ihridoydas.simpleapp.features.twoPaneSample

import com.google.accompanist.adaptive.FoldAwareConfiguration

enum class STRATEGY { HORIZONTAL, VERTICAL }
enum class CONFIG(val foldConfig: FoldAwareConfiguration) {
    ALL(FoldAwareConfiguration.AllFolds),
    HORIZONTAL(FoldAwareConfiguration.HorizontalFoldsOnly),
    VERTICAL(FoldAwareConfiguration.VerticalFoldsOnly)
}
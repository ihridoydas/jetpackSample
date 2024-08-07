package com.ihridoydas.simpleapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


val Primary40 = Color(0xFF00cc99) //Primary
val Secondary40 = Color(0xFF002d30) //Secondary
val Tertiary40 = Color(0xFF00c3f1) //Tertiary
val Neutral40 = Color(0xFF8e918e) //Neutral [Used for background and surfaces]

// Checkout for learn UI Theme
// https://www.w3schools.com/colors/colors_picker.asp (For Color picker)
// https://m3.material.io/theme-builder#/custom (custom Theme builder)

val md_theme_light_primary = Color(0xFF006C4F)
val md_theme_light_onPrimary = Color(0xFFFFFFFF)
val md_theme_light_primaryContainer = Color(0xFF5DFCC6)
val md_theme_light_onPrimaryContainer = Color(0xFF002116)
val md_theme_light_secondary = Color(0xFF00696F)
val md_theme_light_onSecondary = Color(0xFFFFFFFF)
val md_theme_light_secondaryContainer = Color(0xFF75F5FF)
val md_theme_light_onSecondaryContainer = Color(0xFF002022)
val md_theme_light_tertiary = Color(0xFF006781)
val md_theme_light_onTertiary = Color(0xFFFFFFFF)
val md_theme_light_tertiaryContainer = Color(0xFFB9EAFF)
val md_theme_light_onTertiaryContainer = Color(0xFF001F29)
val md_theme_light_error = Color(0xFFBA1A1A)
val md_theme_light_errorContainer = Color(0xFFFFDAD6)
val md_theme_light_onError = Color(0xFFFFFFFF)
val md_theme_light_onErrorContainer = Color(0xFF410002)
val md_theme_light_background = Color(0xFFFBFDF9)
val md_theme_light_onBackground = Color(0xFF191C1A)
val md_theme_light_surface = Color(0xFFFBFDF9)
val md_theme_light_onSurface = Color(0xFF191C1A)
val md_theme_light_surfaceVariant = Color(0xFFDBE5DE)
val md_theme_light_onSurfaceVariant = Color(0xFF404944)
val md_theme_light_outline = Color(0xFF707974)
val md_theme_light_inverseOnSurface = Color(0xFFEFF1EE)
val md_theme_light_inverseSurface = Color(0xFF2E312F)
val md_theme_light_inversePrimary = Color(0xFF35DFAB)
val md_theme_light_shadow = Color(0xFF000000)
val md_theme_light_surfaceTint = Color(0xFF006C4F)
val md_theme_light_outlineVariant = Color(0xFFBFC9C2)
val md_theme_light_scrim = Color(0xFF000000)

val md_theme_dark_primary = Color(0xFF35DFAB)
val md_theme_dark_onPrimary = Color(0xFF003828)
val md_theme_dark_primaryContainer = Color(0xFF00513B)
val md_theme_dark_onPrimaryContainer = Color(0xFF5DFCC6)
val md_theme_dark_secondary = Color(0xFF4CD9E3)
val md_theme_dark_onSecondary = Color(0xFF00363A)
val md_theme_dark_secondaryContainer = Color(0xFF004F54)
val md_theme_dark_onSecondaryContainer = Color(0xFF75F5FF)
val md_theme_dark_tertiary = Color(0xFF56D5FF)
val md_theme_dark_onTertiary = Color(0xFF003544)
val md_theme_dark_tertiaryContainer = Color(0xFF004D61)
val md_theme_dark_onTertiaryContainer = Color(0xFFB9EAFF)
val md_theme_dark_error = Color(0xFFFFB4AB)
val md_theme_dark_errorContainer = Color(0xFF93000A)
val md_theme_dark_onError = Color(0xFF690005)
val md_theme_dark_onErrorContainer = Color(0xFFFFDAD6)
val md_theme_dark_background = Color(0xFF191C1A)
val md_theme_dark_onBackground = Color(0xFFE1E3DF)
val md_theme_dark_surface = Color(0xFF191C1A)
val md_theme_dark_onSurface = Color(0xFFE1E3DF)
val md_theme_dark_surfaceVariant = Color(0xFF404944)
val md_theme_dark_onSurfaceVariant = Color(0xFFBFC9C2)
val md_theme_dark_outline = Color(0xFF89938D)
val md_theme_dark_inverseOnSurface = Color(0xFF191C1A)
val md_theme_dark_inverseSurface = Color(0xFFE1E3DF)
val md_theme_dark_inversePrimary = Color(0xFF006C4F)
val md_theme_dark_shadow = Color(0xFF000000)
val md_theme_dark_surfaceTint = Color(0xFF35DFAB)
val md_theme_dark_outlineVariant = Color(0xFF404944)
val md_theme_dark_scrim = Color(0xFF000000)


val seed = Color(0xFF00CC99)

val AndroidBlueColor = Color(0xff9bbed3)
val AndroidBlueDarkerColor = Color(0xff073042)

//Others
val GreenColor = Color(0xFF4CAF50)
val GreenVarient = Color(0xFFB3D5B3)

//ForDynamic Island
val Green = Color(0xFF6EE228)
val Orange = Color(0xFFFFAD57)
val White = Color(0xffFFFFFF)
val Blue = Color(0xFF2CADF1)

//LoadingAnimation

val SwatchA = Color(0xFFFFA987)
val SwatchB = Color(0xFFD85151)
val SwatchC = Color(0xFFF2DDA4)


//For Sorting Visualizer
val GreenDark = Color(0xFF24B273)
val GreenExtraDark = Color(0xFF243D44)
val GreenExtraLight = Color(0xFF2DEA8F)
val BlueSort = Color(0xFF6356E5)
val Red = Color(0xFFF83E4B)

//For Two Pane
val Brown = Color(0xFF442A03)
val Beige = Color(0xFFBE9962)
val DarkOrange = Color(0xFFC14B09)

//=-------------------
//For Composible Sheep
object SheepColor {
    val Gray = Color(0xFFCCCCCC)
    val Blue = Color(0xFF1976D2)
    val Green = Color(0xFF3DDC84)
    val DarkGreen = Color(0xFF24804D)
    val Black = Color(0xFF191B1B)
    val Purple = Color(0xFF6200EA)
    val Magenta = Color(0xFFC51162)
    val Orange = Color(0xFFFF9800)
    val Red = Color(0xFFFF0000)
    val Eye = Color(0xFFE69B43)
    val Skin = Color(0xFF444444)
    val Fluff = Color(0xFFCCCCCC)
}
//=-------------------

val Translucent = Color(0x9E1F1F1F)

//Rive Animation
val isDarkThemeEnabled : Boolean
    @Composable
    get() = isSystemInDarkTheme()
val P2PBackground: Color
    @Composable
    get() = if (isDarkThemeEnabled) Color(0xFFd7e1e8) else Color(0xFFd7e1e8)

val TextColor: Color
    @Composable
    get() = if (isDarkThemeEnabled) Color.Black else Color.Black

//For TimeLine Compose Feature
val Gray200: Color = Color(0xFFEEEBF4)
val Orange500: Color = Color(0xFFFF8700)
val Green500: Color = Color(0xFF00FF86)

val LightBlue: Color = Color(0xff12c2e9)
val Purple: Color = Color(0xffc471ed)
val Coral: Color = Color(0xfff64f59)
val LightCoral: Color = Color(0xfffbaeb2)

//Quiz app color
val CodeeBlue = Color(0xff141A33)
val CodeeRed = Color(0xffCE0E4B)
val CodeeGreen = Color(0xff0CBA8D)
val CodeeGray = Color(0xff666B86)

//Pendulum
val Honeydew = Color(0xfff1faee)
val Desire = Color(0xffe63946)
val Crystal = Color(0xffa8dadc)
val JellyBeanBlue = Color(0xff457b9d)
val SpaceCadet = Color(0xff1d3557)

//For Qr Code
val LightSurface = Color(0xFFFFFFFF)
val LightYellow = Color(0xFFFFF3E0)
val DarkYellow = Color(0xFFFFD152)
val DarkSurface = Color(0xFF3C3E4B)
val LightGrey = Color(0xFFCECECE)
val DarkGrey = Color(0xFF3C3E4B)
val LightText = Color(0xFFBEB083)
val DarkText = Color(0xFF852E2E)

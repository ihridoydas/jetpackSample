# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Retrofit does reflection on generic parameters. InnerClasses is required to use Signature and
# EnclosingMethod is required to use InnerClasses.
-keepattributes Signature, InnerClasses, EnclosingMethod

# Retrofit does reflection on method and parameter annotations.
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations

# Keep annotation default values (e.g., retrofit2.http.Field.encoded).
-keepattributes AnnotationDefault

-keep class okio.** { *; }
-keep class okhttp3.** { *; }
-keep class retrofit2.** { *; }
-keep class jp.cognivision.cpbmobile.data.remote.endpoint.** { *; }
-keep class jp.cognivision.cpbmobile.util.converter.** { *; }
-keep class jp.cognivision.cpbmobile.data.models.api.** { *; }
-keep class retrofit2.converter.gson.** { *; }

-keep class com.google.gson.** { *; }
-keep class com.google.gson.stream.** { *; }
-keepattributes SerializedName

# With R8 full mode generic signatures are stripped for classes that are not
# kept. Suspend functions are wrapped in continuations where the type argument
# is used.
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable
-keep class net.sqlcipher.** { *; }
-keep class net.sqlcipher.database.** { *; }

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# 京セラ TORQUE端末で発生したエラー解決ため　
# エラーは「 java.lang.RuntimeException: Unable to start activity ComponentInfo{jp.cognivision.cpbmobile.develop/jp.cognivision.cpbmobile.ui.home.MainActivity}: java.lang.IllegalStateException: You need to use a Theme.AppCompat theme (or descendant) with this activity.」
# [https://qiita.com/ralph/items/96e57fe561497edb019a]のエラー解決ため　
-keep class com.google.android.gms.** { *; }
-keep public class com.google.android.gms.**
-dontwarn com.google.android.gms.**
-keep class android.support.v7.** { *; }
-keep interface android.support.v7.** { *; }
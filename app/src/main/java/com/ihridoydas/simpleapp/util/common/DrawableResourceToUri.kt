
package com.ihridoydas.simpleapp.util.common

import android.content.ContentResolver
import android.content.Context
import android.net.Uri

/**
 * Drawable Resourceにある画像はUriとして扱う。
 * @param context
 * @param resourceId
 */
fun drawableResToUri(context: Context, resourceId: Int): Uri {
    return  Uri.Builder()
        .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
        .authority(context.resources.getResourcePackageName(resourceId))
        .appendPath(context.resources.getResourceTypeName(resourceId))
        .appendPath(context.resources.getResourceEntryName(resourceId))
        .build()
}

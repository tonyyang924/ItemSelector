package com.tonyyang.common.itemselector.util

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable
import android.net.Uri
import com.tonyyang.common.itemselector.CoreApplication
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.security.SecureRandom

class ImageUtils {
    companion object {
        fun getResourceDrawableUri(resourceId: Int): Uri {
            val resources = CoreApplication.getContext().resources
            return Uri.Builder()
                .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
                .authority(resources.getResourcePackageName(resourceId))
                .appendPath(resources.getResourceTypeName(resourceId))
                .appendPath(resources.getResourceEntryName(resourceId))
                .build()
        }

        fun drawable2Bitmap(drawable: Drawable): Bitmap {
            val config = if (drawable.opacity != PixelFormat.OPAQUE) {
                Bitmap.Config.ARGB_8888
            } else {
                Bitmap.Config.RGB_565
            }
            val bitmap = Bitmap.createBitmap(
                drawable.intrinsicWidth,
                drawable.intrinsicHeight,
                config)
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight);
            drawable.draw(canvas)
            return bitmap
        }

        fun bitmap2InputStream(bm: Bitmap): InputStream {
            val baos = ByteArrayOutputStream()
            bm.compress(Bitmap.CompressFormat.PNG, 100, baos)
            return ByteArrayInputStream(baos.toByteArray())
        }

        fun drawable2InputStream(drawable: Drawable?): InputStream? {
            if (drawable == null) {
                return null
            }
            val bitmap = drawable2Bitmap(drawable)
            return bitmap2InputStream(bitmap)
        }
    }
}
package com.mine.cappella.utils

import android.util.Log
import android.webkit.MimeTypeMap
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class GalleryCameraUtility {
    companion object{

        fun getImageRequestBody(sourceFile: File) : RequestBody? {
            var requestBody: RequestBody? = null
            Thread {
                val mimeType = getMimeType(sourceFile);
                if (mimeType == null) {
                    Log.e("file error", "Not able to get mime type")
                    return@Thread
                }
                try {
                    requestBody = sourceFile.path.toRequestBody("multipart/form-data".toMediaTypeOrNull())
                } catch (ex: Exception) {
                    ex.printStackTrace()
                    Log.e("File upload", "failed")
                }
            }.start()

            return requestBody;
        }

        // url = file path or whatever suitable URL you want.
        private fun getMimeType(file: File): String? {
            var type: String? = null
            val extension = MimeTypeMap.getFileExtensionFromUrl(file.path)
            if (extension != null) {
                type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
            }
            return type
        }
    }
}
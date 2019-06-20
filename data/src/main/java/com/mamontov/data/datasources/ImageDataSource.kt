package com.mamontov.data.datasources

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import javax.inject.Inject

interface ImageDataSource {

	fun saveImage(url: String)
}

class ImageDataSourceImpl @Inject constructor(
		private val context: Context
) : ImageDataSource {

	override fun saveImage(url: String) {
		val uri = Uri.parse(url)
		val title = uri.lastPathSegment

		val request = DownloadManager.Request(uri).apply {
			setTitle(title)
			setVisibleInDownloadsUi(true)
			setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
			setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, uri.lastPathSegment)
			setAllowedOverMetered(true)
			setAllowedOverRoaming(true)
		}

		try {
			val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
			downloadManager.enqueue(request)
		} catch (e: Exception) {
			e.printStackTrace()
		}
	}
}
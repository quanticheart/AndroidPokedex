package com.quanticheart.repository.push

import android.content.Intent
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.quanticheart.core.notification.notify
import com.quanticheart.repository.R

class PushService : FirebaseMessagingService() {

    override fun onMessageReceived(p0: RemoteMessage) {

        val isDeeplink = p0.data.containsKey("deeplink")

        val intent =
            if (isDeeplink) Intent(p0.data["deeplink"]) else Intent("homa_activity")

        val title = p0.data["title"] ?: p0.notification?.title ?: this.getString(R.string.app_name)
        val message = p0.data["message"] ?: p0.notification?.body ?: ""

        val channelID = this.getString(R.string.default_notification_channel_id)
        val channelName = this.getString(R.string.default_notification_channel_name)
        val channelDescription = this.getString(R.string.default_notification_channel_description)

        notify()
            .setTitle(title)
            .setContent(message)
            .setChannelName(channelName)
            .setChannelId(channelID)
            .setChannelDescription(channelDescription)
            .setAction(intent)
            .show()
    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Log.i("TOKEN_FIREBASE", p0)
    }
}

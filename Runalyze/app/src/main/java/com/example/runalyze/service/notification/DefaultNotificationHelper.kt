package com.example.runalyze.service.location.notification

import android.content.Context

class DefaultNotificationHelper (private val context: Context) {
}

//    companion object {
//        private const val TRACKING_NOTIFICATION_CHANNEL_ID = "tracking_notification"
//        private const val TRACKING_NOTIFICATION_CHANNEL_NAME = "Run Tracking Status"
//    }
//
//    override val baseNotificationBuilder
//        get() = NotificationCompat.Builder(
//            context,
//            TRACKING_NOTIFICATION_CHANNEL_ID
//        )
//            .setSmallIcon(R.drawable.testing)
//            .setAutoCancel(false)
//            .setOngoing(true)
//            .setContentTitle("Running Time")
//            .setContentText("00:00:00")
//
//    private val notificationManager by lazy {
//        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//    }

//    override fun updateTrackingNotification(durationInMillis: Long, isTracking: Boolean) {
//        val notification = baseNotificationBuilder
////            .setContentText(RunUtils.getFormattedStopwatchTime(durationInMillis))
//            .clearActions()
//            .addAction(getTrackingNotificationAction(isTracking))
//            .build()
//
//        notificationManager.notify(TRACKING_NOTIFICATION_ID, notification)
//    }
//
//    private fun getTrackingNotificationAction(isTracking: Boolean): NotificationCompat.Action {
//
////        return NotificationCompat.Action(
////            if (isTracking) R.drawable.fire else R.drawable.testing,
////            if (isTracking) "Pause" else "Resume",
////            PendingIntent.getService(
////                context,
////                2234,
////                Intent()
////            )
////                context,
////                2234,
////                Intent(
////                    context,
//////                    TrackingService::class.java
////                ).apply {
////                    action =
////                        if (isTracking) TrackingService.ACTION_PAUSE_TRACKING else TrackingService.ACTION_RESUME_TRACKING
////                },
////                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
////            )
//        )


//    override fun removeTrackingNotification() {
//        notificationManager.cancel(TRACKING_NOTIFICATION_ID)
//    }
//
//    override fun createNotificationChannel() {
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
//            return
//
//        val notificationChannel = NotificationChannel(
//            TRACKING_NOTIFICATION_CHANNEL_ID,
//            TRACKING_NOTIFICATION_CHANNEL_NAME,
//            NotificationManager.IMPORTANCE_LOW
//        )
//        notificationManager.createNotificationChannel(notificationChannel)
//    }

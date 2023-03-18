package com.ihridoydas.simpleapp.util.extensions

import android.app.Activity
import android.content.Context
import android.widget.Toast
import com.google.android.play.core.review.ReviewManagerFactory
import com.ihridoydas.simpleapp.ui.MainActivity

//Add Dependency in gradle build
//implementation 'com.google.android.play:review:2.0.1'
//implementation 'com.google.android.play:review-ktx:2.0.1'

 fun showFeedBackDialog(context: Context,activity: Activity){
    val reviewManager = ReviewManagerFactory.create(context)
    reviewManager.requestReviewFlow().addOnCompleteListener{task->
        if (task.isSuccessful){
            reviewManager.launchReviewFlow(activity,task.result)
        }else {
            // There was some problem, log or handle the error code.
            Toast.makeText(context,"Welcome ...Error undefined code", Toast.LENGTH_LONG).show()
        }

    }
}
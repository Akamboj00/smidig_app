package com.google.firebase.referencecode.database.models

import com.example.report_app.databuilders.Progress
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

// [START post_class]
@IgnoreExtraProperties
data class User(
    var id: String? = "",
    var userId: String? = "",
    var firstName: String? = "",
    var lastName: String? = "",
    var language: String? = "",
    var progress: ArrayList<Any>
) {
    // [START post_to_map]
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "id" to id,
            "userId" to userId,
            "firstName" to firstName,
            "lastName" to lastName,
            "language" to language
        )
    }
    // [END post_to_map]
}
// [END post_class]
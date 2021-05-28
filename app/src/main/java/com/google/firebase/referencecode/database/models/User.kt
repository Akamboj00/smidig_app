package com.google.firebase.referencecode.database.models

import com.google.firebase.database.IgnoreExtraProperties

// [START post_class]
@IgnoreExtraProperties
class User {
    var id: Any? = null
    var userId: Any? = null
    var firstName: Any? = null
    var lastName: Any? = null
    var language: Any? = null
    var progress: ArrayList<Any>? = null

    constructor() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    constructor(
        id: Any?,
        userId: Any?,
        firstName: Any?,
        lastName: Any?,
        language: Any?,
        progress: ArrayList<Any>?
    ) {
        this.id = id
        this.userId = userId
        this.firstName = firstName
        this.lastName = lastName
        this.language = language
        this.progress = progress
    }
}
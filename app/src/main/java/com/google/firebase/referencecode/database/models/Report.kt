package com.google.firebase.referencecode.database.models

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import java.util.*

// [START post_class]
@IgnoreExtraProperties
class Report {
    var id: String? = null
    var userId: String? = null
    var reportId: String? = null
    var productId: String? = null
    var partId: String? = null
    var description: String? = null

    constructor() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    constructor(id: String?, userId: String?, reportId: String?, productId: String?, partId: String?, description: String?) {
        this.id = id
        this.userId = userId
        this.reportId = reportId
        this.productId = productId
        this.partId = partId
        this.description = description
    }

    // [START post_to_map]
    @Exclude
    fun toMap(): Map<String, Any?> {
        val result = HashMap<String, Any?>()
        result["id"] = id
        result["userId"] = userId
        result["reportId"] = reportId
        result["productId"] = productId
        result["partId"] = partId
        result["description"] = description
        return result
    } // [END post_to_map]
}
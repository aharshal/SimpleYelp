package com.aharshal.simpleyelp

import com.google.gson.annotations.SerializedName

data class YelpSearchResult(
    @SerializedName(value = "total") val total:Int,
    @SerializedName(value = "businesses") val restaurants: List<YelpRestaurant>
)

data class YelpRestaurant(
    val name: String,
    val rating: Double,
    val price: String,
    @SerializedName("review_count") val numReviews: Int,
    @SerializedName("distance") val distanceInMeters: Double,
    @SerializedName("image_url") val imageUrl: String,
    val categories: List<YelpCategory>,
    val location: YelpLocation
    //@SerializedName("name") val name: String
) {
    fun displayDistance(): String{
        val milesPerMeter = 0.000621371
        val distanceInMiles = "%.2f".format( distanceInMeters*milesPerMeter)
        return "$distanceInMiles mi"
    }
}

data class YelpCategory(
    val title:String
)

data class YelpLocation(
    @SerializedName("address1") val address: String
)
package data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    @SerialName("id")
    val id: Int,
    @SerialName("price")
    val price: Int,
    @SerialName("name")
    val name: String,
    @SerialName("desc")
    val desc: String,
    @SerialName("color")
    val color: String,
    @SerialName("image")
    val image: String,


    )

@Serializable
data class Rating(
    @SerialName("count")
    val count: Int,
    @SerialName("rate")
    val rate: Double
)
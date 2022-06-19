package com.rehthinkdev.gramoday.Model

data class GramModel(
    val _id: String,
    val business: Business,
    val countryCode: String,
    val createdAt: String,
    val isWhatsappNum: Boolean,
    val language: String,
    val lastContentCreationDate: String,
    val lastLoginDate: String,
    val loclevel1Name: String,
    val loclevel2: Int,
    val loclevel2Name: String,
    val loclevel2ShortName: String,
    val loclevel3: Int,
    val loclevel3Name: String,
    val name: String,
    val numOfConnections: Int,
    val products: List<Product>,
    val profileSlug: String,
    val status: String,
    val updatedAt: String,
    val userID: String,
    val userInfoRating: Int
)
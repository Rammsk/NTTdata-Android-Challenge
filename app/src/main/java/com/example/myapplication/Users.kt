package com.example.myapplication

data class Users(
    val info : Info,
    val results : List<Result>
)

data class Info(
    val page: Int,
    val results: Int,
    val seed : String,
    val version : String
)

data class Result(
    val cell : String,
    val dob : Dob,
    val email : String,
    val gender : String,
    val id : Id,
    val login : Login,
    val name: Name,
    val picture : Picture,
    val phone : String
)

data class Login(
    val password: String
)

data class Name(
    val first: String,
    val last : String,
    val title : String
)

data class Dob(
    val age : Int,
    val date : String
)

data class Picture(
    val large: String,
    val medium : String,
    val thumbnail : String
)

data class Id(
    val name: String,
    val value: String
)
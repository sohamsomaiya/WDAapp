package com.example.wda.model

class UserWebsite(
    var WebsiteName:String?,
    var WebsiteId:String?
) {
        override fun toString(): String {
            return WebsiteId+" "+WebsiteName
        }
}
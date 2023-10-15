package com.example.wda.model

import org.json.JSONArray

class Status(
    var Name: String,
    var Contact: String,
    var StatusData: JSONArray
        )
class ListOfWebsite(var StatusName : String,
                    var WebsiteId : String,
                    var WebsiteName : String,
                    var DomainName : String
                    )

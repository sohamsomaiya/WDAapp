package com.example.wda.model

class UserWebsite(
    var WebsiteId:String?,
    var WebsiteName:String?,
    var WebsiteType : String,
    var DateOfIncorporation : String?,
    var CorporateIdentificationNo :String?,
    var TaxDeductionAccNo:String,
    var DomainName : String
)
{
        override fun toString(): String {
            return WebsiteName.toString()
        }
}
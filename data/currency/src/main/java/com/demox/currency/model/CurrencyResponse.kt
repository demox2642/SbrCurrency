package com.demox.currency.model

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import org.simpleframework.xml.convert.Convert

@Root(name = "Record", strict = false)
@Convert(CurrencyConverter::class)
data class CurrencyResponse(
    @field:Attribute(name = "Date", required = false)
    var data: String? = "",

    @field:Attribute(name = "Id", required = false)
    var id: String? = "",

    @field:Element(name = "Nominal")
    var nominal: Int? = null,

    @field:Element(name = "Value", required = false)
    var value: String? = null
)

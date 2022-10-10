package com.demox.currency.model

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

//@Root(name = "Record", strict = false)
class RecordCurse @JvmOverloads constructor(
    @get:Attribute(name = "Date", required = false)
    var data: String? = "",
//    @field:Attribute(name = "Id", required = false)
//    var Id: String,
//    @field: Element(name = "Nominal", required = false)
//    var nominal: String? = "1",
//    @field: Element(name = "Value", required = false)
//    var value: Double = 0.00001
)

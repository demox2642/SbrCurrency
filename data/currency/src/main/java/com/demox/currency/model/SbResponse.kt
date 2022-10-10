package com.demox.currency.model

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "ValCurs", strict = false)
data class SbResponse(
    @field:ElementList(name = "Record", inline = true)
    var currencyList: List<CurrencyResponse>? = null
)
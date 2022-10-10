package com.demox.currency.model

import org.simpleframework.xml.convert.Converter
import org.simpleframework.xml.stream.InputNode
import org.simpleframework.xml.stream.OutputNode

class CurrencyConverter : Converter<CurrencyResponse?> {
    @Throws(Exception::class)
    override fun read(node: InputNode): CurrencyResponse {
        val channel = CurrencyResponse()
        var child: InputNode

        while (node.next.also { child = it } != null) {
            when (child.name) {
                "Nominal" -> channel.nominal = child.value.toInt()
                "Value" -> {
                    channel.value = child.value.toString()
                }
                else -> throw RuntimeException("Unknown Element found: $child")
            }
        }
        return channel
    }

    @Throws(Exception::class)
    override fun write(node: OutputNode?, value: CurrencyResponse?) {
        throw UnsupportedOperationException("Not supported yet.")
    }
}

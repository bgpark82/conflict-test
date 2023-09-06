package com.bgpark.kotlinspringmysqldemo.queries

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class QueryController {

    @PostMapping("/queries")
    fun create(@RequestBody request:QueryRequest) {

        println(request)
    }

    data class QueryRequest(
        val template: String,
        val params: Map<String, Any>,
        val interval: String
    ) {

    }
}
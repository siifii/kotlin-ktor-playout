package com.siifii.plugins

import com.siifii.routes.customerRouting
import com.siifii.routes.getOrderRoute
import com.siifii.routes.listOrdersRoute
import com.siifii.routes.totalizeOrderRoute
import io.ktor.server.routing.*
import io.ktor.server.http.content.*
import io.ktor.server.application.*

fun Application.configureRouting() {

    routing {
        customerRouting()
        getOrderRoute()
        listOrdersRoute()
        totalizeOrderRoute()
    }
}


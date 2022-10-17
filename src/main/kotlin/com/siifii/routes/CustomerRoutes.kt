package com.siifii.routes

import com.siifii.models.Customer
import com.siifii.models.customerStorage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlin.text.get

fun Route.customerRouting() {
    route("/customer") {
        get {
            if (customerStorage.isEmpty()) {
                call.respond(customerStorage)
            } else {
                call.respondText("No customers found", status = HttpStatusCode.OK)
            }
        }

        get("{id?}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            val customer =
                customerStorage.find { it.id == id } ?: return@get call.respondText(
                    text = "No Customer with this id $id found",
                    status = HttpStatusCode.NotFound
                )

            call.respond(customer)

        }

        post {
            val customers = call.receive<Customer>()
            customerStorage.add(customers)
            call.respondText("Customer has been added successfully!", status = HttpStatusCode.Created)
        }

        delete("{id?}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            if (customerStorage.removeIf { it.id == id }) {
                call.respondText("Customer has been deleted successfully", status = HttpStatusCode.Accepted)
            } else {
                call.respondText("Not found!", status = HttpStatusCode.NotFound)
            }
        }
    }

}

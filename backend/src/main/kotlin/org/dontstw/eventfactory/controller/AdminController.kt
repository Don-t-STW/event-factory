package org.dontstw.eventfactory.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin")
class AdminController {

    @GetMapping("/hello")
    fun sayHello() = "hello admin"

    @GetMapping("/logout")
    fun logout(){}
}

package org.dontstw.eventfactory.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController{
    @GetMapping("/")
    fun app(): String {
        return "app"
    }
}
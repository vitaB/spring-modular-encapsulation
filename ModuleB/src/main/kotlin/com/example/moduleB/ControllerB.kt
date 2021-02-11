package com.example.moduleB

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/api/v1/moduleB")
class ControllerB internal constructor(private val service: ServiceB) {
    @PostMapping("/save")
    fun save(@RequestBody dto: DtoB): ResponseEntity<DtoB> {
        val entity = EntityB(lastName = dto.lastName)
        val saved = service.save(entity)
        return ResponseEntity.ok(DtoB(saved.id, saved.lastName))
    }

    @GetMapping("finById")
    fun findById(@RequestParam i: Long): ResponseEntity<DtoB?> {
        val entity = service.find(i)
        return if (entity != null)
            ResponseEntity.ok(DtoB(entity.id, entity.lastName))
        else
            ResponseEntity.notFound().build()
    }
}
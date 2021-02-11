package com.example.moduleA

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/api/v1/moduleA")
class ControllerA internal constructor(private val service: ServiceA) {
    @PostMapping("/save")
    fun save(@RequestBody dto: DtoA): ResponseEntity<DtoA> {
        val entity = EntityA(firstName = dto.firstName)
        val saved = service.save(entity)
        return ResponseEntity.ok(DtoA(saved.id, saved.firstName))
    }

    @GetMapping("finById")
    fun findById(@RequestParam i: Long): ResponseEntity<DtoA?> {
        val entity = service.find(i)
        return if (entity != null)
            ResponseEntity.ok(DtoA(entity.id, entity.firstName))
        else
            ResponseEntity.notFound().build()
    }
}
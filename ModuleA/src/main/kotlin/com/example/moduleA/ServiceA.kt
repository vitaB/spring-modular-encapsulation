package com.example.moduleA

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional("moduleATransactionManager")
internal class ServiceA(private val repo: RepoA) {
    fun save(e: EntityA): EntityA = repo.save(e)
    fun find(id: Long): EntityA? = repo.findByIdOrNull(id)
}
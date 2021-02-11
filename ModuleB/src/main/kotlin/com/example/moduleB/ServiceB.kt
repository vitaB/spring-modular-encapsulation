package com.example.moduleB

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional("moduleBTransactionManager")
internal class ServiceB(private val repo: RepoB) {
    fun save(e: EntityB): EntityB = repo.save(e)
    fun find(id: Long): EntityB? = repo.findByIdOrNull(id)
}
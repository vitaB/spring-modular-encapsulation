package com.example.moduleA

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
internal interface RepoA : CrudRepository<EntityA, Long>
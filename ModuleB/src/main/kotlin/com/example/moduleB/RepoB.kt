package com.example.moduleB

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
internal interface RepoB : CrudRepository<EntityB, Long>
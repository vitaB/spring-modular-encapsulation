package com.example.moduleB

import javax.persistence.*

@Entity
class EntityB internal constructor(@Column(name = "lastname")
              val lastName: String,
              @Id
              @GeneratedValue(strategy = GenerationType.IDENTITY)
              val id: Long = -1)
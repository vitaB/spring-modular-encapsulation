package com.example.moduleA

import javax.persistence.*

@Entity
class EntityA internal constructor(@Column(name = "firstname")
              val firstName: String,
              @Id
              @GeneratedValue(strategy = GenerationType.IDENTITY)
              val id: Long = 0)
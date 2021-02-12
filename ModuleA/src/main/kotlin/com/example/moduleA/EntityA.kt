package com.example.moduleA

import javax.persistence.*

@Entity
internal class EntityA(@Column(name = "firstname")
                       val firstName: String,
                       @Id
                       @GeneratedValue(strategy = GenerationType.IDENTITY)
                       val id: Long = 0)
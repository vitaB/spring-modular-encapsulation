package com.example.moduleB

import javax.persistence.*

@Entity
internal class EntityB(@Column(name = "lastname")
                       val lastName: String,
                       @Id
                       @GeneratedValue(strategy = GenerationType.IDENTITY)
                       val id: Long = -1)
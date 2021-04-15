package com.aqube.ram.remote.mappers

interface EntityMapper<M, E> {

    fun mapFromModel(model: M): E
}

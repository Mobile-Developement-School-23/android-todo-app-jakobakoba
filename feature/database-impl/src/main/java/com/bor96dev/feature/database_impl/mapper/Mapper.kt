package com.bor96dev.feature.database_impl.mapper

interface Mapper<T, R> {

    fun toDto(from: T): R

    fun toEntity(from: R): T

}
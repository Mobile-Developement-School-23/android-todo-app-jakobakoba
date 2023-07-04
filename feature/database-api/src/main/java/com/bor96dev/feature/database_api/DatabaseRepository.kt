package com.bor96dev.feature.database_api

interface DatabaseRepository {
    suspend fun addElement(name: String)

}
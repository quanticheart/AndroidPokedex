package com.quanticheart.repository.utils

interface Mapper<S, T> {
    fun map(source: S): T
}
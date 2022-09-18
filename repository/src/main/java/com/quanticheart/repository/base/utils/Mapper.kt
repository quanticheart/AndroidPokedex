package com.quanticheart.repository.base.utils

interface Mapper<S, T> {
    fun map(source: S): T
}
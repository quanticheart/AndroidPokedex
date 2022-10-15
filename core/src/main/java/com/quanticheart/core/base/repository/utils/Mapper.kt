package com.quanticheart.core.base.repository.utils

interface Mapper<S, T> {
    fun map(source: S): T
}
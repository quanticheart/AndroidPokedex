package com.quanticheart.domain.repository

import com.quanticheart.domain.model.remote.DashboardMenu

interface AppRepository {
    suspend fun getMinVersionApp(): Result<Int>
    suspend fun getDashboardMenu(): Result<DashboardMenu>
}

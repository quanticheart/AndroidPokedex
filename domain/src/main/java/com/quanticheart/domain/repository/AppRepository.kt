package com.quanticheart.domain.repository

import com.quanticheart.domain.model.remote.DashboardMenu
import com.quanticheart.domain.model.remote.TcgRemote

interface AppRepository {
    suspend fun getMinVersionApp(): Result<Int>
    suspend fun getDashboardMenu(): Result<DashboardMenu>
    suspend fun getTcg(): Result<TcgRemote>
}

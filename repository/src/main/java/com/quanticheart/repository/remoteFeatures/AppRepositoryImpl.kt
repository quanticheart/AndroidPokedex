package com.quanticheart.repository.remoteFeatures

import com.google.gson.Gson
import com.quanticheart.domain.model.remote.DashboardMenu
import com.quanticheart.domain.model.remote.TcgRemote
import com.quanticheart.domain.repository.AppRepository

class AppRepositoryImpl : AppRepository {
    override suspend fun getMinVersionApp(): Result<Int> {
        val minVersion =
            Gson().fromRemoteConfig(RemoteConfigKeys.MIN_VERSION_APP, Int::class.java) ?: 0
        return Result.success(minVersion)
    }

    override suspend fun getDashboardMenu(): Result<DashboardMenu> {
        val dashboardMenu =
            Gson().fromRemoteConfig(RemoteConfigKeys.MENU_DASHBOARD, DashboardMenu::class.java)

        return if (dashboardMenu == null)
            Result.failure(Exception("Não foi possível encontrar o menu"))
        else
            Result.success(dashboardMenu)
    }

    override suspend fun getTcg(): Result<TcgRemote> {
        val tcg =
            Gson().fromRemoteConfig(RemoteConfigKeys.TCG, TcgRemote::class.java)

        return if (tcg == null)
            Result.failure(Exception("Não foi possível encontrar o menu"))
        else
            Result.success(tcg)
    }
}

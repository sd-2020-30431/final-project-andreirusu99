package com.shopping.baseproject.data.api

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject

class ShoppingProvider : KoinComponent {

    private val retrofit by inject<ShoppingAPI>()

    suspend fun getAllAisles() = withContext(Dispatchers.IO) {
        return@withContext try {
            Result.Success(retrofit.getAllAisles())
        } catch (ex: Exception) {
            Result.Error(ex)
        }
    }

    suspend fun getShopMap(id: Int) = withContext(Dispatchers.IO) {
        return@withContext try {
            Result.Success(retrofit.getShopMap(id))
        } catch (ex: Exception) {
            Result.Error(ex)
        }
    }

    suspend fun getStores() = withContext(Dispatchers.IO) {
        return@withContext try {
            Result.Success(retrofit.getStores())
        } catch (ex: Exception) {
            Result.Error(ex)
        }
    }

    suspend fun getPath(id: Int, startId: Int, points: List<Aisle>) =
        withContext(Dispatchers.IO) {
            return@withContext try {
                Result.Success(retrofit.getPath(id, startId, points))
            } catch (ex: Exception) {
                Result.Error(ex)
            }
        }
}

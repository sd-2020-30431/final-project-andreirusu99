package com.shopping.baseproject.data.api

import com.shopping.baseproject.data.models.UserProfile
import java.lang.Exception
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject

class AuthProvider : KoinComponent {

    private val retrofit by inject<AuthAPI>()

    suspend fun login(user: UserProfile) = withContext(Dispatchers.IO) {
        return@withContext try {
            Result.Success(retrofit.login(user))
        } catch (ex: Exception) {
            Result.Error(ex)
        }
    }

    suspend fun register(user: UserProfile) = withContext(Dispatchers.IO) {
        return@withContext try {
            Result.Success(retrofit.register(user))
        } catch (ex: Exception) {
            Result.Error(ex)
        }
    }
}

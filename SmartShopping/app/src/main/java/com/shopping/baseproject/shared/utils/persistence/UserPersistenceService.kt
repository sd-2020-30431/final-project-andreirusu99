package com.shopping.baseproject.shared.utils.persistence

import com.orhanobut.hawk.Hawk
import com.shopping.baseproject.data.models.UserProfile

interface UserPersistenceService {

    var userProfile: UserProfile?
        get() = Hawk.get(HawkKeys.USER_PROFILE)
        set(user) {
            Hawk.put(HawkKeys.USER_PROFILE, user)
        }

    fun destroyPrefs() {
        Hawk.delete(HawkKeys.USER_PROFILE)
    }

    fun UserProfile.persist() {
        userProfile = this
    }
}

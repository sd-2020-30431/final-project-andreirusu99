package com.andrei.smartshopping.utils.persistence

import com.orhanobut.hawk.Hawk
import com.andrei.smartshopping.data.models.UserProfile
import com.andrei.smartshopping.utils.persistence.HawkKeys

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

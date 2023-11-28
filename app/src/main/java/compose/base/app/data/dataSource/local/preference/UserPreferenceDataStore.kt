package compose.base.app.data.dataSource.local.preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferencesDataStore @Inject constructor(
    private val userPreferencesDataStore: DataStore<Preferences>
) {
    private val _userNameKey = stringPreferencesKey("username")

    val isLoggedIn: Flow<Boolean> = userPreferencesDataStore.data.map {
        !it[_userNameKey].isNullOrEmpty()
    }

    suspend fun login(userName: String) {
        userPreferencesDataStore.edit {
            it[_userNameKey] = userName
        }
    }

    suspend fun putUserDetails(userName: String) {
        userPreferencesDataStore.edit {
            it[_userNameKey] = userName
        }
    }

    val userDetails: Flow<String?> = userPreferencesDataStore.data.map {
        it[_userNameKey]
    }

    fun logout() {
        runBlocking {
            userPreferencesDataStore.edit {
                it.clear()
            }
        }
    }
}
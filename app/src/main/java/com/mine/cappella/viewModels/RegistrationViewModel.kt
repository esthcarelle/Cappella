package com.mine.cappella.viewModels

import android.content.ContentValues.TAG
import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.lifecycle.viewModelScope
import com.mine.cappella.network.RetrofitInstance
import com.mine.cappella.screens.RegistrationUiState
import com.mine.cappella.screens.UiState
import com.mine.cappella.screens.UiText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class RegistrationViewModel : BaseViewModel<RegistrationUiState>() {
    private val _uiState = MutableStateFlow(
        RegistrationUiState()
    )
    val uiState = _uiState.asStateFlow()

    private val apiService = RetrofitInstance.api

    init {
        getInitialProfile()
    }

    fun onNewName(name: String) {
        _uiState.update { registrationUiState ->
            registrationUiState.copy(
                name = name.trim()
            )
        }
    }

    fun onNewDate(date: String) {
        _uiState.update { registrationUiState ->
            registrationUiState.copy(
                date = date.trim()
            )
        }
    }

    fun onNewImage(image: String) {
        _uiState.update { registrationUiState ->
            registrationUiState.copy(
                imageProfile = image.trim()
            )
        }
    }

    fun onNewGenderSelected(gender: String) {
        _uiState.update { registrationUiState ->
            registrationUiState.copy(
                gender = gender.trim()
            )
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    private fun getInitialProfile() {
        if (job?.isActive == true)
            return
        job = viewModelScope.launch {
            _uiStateFlow.update {
                UiState.Loading()
            }
            try {

                val response = apiService.getUserProfile("e71b3147-01f5-42bb")
                if (response != null) {
                    _uiStateFlow.update {
                        UiState.Success(
                            RegistrationUiState(
                                name = response.name,
                                date = response.dob,
                                gender = response.gender,
                                imageProfile = response.profile_picture
                            )
                        )
                    }
                }
            } catch (e: HttpException) {
                Log.e(TAG, "getInitialProfile: " + e.message)
                _uiStateFlow.update {

                    UiState.Error(
                        error = e.message?.let { it1 -> UiText.DynamicString(it1) }
                    )
                }
            }

        }
    }

    fun updateProfile() {
        if (job?.isActive == true)
            return
        job = viewModelScope.launch {
            if (uiState.value.name.isEmpty()) {
                _uiStateFlow.update {
                    UiState.Error(error = UiText.DynamicString("Name can't be empty"))
                }
            }

            try {
                val nameBody: RequestBody =
                    uiState.value.name.toRequestBody("text/plain".toMediaType())
                val genderBody: RequestBody =
                    uiState.value.gender.toRequestBody("text/plain".toMediaType())
                val date: RequestBody = uiState.value.date.toRequestBody("text/plain".toMediaType())

//                val fileData: RequestBody? = GalleryCameraUtility.getImageRequestBody(imageFile)
//
//                val response = apiService.updateProfile(id = uiState.value.id,gender = uiState.value.gender,name = uiState.value.name,dob = uiState.value.date)

            } catch (e: Exception) {


            }
        }
    }
}
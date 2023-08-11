package com.test.feature_search.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.feature_search.domain.Picture
import com.test.feature_search.utils.toPictures
import com.test.repository_search.domain.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
) : ViewModel() {

    private val _searchPhotos: MutableStateFlow<List<Picture>> =
        MutableStateFlow(listOf())
    val searchPhotos: StateFlow<List<Picture>> = _searchPhotos.asStateFlow()

    private val _partRequests: MutableStateFlow<List<String>> =
        MutableStateFlow<List<String>>(listOf())
    val partRequests: StateFlow<List<String>> = _partRequests.asStateFlow()


    fun getPhotos(request: String) {
        viewModelScope.launch(Dispatchers.IO) {
            searchUseCase(request).onSuccess { photos ->
                val pictures = photos.toPictures()
                _searchPhotos.emit(pictures)
                saveRequest(request)
                val requestParts = searchUseCase.getRequestParts(request)
                    .map { parts ->
                        if (parts.isSuccess) parts.getOrThrow() else emptyList()
                    }
                    .catch { e -> Log.d("ERROR_:", e.message.toString()) }
                    .flowOn(Dispatchers.IO).collect{
                        _partRequests.emit(it)
                    }
            }.onFailure { e ->
                Log.d("ERROR_:", e.message.toString())
            }
        }
    }

    private fun saveRequest(request: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                searchUseCase.saveRequestPart(request)
            }catch (e:Exception){
                Log.d("ERROR_:", e.message.toString())
            }
        }
    }
}

package com.shahbozbek.museumwarehouse.ui.screens

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahbozbek.museumwarehouse.data.local.Items
import com.shahbozbek.museumwarehouse.data.repository.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: RepositoryImpl
) : ViewModel() {

    private val _items = MutableStateFlow<List<Items>>(emptyList())
    val items: StateFlow<List<Items>> get() = _items

    private val _imageUri = MutableStateFlow<Uri?>(null)
    val imageUri: StateFlow<Uri?> get() = _imageUri

    fun setImageUri(uri: Uri?) {
        _imageUri.value = uri
    }

    fun saveImage(bitmap: Bitmap, context: Context): String {
        return saveImageToInternalStorage(bitmap, context)
    }

    fun insert(item: Items) {
        viewModelScope.launch {
            repository.insert(item)
            _items.value = repository.getAll()
        }
    }

    fun update(item: Items) {
        viewModelScope.launch {
            repository.update(item)
            _items.value = repository.getAll()
        }
    }

    fun getById(id: Int) {
        viewModelScope.launch {
            repository.getById(id)
        }
    }

    fun getAll() {
        viewModelScope.launch {
            _items.value = repository.getAll()
        }
    }
}
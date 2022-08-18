package com.kudzai.mutswairo.dogpics.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kudzai.mutswairo.dogpics.data.remote.Resource
import com.kudzai.mutswairo.dogpics.domain.usecase.GetBreedsListUseCase
import com.kudzai.mutswairo.dogpics.domain.usecase.GetDogsByBreedUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class DogsViewModel @Inject constructor(
    private val getDogsByBreedUseCase: GetDogsByBreedUseCase,
    private val getBreedsListUseCase: GetBreedsListUseCase
) : ViewModel() {

    private var _dogBreeds = MutableLiveData<List<String>>()
    public val dogBreeds = _dogBreeds

    private var _dogPics = MutableLiveData<Resource<List<String>>>()
    public val dogPics = _dogPics

    init {
        getDogBreeds()
    }

    fun getDogBreeds() {
        viewModelScope.launch {
            getBreedsListUseCase.invoke().collect {
                _dogBreeds.postValue(it)
            }
        }
    }

    fun getDogPics(breed: String) {
        viewModelScope.launch {
            getDogsByBreedUseCase.invoke(breed).collect {
                _dogPics.postValue(it)
            }
        }
    }
}

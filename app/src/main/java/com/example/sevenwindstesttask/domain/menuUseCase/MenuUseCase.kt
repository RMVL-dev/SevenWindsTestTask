package com.example.sevenwindstesttask.domain.menuUseCase

import com.example.sevenwindstesttask.data.repository.Repository
import com.example.sevenwindstesttask.data.responses.coffee.Coffee
import javax.inject.Inject

class MenuUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun getMenu(url:String):List<Coffee> =
        repository.getMenu(url = url)

}
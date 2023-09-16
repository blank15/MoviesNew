package com.blank.movie.helper

/**
 * Abstraction form mapper
 */
interface DomainMapper<DataModel, DomainModel> {

    fun mapToDomainModel(dataModel: DataModel): DomainModel

}
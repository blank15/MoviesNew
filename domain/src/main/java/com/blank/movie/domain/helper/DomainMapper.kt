package com.blank.movie.domain.helper

/**
 * Abstraction form mapper
 */
interface DomainMapper<DataModel, DomainModel> {

    fun mapToDomainModel(dataModel: DataModel): DomainModel

}
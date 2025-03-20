package br.com.victorcs.querosermb.domain.mapper

interface DomainMapper<in T, out Model> {
    fun toDomain(from: T): Model
    fun toDomain(from: List<T>): List<Model> = from.map { toDomain(it) }
}
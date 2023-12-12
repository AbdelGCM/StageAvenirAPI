package com.crosemont.dti.g26.stageavenirapi.DAO

interface DAO<T> {
    fun chercherTous(): List<T>
    fun chercherParCode(code: Int): T?
    fun ajouter(element: T): T?

    fun modifier(element: T): T?
    fun supprimer(element: T): Boolean
}
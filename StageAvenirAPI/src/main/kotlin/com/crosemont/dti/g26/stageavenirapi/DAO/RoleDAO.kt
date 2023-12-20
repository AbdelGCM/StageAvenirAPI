package com.crosemont.dti.g26.stageavenirapi.DAO

import com.crosemont.dti.g26.stageavenirapi.Modèle.Role

interface RoleDAO:DAO<Role> {

    override fun chercherParCode(code: Int): Role?
}
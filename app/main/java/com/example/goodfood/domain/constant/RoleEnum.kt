package com.example.goodfood.domain.constant

enum class RoleEnum(val role: String) {
    USER("client"),
    DELIVERY_MAN("deliveryMan"),
    RESTAURANT("restaurant"),
    ADMIN("admin"),
    SUPER_ADMIN("superAdmin");

    override fun toString(): String {
        return role
    }
}
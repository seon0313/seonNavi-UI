package com.seon06.seon_navi

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
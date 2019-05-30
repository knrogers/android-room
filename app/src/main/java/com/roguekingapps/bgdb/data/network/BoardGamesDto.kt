package com.roguekingapps.bgdb.data.network

import com.tickaroo.tikxml.annotation.Attribute
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Path
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "items")
class BoardGamesDto(
    @Element
    val boardGameDtos: List<BoardGameDto>
)

@Xml(name = "item")
data class BoardGameDto(
    @Attribute val rank: String,
    @Attribute val id: String,
    @Path("name") @Attribute(name = "value") val name: String,
    @Path("yearpublished") @Attribute(name = "value") val year: String?,
    @Path("thumbnail") @Attribute(name = "value") val thumbnailUrl: String?
)
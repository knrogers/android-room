package com.roguekingapps.bgdb.boardgame.network

import com.tickaroo.tikxml.annotation.Attribute
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Path
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "items")
class BoardGames(
    @Element
    val boardGames: List<BoardGame>
)

@Xml(name = "item")
data class BoardGame(
    @Attribute val id: String,
    @Attribute val rank: String,
    @Path("name") @Attribute(name = "value") val name: String,
    @Path("thumbnail") @Attribute(name = "value") val thumbnailUrl: String,
    @Path("yearpublished") @Attribute(name = "value") val year: String
)
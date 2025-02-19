package com.sstudio.submissionbajetpackpro.core.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class TvDetailEmbed (
    @Embedded
    var tv: TvEntity,

    @Relation(parentColumn = "id_tv", entityColumn = "id_tv")
    var detailEntity: TvDetailEntity?
)
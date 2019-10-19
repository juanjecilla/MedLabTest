package com.medlab.medlabtest.data.model.messages

import com.medlab.medlabtest.data.model.MovieItem

data class FavUpdatedMessage(
    var uid: String,
    var item: MovieItem
)

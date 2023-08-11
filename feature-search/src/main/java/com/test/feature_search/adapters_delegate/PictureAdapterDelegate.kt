package com.test.feature_search.adapters_delegate

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.squareup.picasso.Picasso
import com.test.feature_home.databinding.PictureSearchItemBinding
import com.test.feature_search.domain.Picture

internal fun getPictureAdapterDelegate() =
    adapterDelegateViewBinding<Picture, Picture, PictureSearchItemBinding>({ layoutInflater, parent ->
        PictureSearchItemBinding.inflate(layoutInflater, parent, false)
    }) {

        bind {
            if (item.image.isNotEmpty())
                Picasso.get().load(item.image).into(binding.image)
        }

    }

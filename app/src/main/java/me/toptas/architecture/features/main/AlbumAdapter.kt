package me.toptas.architecture.features.main

import me.toptas.architecture.R
import me.toptas.architecture.base.BaseListAdapter
import me.toptas.architecture.common.model.Album
import me.toptas.architecture.databinding.RowAlbumBinding
import me.toptas.architecture.BR

class AlbumAdapter : BaseListAdapter<Album, RowAlbumBinding>() {

    override fun layoutResource() = R.layout.row_album

    override fun bindingVariableId() = BR.album

}
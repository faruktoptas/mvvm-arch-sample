package me.toptas.architecture.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseListAdapter<T, in DB : ViewDataBinding> :
    RecyclerView.Adapter<BaseListAdapter<T, DB>.BaseViewHolder>() {

    private var items: List<T>? = null

    var onItemClick: (T) -> Unit = {}

    var onItemPositionClick: (Int) -> Unit = {}

    abstract fun layoutResource(): Int

    abstract fun bindingVariableId(): Int

    // TODO: DiffUtil can be used here
    fun setItems(newList: List<T>?) {
        items = newList
        notifyDataSetChanged()
    }

    fun getItems() = items

    open fun bind(holder: BaseViewHolder, item: T?) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<DB>(layoutInflater, layoutResource(), parent, false)

        return BaseViewHolder(binding, bindingVariableId())
    }


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item =
            if (!items.isNullOrEmpty() && position < items!!.size) items!![position] else null
        holder.bind(item)
        holder.itemView.setOnClickListener {
            item?.let { onItemClick(it) }
            onItemPositionClick(position)
        }
        bind(holder, item)
    }

    override fun getItemCount() = items?.size ?: 0


    inner class BaseViewHolder(val binding: ViewDataBinding, private val variableId: Int) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: T?) {
            binding.setVariable(variableId, item)
            binding.executePendingBindings()
        }
    }
}
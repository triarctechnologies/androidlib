package com.triarc.android.lib.support.view.base

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.triarc.android.lib.logger.LoggerImpl
import com.triarc.android.lib.logger.intf.Logger
import com.triarc.android.lib.support.view.intf.ItemClickListener

/**
 * Created by Devanshu Verma on 20 May, 2020
 */
abstract class AbstractBaseRecyclerListAdapter<ViewHolder : AbstractBaseViewHolder>(protected var context: Context? = null) : RecyclerView.Adapter<ViewHolder>(), View.OnClickListener {

    protected val logger: Logger = LoggerImpl.getLogger(this)

    private var itemClickListener: ItemClickListener? = null

    companion object ViewType {
        const val ITEM = 1
        const val FOOTER = 2
    }

    abstract fun getViewHolder(parent: ViewGroup, viewType: Int): ViewHolder

    abstract fun updateList(list: List<Any?>?)

    abstract fun getItem(position: Int): Any?

    abstract override fun getItemCount(): Int

    abstract override fun getItemViewType(position: Int): Int

    abstract override fun onBindViewHolder(viewHolder: ViewHolder, position: Int)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return getViewHolder(parent, viewType)
    }

    override fun onClick(view: View?) {
        itemClickListener?.onItemClick(view, view?.tag)
    }

    fun setOnItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}

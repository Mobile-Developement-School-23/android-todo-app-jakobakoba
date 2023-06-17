package com.bor96dev.feature.items_impl

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.bor96dev.feature.items_impl.presentation.adapter.ItemsAdapter

internal class BaseMainSwipeCallback(
    private val context: Context,
    private val adapter: ItemsAdapter
) :
    ItemTouchHelper.Callback() {

    var backgroundLeft = ColorDrawable()
    var backgroundRight = ColorDrawable()
    var iconLeft = android.R.drawable.btn_star
    var iconRight = android.R.drawable.ic_menu_upload

    init {
        backgroundLeft.color = ContextCompat.getColor(context, android.R.color.holo_green_light)
        backgroundRight.color = ContextCompat.getColor(context, android.R.color.holo_red_light)
    }


    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        val itemView = viewHolder.itemView

        if (dX > 0) drawViewHolder(c, itemView, dX, itemView.left)
        if (dX < 0) drawViewHolder(c, itemView, dX, itemView.right)


        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }


    private fun drawViewHolder(c: Canvas, itemView: View, dX: Float, itemViewDirection: Int) {
        drawBackground(c, dX, itemViewDirection, itemView)
        drawIcon(c, itemViewDirection, itemView)
    }

    private fun drawBackground(c: Canvas, dX: Float, itemViewDirection: Int, itemView: View) {
        var background = ColorDrawable()

        when (itemViewDirection) {
            itemView.left -> {
                background = backgroundLeft
                background.setBounds(
                    itemViewDirection,
                    itemView.top,
                    itemViewDirection + dX.toInt(),
                    itemView.bottom
                )
            }
            itemView.right -> {
                background = backgroundRight
                background.setBounds(
                    itemViewDirection + dX.toInt(),
                    itemView.top,
                    itemViewDirection,
                    itemView.bottom
                )
            }
        }

        background.draw(c)
    }

    private fun drawIcon(c: Canvas, itemViewDirection: Int, itemView: View) {
        var icon: Drawable? = null

        val csl = AppCompatResources.getColorStateList(context, android.R.color.white)

        when (itemViewDirection) {
            itemView.left -> {
                ContextCompat.getDrawable(context, iconLeft)?.let {
                    icon = it
                }
            }
            itemView.right -> {
                ContextCompat.getDrawable(context, iconRight)?.let {
                    icon = it
                }
            }
        }

        icon?.let {
            DrawableCompat.setTintList(it, csl)
        }

        val intrinsicWidth = icon?.intrinsicWidth
        val intrinsicHeight = icon?.intrinsicHeight


        val itemHeight = itemView.bottom - itemView.top
        val deleteIconTop = itemView.top + (itemHeight - intrinsicHeight!!) / 2
        val deleteIconMargin = (itemHeight - intrinsicHeight!!) / 2
        val deleteIconLeft =
            if (itemViewDirection == itemView.right) itemViewDirection - deleteIconMargin - intrinsicWidth!!
            else itemViewDirection + deleteIconMargin
        val deleteIconRight =
            if (itemViewDirection == itemView.right) itemViewDirection - deleteIconMargin
            else itemViewDirection + deleteIconMargin + intrinsicWidth!!
        val deleteIconBottom = deleteIconTop + intrinsicHeight


        icon?.setBounds(deleteIconLeft, deleteIconTop, deleteIconRight, deleteIconBottom)
        icon?.draw(c)
    }

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return makeMovementFlags(0,
//            ItemTouchHelper.RIGHT or
                        ItemTouchHelper.LEFT
        )
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        adapter.removeAt(viewHolder.adapterPosition)
    }
}
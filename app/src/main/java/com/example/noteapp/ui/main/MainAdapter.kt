package com.example.noteapp.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.R
import com.example.noteapp.data.model.NoteEntity
import com.example.noteapp.databinding.ItemNotesBinding
import com.example.noteapp.utils.*
import javax.inject.Inject

class MainAdapter @Inject constructor() : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    lateinit var binding: ItemNotesBinding

    lateinit var context: Context

    private var noteList = emptyList<NoteEntity>()

    inner class MainViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        fun onBind(oneItem: NoteEntity) {

            binding.descTxt.text = oneItem.desc.toString()
            binding.titleTxt.text = oneItem.title.toString()



            when (oneItem.priority) {

                HIGH -> {

                    binding.priorityColor.setBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.red
                        )
                    )


                }

                NORMAL -> {
                    binding.priorityColor.setBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.yellow
                        )
                    )

                }

                LOW -> {
                    binding.priorityColor.setBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.aqua
                        )
                    )
                }


            }


            when (oneItem.category) {

                WORK -> {
                    binding.categoryImg.setImageResource(R.drawable.work)
                }
                HOME -> {
                    binding.categoryImg.setImageResource(R.drawable.home)
                }
                HEALTH -> {
                    binding.categoryImg.setImageResource(R.drawable.healthcare)
                }
                EDUCATION -> {
                    binding.categoryImg.setImageResource(R.drawable.education)
                }

            }


            binding.menuImg.setOnClickListener {


                val popUp = PopupMenu(context, it)
                popUp.menuInflater.inflate(R.menu.menu_popup, popUp.menu)

                popUp.show()

                popUp.setOnMenuItemClickListener {

                    when (it.itemId) {


                        R.id.itemEdit -> {

                         onItemClick?.let {
                             it(oneItem, EDIT)
                         }


                        }

                        R.id.itemDelete -> {

onItemClick?.let {
    it(oneItem, DELETE)
}

                        }


                    }


                    return@setOnMenuItemClickListener true
                }


            }


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        context = parent.context
        binding = ItemNotesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.onBind(noteList[position])
    }

    override fun getItemCount() = noteList.size


    private var onItemClick: ((NoteEntity, String) -> Unit)? = null

    fun setOnItemClickListener(listener: (NoteEntity, String) -> Unit) {

        onItemClick = listener


    }


    fun setData(data: List<NoteEntity>) {

        val noteDiffUtil = MainDiffUtils(noteList, data)

        val util = DiffUtil.calculateDiff(noteDiffUtil)

        noteList = data

        util.dispatchUpdatesTo(this)


    }

    class MainDiffUtils(
        private val oldItem: List<NoteEntity>,
        private val newItem: List<NoteEntity>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldItem.size
        }

        override fun getNewListSize(): Int {
            return newItem.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem[oldItemPosition] === newItem[newItemPosition]

        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem[oldItemPosition] === newItem[newItemPosition]
        }


    }


}
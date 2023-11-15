package com.example.noteapp.ui.add

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.noteapp.data.model.NoteEntity
import com.example.noteapp.data.repository.add.AddNoteRepository
import com.example.noteapp.databinding.FragmentNoteBinding
import com.example.noteapp.utils.*
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class NoteFragment : BottomSheetDialogFragment(), NoteContract.View {
    private lateinit var binding: FragmentNoteBinding


    @Inject
    lateinit var entity: NoteEntity

    @Inject
    lateinit var repository: AddNoteRepository

    @Inject
    lateinit var presenter: NotePresenter


    private lateinit var categoryList: Array<String>


    private lateinit var priorityList: Array<String>

    private var category = ""

    private var priority = ""

  private var noteId=0

    private var type=""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteBinding.inflate(layoutInflater)
        return binding.root


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        noteId = arguments?.getInt(BUNDLE_ID) ?: 0

        //Type
        type = if (noteId > 0) {
            EDIT
        } else {
            NEW
        }





        binding.imgClose.setOnClickListener {

            this@NoteFragment.dismiss()


        }

        categorySpinnerItems()

        prioritySpinnerItems()

        if (type== EDIT) {

            presenter.detailNote(noteId)

        }


        binding.saveNote.setOnClickListener {

            val desc = binding.desEdt.text.toString()

            val title = binding.edtTitle.text.toString()

           entity.id = noteId
            entity.desc = desc
            entity.title = title
            entity.priority = priority
            entity.category = category





            if (type== NEW) {

                presenter.saveNote(entity)
            }else{


               presenter.updateNote(entity)

            }




        }








}





    private fun categorySpinnerItems() {

        categoryList = arrayOf(WORK, HOME, EDUCATION, HEALTH)

        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categoryList)


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)


        binding.categoriesSpinner.adapter = adapter

        binding.categoriesSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                    category = categoryList[position]

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }


            }


    }



    private fun prioritySpinnerItems() {

        priorityList = arrayOf(HIGH, NORMAL, LOW)

        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, priorityList)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)

        binding.prioritySpinner.adapter = adapter

        binding.prioritySpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                    priority = priorityList[position]

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }


            }


    }


    override fun onStop() {
        super.onStop()

        presenter.onStop()


    }

    override fun close() {
        this.dismiss()
    }

    override fun loadNote(note: NoteEntity) {
        if (this.isAdded) {
            requireActivity().runOnUiThread {
                binding.edtTitle.setText(note.title)
                binding.desEdt.setText(note.desc)


                binding.prioritySpinner.setSelection(getIndexed(priorityList,note.priority))

                binding.categoriesSpinner.setSelection(getIndexed(categoryList,note.category))

            }
        }


    }



    fun getIndexed(list:Array<String>,item:String):Int{

        var index=0

        for (i in list.indices){

            if (list[i]==item){

                index=i
                break


            }


        }

        return index


    }
}








package com.example.noteapp.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.noteapp.R
import com.example.noteapp.data.model.NoteEntity
import com.example.noteapp.data.repository.main.MainRepository
import com.example.noteapp.databinding.ActivityMainBinding
import com.example.noteapp.ui.add.NoteFragment
import com.example.noteapp.utils.*
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainContract.View {
    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var adapter: MainAdapter

    @Inject
    lateinit var repo: MainRepository


    @Inject
    lateinit var presenter: MainPresenter

    private var selectedItem = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        setSupportActionBar(binding.noteToolbar)









        binding.btnAddNote.setOnClickListener {

            NoteFragment().show(supportFragmentManager, NoteFragment().tag)


        }


        presenter.loadAllNotes()



        adapter.setOnItemClickListener { noteEntity, state ->


            when (state) {


                EDIT -> {

                    val noteFragment = NoteFragment()

                    val bundle = Bundle()

                    bundle.putInt(BUNDLE_ID, noteEntity.id)

                    noteFragment.arguments = bundle

                    noteFragment.show(supportFragmentManager, NoteFragment().tag)


                }

                DELETE -> {


                    val entity = NoteEntity(
                        noteEntity.id,
                        noteEntity.title,
                        noteEntity.category,
                        noteEntity.desc,
                        noteEntity.priority
                    )

                    presenter.deleteNote(entity)


                }


            }


        }


        binding.noteToolbar.setOnMenuItemClickListener {

            when (it.itemId) {

                R.id.actionFilter -> {


                    getFilterNoteByPriority()
                    return@setOnMenuItemClickListener true

                }
                else -> {

                    return@setOnMenuItemClickListener false
                }


            }


        }


    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {


        menuInflater.inflate(R.menu.menu_toolbar,menu)

        val search=menu.findItem(R.id.actionSearch)

        val searchView=search.actionView as androidx.appcompat.widget.SearchView

        searchView.queryHint="Search Here ..."

        searchView.setOnQueryTextListener(object:androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                presenter.searchNotes(newText!!)


return true           }


        })





        return super.onCreateOptionsMenu(menu)
    }




    override fun showEmptyList() {
        binding.emptyLay.visibility = View.VISIBLE
        binding.recyclerNoteList.visibility = View.GONE


    }

    override fun showAllNotes(notes: List<NoteEntity>) {

        binding.emptyLay.visibility = View.GONE
        binding.recyclerNoteList.visibility = View.VISIBLE


        adapter.setData(notes)


        binding.recyclerNoteList.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        binding.recyclerNoteList.adapter = adapter


    }


    override fun deleteMessage() {
        Snackbar.make(binding.root, " Item Deleted", Snackbar.LENGTH_SHORT).show()

    }


    override fun onStop() {
        super.onStop()

        presenter.onStop()

    }


    private fun getFilterNoteByPriority() {

        val builder = AlertDialog.Builder(this)
        val priorityList = arrayOf(ALL, HIGH, NORMAL, LOW)
        builder.setSingleChoiceItems(priorityList, selectedItem) { dialog, item ->

            when (item) {

                0 -> {
                    presenter.loadAllNotes()
                }
                in 1..3 -> {

                    presenter.filterNoteByPriority(
                        priorityList[item]
                    )

                }


            }

            selectedItem = item
            dialog.dismiss()


        }

        val dialog: AlertDialog = builder.create()
        dialog.show()


    }


}
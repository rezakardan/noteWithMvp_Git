package com.example.noteapp.ui.main

import com.example.noteapp.base.BasePresenterImpl
import com.example.noteapp.data.model.NoteEntity
import com.example.noteapp.data.repository.main.MainRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val mainRepo: MainRepository,
    private val view: MainContract.View
) : MainContract.Presenter, BasePresenterImpl() {
    override fun loadAllNotes() {

        disposable = mainRepo.getAllNotes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {

                if (it.isEmpty()) {

                    view.showEmptyList()
                } else {

                    view.showAllNotes(it)
                }


            }


    }

    override fun deleteNote(entity: NoteEntity) {
        disposable = mainRepo.deleteNote(entity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view.deleteMessage()
            }
    }

    override fun filterNoteByPriority(priority: String) {
        disposable=mainRepo.filterByPriority(priority)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{

                if (it.isEmpty()){

                    view.showEmptyList()

                }else{

                    view.showAllNotes(it)
                }

            }
    }

    override fun searchNotes(searching: String) {
        disposable=mainRepo.searchNotes(searching)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{

                if (it.isNotEmpty()){

                    view.showAllNotes(it)
                }else{
                    view.showEmptyList()
                }

            }
    }


}
package com.example.noteapp.ui.add

import com.example.noteapp.base.BasePresenterImpl
import com.example.noteapp.data.model.NoteEntity
import com.example.noteapp.data.repository.add.AddNoteRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class NotePresenter @Inject constructor(
    private val noteRepo: AddNoteRepository,
    private val view: NoteContract.View
) : NoteContract.Presenter, BasePresenterImpl() {
    override fun saveNote(entity: NoteEntity) {
        disposable = noteRepo.saveNote(entity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {

                view.close()


            }

    }

    override fun detailNote(id: Int) {
        disposable=noteRepo.detailNote(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{

                view.loadNote(it)

            }
    }

    override fun updateNote(entity: NoteEntity) {
        disposable=noteRepo.updateNote(entity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{

                view.close()
            }
    }


}
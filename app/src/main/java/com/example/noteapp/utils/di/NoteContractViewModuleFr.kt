package com.example.noteapp.utils.di

import androidx.fragment.app.Fragment
import com.example.noteapp.ui.add.NoteContract
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
class NoteContractViewModuleFr {



    @Provides
    fun addNote(fragment:Fragment): NoteContract.View{

        return fragment as NoteContract.View


    }






}
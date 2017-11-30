package dev.olog.presentation.dialog_add_favorite.di

import dagger.Subcomponent
import dagger.android.AndroidInjector
import dev.olog.presentation.dagger.PerFragment
import dev.olog.presentation.dialog_add_favorite.AddFavoriteDialog

@Subcomponent(modules = arrayOf(
        AddFavoriteDialogModule::class
))
@PerFragment
interface AddFavoriteDialogSubComponent : AndroidInjector<AddFavoriteDialog> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<AddFavoriteDialog>() {

        abstract fun module(module: AddFavoriteDialogModule): Builder

        override fun seedInstance(instance: AddFavoriteDialog) {
            module(AddFavoriteDialogModule(instance))
        }
    }

}
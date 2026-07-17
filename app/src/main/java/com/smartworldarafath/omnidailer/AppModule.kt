package com.smartworldarafath.omnidailer

import androidx.room.Room
import com.smartworldarafath.omnidailer.modal.db.OmniDatabase
import com.smartworldarafath.omnidailer.controller.BackupViewModel
import com.smartworldarafath.omnidailer.controller.CallLogViewModel
import com.smartworldarafath.omnidailer.controller.ContactsViewModel
import com.smartworldarafath.omnidailer.modal.`interface`.ICallLogRepository
import com.smartworldarafath.omnidailer.modal.`interface`.IContactsRepository
import com.smartworldarafath.omnidailer.modal.repository.CallLogRepository
import com.smartworldarafath.omnidailer.modal.repository.ContactsRepository
import com.smartworldarafath.omnidailer.controller.util.PreferenceManager
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            OmniDatabase::class.java,
            "omni_database"
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<OmniDatabase>().privateContactDao() }

    single<IContactsRepository> {
        ContactsRepository(androidContext(), get())
    }
    single<ICallLogRepository> {
        CallLogRepository(androidContext().contentResolver, androidContext(), get())
    }
    single {
        PreferenceManager(androidContext())
    }
    viewModel { ContactsViewModel(get(), get()) }
    viewModel { CallLogViewModel(get(), androidContext().contentResolver) }
    viewModel { BackupViewModel(get(), get()) }
}
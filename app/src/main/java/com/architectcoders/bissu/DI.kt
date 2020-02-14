package com.architectcoders.bissu

import BookListFragment
import com.architectcoders.bissu.data.database.LocalDatabase
import com.architectcoders.bissu.data.database.Prefs
import com.architectcoders.bissu.data.database.book.BookDataSource
import com.architectcoders.bissu.data.database.login.LoginDataSource
import com.architectcoders.bissu.data.database.observation.ObservationDataSource
import com.architectcoders.bissu.data.server.book.BookDatasource
import com.architectcoders.bissu.data.server.login.LoginDatasource
import com.architectcoders.bissu.data.server.observation.ObservationDatasource
import com.architectcoders.bissu.ui.book.BookDetailFragment
import com.architectcoders.bissu.ui.book.BookDetailViewModel
import com.architectcoders.bissu.ui.home.bookList.BookListViewModel
import com.architectcoders.bissu.ui.home.myObservations.MyObservationsFragment
import com.architectcoders.bissu.ui.home.myObservations.MyObservationsViewModel
import com.architectcoders.bissu.ui.home.profile.ProfileViewModel
import com.architectcoders.bissu.ui.home.profile.fragments.ProfileFragment
import com.architectcoders.bissu.ui.login.LoginCreateAccountViewModel
import com.architectcoders.bissu.ui.login.LoginViewModel
import com.architectcoders.bissu.ui.login.fragments.CreateAccountFragment
import com.architectcoders.bissu.ui.login.fragments.LoginFragment
import com.architectcoders.bissu.ui.observation.ObservationFragment
import com.architectcoders.bissu.ui.observation.ObservationViewModel
import com.architectcoders.data.repository.BookRepository
import com.architectcoders.data.repository.ObservationRepository
import com.architectcoders.data.repository.UserRepository
import com.architectcoders.data.source.*
import com.architectcoders.usecases.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun AndroidApplication.initDI() {
    startKoin {
        androidLogger()
        androidContext(this@initDI)
        modules(listOf(appModule, dataModule, scopesModule))
    }
}

private val appModule = module {
    single { LocalDatabase.build(get()) }
    single { Prefs((androidApplication())) }
    single<CoroutineDispatcher> { Dispatchers.Main }
}

private val dataModule = module {
    factory<LoginLocalDataSource> { LoginDataSource(get()) }
    factory<BookLocalDataSource> { BookDataSource(get()) }
    factory<ObservationLocalDataSource> { ObservationDataSource(get()) }
    factory<LoginRemoteDatasource> { LoginDatasource() }
    factory<BookRemoteDatasource> { BookDatasource() }
    factory<ObservationRemoteDatasource> { ObservationDatasource() }
    factory { UserRepository(get(), get()) }
    factory { BookRepository(get(), get()) }
    factory { ObservationRepository(get(), get()) }
}

private val scopesModule = module {
    scope(named<LoginFragment>()) {
        viewModel { LoginViewModel(get()) }
        scoped { DoLogin(get()) }
    }

    scope(named<CreateAccountFragment>()) {
        viewModel { LoginCreateAccountViewModel(get()) }
        scoped { CreateAccount(get()) }
    }

    scope(named<BookListFragment>()) {
        viewModel { BookListViewModel(get()) }
        scoped { GetBooks(get()) }
    }

    scope(named<MyObservationsFragment>()) {
        viewModel { MyObservationsViewModel(get(), get()) }
        scoped { GetAccount(get()) }
        scoped { GetOwnerObservations(get()) }
    }

    scope(named<ProfileFragment>()) {
        viewModel { ProfileViewModel(get()) }
        scoped { GetAccount(get()) }
    }

    scope(named<BookDetailFragment>()) {
        viewModel { BookDetailViewModel(get(), get()) }
        scoped { GetBook(get()) }
        scoped { GetObservations(get()) }
    }

    scope(named<ObservationFragment>()) {
        viewModel { ObservationViewModel(get(), get(), get()) }
        scoped { GetAccount(get()) }
        scoped { GetBook(get()) }
        scoped { CreateObservation(get()) }
    }
}
package com.architectcoders.bissu

import BookListFragment
import com.architectcoders.framework.database.LocalDatabase
import com.architectcoders.framework.database.Prefs
import com.architectcoders.framework.database.book.BookDataSource
import com.architectcoders.framework.database.login.LoginDataSource
import com.architectcoders.framework.database.observation.ObservationDataSource
import com.architectcoders.framework.server.book.BookDatasource
import com.architectcoders.framework.server.category.CategoryDatasource as CategoryRemotelDataSource
import com.architectcoders.framework.database.category.CategoryDatasource as CategoryLocalDataSource
import com.architectcoders.framework.server.login.LoginDatasource
import com.architectcoders.framework.server.observation.ObservationDatasource
import com.architectcoders.bissu.ui.book.BookDetailFragment
import com.architectcoders.bissu.ui.book.BookDetailViewModel
import com.architectcoders.bissu.ui.book.CreateBookViewModel
import com.architectcoders.bissu.ui.book.fragments.CreateBookFragment
import com.architectcoders.bissu.ui.home.bookList.BookListViewModel
import com.architectcoders.bissu.ui.home.myObservations.MyObservationsFragment
import com.architectcoders.bissu.ui.home.myObservations.MyObservationsViewModel
import com.architectcoders.bissu.ui.profile.ProfileViewModel
import com.architectcoders.bissu.ui.profile.fragments.ProfileFragment
import com.architectcoders.bissu.ui.login.CreateAccountViewModel
import com.architectcoders.bissu.ui.login.LoginViewModel
import com.architectcoders.bissu.ui.profile.UpdateAccountViewModel
import com.architectcoders.bissu.ui.login.fragments.CreateAccountFragment
import com.architectcoders.bissu.ui.login.fragments.LoginFragment
import com.architectcoders.bissu.ui.profile.fragments.UpdateAccountFragment
import com.architectcoders.bissu.ui.observation.ObservationFragment
import com.architectcoders.bissu.ui.observation.ObservationViewModel
import com.architectcoders.bissu.ui.profile.ChangePasswordViewModel
import com.architectcoders.bissu.ui.profile.fragments.ChangePasswordFragment
import com.architectcoders.data.repository.BookRepository
import com.architectcoders.data.repository.CategoryRepository
import com.architectcoders.data.repository.ObservationRepository
import com.architectcoders.data.repository.UserRepository
import com.architectcoders.domain.interfaces.UserRepository as UserRepositoryDomain
import com.architectcoders.domain.interfaces.ObservationRepository as ObservationRepositoryDomain
import com.architectcoders.domain.interfaces.CategoryRepository as CategoryRepositoryDomain
import com.architectcoders.domain.interfaces.BookRepository as BookRepositoryDomain
import com.architectcoders.data.source.*
import com.architectcoders.domain.usecases.*
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
    factory<CategoryLocalDatasource> { CategoryLocalDataSource(get()) }
    factory<CategoryRemoteDatasource> { CategoryRemotelDataSource() }
    factory<LoginRemoteDatasource> { LoginDatasource() }
    factory<BookRemoteDatasource> { BookDatasource() }
    factory<ObservationRemoteDatasource> { ObservationDatasource() }

    factory<UserRepositoryDomain> { UserRepository(get(), get()) }
    factory<BookRepositoryDomain> { BookRepository(get(), get()) }
    factory<ObservationRepositoryDomain> { ObservationRepository(get(), get()) }
    factory<CategoryRepositoryDomain> { CategoryRepository(get(), get()) }
}

private val scopesModule = module {
    scope(named<LoginFragment>()) {
        viewModel { LoginViewModel(get()) }
        scoped { DoLogin(get()) }
    }

    scope(named<CreateAccountFragment>()) {
        viewModel { CreateAccountViewModel(get()) }
        scoped { CreateAccount(get()) }
    }

    scope(named<UpdateAccountFragment>()) {
        viewModel {
            UpdateAccountViewModel(
                get(),
                get()
            )
        }
        scoped { UpdateAccount(get()) }
        scoped { GetAccount(get()) }
    }

    scope(named<ChangePasswordFragment>()) {
        viewModel {
            ChangePasswordViewModel(
                get(),
                get()
            )
        }
        scoped { UpdateAccount(get()) }
        scoped { GetAccount(get()) }
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

    scope(named<CreateBookFragment>()) {
        viewModel { CreateBookViewModel(get(), get()) }
        scoped { GetCategories(get()) }
        scoped { CreateBook(get()) }
    }
}
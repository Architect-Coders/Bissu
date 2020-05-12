package com.architectcoders.bissu

import BookListFragment
import com.architectcoders.bissu.main.MainActivity
import com.architectcoders.bissu.main.MainViewModel
import com.architectcoders.framework.database.LocalDatabase
import com.architectcoders.framework.database.Prefs
import com.architectcoders.framework.database.book.BookDataSource
import com.architectcoders.framework.database.login.LoginDataSource
import com.architectcoders.framework.database.observation.ObservationDataSource
import com.architectcoders.framework.server.book.BookDatasource
import com.architectcoders.framework.server.category.CategoryDatasource as CategoryRemotelDataSource
import com.architectcoders.framework.database.category.CategoryDatasource as CategoryLocalDataSource
import com.architectcoders.framework.server.user.UserDatasource
import com.architectcoders.framework.server.observation.ObservationDatasource
import com.architectcoders.bissu.book.fragments.BookDetailFragment
import com.architectcoders.bissu.book.viewModel.BookDetailViewModel
import com.architectcoders.bissu.book.viewModel.CreateBookViewModel
import com.architectcoders.bissu.book.fragments.CreateBookFragment
import com.architectcoders.bissu.home.bookList.BookListViewModel
import com.architectcoders.bissu.home.myObservations.MyObservationsFragment
import com.architectcoders.bissu.home.myObservations.MyObservationsViewModel
import com.architectcoders.bissu.profile.viewmodels.ProfileViewModel
import com.architectcoders.bissu.profile.fragments.ProfileFragment
import com.architectcoders.bissu.login.viewmodels.CreateAccountViewModel
import com.architectcoders.bissu.login.viewmodels.LoginViewModel
import com.architectcoders.bissu.profile.viewmodels.UpdateAccountViewModel
import com.architectcoders.bissu.login.fragments.CreateAccountFragment
import com.architectcoders.bissu.login.fragments.LoginFragment
import com.architectcoders.bissu.observation.ObservationFragment
import com.architectcoders.bissu.observation.ObservationViewModel
import com.architectcoders.bissu.profile.fragments.UpdateAccountFragment
import com.architectcoders.bissu.profile.viewmodels.ChangePasswordViewModel
import com.architectcoders.bissu.profile.fragments.ChangePasswordFragment
import com.architectcoders.data.repository.BookRepository
import com.architectcoders.data.repository.CategoryRepository
import com.architectcoders.data.repository.ObservationRepository
import com.architectcoders.data.repository.UserRepository
import com.architectcoders.domain.repositories.UserRepository as UserRepositoryDomain
import com.architectcoders.domain.repositories.ObservationRepository as ObservationRepositoryDomain
import com.architectcoders.domain.repositories.CategoryRepository as CategoryRepositoryDomain
import com.architectcoders.domain.repositories.BookRepository as BookRepositoryDomain
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

fun AndroidApplication.initDi() {
    startKoin {
        androidLogger()
        androidContext(this@initDi)
        modules(listOf(appModule,domainModule, dataModule, scopesModule))
    }
}

private val appModule = module {
    single { LocalDatabase.build(get()) }
    single { Prefs((androidApplication())) }
    single<CoroutineDispatcher> { Dispatchers.Main }
}

private val dataModule = module {
    factory<UserLocalDataSource> { LoginDataSource(get()) }
    factory<UserRemoteDatasource> { UserDatasource(androidApplication()) }

    factory<BookRemoteDatasource> { BookDatasource(androidApplication()) }
    factory<BookLocalDataSource> { BookDataSource(get()) }

    factory<ObservationRemoteDatasource> { ObservationDatasource(androidApplication()) }
    factory<ObservationLocalDataSource> { ObservationDataSource(get()) }

    factory<CategoryLocalDatasource> { CategoryLocalDataSource(get()) }
    factory<CategoryRemoteDatasource> { CategoryRemotelDataSource(androidApplication()) }
}

val domainModule = module{
    factory<UserRepositoryDomain> { UserRepository(get(), get()) }
    factory<BookRepositoryDomain> { BookRepository(get(), get()) }
    factory<ObservationRepositoryDomain> { ObservationRepository(get(), get()) }
    factory<CategoryRepositoryDomain> { CategoryRepository(get(), get()) }
}

private val scopesModule = module {
    scope(named<LoginFragment>()) {
        viewModel {
            LoginViewModel( get(),get())
        }
        scoped { DoLogin(get()) }
    }

    scope(named<CreateAccountFragment>()) {
        viewModel {
            CreateAccountViewModel(
                get(),get() )
        }
        scoped { CreateAccount(get()) }
    }

    scope(named<UpdateAccountFragment>()) {
        viewModel {
            UpdateAccountViewModel(
                get(),
                get(),
                get()
            )
        }
        scoped { UpdateAccount(get()) }
        scoped { GetSessionUser(get()) }
    }

    scope(named<ChangePasswordFragment>()) {
        viewModel {
            ChangePasswordViewModel(
                get(), get(), get() )
        }
        scoped { UpdateAccount(get()) }
        scoped { GetSessionUser(get()) }
    }


    scope(named<BookListFragment>()) {
        viewModel { BookListViewModel(get(), get()) }
        scoped { GetBooks(get()) }
    }

    scope(named<MyObservationsFragment>()) {
        viewModel { MyObservationsViewModel(get(), get(), get()) }
        scoped { GetSessionUser(get()) }
        scoped { GetObservationsByUser(get()) }
    }

    scope(named<ProfileFragment>()) {
        viewModel {
            ProfileViewModel( get())
        }
    }

    scope(named<BookDetailFragment>()) {
        viewModel {
            BookDetailViewModel(
                get(),
                get()
            )
        }
        scoped { GetBook(get()) }

    }

    scope(named<ObservationFragment>()) {
        viewModel { ObservationViewModel(get(), get(), get(),get()) }
        scoped { GetSessionUser(get()) }
        scoped { GetBook(get()) }
        scoped { CreateObservation(get()) }
    }

    scope(named<CreateBookFragment>()) {
        viewModel {
            CreateBookViewModel(
                get(),
                get(),
                get()
            )
        }
        scoped { GetCategories(get()) }
        scoped { CreateBook(get()) }
    }

    scope(named<MainActivity>()){
        viewModel { MainViewModel(get(), get()) }
        scoped { GetSessionUser(get()) }

    }
}
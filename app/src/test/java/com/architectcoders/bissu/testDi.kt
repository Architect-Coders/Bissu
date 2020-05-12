package com.architectcoders.bissu

import com.architectcoders.data.source.*
import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.domain.entities.User
import com.architectcoders.framework.database.book.BookDataSource
import com.architectcoders.framework.database.category.CategoryDatasource
import com.architectcoders.framework.database.observation.ObservationDataSource
import com.architectcoders.framework.server.book.BookDatasource
import com.architectcoders.framework.server.observation.ObservationDatasource
import com.example.testshared.mockedUser
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

fun initMockedDi(vararg modules: Module){
    startKoin {
        modules(listOf(mockedAppModule, domainModule) + modules)
    }
}

private val mockedAppModule = module {
    single<UserLocalDataSource> { FakeUserLocalDatasource() }
    single<UserRemoteDatasource> { FakeUserRemoteDatasource() }

    single<BookRemoteDatasource> { FakeBookRemoteDatasource() }
    single<BookLocalDataSource> { FakeBookLocalDatasource() }

    single<CategoryLocalDatasource> { FakeCategoryLocalDatasource() }
    single<CategoryRemoteDatasource> { FakeCategoryRemoteDatasource() }

    single<ObservationRemoteDatasource> { FakeObservationRemoteDatasource() }
    single<ObservationLocalDataSource> { FakeObservationLocalDatasource() }

    single { Dispatchers.Unconfined }
}



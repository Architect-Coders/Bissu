package com.architectcoders.bissu.data.server.observation

import com.architectcoders.bissu.data.mappers.toDomainObservation
import com.architectcoders.bissu.data.server.RetrofitClient
import com.architectcoders.data.source.ObservationRemoteDatasource
import com.architectcoders.domain.Book
import com.architectcoders.domain.Observation
import com.architectcoders.domain.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ObservationDatasource : ObservationRemoteDatasource {
    override suspend fun getObservationsByBook(id: String): ArrayList<Observation> =
        withContext(Dispatchers.IO) {
            /*ArrayList(
                RetrofitClient().observationService.getObservations(id)
                .await()
                .value
                .map { it.toDomainObservation() })*/

            arrayListOf<Observation>(
                Observation(
                    "1",
                    User("","deneb", "","","","",ArrayList()),
                    Book("1","","","","","","",""),
                    "Esto es un comentario absurdo",
                    "5"
                ),
                Observation(
                    "2",
                    User("","deneb", "","","","",ArrayList()),
                    Book("1","","","","","","",""),
                    "El error esta es aqui",
                    "9"
                ),
                Observation(
                    "3",
                    User("","deneb", "","","","https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80",ArrayList()),
                    Book("1","","","","","","",""),
                    "Este autor necesita es un milagro",
                    "10"
                )
            )
        }

}
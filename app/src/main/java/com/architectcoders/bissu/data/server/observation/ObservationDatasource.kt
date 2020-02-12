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
                    User("5e2eff4e40da3b0017e2d46a", "deneb", "", "", "", "", ArrayList()),
                    Book(
                        "1",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "https://media.npr.org/assets/img/2018/11/18/gettyimages-865109088-170667a_wide-f4e3c4a58ad5e1268dec3654c0b2d490e320bba6-s800-c85.jpg"
                    ),
                    "Esto es un comentario absurdo",
                    "5"
                ),
                Observation(
                    "2",
                    User("5e2eff4e40da3b0017e2d46a", "deneb", "", "", "", "", ArrayList()),
                    Book(
                        "2",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ8l638YWxifwX3KmAWAbzpxv2OeRI_P3oj2qjdLRTq-PCkAuok&s"
                    ),
                    "El error esta es aqui",
                    "9"
                ),
                Observation(
                    "3",
                    User(
                        "5e2eff4e40da3b0017e2d46a",
                        "deneb",
                        "",
                        "",
                        "",
                        "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80",
                        ArrayList()
                    ),
                    Book(
                        "3",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "https://trojantimes.net/wp-content/uploads/2019/03/top-10-confidence-books.jpg"
                    ),
                    "Este autor necesita es un milagro",
                    "10"
                )
            )
        }

    override suspend fun getObservationsByOwner(userId: String): ArrayList<Observation> =
        withContext(Dispatchers.IO) {
            /*ArrayList(
                RetrofitClient().observationService.getObservationsByUser(userId)
                .await()
                .value
                .map { it.toDomainObservation() })*/

            arrayListOf<Observation>(
                Observation(
                    "1",
                    User("5e2eff4e40da3b0017e2d46a", "deneb", "", "", "", "", ArrayList()),
                    Book(
                        "1",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "https://media.npr.org/assets/img/2018/11/18/gettyimages-865109088-170667a_wide-f4e3c4a58ad5e1268dec3654c0b2d490e320bba6-s800-c85.jpg"
                    ),
                    "Esto es un comentario absurdo",
                    "5"
                ),
                Observation(
                    "2",
                    User("5e2eff4e40da3b0017e2d46a", "deneb", "", "", "", "", ArrayList()),
                    Book(
                        "2",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ8l638YWxifwX3KmAWAbzpxv2OeRI_P3oj2qjdLRTq-PCkAuok&s"
                    ),
                    "El error esta es aqui",
                    "9"
                ),
                Observation(
                    "3",
                    User(
                        "5e2eff4e40da3b0017e2d46a",
                        "deneb",
                        "",
                        "",
                        "",
                        "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80",
                        ArrayList()
                    ),
                    Book(
                        "3",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "https://trojantimes.net/wp-content/uploads/2019/03/top-10-confidence-books.jpg"
                    ),
                    "Este autor necesita es un milagro",
                    "10"
                )
            )
        }

    override suspend fun createObservation(observation: Observation): Boolean =
        withContext(Dispatchers.IO) {
            /*RetrofitClient().observationService.createObservation(observation)
                .await()*/

            true
        }

}
package com.example.aplikasievent.network

import com.example.aplikasievent.ui.Finished.FinishedResponse
import com.example.aplikasievent.ui.Upcoming.UpcomingResponse
import retrofit2.http.GET

interface EventApiService {

    // API for Finished Events
    @GET("events?active=0")  // Finished events (active = 0)
    suspend fun getFinishedEvents(): FinishedResponse

    // API for Upcoming Events
    @GET("events?active=1")  // Upcoming events (active = 1)
    suspend fun getUpcomingEvents(): UpcomingResponse
}

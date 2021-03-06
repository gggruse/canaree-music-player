package dev.olog.msc.domain.gateway

import dev.olog.msc.domain.entity.Podcast
import io.reactivex.Observable

interface ChildsHasPodcasts<in Param> {

    fun observePodcastListByParam(param: Param): Observable<List<Podcast>>

}

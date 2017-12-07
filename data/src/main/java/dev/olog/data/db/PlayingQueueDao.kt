package dev.olog.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import dev.olog.data.entity.PlayingQueueEntity
import dev.olog.domain.entity.Song
import io.reactivex.Completable
import io.reactivex.CompletableSource
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.*

@Dao
abstract class PlayingQueueDao {

    @Query("SELECT * FROM playing_queue ORDER BY timeAdded DESC")
    internal abstract fun getAllImpl(): Single<List<PlayingQueueEntity>>

    @Query("DELETE FROM playing_queue")
    internal abstract fun deleteAllImpl()

    @Query("SELECT * FROM playing_queue ORDER BY timeAdded DESC")
    abstract fun observeAll(): Flowable<List<PlayingQueueEntity>>

    @Insert
    internal abstract fun insertAllImpl(list: List<PlayingQueueEntity>)

    fun getAllAsSongs(songList: Single<List<Song>>): Single<List<Song>> {

        return this.getAllImpl()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flattenAsFlowable { it }
                .map(PlayingQueueEntity::value)
                .flatMapMaybe { songId ->
                    songList.flattenAsFlowable { it }
                            .filter { it.id == songId }
                            .firstElement()
                }.toSortedList { o1, o2 -> String.CASE_INSENSITIVE_ORDER.compare(o1.title, o2.title) }
                .onErrorReturnItem(ArrayList(0))
    }

    fun insert(list: List<Long>) : Completable {
        return Single.fromCallable { deleteAllImpl() }
                .map { list.map { PlayingQueueEntity(value = it) } }
                .flatMapCompletable { queueList -> CompletableSource { insertAllImpl(queueList) } }
    }

}
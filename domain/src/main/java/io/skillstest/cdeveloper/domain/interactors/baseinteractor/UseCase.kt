package io.skillstest.cdeveloper.domain.interactors.baseinteractor

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


abstract class UseCase<Response, Params>(
        private val mSubsOnScheduler: Scheduler = Schedulers.io(),
        private val mObseOnScheduler: Scheduler = AndroidSchedulers.mainThread()) : IUseCase<Response, Params> {

    abstract fun buildUseCase(params: Params): Observable<Response>

    override fun invoke(
            params: Params,
            onSuccess: (response: Response) -> Unit,
            onError: (error: Throwable) -> Unit,
            doFinally: () -> Unit): Disposable = buildUseCase(params).subscribeOn(mSubsOnScheduler)
            .observeOn(mObseOnScheduler)
            .subscribe(onSuccess, onError)

}
package io.skillstest.cdeveloper.domain.interactors.baseinteractor

import io.reactivex.disposables.Disposable

interface IUseCase<Response, Params> {

    fun invoke(params: Params,
               onSuccess: (response: Response) -> Unit,
               onError: (error: Throwable) -> Unit,
               doFinally: () -> Unit = {}
    ): Disposable
}
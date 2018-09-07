package io.skillstest.cdeveloper.skillstest.leagues

import io.reactivex.disposables.CompositeDisposable
import io.skillstest.cdeveloper.domain.model.League
import io.skillstest.cdeveloper.domain.interactors.baseinteractor.IUseCase

class LeaguePresenter(private val getLeaguesInteractor: IUseCase<List<League>, Any?>) : ILeagueContract.Presenter {

    private var view: ILeagueContract.View? = null

    private val mSubscripcion = CompositeDisposable()

    override fun view(view: ILeagueContract.View) {
        this.view = view
    }

    override fun loadLeagues() {
        mSubscripcion.add(
                getLeaguesInteractor.invoke(null, {
                    view?.showLeagueNotAvailable(it.isNotEmpty())
                    view?.loadLeagues(it)
                }, {
                    view?.showLeagueNotAvailable(false)
                })
        )
    }

    override fun itemCLick(t: League) {
        view?.leagueItemClick(t.strLeague)
    }

}
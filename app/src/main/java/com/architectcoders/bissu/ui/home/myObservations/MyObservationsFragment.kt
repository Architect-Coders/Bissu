package com.architectcoders.bissu.ui.home.myObservations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.architectcoders.bissu.R
import com.architectcoders.bissu.data.database.login.LoginDataSource
import com.architectcoders.bissu.data.database.observation.ObservationDataSource
import com.architectcoders.bissu.data.server.login.LoginDatasource
import com.architectcoders.bissu.data.server.observation.ObservationDatasource
import com.architectcoders.bissu.ui.common.app
import com.architectcoders.bissu.ui.common.getViewModel
import com.architectcoders.data.repository.ObservationRepository
import com.architectcoders.data.repository.UserRepository
import com.architectcoders.domain.Observation
import com.architectcoders.usecases.GetAccount
import com.architectcoders.usecases.GetOwnerObservations
import kotlinx.android.synthetic.main.fragment_my_observations.*

class MyObservationsFragment : Fragment() {

    private val observationAdapter by lazy { OwnerObservationAdapter() }

    companion object {
        fun newInstance() = MyObservationsFragment()
    }

    private lateinit var viewModel: MyObservationsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = getViewModel {
            MyObservationsViewModel(
                GetAccount(
                    UserRepository(
                        LoginDataSource(activity!!.app.db),
                        LoginDatasource()
                    )
                ),
                GetOwnerObservations(
                    ObservationRepository(
                        ObservationDataSource(activity!!.app.db),
                        ObservationDatasource()
                    )
                )
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_observations, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MyObservationsViewModel::class.java)

        viewModel.getObservationsByOwner()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))

        initializeAdapter()
    }

    private fun initializeAdapter() {
        myobservations_observations.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = observationAdapter
        }
    }

    private fun updateUi(model: MyObservationsViewModel.UiModel?) {
        when (model) {
            is MyObservationsViewModel.UiModel.Loading -> progressVisibility(model.value)
            is MyObservationsViewModel.UiModel.Content -> processObservations(model.observations)
        }
    }

    private fun progressVisibility(value: Boolean) {
        myobservations_progress.visibility = if (value) View.VISIBLE else View.GONE
    }

    private fun processObservations(books: List<Observation>) {
        observationAdapter.submitList(books.map { OwnerObservationItem(it) })
        myobservations_observations.visibility =
            if (observationAdapter.itemCount > 0) View.VISIBLE else View.GONE
    }
}

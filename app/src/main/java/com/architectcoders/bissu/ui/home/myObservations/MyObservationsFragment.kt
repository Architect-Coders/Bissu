package com.architectcoders.bissu.ui.home.myObservations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.architectcoders.bissu.R
import com.architectcoders.domain.entities.Observation
import kotlinx.android.synthetic.main.fragment_my_observations.*
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class MyObservationsFragment : Fragment() {

    private val observationAdapter by lazy { OwnerObservationAdapter() }

    companion object {
        fun newInstance() = MyObservationsFragment()
    }

    private val viewModel: MyObservationsViewModel by lifecycleScope.viewModel(this)

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_my_observations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))

        initializeAdapter()

        viewModel.getObservationsByUser()
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

    private fun processObservations(observations: List<Observation>) {
        observationAdapter.submitList(observations.map { OwnerObservationItem(it) })
        myobservations_observations.visibility = if (observationAdapter.itemCount > 0) View.VISIBLE else View.GONE
    }
}

package com.architectcoders.bissu.ui.observation

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.architectcoders.bissu.R

class ObservationFragment : Fragment() {

    companion object {
        fun newInstance() = ObservationFragment()
    }

    private lateinit var viewModel: ObservationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_observation, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ObservationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}

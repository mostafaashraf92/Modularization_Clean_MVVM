package com.starwars.charactersearch.presentation.characterdetails.planets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.starwars.charactersearch.R
import com.starwars.charactersearch.databinding.PlanetDetailsFragmentBinding
import com.starwars.core.Constants
import com.starwars.domain.entities.CharacterModel
import com.starwars.domain.entities.ErrorModel
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


class FragmentPlanetsDetails : Fragment() {


    private val mViewModel: PlanetsViewModel by viewModel()

    private val constants: Constants by inject()
    private var model: CharacterModel? = null

    companion object {

        @JvmStatic
        fun newInstance(param1: CharacterModel?) =
            FragmentPlanetsDetails().apply {
                arguments = Bundle().apply {
                    putSerializable(constants.FRAGMENT_ARG_PARAM1, param1)
                }
            }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            model = it.getSerializable(constants.FRAGMENT_ARG_PARAM1) as CharacterModel?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<PlanetDetailsFragmentBinding>(
            inflater,
            R.layout.planet_details_fragment,
            container,
            false
        )
            .also {
                it.viewModel = mViewModel
                it.lifecycleOwner = viewLifecycleOwner
            }

        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        model?.homeworld?.let { mViewModel.search(it) }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun showErrorMessgae(errorModel: ErrorModel) {
        Toast.makeText(activity, errorModel.errorMessage, Toast.LENGTH_LONG).show()
    }


}

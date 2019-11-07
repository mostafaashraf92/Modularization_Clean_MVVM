package com.starwars.charactersearch.presentation.characterdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.starwars.charactersearch.R
import com.starwars.charactersearch.databinding.CharacterDetailsFragmentBinding
import com.starwars.charactersearch.presentation.characterdetails.films.FragmentFilmDetails
import com.starwars.charactersearch.presentation.characterdetails.planets.FragmentPlanetsDetails
import com.starwars.charactersearch.presentation.characterdetails.species.FragmentSpeciesDetails
import com.starwars.core.Constants
import com.starwars.domain.entities.CharacterModel
import com.starwars.domain.entities.ErrorModel
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


class FragmentCharacterDetails : Fragment() {


    private val mViewModel: CharacterDetailsViewModel by viewModel()
    private var characterModel: CharacterModel? =null
    private val constants:Constants by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<CharacterDetailsFragmentBinding>(
            inflater,
            R.layout.character_details_fragment,
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
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        characterModel = arguments?.get(constants.FRAGMENT_DETAILS_ARG_PARAM) as CharacterModel
        mViewModel.setData(characterModel)
        addAllFragments()


    }


    private fun showErrorMessgae(errorModel: ErrorModel) {
        Toast.makeText(activity, errorModel.errorMessage, Toast.LENGTH_LONG).show()
    }

    private fun addAllFragments()
    {
        fragmentManager?.beginTransaction()?.add(R.id.planetContainer, FragmentPlanetsDetails.newInstance(characterModel))
            ?.commit()
        fragmentManager?.beginTransaction()?.add(R.id.speciesContainer, FragmentSpeciesDetails.newInstance(characterModel))
            ?.commit()

        fragmentManager?.beginTransaction()?.add(R.id.filmsContainer, FragmentFilmDetails.newInstance(characterModel))
            ?.commit()
    }

}

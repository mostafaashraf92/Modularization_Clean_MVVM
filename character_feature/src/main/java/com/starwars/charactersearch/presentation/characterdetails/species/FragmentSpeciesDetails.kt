package com.starwars.charactersearch.presentation.characterdetails.species

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.starwars.charactersearch.R
import com.starwars.charactersearch.databinding.SpeciesDetailsFragmentBinding
import com.starwars.core.Constants
import com.starwars.domain.entities.CharacterModel
import com.starwars.domain.entities.ErrorModel
import kotlinx.android.synthetic.main.species_details_fragment.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*


class FragmentSpeciesDetails : Fragment() {

    private val mViewModel: SpeciesViewModel by viewModel()
    private val constants: Constants by inject()
    private var model: CharacterModel? = null
    private val mAdapter: SpeciesAdapter by inject()


    companion object {

        @JvmStatic
        fun newInstance(param1: CharacterModel?) =
            FragmentSpeciesDetails().apply {
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
        arguments?.let {
            model = it.getSerializable(constants.FRAGMENT_ARG_PARAM1) as CharacterModel?
        }
        mViewModel.responseLiveData.observe(
            this,
            Observer { setAdapterData(it) })
        mViewModel.errorLiveData.observe(
            this,
            Observer<ErrorModel> { showErrorMessgae(it) })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<SpeciesDetailsFragmentBinding>(
            inflater,
            R.layout.species_details_fragment,
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
        model?.species?.let { mViewModel.searchDetails(it) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }


    private fun initViews() {
        val linearLayoutManager = LinearLayoutManager(context)
        speciesRecyclerView.isNestedScrollingEnabled = true
        speciesRecyclerView.layoutManager = linearLayoutManager
        speciesRecyclerView.adapter = mAdapter
    }


    private fun setAdapterData(arrayList: ArrayList<com.starwars.domain.entities.SpeciesModel>?) {

        arrayList?.let {
            mAdapter.addAll(arrayList)
        }
    }

    private fun showErrorMessgae(errorModel: ErrorModel) {
        Toast.makeText(activity, errorModel.errorMessage, Toast.LENGTH_LONG).show()
    }


}

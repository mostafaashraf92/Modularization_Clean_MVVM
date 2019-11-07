package com.starwars.charactersearch.presentation.characterdetails.films

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
import com.starwars.charactersearch.databinding.FilmDetailsFragmentBinding
import com.starwars.charactersearch.presentation.characterdetails.species.FilmsAdapter
import com.starwars.core.Constants
import com.starwars.domain.entities.CharacterModel
import kotlinx.android.synthetic.main.film_details_fragment.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*


class FragmentFilmDetails : Fragment() {


    private val mViewModel: FilmDetailsViewModel by viewModel()

    private val constants: Constants by inject()
    private val mAdapter: FilmsAdapter by inject()
    private var model: CharacterModel? = null

    companion object {

        @JvmStatic
        fun newInstance(param1: CharacterModel?) =
            FragmentFilmDetails().apply {
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
        mViewModel.responseLiveData.observe(
            this,
            Observer { setAdapterData(it) })
        mViewModel.errorLiveData.observe(
            this,
            Observer<com.starwars.domain.entities.ErrorModel> { showErrorMessgae(it) })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FilmDetailsFragmentBinding>(
            inflater,
            R.layout.film_details_fragment,
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
        model?.films?.let { mViewModel.searchDetails(it) }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

    }


    private fun initViews() {
        val linearLayoutManager = LinearLayoutManager(context)
        filmsRecyclerView.isNestedScrollingEnabled=true
        filmsRecyclerView.layoutManager = linearLayoutManager
        filmsRecyclerView.adapter = mAdapter
    }

    private fun setAdapterData(arrayList: ArrayList<com.starwars.domain.entities.FilmModel>?) {

        arrayList?.let {
            mAdapter.addAll(arrayList)
        }
    }

    private fun showErrorMessgae(errorModel: com.starwars.domain.entities.ErrorModel) {
        Toast.makeText(activity, errorModel.errorMessage, Toast.LENGTH_LONG).show()
    }

}

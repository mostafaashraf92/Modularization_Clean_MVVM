package com.starwars.charactersearch.presentation.charactersearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.starwars.charactersearch.R
import com.starwars.charactersearch.databinding.CharacterSearchFragmentBinding
import com.starwars.core.Constants
import com.starwars.core.interfaces.IOnCharacterClickedListener
import com.starwars.domain.entities.CharacterModel
import com.starwars.domain.entities.CharacterSearchModel
import kotlinx.android.synthetic.main.character_search_fragment.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


class FragmentCharacterSearch : Fragment(),
    IOnCharacterClickedListener<CharacterModel> {


    private val mViewModel: CharacterSearchViewModel by viewModel()
    private val mAdapter: CharactersSearchAdapter by inject()
    private val constants: Constants by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<CharacterSearchFragmentBinding>(
            inflater,
            R.layout.character_search_fragment,
            container,
            false
        )
            .also {
                it.viewModel = mViewModel
                it.lifecycleOwner = viewLifecycleOwner
                it.listener = this@FragmentCharacterSearch
            }

        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel.responseLiveData.observe(
            this,
            Observer<CharacterSearchModel> { setAdapterData(it) })
        mViewModel.errorLiveData.observe(
            this,
            Observer<com.starwars.domain.entities.ErrorModel> { showErrorMessgae(it) })
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

    }

    private fun setAdapterData(characterSearchModel: CharacterSearchModel) {
        characterSearchModel.results?.let { mAdapter.setAllItems(it) }
    }

    private fun showErrorMessgae(errorModel: com.starwars.domain.entities.ErrorModel) {
        Toast.makeText(activity, errorModel.errorMessage, Toast.LENGTH_LONG).show()
    }

    private fun initViews() {
        initCallBacks()
        val linearLayoutManager = LinearLayoutManager(context)
        charactersRecyclerView.layoutManager = linearLayoutManager
        charactersRecyclerView.adapter = mAdapter

    }

    private fun initCallBacks() {
        searchImgView.setOnClickListener {
            mViewModel.afterCharacterChanged(edit_query?.text.toString())
        }
    }

    override fun onListItemClicked(item: CharacterModel) {
        val bundle = bundleOf(constants.FRAGMENT_DETAILS_ARG_PARAM to item)
        this.findNavController().navigate(R.id.action_fragmentCharacterSearch_to_fragmentCharacterDetails, bundle)
    }

}

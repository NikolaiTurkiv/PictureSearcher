package com.test.feature_search.presentation

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.test.android_utils.viewBinding
import com.test.feature_home.R
import com.test.feature_home.databinding.FragmentSearchBinding
import com.test.feature_search.adapters_delegate.getPictureAdapterDelegate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private val binding by viewBinding<FragmentSearchBinding>()
    private val viewModel by viewModels<SearchViewModel>()

    private var delayJob: Job? = null


    private val adapter = ListDelegationAdapter(
        getPictureAdapterDelegate()
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initSearch()
        initObservers()
    }


    private fun initRecyclerView() {
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.rv.layoutManager = gridLayoutManager
    }

    private fun initObservers(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.searchPhotos.collect{
                adapter.apply {
                    items = it
                    notifyDataSetChanged()
                }
                binding.rv.adapter = adapter
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.partRequests.collect{
                val adapter  = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, it)

                binding.pictureSearch.setAdapter(adapter)
                binding.pictureSearch.threshold = 1
            }
        }
    }

    private fun initSearch(){
        binding.pictureSearch.doAfterTextChanged {
            delaySearchChange(lifecycleScope){
                if (!it.isNullOrEmpty())
                 viewModel.getPhotos(it.toString())
            }
        }
        val adapter  = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, emptyList<String>())

        binding.pictureSearch.setAdapter(adapter)
        binding.pictureSearch.threshold = 1
    }

    private fun delaySearchChange(
        scope: CoroutineScope,
        onTextChanged: () -> Unit
    ) {
        delayJob?.cancel()
        delayJob = scope.launch {
            delay(DEBOUNCE_DELAY)
            onTextChanged()
        }
    }

    companion object {
        const val DEBOUNCE_DELAY = 750L
    }

}

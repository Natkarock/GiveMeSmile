package com.natkarock.myapplication.views.counter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.natkarock.myapplication.R
import com.natkarock.myapplication.databinding.FragmentCounterBinding
import com.natkarock.myapplication.views.BaseFragment
import com.natkarock.myapplication.views.navigation.RouterImpl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CounterFragment : BaseFragment() {

    companion object {
        fun create(): CounterFragment {
            return CounterFragment()
        }
    }

    private lateinit var binding: FragmentCounterBinding

    val viewModel: CounterViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCounterBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        observeCount()
        observeScreenState()
        return binding.root
    }


    private fun observeCount() {
        viewModel.count.observe(viewLifecycleOwner) { count ->
            binding.btnApprove.visibility =
                if (count > 0) {
                    View.VISIBLE
                } else {
                    View.INVISIBLE
                }
        }
    }

    private fun observeScreenState() {
        viewModel.screenState.observe(viewLifecycleOwner) { state ->
            when (state) {
                CounterScreenState.COMPLETE -> finishAction()
            }
        }
    }

    private fun finishAction() {
        router.toCamera()
    }
}
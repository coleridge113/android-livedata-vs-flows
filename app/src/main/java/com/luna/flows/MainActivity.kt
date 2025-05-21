package com.luna.flows

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.luna.flows.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val stateViewModel: StateViewModel by viewModels<StateViewModel>()
    private val liveDataViewModel: LiveDataViewModel by viewModels<LiveDataViewModel>()
    private val sharedFlowViewModel: SharedFlowViewModel by viewModels<SharedFlowViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        useStateData()
        useSharedFlowData()
//        useLiveData()


    }

    fun useStateData(){
        lifecycleScope.launch {
            stateViewModel.namesList.collect { nameList ->
                binding.names.text = nameList.joinToString("\n")
            }
        }

        with(binding){
            addName.setOnClickListener {
                stateViewModel.addName(editText.text.toString())
                editText.text.clear()
            }

            deleteName.setOnClickListener {
                stateViewModel.deleteName()
            }
        }
    }

    fun useLiveData(){
        liveDataViewModel.namesList.observe(this) { namesList ->
            binding.names.text = namesList.joinToString("\n")
        }

        with(binding){
            addName.setOnClickListener {
                liveDataViewModel.addName(editText.text.toString())
                editText.text.clear()
            }

            deleteName.setOnClickListener {
                liveDataViewModel.deleteName()
            }
        }
    }

    fun useSharedFlowData(){
        lifecycleScope.launch {
            sharedFlowViewModel.namesList.collect { namesList ->
                binding.names.text = namesList.joinToString("\n")
            }
        }

        with(binding){
            addName.setOnClickListener {
                sharedFlowViewModel.addName(editText.text.toString())
                editText.text.clear()
            }

            deleteName.setOnClickListener {
                sharedFlowViewModel.deleteName()
            }
        }
    }
}
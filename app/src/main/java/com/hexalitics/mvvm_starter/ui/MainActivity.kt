package com.hexalitics.mvvm_starter.ui

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.hexalitics.mvvm_starter.R
import com.hexalitics.mvvm_starter.databinding.ActivityMainBinding
import com.hexalitics.mvvm_starter.di.network.Status
import com.hexalitics.mvvm_starter.ui.view_model.MainViewModel
import com.hexalitics.mvvm_starter.utils.AppProgressDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var mContext: Context
    private var dialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mContext = this
        dialog = Dialog(mContext)
        observeData()
        getLink()
    }

    private fun observeData() {

        viewModel.isLoading.observe(this) {
            if (it) {
                AppProgressDialog.show(dialog!!)
            } else {
                AppProgressDialog.hide(dialog)
                dialog = null
                dialog = Dialog(mContext)
            }
        }

        viewModel.resultData.observe(this){
            binding.tvContent.text = viewModel.resultContent()
        }
    }


    private fun getLink() {
        viewModel.getResultData().observe(this) {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        viewModel.isLoading.postValue(false)
                        viewModel.resultData.postValue(resource.data?.results)
                    }
                    Status.ERROR -> {
                        viewModel.isLoading.postValue(false)
                        val errorData = resource.message ?: getString(R.string.msg_api_error_msg)
                        Toast.makeText(mContext, errorData, Toast.LENGTH_SHORT).show()
                    }
                    Status.LOADING -> {
                        viewModel.isLoading.postValue(true)
                    }
                }
            }
        }
    }
}
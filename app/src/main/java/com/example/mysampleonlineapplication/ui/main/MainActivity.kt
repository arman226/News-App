package com.example.mysampleonlineapplication.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mysampleonlineapplication.R
import com.example.mysampleonlineapplication.ui.BaseActivity
import com.example.mysampleonlineapplication.ui.main.service.model.TeslaResponse
import javax.inject.Inject

class MainActivity : BaseActivity(), TeslaNavigator{

    @Inject
    lateinit var viewModel: TeslaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.navigator=this

        viewModel.getTesla()
    }

    override fun onShowTesla(teslaResponse: TeslaResponse) {
        Toast.makeText(this, teslaResponse.toString(), Toast.LENGTH_LONG).show()
    }

    override fun onError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }
}
package com.example.mysampleonlineapplication.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mysampleonlineapplication.R
import com.example.mysampleonlineapplication.data.network.tesla.Article
import com.example.mysampleonlineapplication.ui.BaseActivity
import com.example.mysampleonlineapplication.ui.main.adapter.TeslaAdapter
import com.example.mysampleonlineapplication.ui.main.service.model.TeslaResponse
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), TeslaNavigator{

    @Inject
    lateinit var viewModel: TeslaViewModel

    lateinit var adapter: TeslaAdapter

    private lateinit var articleList : ArrayList<Article>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.navigator=this
        viewModel.getTesla()
        articleList = ArrayList()
        adapter= TeslaAdapter(articleList)
        adapter.onItemClickListener = object : TeslaAdapter.OnItemClickListener {
            override fun onItemClick(selectedIndex: Int) {
                Toast.makeText(applicationContext, articleList[selectedIndex].title, Toast.LENGTH_LONG).show()
            }
        }
        rv_tesla.adapter=adapter

    }

    override fun onShowTesla(teslaResponse: TeslaResponse) {
        articleList.addAll(teslaResponse.articles)
        adapter.notifyDataSetChanged()
        rv_tesla.adapter?.notifyDataSetChanged()
    }

    override fun onError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }
}
package com.m0_0m.todayquote

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class QuoteEditAcivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quote_edit_activity)

        val pref = getSharedPreferences("quotes", Context.MODE_PRIVATE)
        val quotes = Quote.getQuotesFromPreference(pref)

        val layoutManager = LinearLayoutManager(this) //목록형

        val adapter = QuoteAdapter(quotes)

        val recyclerView = findViewById<RecyclerView>(R.id.quote_edit_list)
        //모든 요소의 크기가 똑같아서 true를 전달하면 내부적으로 추가적인 최적화를 진행함
        recyclerView.setHasFixedSize(false) //높이 차이 발생하므로 false
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }
}
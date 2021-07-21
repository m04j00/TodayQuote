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

        //20개 데이터 채우고 기존의 quotes 데이터랑 통합한 List 만들기
        val editQuotes = mutableListOf<Quote>()
        for(i in 0 until 20){
            editQuotes.add(Quote(i, "", ""))
        }

        //기존 내용 덮어쓰기
        for(q in quotes){
            editQuotes[q.idx].idx = q.idx
            editQuotes[q.idx].text = q.text
            editQuotes[q.idx].from = q.from
        }

        val recyclerView = findViewById<RecyclerView>(R.id.quote_edit_list)
        //모든 요소의 크기가 똑같아서 true를 전달하면 내부적으로 추가적인 최적화를 진행함
        recyclerView.setHasFixedSize(false) //높이 차이 발생하므로 false
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = QuoteEditAdapter(editQuotes)
    }
}
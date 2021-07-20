package com.m0_0m.todayquote

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class QuoteListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quote_list_activity)

        /*val data1 = intent.getIntExtra("data1", -1)
        val data2 = intent.getStringExtra("data2")*/

        //RecyclerView를 사용하기 위한 1번 준비 - 명언 데이터 가져오기
        val pref = getSharedPreferences("quotes", Context.MODE_PRIVATE)
        val quotes = Quote.getQuotesFromPreference(pref)

        //RecyclerView를 사용하기 위한 2번 준비 - 데이터 표시할 xml 레이아웃
        //RecyclerView를 사용하기 위한 3번 준비 - 레이아웃 메니저
        val layoutManager = LinearLayoutManager(this) //목록형

        //RecyclerView를 사용하기 위한 4, 5번 준비 - 뷰 홀더 클래스 + 어댑터 클래스
        val adapter = QuoteAdapter(quotes)

        val recyclerView = findViewById<RecyclerView>(R.id.quote_list)
        //모든 요소의 크기가 똑같아서 true를 전달하면 내부적으로 추가적인 최적화를 진행함
        recyclerView.setHasFixedSize(false) //높이 차이 발생하므로 false
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

    }
}
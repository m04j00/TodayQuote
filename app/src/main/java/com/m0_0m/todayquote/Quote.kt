package com.m0_0m.todayquote

import android.content.SharedPreferences

data class Quote(var idx: Int,
                 var text: String,
                 var from : String = ""){
    companion object{
        //Quote를 SharePreferences에 추가, 수정, 삭제하고 모든 명언 데이터를 반환해주는 클래스 메서드 정의

        //추가, 수정하는 메서드
        fun saveToPreference(pref : SharedPreferences,
                             idx: Int, text: String, from: String = "") : Quote{ // :Quote -- 반환 타입
            val editor = pref.edit()

            editor.putString("${idx}.text", text.trim())
            editor.putString("${idx}.from", from.trim())

            editor.apply()

            return Quote(idx, text, from)
        }

        //저장된 명언을 반환하는 메소드
        fun getQuotesFromPreference(pref: SharedPreferences) : MutableList<Quote>{
            val quotes = mutableListOf<Quote>()

            //0..20 -- 0  20
            //0 until 20 -- 0 ~ 19
            for(i in 0 until 20){
                // !! => nullable 타입을 non-nullable 타입으로 변환하는 연산자
                val quoteText = pref.getString("${i}.text", "")!!
                val quoteFrom = pref.getString("${i}.from", "")!!

                if(quoteText.isNotBlank()){
                    quotes.add(Quote(i, quoteText, quoteFrom))
                }
            }
            return quotes
        }
        //삭제 메서드
        fun removeQuoteFromPreference(pref: SharedPreferences, idx: Int) {
            val editor = pref.edit()

            editor.remove("${idx}.text")
            editor.remove("${idx}.from")

            editor.apply()
        }

    }
}
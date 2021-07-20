package com.m0_0m.todayquote

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.m0_0m.todayquote.Quote.Companion.getQuotesFromPreference
import java.util.*

class QuoteMainActivity : AppCompatActivity() {

    //private => 클래스 내부에서만 사용(접근 제어자)
    //lateinit => 나중에 초기화할 값을 지정하기 위해 사용
    //lateinit을 쓰는 이유는 null 대입가능으로 안만들면서 초기화는 나중에 하려고
    //액티비티의 생성자를 우리가 직접 호출할 수 없음
    //보통 초기화는 onCreate 생명주기 메소드에서 이루어짐
    //MutableList => ArraList
    private lateinit var quotes: MutableList<Quote>
    private lateinit var pref: SharedPreferences

    fun initializeQuotes(){
        //프리퍼런스 객체 가져오기
        //"quote.xml" 파일에 명언 데이터 저장

        //초기화 여부 확인
        val initialized = pref.getBoolean("initialized", false)

        if(!initialized){
            //초기화 로직
            Log.d("myapp", "초기화 작업 진행")
            Quote.saveToPreference(pref, 0, "괴로운 시련처럼 보이는 것이 뜻밖의 좋은 일일 때가 많다.", "오스카 와일드")
            Quote.saveToPreference(pref, 1, "성공한 사람이 되려고 노력하기보다 가치있는 사람이 되려고 노력하라.", "알버트 아인슈타인")
            Quote.saveToPreference(pref, 2, "추구할 수 있는 용기가 있다면 우리의 모든 꿈은 이뤄질 수 있다.", "월트 디즈니")
            Quote.saveToPreference(pref, 3, "실패에서부터 성공을 만들어 내라. 좌절과 실패는 성공으로 가는 가장 확실한 디딤돌이다.", "데일 카네기")
            Quote.saveToPreference(pref, 4, "창조적인 삶을 살려면 내가 틀릴지도 모른다는 공포를 버려야 한다.")

            //initialized 값 ture로 변환
            val editor = pref.edit()
            editor.putBoolean("initialized",true)
            editor.apply()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quote_main_activity)

        val quoteText = findViewById<TextView>(R.id.quote_text)
        val quoteFrom = findViewById<TextView>(R.id.quote_from)

        pref = getSharedPreferences("quotes", Context.MODE_PRIVATE)

        initializeQuotes()

        quotes = Quote.getQuotesFromPreference(pref)

        //lateinit 관련 값들을 초기화

        /*quotes.add(Quote(1, "명언 1", "출처 1"))
        quotes.add(Quote(2, "명언 2", "출처 2"))
        quotes.add(Quote(3, "명언 3", "출처 3"))
        */

        if(quotes.isNotEmpty()){
            val random = Random()
            val randomIndex = random.nextInt(quotes.size)
            val randomQuote = quotes[randomIndex]
            quoteText.text = randomQuote.text
            quoteFrom.text = randomQuote.from
        }
        else{
            quoteText.text = "저장된 명언이 없습니다."
            quoteFrom.text = ""
        }

        val quote_list_btn = findViewById<Button>(R.id.quote_list_btn)
        val quote_edit_btn = findViewById<Button>(R.id.quote_edit_btn)

        //액티비티 클래스가 Context 클래스를 상속받기 때문에 Context 객체가 필요한 시점에 엑티비티 객체를 전달해도 무방함
        //목적지가 있는 명시적 인텐트 생성

        quote_list_btn.setOnClickListener{
            val intent = Intent(this, QuoteListActivity::class.java)
            intent.putExtra("data1", 100)
            intent.putExtra("data", "Hello")
            startActivity(intent)
        }

        quote_edit_btn.setOnClickListener{
            val intent = Intent(this, QuoteEditAcivity::class.java)
            startActivity(intent)
        }

        /*
        //SharedPreferences 인터페이스 공부 p.544
        //앱의 설정 내용을 저장하기 위해서 사용하는 클래스
        //ex 음악 스트리밍앱 -> 설정 화면 -> 라이트 모드, 다크모드 설정, 볼륨 설정, 선호하는 장르 설정
        //ex 페이스북 -> 설정 화면 (푸시 여부)
        //데이터를 저장하는 용도로 사용 (내부적으로 SQLite)
        //대용량 데이터를 저장하기에 적합하지 않음
        // 내부족으로 xml 파일을 이용하여 데이터 저장, 대용량으로 저장할 필요성이 있을 경우 데이터베이스 이용

        //SharedPreferences => XML 파일 저장 읽기
        //SQLite => 데이터베이스 (대용량 데이터 저장 가능)

        val sp : SharedPreferences = getSharedPreferences("filename", Context.MODE_PRIVATE)

        //데이터 쓰기
        val editor : SharedPreferences.Editor = sp.edit()

        //"put 자료형" 이름의 메소드를 호출하여 데이터 저장
        //key는 문자열

        editor.putInt("key1", 1)
        editor.putBoolean("key2", true)
        editor.putString("key3", "Hello")

        editor.apply()

        val key1Value = sp.getInt("key1", -1) // -1 --> 대체값, 생략 불가능
        val key2Value = sp.getBoolean("key2", false)
        val key3Value = sp.getString("key3", "")

        Log.d("myapp", "${key1Value},${key2Value},${key3Value}")

        editor.remove("key3")

        Log.d("myapp", sp.contains("non-exist").toString())

        editor.clear()

         */
    }
}
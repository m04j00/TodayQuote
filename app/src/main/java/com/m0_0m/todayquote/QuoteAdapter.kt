package com.m0_0m.todayquote

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//RecyclerView를 사용하기 위한 4, 5번 준비 - 뷰 홀더 클래스, 어댑터 클래스
class QuoteAdapter(val dataList : List<Quote>)
    : RecyclerView.Adapter<QuoteAdapter.QuoteItemViewHolder>()
{
    // 뷰 홀터 클래스는 내부 클래스로 정의
    class QuoteItemViewHolder(val view : View) : RecyclerView.ViewHolder(view){ //view : 목록에 표시할 항목을 보여줄 때 사용할 뷰 객체
        lateinit var quote : Quote
        val quoteText = view.findViewById<TextView>(R.id.quote_text)
        val quoteFrom = view.findViewById<TextView>(R.id.quote_from)
        val quoteShareBtn = view.findViewById<Button>(R.id.quote_share_btn)
        val quoteFromSearchBtn = view.findViewById<Button>(R.id.quote_from_search_btn)

        init{
            quoteShareBtn.setOnClickListener{
                //암시적 인텐트
                val intent = Intent(Intent.ACTION_SEND)

                //인텐트 내용 채우기 (공유할 내용)
                intent.putExtra(Intent.EXTRA_TITLE, "힘이 되는 명언")
                intent.putExtra(Intent.EXTRA_SUBJECT, "힘이 되는 명언")
                intent.putExtra(Intent.EXTRA_TEXT, quote.text)
                intent.type = "text/plain"

                //어떤 앱으로 공유할지를 매번 띄울 수 있도록 chooser 만들기
                val chooser = Intent.createChooser(intent, "명언 공유")
                //startActivity 이용해서 공유할 앱 선택하는 액티비티 띄워주기
                it.context.startActivity(chooser)
            }

            quoteFromSearchBtn.setOnClickListener{
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=${quote.from}"))
                //val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:010-1234-1234"))

                it.context.startActivity(intent)
            }
        }
        fun bind(q: Quote){
           this.quote = q
           quoteText.text = quote.text
           quoteFrom.text = quote.from
        }

    }

    //onCreateViewHolder 역할 : 뷰 홀더 클래스를 생성해서 반환하는 역할
    //parent는 RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteItemViewHolder {
        //layoutInflater 역할 : 레이아웃 xml 파일의 식별자를 전달하면 뷰 객체 반환
        //모든 뷰 객체는 context 속성을 가지고 있음
        val inflater = LayoutInflater.from(parent.context)
        /*
        * inflate 메소드(
        * 첫번째 인자 - 레이아웃 리소스 식별자,
        * 두번째 인자 - 여기서 생성될 뷰 객체가 붙을 부모 뷰
        * 세번째 인자 - 지금 당장 뷰를 부착할 여부
        * )
        * 세번째 인자 무조건 flase : 직접 붙이는게 아니고 안드로이드 시스템에서 뷰 객체 붙여줌
        * */
        val view = inflater.inflate(viewType, parent, false)

        return QuoteItemViewHolder(view)
    }

    //
    override fun onBindViewHolder(holder: QuoteItemViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    //getItemCount 역할 : 보여줘야 할 목록 아이템의 개수를 반환하는 역할
    override fun getItemCount() = dataList.size
    /*
    override fun getItemCount(): Int {
        return dataList.size
    }
    */

    //getItemViewType 역할 : 사용할 레이아웃 리소스의 식별자를 반환하는 역할
    override fun getItemViewType(position: Int) : Int = R.layout.quote_list_item
    /*
    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
    */
}
package com.m0_0m.todayquote

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
package com.m0_0m.todayquote


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class QuoteEditAdapter(val dataList : List<Quote>)
    : RecyclerView.Adapter<QuoteEditAdapter.QuoteItemViewHolder>()
{
    class QuoteItemViewHolder(val view: View)
        : RecyclerView.ViewHolder(view)
    {
        lateinit var quote : Quote
        val quoteTextEdit = view.findViewById<EditText>(R.id.quote_text_edit)
        val quoteFromEdit = view.findViewById<EditText>(R.id.quote_from_edit)
        val quoteDeleteBtn = view.findViewById<Button>(R.id.quote_del_btn)
        val quoteModifyBtn = view.findViewById<Button>(R.id.quote_Modify_btn)

        init{

            val pref = view.context.getSharedPreferences("quotes", Context.MODE_PRIVATE)


            quoteDeleteBtn.setOnClickListener{
                quoteTextEdit.setText("")
                quoteFromEdit.setText("")
                quote.text = ""
                quote.from = ""

                Quote.removeQuoteFromPreference(pref, quote.idx)
                Toast.makeText(it.context, "삭제되었습니다.", Toast.LENGTH_SHORT).show()
            }

            quoteModifyBtn.setOnClickListener{
                quote.text = quoteTextEdit.text.toString()
                quote.from = quoteFromEdit.text.toString()

                Quote.saveToPreference(pref, quote.idx, quote.text, quote.from)
                Toast.makeText(it.context, "수정되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }
        fun bind(q: Quote) {
            this.quote = q
            // EditText의 경우에는 text 속성에 대입하는 방식으로 작동하지 않으므로,
            // setText 세터 메소드를 직접 호출해서 텍스트를 변경해야 함
            quoteTextEdit.setText(quote.text)
            quoteFromEdit.setText(quote.from)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)

        return QuoteItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuoteItemViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    override fun getItemViewType(position: Int): Int = R.layout.quote_edit_list_item
}





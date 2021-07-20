package com.m0_0m.todayquote

import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView

class QuoteEditAdapter (val dataList : List<Quote>)
    : RecyclerView.Adapter<QuoteAdapter.QuoteItemViewHolder>(){

        class QuoteItemViewHolder(val view : View) :RecyclerView.ViewHolder(view){
            lateinit var quote : Quote
            val quoteTextEdit = view.findViewById<EditText>(R.id.quote_text_edit)
            val quoteFromEdit = view.findViewById<EditText>(R.id.quote_from_edit)
            val quoteDelBtn = view.findViewById<EditText>(R.id.quote_del_btn)
            val quoteModifyBtn = view.findViewById<EditText>(R.id.quote_Modify_btn)

            fun bind(q: Quote){
                this.quote = q
                quoteTextEdit.setText(quote.text)
                quoteFromEdit.setText(quote.from)
            }

        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): QuoteAdapter.QuoteItemViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: QuoteAdapter.QuoteItemViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }


}
package uas.c14220049.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uas.c14220049.app.database.DataKesehatan

class AdapterKesehatan(private val dataKesehatan: MutableList<DataKesehatan>):
    RecyclerView.Adapter<AdapterKesehatan.ListViewHolder>() {

    class ListViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var _tvTanggal = view.findViewById<TextView>(R.id.tvTanggal)
        var _tvBB = view.findViewById<TextView>(R.id.tvBeratBadan)
        var _tvTekanan = view.findViewById<TextView>(R.id.tvTekanan)
        var _tvCatatan = view.findViewById<TextView>(R.id.tvCatatan)
    }

    fun isiData(listKesehatan: List<DataKesehatan>) {
        dataKesehatan.clear()
        dataKesehatan.addAll(listKesehatan)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.kesehatan_list, parent, false
        )
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataKesehatan.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        var data = dataKesehatan[position]

        holder._tvTanggal.setText(data.tanggal)
        holder._tvBB.setText(data.bb.toString())
        holder._tvTekanan.setText(data.tekanan.toString())
        holder._tvCatatan.setText(data.catatan.toString())
    }

}
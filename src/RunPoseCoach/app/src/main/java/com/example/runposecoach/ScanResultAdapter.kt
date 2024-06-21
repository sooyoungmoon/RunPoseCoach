package com.example.runposecoach
import android.bluetooth.le.ScanResult
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
//import kotlinx.android.synthetic.main.row_scan_result.view.*
import androidx.recyclerview.widget.RecyclerView

class ScanResultAdapter(
    private val items: List<ScanResult>,
    private val onClickListener: ((device: ScanResult) -> Unit)) :
    RecyclerView.Adapter<ScanResultAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(
        private val view: View,
        private val onClickListener: ((device: ScanResult) -> Unit)
    ) : RecyclerView.ViewHolder(view) {

        fun bind(result: ScanResult) {
            val dn_textView: TextView
            val ma_textView: TextView
            val ss_textView: TextView

            dn_textView = view.findViewById(R.id.device_name)
            ma_textView = view.findViewById(R.id.mac_address)
            ss_textView = view.findViewById(R.id.signal_strength)

            dn_textView.text = result.device.name ?: "Unnamed"
            ma_textView.text = result.device.address
            ss_textView.text = "${result.rssi} dBm"
            view.setOnClickListener { onClickListener.invoke(result) }
        }

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.row_scan_result, viewGroup, false)

        return ViewHolder(view, onClickListener)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val item = items[position]
        viewHolder.bind(item)
    }


    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = items.size

}


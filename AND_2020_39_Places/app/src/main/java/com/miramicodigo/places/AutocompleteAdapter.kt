package com.miramicodigo.places

import android.R
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.RectangularBounds
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse
import com.google.android.libraries.places.api.net.PlacesClient
import java.util.concurrent.ExecutionException
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class AutocompleteAdapter internal constructor(context: Context?, placesClient: PlacesClient) : ArrayAdapter<AutocompletePrediction>(context!!, R.layout.simple_expandable_list_item_2, R.id.text1), Filterable {
    private var mResultList: List<AutocompletePrediction>? = null
    private val placesClient: PlacesClient = placesClient

    override fun getCount(): Int {
        return mResultList!!.size
    }

    override fun getItem(position: Int): AutocompletePrediction {
        return mResultList!![position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val row: View = super.getView(position, convertView, parent)
        val item = getItem(position)
        val textView1: TextView = row.findViewById(R.id.text1)
        val textView2: TextView = row.findViewById(R.id.text2)
        textView1.text = item.getPrimaryText(null)
        textView2.text = item.getSecondaryText(null)
        return row
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val results = FilterResults()
                var filterData: List<AutocompletePrediction?>? = ArrayList()
                if (charSequence != null) {
                    filterData = getAutocomplete(charSequence)
                }
                results.values = filterData
                if (filterData != null) {
                    results.count = filterData.size
                } else {
                    results.count = 0
                }
                return results
            }

            override fun publishResults(charSequence: CharSequence?, results: FilterResults?) {
                try {
                    if (results != null && results.count > 0) {
                        mResultList = results.values as List<AutocompletePrediction>
                        notifyDataSetChanged()
                    } else {
                        notifyDataSetInvalidated()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun convertResultToString(resultValue: Any?): CharSequence {
                return if (resultValue is AutocompletePrediction) {
                    resultValue.getFullText(null)
                } else {
                    super.convertResultToString(resultValue)
                }
            }
        }
    }

    private fun getAutocomplete(constraint: CharSequence): List<AutocompletePrediction?>? {
        val bounds: RectangularBounds = RectangularBounds.newInstance(LatLng(-16.545617, -68.195454), LatLng(-16.469245, -68.090869))
        val requestBuilder: FindAutocompletePredictionsRequest.Builder = FindAutocompletePredictionsRequest.builder()
                .setQuery(constraint.toString())
                .setCountry("BO")
                .setLocationBias(bounds)
                //.setTypeFilter(TypeFilter.ADDRESS)
                .setSessionToken(AutocompleteSessionToken.newInstance())

        val results: Task<FindAutocompletePredictionsResponse?> = placesClient.findAutocompletePredictions(requestBuilder.build())

        try {
            Tasks.await(results, 60, TimeUnit.SECONDS)
        } catch (e: ExecutionException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        } catch (e: TimeoutException) {
            e.printStackTrace()
        }
        return if (results.isSuccessful) {
            if (results.result != null) {
                results.result!!.autocompletePredictions
            } else null
        } else {
            null
        }
    }

}
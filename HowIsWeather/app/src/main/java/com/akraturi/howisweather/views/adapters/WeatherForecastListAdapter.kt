package com.akraturi.howisweather.views.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.akraturi.howisweather.R
import com.akraturi.howisweather.data.models.Weather
import com.akraturi.howisweather.databinding.WeatherForecastListItemBinding
import com.akraturi.howisweather.utils.OpenWeatherMapApiUtils
import utils.AppLogger

class WeatherForecastListAdapter(private val mContext: Context, private val mList:MutableList<Weather>): RecyclerView.Adapter<WeatherForecastListAdapter.WeatherForecastViewHolder>() {

    private val TAG = WeatherForecastListAdapter::class.java.simpleName

    init {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherForecastViewHolder {
        val inflater = LayoutInflater.from(mContext)
        val binding = DataBindingUtil.inflate<WeatherForecastListItemBinding>(inflater, R.layout.weather_forecast_list_item,parent,false)
        return WeatherForecastViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return mList.size
    }

    override fun onBindViewHolder(holder: WeatherForecastViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    fun updateData(forecastList:List<Weather>){
        with(mList){
            clear()
            addAll(forecastList)
        }
        notifyDataSetChanged()
    }

    class WeatherForecastViewHolder(private val mBinding: WeatherForecastListItemBinding): RecyclerView.ViewHolder(mBinding.root) {

        private val TAG = WeatherForecastViewHolder::class.java.simpleName

        init {

        }

        fun bind(weather:Weather){
             AppLogger.logCurrentMethodName(TAG)
             mBinding.weather = weather
             mBinding.utils = OpenWeatherMapApiUtils
        }
    }
}
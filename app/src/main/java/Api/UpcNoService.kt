package Api

import com.example.poweractivity.Upc.Product
import com.example.poweractivity.Upc.UpcModel
import com.example.poweractivity.data.RapidModel

import retrofit2.Call
import retrofit2.http.*

interface UpcNoService {
    @GET("/request")
    fun upc(
        @Query("api_key") api_key: String,
        @Query("type") type: String,
        @Query("item_id") item_id: String,
        @Query("customer_zipcode")customer_zipcode:String
    ): Call<UpcModel>



    @GET("https://amazon-price1.p.rapidapi.com/search")
    fun asin(
        @Header("x-rapidapi-host")api_host:String,
        @Header("x-rapidapi-key")api_key: String,
        @Query("keywords")keywords:String,
        @Query("marketplace")market_place:String,
    ): Call<List<RapidModel>>
}
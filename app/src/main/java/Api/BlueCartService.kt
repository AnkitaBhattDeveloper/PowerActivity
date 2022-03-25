package Api

import com.example.ScanPower.data.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BlueCartService {
    @GET("/request")
    fun search(
        @Query("api_key") api_key: String,
        @Query("type") type: String,
        @Query("search_term") search_term: String,
        @Query("customer_zipcode") customer_zipcode: String,
        @Query("page") page: String,
    ):Call<SearchResponse>


    @GET("/request")
    fun storeId(
        @Query("api_key") api_key: String,
        @Query("type") type: String,
        @Query("store_id") store_id: String,
        @Query("search_term") search_term: String,
        @Query("page") page: String,
    ):Call<SearchResponse>


}
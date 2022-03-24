package Api

import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface KeepaApiService {
    @GET("/graphimage")
    fun keepa(
        @Query("key")key:String,
        @Query("domain")domain:String,
        @Query("asin") asin:String
    ): Call<ResponseBody>


}
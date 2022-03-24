package Api

import com.example.poweractivity.Utils.App
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UpcNoClient {
    companion object UpcRetrofit{
        val retrofitInstance = Retrofit.Builder()
            .baseUrl(App.UPC_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    }
}
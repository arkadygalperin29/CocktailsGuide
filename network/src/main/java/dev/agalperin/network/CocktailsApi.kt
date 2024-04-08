package dev.agalperin.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import dev.agalperin.network.models.CocktailDTO
import dev.agalperin.network.models.GlassDTO
import dev.agalperin.network.models.IngredientDTO
import dev.agalperin.network.models.IngredientDetailedDTO
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query


interface CocktailsApi {

    @GET("search.php")
    suspend fun getCocktailsByFirstLetter(@Query("f") letterSearch: String): Result<ApiResponse<CocktailDTO>>

    @GET("search.php")
    suspend fun getIngredientByIngredientsName(@Query("i") ingredientNameSearch: String): IngredientsResponse<List<IngredientDetailedDTO>>

    //list all cocktails by first letter www.thecocktaildb.com/api/json/v1/1/search.php?f=a
    @GET("filter.php?a=Alcoholic")
    suspend fun getCocktailsWithAlcohol(): ApiResponse<List<CocktailDTO>>

    @GET("filter.php?a=Non_Alcoholic")
    suspend fun getNonAlcoholicCocktails(): ApiResponse<List<CocktailDTO>>

    @GET("lookup.php")
    suspend fun getCocktailById(@Query("i") cocktailId: String): ApiResponse<List<CocktailDTO>>

    @GET("list.php?g=list")
    suspend fun getAllKindsOfGlasses(): ApiResponse<List<GlassDTO>>

    @GET("list.php?i=list")
    suspend fun getIngredientsList(): ApiResponse<List<IngredientDTO>>

    @GET("lookup.php")
    suspend fun getIngredientById(@Query("iid") ingredientId: String): IngredientsResponse<List<IngredientDetailedDTO>>
}


fun CocktailsApi(
    baseUrl: String,
    okHttpClient: OkHttpClient? = null,
    json: Json = Json { ignoreUnknownKeys = true },
    apiKey: String
): CocktailsApi {
    return retrofit("$baseUrl$apiKey", okHttpClient, json).create(CocktailsApi::class.java)
}

private fun retrofit(
    baseUrl: String,
    okHttpClient: OkHttpClient?,
    json: Json
): Retrofit {

    val jsonConverterFactory =
        json.asConverterFactory(contentType = "application/json".toMediaType())

    val loggingInterceptor = HttpLoggingInterceptor().apply {
        this.setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    val modifierOkhttpClient: OkHttpClient =
        (okHttpClient?.newBuilder() ?: OkHttpClient.Builder())
            .addInterceptor(loggingInterceptor)
            .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(modifierOkhttpClient)
        .addConverterFactory(jsonConverterFactory)
        .addCallAdapterFactory(ResultCallAdapterFactory.create())
        .run { if (okHttpClient != null) client(okHttpClient) else this }
        .build()
    return retrofit
}
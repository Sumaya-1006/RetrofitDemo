package com.example.retrofitdemo.service;


public class ApiRepositories {

    public static final String BASE_URL = "https://api.themoviedb.org";
    public static final String API_KEY = "7101f5fd5312cae06198b68a7737c325";



   /* public static Retrofit retrofit = null;

    public static ApiInterface  getApiInterface() {

       OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl url = request.url().newBuilder().addQueryParameter("api_key",API_KEY).build();
                request = request.newBuilder().url(url).build();
                return chain.proceed(request);
            }
        });
        httpClient.connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();

        OkHttpClient client = httpClient.build();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();


        }
        return retrofit.create(ApiInterface.class);
    }*/

}

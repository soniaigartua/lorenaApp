package com.example.pps_tudai.services.exerciseService;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ExerciseServiceCall {
    @GET("/api/v2/exerciseimage/")
    Call<ExerciseAPIResponse> getData(@Query("limit") int limit,
                                      @Query("format") String format);
}

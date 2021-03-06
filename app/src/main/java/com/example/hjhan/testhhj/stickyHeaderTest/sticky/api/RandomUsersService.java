package com.example.hjhan.testhhj.stickyHeaderTest.sticky.api;


import com.example.hjhan.testhhj.stickyHeaderTest.sticky.model.RandomUserResults;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Retrofit interface for http://api.randomuser.me/
 */
public interface RandomUsersService {

	@GET("/")
	Call<RandomUserResults> randomUsers(@Query("results") int results, @Query("nat") String nat, @Query("seed") String seed);

}

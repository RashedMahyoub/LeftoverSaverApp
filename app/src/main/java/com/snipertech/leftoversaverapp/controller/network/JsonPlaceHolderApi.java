package com.snipertech.leftoversaverapp.controller.network;

import com.snipertech.leftoversaverapp.model.Donor;
import com.snipertech.leftoversaverapp.model.Item;
import com.snipertech.leftoversaverapp.model.Needy;
import com.snipertech.leftoversaverapp.model.Order;
import com.snipertech.leftoversaverapp.model.Volunteer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {

    @GET("item/{name}/{amount}/update")
    Call<Item> updateItem(@Path("name") String name, @Path("amount") String amount);

    @POST("order/add")
    Call<Order> insertOrder(@Body Order order);

    @POST("needy/add")
    Call<Needy> insertNeedy(@Body Needy needy);

    @POST("donor/add")
    Call<Donor> insertDonor(@Body Donor donor);

    @POST("volunteer/add")
    Call<Volunteer> insertVolunteer(@Body Volunteer volunteer);

    @POST("item/add")
    Call<Item> insertItem(@Body Item item);

    @GET("needy/{id}/get")
    Call<Needy> getNeedy(@Path("id") String postId);

    @GET("needy/{username}/{password}/get")
    Call<Needy> searchNeedy(@Path("username") String username, @Path("password") String pass);

    @GET("donor/{id}/get")
    Call<Donor> getDonor(@Path("id") String postId);

    @GET("donor/{username}/{password}/get")
    Call<Donor> searchDonor(@Path("username") String username, @Path("password") String pass);

    @GET("volunteer/{id}/get")
    Call<Volunteer> getVolunteer(@Path("id") String postId);

    @GET("volunteer/{username}/{password}/get")
    Call<Volunteer> searchVolunteer(@Path("username") String username, @Path("password") String pass);

    @GET("order/{id}/get")
    Call<Order> getOrder(@Path("id") String postId);

    @GET("item/{id}/get")
    Call<Item> getItem(@Path("id") String postId);

    @GET("needy/getAll")
    Call<List<Needy>> getNeedyList();

    @GET("donor/getAll")
    Call<List<Donor>> getDonorList();

    @GET("volunteer/getAll")
    Call<List<Volunteer>> getVolunteerList();

    @GET("order/getAll")
    Call<List<Order>> getOrderList();

    @GET("donor/{city}/getCertain")
    Call<List<Donor>> filterDonor(@Path("city") String city);


    @GET("item/getAll")
    Call<List<Item>> getItemList();

    @GET("item/{id}/getCertain")
    Call<List<Item>> getItemLists(@Path("id") String Id);

    @GET("needy/{id}/del")
    Call<Needy> delNeedy(@Path("id") String postId);

    @GET("donor/{id}/del")
    Call<Donor> delDonor(@Path("id") String postId);

    @GET("volunteer/{id}/del")
    Call<Volunteer> delVolunteer(@Path("id") String postId);

    @GET("order/{id}/del")
    Call<Order> delOrder(@Path("id") String postId);

    @GET("item/{id}/{name}/del")
    Call<Item> delItem(@Path("id") String donorId, @Path("name") String itemName);

}

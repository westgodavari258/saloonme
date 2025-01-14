package com.saloonme.presenters;

import com.saloonme.interfaces.ApiInterface;
import com.saloonme.interfaces.IProfileView;
import com.saloonme.model.request.CancelRequest;
import com.saloonme.model.response.ProfileResponse;
import com.saloonme.model.response.RemoveCartResponse;
import com.saloonme.model.response.UserBookingDetailsResponse;
import com.saloonme.model.response.UserFeedPhotsResponse;
import com.saloonme.model.response.UserOrderDetailsResponse;
import com.saloonme.model.response.UserReviewsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePresenter {
    private ApiInterface apiInterface;
    private IProfileView iProfileView;

    public ProfilePresenter(ApiInterface apiInterface, IProfileView iProfileView) {
        this.apiInterface = apiInterface;
        this.iProfileView = iProfileView;
    }

    public void getProfileDetails(String userId, String token) {
        iProfileView.showProgressDialog("Getting Profile details....");
        Call<ProfileResponse> profileResponseCall = apiInterface.getProfile(userId, token);
        profileResponseCall.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                iProfileView.dismissProgress();
                iProfileView.getProfileDetailsSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                iProfileView.dismissProgress();
                iProfileView.getProfileDetailsFailed();
            }
        });
    }

    public void getUserBookingDetails(String userId) {
        iProfileView.showProgressDialog("Getting Booking details....");
        Call<UserBookingDetailsResponse> userBookingDetailsResponseCall = apiInterface.
                getUserBookings(userId);
        userBookingDetailsResponseCall.enqueue(new Callback<UserBookingDetailsResponse>() {
            @Override
            public void onResponse(Call<UserBookingDetailsResponse> call, Response<UserBookingDetailsResponse> response) {
                iProfileView.dismissProgress();
                iProfileView.getUserBookingDetailsSuccess(response.body());
            }

            @Override
            public void onFailure(Call<UserBookingDetailsResponse> call, Throwable t) {
                iProfileView.dismissProgress();
                iProfileView.getUserBookingDetailsFailed();
            }
        });
    }

    public void cancelBooking(CancelRequest cancelRequest) {
        iProfileView.showProgressDialog("Canceling Booking....");
        Call<RemoveCartResponse> cancelBookingCall = apiInterface.cancelBooking(cancelRequest);
        cancelBookingCall.enqueue(new Callback<RemoveCartResponse>() {
            @Override
            public void onResponse(Call<RemoveCartResponse> call, Response<RemoveCartResponse> response) {
                iProfileView.dismissProgress();
                iProfileView.cancelBookingSuccess(response.body());
            }

            @Override
            public void onFailure(Call<RemoveCartResponse> call, Throwable t) {
                iProfileView.dismissProgress();
                iProfileView.cancelBookingFailed();
            }
        });
    }

    public void rescheduleBooking(String bookingId, String date, String time) {
        iProfileView.showProgressDialog("Rescheduling Booking....");
        Call<RemoveCartResponse> rescheduleCall = apiInterface.rescheduleBooking(bookingId, date, time);
        rescheduleCall.enqueue(new Callback<RemoveCartResponse>() {
            @Override
            public void onResponse(Call<RemoveCartResponse> call, Response<RemoveCartResponse> response) {
                iProfileView.dismissProgress();
                iProfileView.reschduleBookingSuccess(response.body());
            }

            @Override
            public void onFailure(Call<RemoveCartResponse> call, Throwable t) {
                iProfileView.dismissProgress();
                iProfileView.reschduleBookingFailed();
            }

        });
    }

    public void getUserReviews(String userId) {
        iProfileView.showProgressDialog("Loading....");
        Call<UserReviewsResponse> userReviewsResponseCall = apiInterface.getUserReviews(userId);
        userReviewsResponseCall.enqueue(new Callback<UserReviewsResponse>() {
            @Override
            public void onResponse(Call<UserReviewsResponse> call, Response<UserReviewsResponse> response) {
                iProfileView.dismissProgress();
                iProfileView.getUserReviewsSuccess(response.body());
            }

            @Override
            public void onFailure(Call<UserReviewsResponse> call, Throwable t) {
                iProfileView.dismissProgress();
                iProfileView.getUserReviewsFailed();
            }
        });
    }
    public void getUserFeedPhotos(String userId) {
        iProfileView.showProgressDialog("Loading....");
        Call<UserFeedPhotsResponse> userFeedPhotosResponseCall = apiInterface.getFeedPhotos(userId);
        userFeedPhotosResponseCall.enqueue(new Callback<UserFeedPhotsResponse>() {
            @Override
            public void onResponse(Call<UserFeedPhotsResponse> call, Response<UserFeedPhotsResponse> response) {
                iProfileView.dismissProgress();
                iProfileView.getUserFeedPhotsSuccess(response.body());
            }

            @Override
            public void onFailure(Call<UserFeedPhotsResponse> call, Throwable t) {
                iProfileView.dismissProgress();
                iProfileView.getUserFeedPhotsFailed();
            }
        });
    }

    public void getOrderDetails(String userId) {
        iProfileView.showProgressDialog("Loading....");
        Call<UserOrderDetailsResponse> userFeedPhotosResponseCall = apiInterface.getOrderDetails(userId);
        userFeedPhotosResponseCall.enqueue(new Callback<UserOrderDetailsResponse>() {
            @Override
            public void onResponse(Call<UserOrderDetailsResponse> call, Response<UserOrderDetailsResponse> response) {
                iProfileView.dismissProgress();
                iProfileView.getUserOrderDetailSuccess(response.body());
            }

            @Override
            public void onFailure(Call<UserOrderDetailsResponse> call, Throwable t) {
                iProfileView.dismissProgress();
                iProfileView.getUserFeedPhotsFailed();
            }
        });
    }
}

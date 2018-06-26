package ewaybill.nectar.com.ewaybill.retrofit;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ewaybill.nectar.com.ewaybill.utils.AppConstants;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by abhishek on 25/07/17.
 */

public interface RetroAPIInterface {


    /*@GET(AppConstants.BASE_URL + "user_signup.php")
    Call<JsonObject> callSignUpAPI(@Query("name") String name,
                                   @Query("username") String username,
                                   @Query("password") String password,
                                   @Query("emailid") String emailID,
                                   @Query("GSTIN") String gstn,
                                   @Query("contactno") int contactNumber);*/

    @FormUrlEncoded
    @POST(AppConstants.BASE_URL + "user_signup.php")
    Call<JsonObject> callSignUpAPI(@Field("name") String name,
                                          @Field("username") String username,
                                          @Field("password") String password,
                                          @Field("emailid") String emailID,
                                          @Field("GSTIN") String gstn,
                                          @Field("contactno") String contactNumber,
                                          @Field("user_type") String userType,
                                          @Field("address") String address,
                                          @Field("state") String state,
                                          @Field("pincode") String pincode);

    @FormUrlEncoded
    @POST(AppConstants.BASE_URL + "client_registration.php")
    Call<JsonObject> callClientRegistrationAPI(@Field("client_GSTIN") String gstin,
                                   @Field("client_name") String name,
                                   @Field("client_contactno") String mobileno,
                                   @Field("client_emailid") String email,
                                   @Field("client_user_type") String usertype,
                                   @Field("client_address") String place,
                                   @Field("client_pincode") String pincode,
                                   @Field("client_state") String state,
                                   @Field("user_id") String userid);



    @FormUrlEncoded
    @POST(AppConstants.BASE_URL + "supplier_registration.php")
    Call<JsonObject> callSupplierRegistrationAPI(@Field("supplier_GSTIN") String gstin,
                                   @Field("supplier_name") String name,
                                   @Field("supplier_contactno") String mobileno,
                                   @Field("supplier_emailid") String email,
                                   @Field("supplier_user_type") String usertype,
                                   @Field("supplier_address") String place,
                                   @Field("supplier_pincode") String pincode,
                                   @Field("supplier_state") String state,
                                   @Field("user_id") String userid);

    @FormUrlEncoded
    @POST(AppConstants.BASE_URL + "transporter_registration.php")
    Call<JsonObject> callTransporterRegistrationAPI(@Field("transporter_GSTIN") String gstin,
                                                    @Field("transporter_name") String name,
                                                    @Field("transporter_contactno") String mobileno,
                                                    @Field("transporter_emailid") String email,
                                                    @Field("transporter_user_type") String usertype,
                                                    @Field("transporter_address") String place,
                                                    @Field("transporter_pincode") String pincode,
                                                    @Field("transporter_state") String state,
                                                    @Field("user_id") String userid);



    /**
     * User Login and Registration
     */
    @FormUrlEncoded
    @POST(AppConstants.BASE_URL + "user_login.php")
    Call<JsonObject> callLoginAPI(@Field("emailid") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST(AppConstants.BASE_URL + "user_1signup.php")
    Call<JsonObject> callEmailRegistrationAPI(@Field("emailid") String emailID, @Field("user_type") String userType);

    @FormUrlEncoded
    @POST(AppConstants.BASE_URL + "send_otp.php")
    Call<JsonObject> callOTPValueAPI(@Field("OTP") String emailID);

    @FormUrlEncoded
    @POST(AppConstants.BASE_URL + "send_otp.php")
    Call<JsonObject> callRequestForForgotPwdAPI(@Field("emailid") String emailID);


    @FormUrlEncoded
    @POST(AppConstants.BASE_URL + "update_password.php")
    Call<JsonObject> callRequestForSetNewPwdAPI(@Field("user_id") String userId,@Field("password") String password);


    @FormUrlEncoded
    @POST(AppConstants.BASE_URL + "get_all_clients.php")
    Call<JsonObject> callClientListAPI(@Field("user_id") String userid);

    @FormUrlEncoded
    @POST(AppConstants.BASE_URL + "get_all_suppliers.php")
    Call<JsonObject> callSupplierListAPI(@Field("user_id") String userid);

    @FormUrlEncoded
    @POST(AppConstants.BASE_URL + "get_all_transporters.php")
    Call<JsonObject> callTransporterListAPI(@Field("user_id") String userid);

    @FormUrlEncoded
    @POST(AppConstants.BASE_URL + "get_all_e_tickets.php")
    Call<JsonObject> callInvoiceProcessListAPI(@Field("user_id") String userid);


    @Multipart
    @POST(AppConstants.BASE_URL + "eway_ticket_generation.php")
    Call<JsonObject> callUploadInvoiceAPi(@Part("user_id") RequestBody userId, @Part("transporter_id") RequestBody transporterId,
                                          @Part("invoice_title") RequestBody title,
                                          @Part("e_way_duration") RequestBody duration,
                                          @Part("remark") RequestBody remark,
                                          @Part MultipartBody.Part file);

    //String profileTag = "profiles/{profileId}";

   /* *//*--------------------APK Version ----------------------------------------------------------*//*
    @GET(AppConstants.BASE_URL + "apk_version")
    Call<JsonObject> callAPKVersionAPI();

    *//*-----------------------------Social Media API Integration---------------------------------*//*
    @GET(AppConstants.BASE_URL + "social/login/{provider}")
    Call<JsonObject> callSocialFBMediaAPI(@Path("provider") String providerName,
                                          @Query("name") String name,
                                          @Query("email") String email,
                                          @Query("id") String id,
                                          @Query(value = "avatar_original", encoded = true) String picture,
                                          @Query("token") String token,
                                          @Query("invite_code") String inviteCode,
                                          @Query("user[link]") String link);

    *//*--------------------------On Boarding API---------------------------------------------------*//*

    // Update profile, handle etc
    @FormUrlEncoded
    @POST(AppConstants.BASE_URL + "profile/{id}")
    Call<JsonObject> callUpdateProfile(@Path("id") int id, @FieldMap Map<String, String> hashFields
            , @Query("_method") String patch);

    @Multipart
    @POST(AppConstants.BASE_URL + "profile/{id}")
    Call<JsonObject> callUploadProfilePicAPI(@Path("id") int id, @Part("profile[onboarding_step]") RequestBody onBoardingStep,
                                             @Part MultipartBody.Part file, @Query("_method") String patch);

    @FormUrlEncoded
    @POST(AppConstants.BASE_URL + "profile/phoneVerify")
        // get otp
    Call<JsonObject> callPhoneVerificationAPI(@FieldMap Map<String, String> hashFields, @Query("_method") String patch);

    @FormUrlEncoded
    @POST(AppConstants.BASE_URL + "profile/requestOtp") // resend otp
    Call<JsonObject> callOTP(@Field("otp") String otp, @Field("profile[onboarding_step]") String onboardingStep);

    @FormUrlEncoded
    @POST(AppConstants.BASE_URL + "verifyInviteCode")
    Call<JsonObject> callInviteCode(@Field("invite_code") String inviteCode);

    @GET(AppConstants.BASE_URL + "autocomplete/filter/{model}/{key}")
    Call<JsonObject> callSKillsAPI(@Path("model") String model,
                                   @Path("key") String key);

    @GET(AppConstants.BASE_URL + "onboarding/skills")
    Call<JsonObject> callSKillsAPI();

    @GET(AppConstants.BASE_URL + "autocomplete/filter/{model}/{key}")
    Call<JsonObject> callSearchSKillsAPI(@Path("model") String model,
                                         @Path("key") String key,
                                         @Query("term") String search);

    @GET(AppConstants.BASE_URL + "people/onboarding")
    Call<JsonObject> callOnBoardingPeopleAPI();

    @FormUrlEncoded
    @POST(AppConstants.BASE_URL + "newsletters")
    Call<JsonObject> callBetaAccess(@Field("email") String email);

    *//*------------------------------ Preview -----------------------------------------------------*//*
    @FormUrlEncoded
    @POST(AppConstants.BASE_URL + "preview")
    Call<JsonObject> callPreviewAPI(@Field("url") String url);

    @GET(AppConstants.BASE_URL + "preview/{model_type}/{model_id}")
    Call<JsonObject> callGeneratePreviewAPI(@Path("model_type") String modelType, @Path("model_id") int modelID);

    @GET(AppConstants.BASE_URL + "preview/{model_type}/{model_id}/shared/{share_id}")
    Call<JsonObject> callGeneratePreviewShareAPI(@Path("model_type") String modelType, @Path("model_id") int modelID,
                                                 @Path("share_id") int shareID);

    *//*------------------------------ Invite Friends -----------------------------------------------------*//*
    @FormUrlEncoded
    @POST(AppConstants.BASE_URL + "invites")
    Call<JsonObject> callInviteFriendsAPI(@Field("email[0][email]") String email,
                                          @Field("email[0][name]") String name);
*/
    /*-----------------------------Login/Register API Integration---------------------------------*/

    /**
     * User Login and Registration
     */
    /*@FormUrlEncoded
    @POST(AppConstants.BASE_URL + "login")
    Call<JsonObject> callLoginAPI(@Field("email") String emailID, @Field("password") String password);*/

    /*@POST(AppConstants.BASE_URL + "user/register")
    Call<JsonObject> callRegisterAPI(@Body() JsonObject user);

    *//*-----------------------------FCM API Integration---------------------------------*//*
    @FormUrlEncoded
    @POST(AppConstants.BASE_URL + "user/fcmToken")
    Call<JsonObject> callFCMAPI(@Field("fcm_token") String fcmToken);

    *//*-----------------------------Logout API Integration---------------------------------*//*
    @FormUrlEncoded
    @POST(AppConstants.BASE_URL + "logout")
    Call<JsonObject> callLogOutPI(@Field("fcm_token") String fcmToken);

    *//**********************************************************************************************//*

    *//*----------------------------Feed API Integration--------------------------------------------*//*

    *//*
    * Create a new Feed or post shoutout, photos,recipes for profile
    * *//*
    @FormUrlEncoded
    @POST(AppConstants.BASE_URL + "shoutout")
    Call<JsonObject> callShoutoutPostAPI(@Field("profile_id") int profile_id,
                                         @Field("privacy_id") int privacy_id, @Field("content") String content);

    @FormUrlEncoded
    @POST(AppConstants.BASE_URL + "shoutout")
    Call<JsonObject> callShoutoutPostWithPreviewAPI(@Field("profile_id") int profile_id,
                                                    @Field("privacy_id") int privacy_id,
                                                    @Field("content") String content,
                                                    @Field("preview[image]") String previewImage,
                                                    @Field("preview[description]") String previewDescription,
                                                    @Field("preview[site_name]") String previewSiteName,
                                                    @Field("preview[title]") String previewTitle,
                                                    @Field("preview[type]") String previewType,
                                                    @Field("preview[url]") String previewURL);

    @Multipart
    @POST(AppConstants.BASE_URL + profileTag + "/photos")
    Call<JsonObject> callPostPhotoAPI(@Path("profileId") int profile_id, @Part("privacy_id") RequestBody privacy_id,
                                      @Part("caption") RequestBody caption,
                                      @Part MultipartBody.Part file);


    *//*
     * Create a new Feed or post shoutout, photos,recipes for company
     * *//*
    @FormUrlEncoded
    @POST(AppConstants.BASE_URL + "shoutout")
    Call<JsonObject> callShoutoutCompanyPostAPI(@Field("company_id") int profile_id,
                                                @Field("privacy_id") int privacy_id, @Field("content") String content);

    @FormUrlEncoded
    @POST(AppConstants.BASE_URL + "shoutout")
    Call<JsonObject> callShoutoutPostCompanyWithPreviewAPI(@Field("company_id") int profile_id,
                                                           @Field("privacy_id") int privacy_id,
                                                           @Field("content") String content,
                                                           @Field("preview[image]") String previewImage,
                                                           @Field("preview[description]") String previewDescription,
                                                           @Field("preview[site_name]") String previewSiteName,
                                                           @Field("preview[title]") String previewTitle,
                                                           @Field("preview[type]") String previewType,
                                                           @Field("preview[url]") String previewURL);

    @Multipart
    @POST(AppConstants.BASE_URL + profileTag + "/companies/{company_id}" + "/photos")
    Call<JsonObject> callPostPhotoCompanyAPI(@Path("profileId") int profile_id, @Path("company_id") int companyID,
                                             @Part("privacy_id") RequestBody privacy_id,
                                             @Part("caption") RequestBody caption,
                                             @Part MultipartBody.Part file);


*//*----------------------------Open Particular Post API Integration--------------------------------------------*//*

    @GET(AppConstants.BASE_URL + "{model_type}/{model_id}")
    Call<JsonObject> callFeedTypeAPI(@Path("model_type") String modelType, @Path("model_id") int modelID);

    @GET(AppConstants.BASE_URL + profileTag + "/{model_type}/{model_id}")
    Call<JsonObject> callPhotosProfileAPI(@Path("profileId") int profile_id, @Path("model_type") String modelType, @Path("model_id") int modelID);

    @GET(AppConstants.BASE_URL + "share/{model_type}/{share_id}/{model_id}/")
    Call<JsonObject> callFeedTypeShareAPI(@Path("model_type") String modelType, @Path("share_id") int sharedID, @Path("model_id") int modelID);

    @GET(AppConstants.BASE_URL + "share/{model_type}/{model_id}")
    Call<JsonObject> callPhotosProfileShareAPI(@Path("profileId") int profile_id, @Path("model_type") String modelType, @Path("model_id") int modelID);

    *//**********************************************************************************************//*

    *//*----------------------------Post(Like/Delete/Share) API Integration-------------------------*//*
    *//*
    * Like a Post
    * *//*
    @FormUrlEncoded
    @POST(AppConstants.BASE_URL + "{model_type}/{model_id}/like")
    // used for Shoutout and Collaboration Like
    Call<JsonObject> callShoutoutCollaborationLikeAPI(@Path("model_type") String type, @Path("model_id") int id, @Field("profile_id") int profile_id);

    @POST(AppConstants.BASE_URL + profileTag + "/{model_type}s" + "/{model_id}/like")
        // used for all except shout and collaboration
    Call<JsonObject> callModelLikeAPI(@Path("model_type") String type, @Path("model_id") int model_id, @Path("profileId") int profile_id);

    @FormUrlEncoded
    @POST(AppConstants.BASE_URL + "share/{model_type}/{model_id}/like")
    Call<JsonObject> callSharedModelLikeAPI(@Path("model_type") String model_type, @Path("model_id") int model_id, @Field("profile_id") int profile_id);

    *//*
    * Delete a post
    * *//*
    @DELETE(AppConstants.BASE_URL + "share/{model_type}/{model_id}")
    Call<JsonObject> callSharedModelDeleteAPI(@Path("model_type") String model_type, @Path("model_id") int model_id);

    @DELETE(AppConstants.BASE_URL + "{model_type}/{model_id}")
    Call<JsonObject> callShoutoutCollaborationDeleteAPI(@Path("model_type") String model_type, @Path("model_id") int id);

    @DELETE(AppConstants.BASE_URL + profileTag + "/{model_type}" + "s/{model_id}")
    Call<JsonObject> callModelDeleteAPI(@Path("profileId") int profile_id, @Path("model_type") String type, @Path("model_id") int model_id);

    @DELETE(AppConstants.BASE_URL + profileTag + "/companies/{company_id}" + "/{model_type}" + "s/{model_id}")
    Call<JsonObject> callModelDeleteCompanyAPI(@Path("profileId") int profile_id,
                                               @Path("company_id") int companyID,
                                               @Path("model_type") String type, @Path("model_id") int model_id);

    *//* For collaborate delete api*//*
    @DELETE(AppConstants.BASE_URL + profileTag + "/{model_type}" + "/{model_id}")
    Call<JsonObject> callCollaborateDeleteAPI(@Path("profileId") int profile_id, @Path("model_type") String type, @Path("model_id") int model_id);

    @DELETE(AppConstants.BASE_URL + profileTag + "/companies/{company_id}" +"/{model_type}" + "/{model_id}")
    Call<JsonObject> callCollaborateDeleteCompanyAPI(@Path("profileId") int profile_id,
                                                     @Path("company_id") int companyID,
                                                     @Path("model_type") String type, @Path("model_id") int model_id);

    *//*
    * Share a post
    * *//*
    @FormUrlEncoded
    @POST(AppConstants.BASE_URL + "share/{model_type}/{model_id}")
    Call<JsonObject> callShareAPI(@Path("model_type") String type, @Path("model_id") int model_id,
                                  @Field("privacy_id") int privacy_id, @Field("profile_id") int profile_id);

    *//*Share post as a message*//*
    @FormUrlEncoded
    @POST(AppConstants.BASE_URL+"chatShareMessage")
    Call<JsonObject> callShareAsMessageAPI(@FieldMap Map<String, String> fields);

    *//* Liked BY API *//*
    @GET(AppConstants.BASE_URL + "peopleLiked/{modelname}/{id}")
    Call<JsonObject> callLikedByAPI(@Path("modelname") String modelname, @Path("id") int id, @Query("page") int page);

    *//**********************************************************************************************//*

    *//*----------------------------Search API Integration------------------------------------------*//*
    *//*
    * Search API
    * *//*
    @GET(AppConstants.BASE_URL + "search")
    Call<JsonObject> callSearchAPI(@Query("q") String query, @Query("page") int page);

    *//*Search Type API*//*
    @GET(AppConstants.BASE_URL + "search/{type}")
    Call<JsonObject> callSearchTypeAPI(@Path("type") String type, @Query("q") String query, @Query("page") int page);

    *//*
    * All see pages - Search API
    * *//*
    @GET(AppConstants.BASE_URL + "{type}")
    Call<JsonObject> callAllSeePagesAPI(@Path("type") String type, @Query("page") int page);


    *//**********************************************************************************************//*

    *//*---------------------Feed/Chats/Notifications(GET) API Integration--------------------------*//*
    *//*
    * Fetch all the feed
    * *//*
    @GET(AppConstants.BASE_URL + "feed")
    Call<JsonObject> callFeedAPI(@Query("page") int page);

    *//**
     * Fetch all the notifications
     *//*
    @GET(AppConstants.BASE_URL + "notifications")
    Call<JsonObject> callNotificationAPI(@Query("page") int page);

    *//**
     * Notifications seen API on click of notifications icon
     *//*
    @POST(AppConstants.BASE_URL + "notifications/{type}/seen")
    Call<JsonObject> callNotificationSeenAPI(@Path("type") String type);

    *//**
     * Fetch all Read notifications
     *//*
    @POST(AppConstants.BASE_URL + "notifications/read/{id}")
    Call<JsonObject> callNotificationReadAPI(@Path("id") String notificationID);

    *//**
     * Fetch all Read notifications
     *//*
    @GET(AppConstants.BASE_URL + "notifications/unread")
    Call<JsonObject> callNotificationUnReadAPI();

    *//**
     * Mark all as Read notifications
     *//*
    @POST(AppConstants.BASE_URL + "notifications/markAllAsRead")
    Call<JsonObject> callNotificationAllReadAPI();

    *//**********************************************************************************************//*

    *//*----------------------------Comment(Get/Post) API Integration-------------------------------*//*
    *//*
    * Fetch comments
    * *//*
    @GET(AppConstants.BASE_URL + "comments/{type}/{id}")
    Call<JsonObject> callCommentAPI(@Path("type") String type, @Path("id") int id, @Query("page") int page);

    *//*
    * Post a comment on a post
    * *//*
    @FormUrlEncoded
    @POST(AppConstants.BASE_URL + "comments/{type}/{id}")
    Call<JsonObject> callPostCommentAPI(@Path("type") String type, @Path("id") int id, @Field("content") String content,
                                        @Field("privacy_id") int privacyId, @Field("profile_id") int profileId);

    *//*
     * Delete comments
     * *//*
    @DELETE(AppConstants.BASE_URL + "comments/{commentID}")
    Call<JsonObject> callDeleteCommentAPI(@Path("commentID") int commentID);

    *//**********************************************************************************************//*

    *//*----------------------------Chat API Integration--------------------------------------------*//*
    *//*
    * Start a new Chat or group
    * *//*
    @Multipart
    @POST(AppConstants.BASE_URL + "chats")
    Call<JsonObject> callCreateChatOrGroupAPI(@Part("profile_id") int[] profiles, @Part("name") RequestBody name, @Part("image") RequestBody image);

    *//*
    * Fetch all the existing chatroom
    * *//*
    @GET(AppConstants.BASE_URL + "chats")
    Call<JsonObject> callChatAPI(@Query("page") int page);

    *//*
     * Fetch all the messages of a chat
     * *//*
    @GET(AppConstants.BASE_URL + "chats/{id}/messages")
    Call<JsonObject> callMessageAPI(@Path("id") int chatID, @Query("page") int page);

    *//*
    * Upload image through chat
    * *//*
    @Multipart
    @POST(AppConstants.BASE_URL + "chats/{id}/messages")
    Call<JsonObject> callUploadImageInChatAPI(@Path("id") int chatID, @Part MultipartBody.Part file);

    *//*
    * Mark a message as read
    * *//*
    @POST(AppConstants.BASE_URL + "chats/{chatId}/messages/{msgId}/markRead")
    Call<JsonObject> callMessageReadAPI(@Path("chatId") int chatId, @Path("msgId") int msgId);

    *//*
     * Delete Chat (individual or group both)
     * *//*
    @DELETE(AppConstants.BASE_URL + "chats/{id}")
    Call<JsonObject> callDeleteChatAPI(@Path("id") int chatID);

    *//*
    * Add members for chat
    * *//*
    @POST(AppConstants.BASE_URL + "chats/{id}/members")
    Call<JsonObject> callAddMembersChatAPI(@Path("id") int chatID, @Query("profile_id[]") int[] profiles);

    *//*
  * get group members for chat
  * *//*
    @GET(AppConstants.BASE_URL + "chats/{id}/members")
    Call<JsonObject> callGroupMembersChatAPI(@Path("id") int chatID);

    *//*
    *//*
   * Add admin for chat
   * *//*
    @POST(AppConstants.BASE_URL + "chats/{id}/members/addAdmin")
    Call<JsonObject> callAddAdminChatAPI(@Path("id") int chatID, @Query("profile_id[]") int[] profiles);

    *//*
   * Remove members from group chat
   * *//*
    @DELETE(AppConstants.BASE_URL + "chats/{id}/members/{profileID}")
    Call<JsonObject> callRemoveGroupMembersChatAPI(@Path("id") int chatID, @Path("profileID") int profileID);

    *//*
     Edit group image
   * *//*
    @Multipart
    @POST(AppConstants.BASE_URL + "chats/{chatID}")
    Call<JsonObject> callUploadGroupImageAPI(@Path("chatID") int chatID, @Part("name") RequestBody name,
                                             @Part("isSingle") int isSingle,
                                             @Part("image") RequestBody image, @Query("_method") String patch);

    *//**********************************************************************************************//*

    *//*----------------------------Profile API Integration-----------------------------------------*//*

    *//*
    * Profile Details Fetching API
    * *//*
    @GET(AppConstants.BASE_URL + "profile/{id}")
    Call<JsonObject> callProfileAPI(@Path("id") String id);

    *//*
    * Fetch Followers OR Following List API with pagination
    * *//*
    @GET(AppConstants.BASE_URL + "profile/{id}/{type}")
    // id will be your own id as well as follower id and type will be either followers or following
    Call<JsonObject> callFollowersOrFollowingListAPI(@Path("id") int id, @Path("type") String type,
                                                     @Query("page") int page);

    *//*
 * Fetch All Followers List API without pagination
 * *//*
    @GET(AppConstants.BASE_URL + "profile/allFollowers")
    // id will be your own id as well as follower id and type will be either followers or following
    Call<JsonObject> callFollowersListAPI();

    *//*
    * Follow and UnFollow API
    * *//*
    @FormUrlEncoded
    @POST(AppConstants.BASE_URL + "profile/{type}")
    // type either follow or unfollow keyword
    Call<JsonObject> callFollowOrUnFollowAPI(@Path("type") String type, @Field("id") int id);

    *//*
    *  Post,Update & Delete Profile Categories Info : category_type will be experiences, awards, tvshows etc.
    *//*

    @POST(AppConstants.BASE_URL + profileTag + "/{category_type}")
    Call<JsonObject> sendProfileCategoryAPI(@Path("profileId") int profileId
            , @Path("category_type") String categoryType, @Body JsonObject obj);

    @PUT(AppConstants.BASE_URL + profileTag + "/{category_type}/{category_Id}")
    Call<JsonObject> updateProfileCategoryAPI(@Path("profileId") int profileId
            , @Path("category_type") String categoryType
            , @Path("category_Id") int categoryId, @Body JsonObject obj);

    @DELETE(AppConstants.BASE_URL + profileTag + "/{category_type}/{category_Id}")
    Call<JsonObject> deleteProfileCategoryAPI(@Path("profileId") int profileId,
                                              @Path("category_type") String categoryType
            , @Path("category_Id") int categoryId);


    *//**********************************************************************************************//*

    *//* ---------------------------- Company API Integration---------------------------------------*//*

    *//*
    * Profile Details Fetching API
    * *//*
    @GET(AppConstants.BASE_URL + "companies/{id}")
    Call<JsonObject> callCompanyProfileDetailsAPI(@Path("id") int id);

    *//*
    *  Create Profile - Company
    * *//*
    @FormUrlEncoded
    @POST(AppConstants.BASE_URL + profileTag + "/companies")
    Call<JsonObject> callProfileCompanyAPI(@Path("profileId") int profileId
            , @Field("name") String companyName, @Field("email") String emailID);

    *//*
    * Update Profile - Company
    *//*
    @PATCH(AppConstants.BASE_URL + profileTag + "/companies/{company_id}")
    Call<JsonObject> updateProfileCompanyAPI(@Path("profileId") int profileId
            , @Path("company_id") int companyID, @Body JsonObject obj);

    *//*
    *  Post, update & delete Company Categories - patents, awards and publications
    * *//*
    @POST(AppConstants.BASE_URL + profileTag + "/companies/{company_id}/{category_type}")
    Call<JsonObject> sendCompanyCategoryAPI(@Path("profileId") int profileId, @Path("company_id") int companyID
            , @Path("category_type") String categoryType, @Body JsonObject obj);

    @PUT(AppConstants.BASE_URL + profileTag + "/companies/{company_id}/{category_type}/{category_Id}")
    Call<JsonObject> updateCompanyCategoryAPI(@Path("profileId") int profileId, @Path("company_id") int companyID
            , @Path("category_type") String categoryType
            , @Path("category_Id") int categoryId, @Body JsonObject obj);

    @DELETE(AppConstants.BASE_URL + profileTag + "/companies/{company_id}/{category_type}/{category_Id}")
    Call<JsonObject> deleteCompanyCategoryAPI(@Path("profileId") int profileId, @Path("company_id") int companyID,
                                              @Path("category_type") String categoryType
            , @Path("category_Id") int categoryId);


    *//* Follow & UnFollow API - Company Profile //category_type will be follow or unfollow
    * *//*
    @POST(AppConstants.BASE_URL + profileTag + "/companies/{company_id}/{category_type}")
    Call<JsonObject> unOrFollowCompanyCategoryAPI(@Path("profileId") int profileId, @Path("company_id") int companyID
            , @Path("category_type") String categoryType);

    *//*
     * Fetch Followers OR Following List API
     * *//*
    @GET(AppConstants.BASE_URL + profileTag + "/companies/{company_id}/{category_type}")
    Call<JsonObject> followersOrFollowingCompanyCatgAPI(@Path("profileId") int profileId, @Path("company_id") int companyID
            , @Path("category_type") String categoryType, @Query("page") int page);

    *//*
    * Get Company Photos API
    * *//*
    @GET(AppConstants.BASE_URL + profileTag + "/companies/{company_id}/photos")
    Call<JsonObject> callGetCompanyPhotosAPI(@Path("profileId") int profileId, @Path("company_id") int companyID);

    *//*
    * Post Company Photos API
    * *//*
    @Multipart
    @POST(AppConstants.BASE_URL + profileTag + "/companies/{company_id}/photos")
    Call<JsonObject> callPostCompanyPhotosAPI(@Path("profileId") int profileId, @Path("company_id") int companyID,
                                              @Part MultipartBody.Part file);

    *//*
    * Update Company Photos API
    * *//*
    @Multipart
    @PATCH(AppConstants.BASE_URL + profileTag + "/companies/{company_id}/photos/{photos_id}")
    Call<JsonObject> callPutCompanyPhotosAPI(@Path("profileId") int profileId, @Path("company_id") int companyID,
                                             @Path("photos_id") int photosId, @Part MultipartBody.Part file);

    *//*
    * Delete Company Photos API
    * *//*
    @DELETE(AppConstants.BASE_URL + profileTag + "/companies/{company_id}/photos/{photos_id}")
    Call<JsonObject> callDeleteCompanyPhotosAPI(@Path("profileId") int profileId, @Path("company_id") int companyID,
                                                @Path("photos_id") int photosId);

    *//*
     *  Company Featured Products API Integration
     * *//*
    @Multipart
    @POST(AppConstants.BASE_URL + profileTag + "/companies/{company_id}/products")
    Call<JsonObject> createCompanyProductsAPI(@Path("profileId") int profileId, @Path("company_id") int companyID
            , @Part RequestBody body, @Part MultipartBody.Part file);

    @Multipart
    @POST(AppConstants.BASE_URL + profileTag + "/companies/{company_id}/products/{product_id}")
    Call<JsonObject> updateCompanyProductsAPI(@Path("profileId") int profileId, @Path("company_id") int companyID
            , @Path("product_id") int productID
            , @Part RequestBody body, @Part MultipartBody.Part file);

    @GET(AppConstants.BASE_URL + profileTag + "/companies/{company_id}/products")
    Call<JsonObject> getCompanyProductsAPI(@Path("profileId") int profileId, @Path("company_id") int companyID,
                                           @Query("page") int page);


    @DELETE(AppConstants.BASE_URL + profileTag + "/companies/{company_id}/products/{product_id}")
    Call<JsonObject> deleteCompanyProductsAPI(@Path("profileId") int profileId, @Path("company_id") int companyID,
                                              @Path("product_id") int productID);

    *//*----------- ProductEway Catalogue API--------------*//*

    @GET(AppConstants.BASE_URL + profileTag + "/companies/{company_id}/products/catalogue")
    Call<JsonObject> getCompanyProductsCatalogueAPI(@Path("profileId") int profileId,
                                                    @Path("company_id") int companyID,
                                                    @Query("page") int page);

    *//*
     *  Company Core Team API Integration
     * *//*

    @Multipart
    @POST(AppConstants.BASE_URL + profileTag + "/companies/{company_id}/coreteam")
    Call<JsonObject> createCompanyCoreteamAPI(@Path("profileId") int profileId, @Path("company_id") int companyID
            , @Part RequestBody body, @Part MultipartBody.Part file);

    @Multipart //update
    @POST(AppConstants.BASE_URL + profileTag + "/companies/{company_id}/coreteam/{coreteam_id}")
    Call<JsonObject> updateCompanyCoreteamAPI(@Path("profileId") int profileId, @Path("company_id") int companyID
            , @Path("coreteam_id") int coreteamID
            , @Part RequestBody body, @Part MultipartBody.Part file);

    @GET(AppConstants.BASE_URL + profileTag + "/companies/{company_id}/coreteam")
    Call<JsonObject> getCompanyCoreteamAPI(@Path("profileId") int profileId, @Path("company_id") int companyID);

    @DELETE(AppConstants.BASE_URL + profileTag + "/companies/{company_id}/coreteam/{coreteam_id}")
    Call<JsonObject> deleteCompanyCoreteamAPI(@Path("profileId") int profileId, @Path("company_id") int companyID,
                                              @Path("coreteam_id") int coreteamID);

    //Company coreteam ordering
    @POST(AppConstants.BASE_URL + profileTag + "/companies/{company_id}/coreteam/ordering")
    Call<JsonObject> createCompanyCoreteamOrderingAPI(@Path("profileId") int profileId, @Path("company_id") int companyID
            , @Body JsonObject obj);

    *//**********************************************************************************************//*

    *//*----------------------------- Recipe API Integration----------------------------------------*//*
     *//*
    * Post RecipesAPI
    * *//*
    @Multipart
    @POST(AppConstants.BASE_URL + profileTag + "/recipes")
    Call<JsonObject> callCreateRecipeAPI(@Path("profileId") int profileId, @PartMap() Map<String, RequestBody> partMap,
                                         @Part List<MultipartBody.Part> files);

    *//*
   * Update RecipesAPI
   * *//*
    @Multipart
    @POST(AppConstants.BASE_URL + profileTag + "/recipes/{recipeId}")
    Call<JsonObject> callUpdateRecipeAPI(@Path("profileId") int profileId,
                                         @Path("recipeId") int recipeId,
                                         @PartMap() Map<String, RequestBody> partMap,
                                         @Part List<MultipartBody.Part> files);

    *//*
   * GET RecipesAPI
   * *//*
    @GET(AppConstants.BASE_URL + profileTag + "/recipes")
    Call<JsonObject> callGetRecipeAPI(@Path("profileId") int profileId);

    *//*
   * Delete RecipesAPI
   * *//*
    @DELETE(AppConstants.BASE_URL + profileTag + "/recipes/{recipeId}")
    Call<JsonObject> callDeleteRecipeAPI(@Path("profileId") int profileId,
                                         @Path("recipeId") int recipeId);

    *//**********************************************************************************************//*

    *//*----------------------------- Jobs API Integration-----------------------------------------*//*

    *//*-----------------------------Job Designations API Integration-----------------------------------*//*
    @GET(AppConstants.BASE_URL + "designations")
    Call<JsonObject> callJobDesignationsAPI();

    //.............................. Individual Job as freelancer
    *//*
   * Post JobsAPI
   * *//*
    @FormUrlEncoded
    @POST(AppConstants.BASE_URL + profileTag + "/jobs")
    Call<JsonObject> callCreateJobsAPI(@Path("profileId") int profileId,
                                       @Field("privacy_id") int privacy_id,
                                       @Field("title") String title,
                                       @Field("description") String description,
                                       @Field("minimum_qualification") String minimum_qualification,
                                       @Field("key_skills") String keySkills,
                                       @Field("why_us") String whyUs,
                                       @Field("location") String location,
                                       @Field("salary_min") double salaryMin,
                                       @Field("salary_max") double salaryMax,
                                       @Field("experience_min") double experience_min,
                                       @Field("experience_max") double experienceMax,
                                       @Field("type_id") int typeId,
                                       @Field("start_month") int startMonth,
                                       @Field("start_year") int startYear,
                                       @Field("end_month") int end_month,
                                       @Field("end_year") int end_year,
                                       @Field("resume_required") int resumeRequired,
                                       @Field("joining") String joining);

    *//*
   * Update JobsAPI
   * *//*
    @FormUrlEncoded
    @POST(AppConstants.BASE_URL + profileTag + "/jobs/{jobID}")
    Call<JsonObject> callUpdateJobsAPI(@Path("profileId") int profileId,
                                       @Path("jobID") int jobID,
                                       @Field("privacy_id") int privacy_id,
                                       @Field("title") String title,
                                       @Field("description") String description,
                                       @Field("minimum_qualification") String minimum_qualification,
                                       @Field("key_skills") String keySkills,
                                       @Field("why_us") String whyUs,
                                       @Field("location") String location,
                                       @Field("salary_min") double salaryMin,
                                       @Field("salary_max") double salaryMax,
                                       @Field("experience_min") double experience_min,
                                       @Field("experience_max") double experienceMax,
                                       @Field("type_id") int typeId,
                                       @Field("start_month") int startMonth,
                                       @Field("start_year") int startYear,
                                       @Field("end_month") int end_month,
                                       @Field("end_year") int end_year,
                                       @Field("resume_required") int resumeRequired,
                                       @Field("joining") String joining,
                                       @Field("_method") String patch);
    *//*
   * GET JobsAPI
   * *//*
    @GET(AppConstants.BASE_URL + profileTag + "/jobs")
    Call<JsonObject> callGetJobsAPI(@Path("profileId") int profileId);

    *//*
   * Delete Jobs API
   * *//*
    @DELETE(AppConstants.BASE_URL + profileTag + "/jobs/{jobID}")
    Call<JsonObject> callDeleteJobsAPI(@Path("profileId") int profileId,
                                       @Path("jobID") int jobID);

    *//*
    * POST - Apply jobs
    * *//*
    @Multipart
    @POST(AppConstants.BASE_URL + profileTag + "/jobs/{jobID}/apply")
    Call<JsonObject> callApplyJobsAPI(@Path("profileId") int profileId,
                                      @Path("jobID") int jobID,
                                      @Part MultipartBody.Part file,
                                      @Part("message") RequestBody message);

    *//*
     * POST - UnApply jobs
     * *//*
    @POST(AppConstants.BASE_URL + profileTag + "/jobs/{jobID}/unapply")
    Call<JsonObject> callUnApplyJobsAPI(@Path("profileId") int profileId,
                                        @Path("jobID") int jobID);

    *//*
    * Get - Create applications
    * *//*
    @GET(AppConstants.BASE_URL + profileTag + "/jobs/{jobID}/applications")
    Call<JsonObject> callCreateApplicationsAPI(@Path("profileId") int profileId,
                                               @Path("jobID") int jobID, @Query("tags") int tag);

    *//*
   * Post - Shortlist applicants API
   * *//*
    @POST(AppConstants.BASE_URL + profileTag + "/jobs/{jobID}/applications/{shortlistedProfileId}/shortlist")
    Call<JsonObject> callShortlistApplicantsAPI(@Path("profileId") int profileId,
                                                @Path("jobID") int jobID, @Path("shortlistedProfileId") int shortlistedProfileId);

    *//*
    * GET - Job AppliedList
    * *//*
    @GET(AppConstants.BASE_URL + profileTag + "/jobs/applied")
    Call<JsonObject> callAppliedJobsAPI(@Path("profileId") int profileId);


    //.......................................Company Jobs

    *//*
      * Post Company Jobs API
      * *//*
    @FormUrlEncoded
    @POST(AppConstants.BASE_URL + profileTag + "/companies/{company_id}/jobs")
    Call<JsonObject> callCreateCompanyJobsAPI(@Path("profileId") int profileId,
                                              @Path("company_id") int companyId,
                                              @Field("privacy_id") int privacy_id,
                                              @Field("title") String title,
                                              @Field("description") String description,
                                              @Field("minimum_qualification") String minimum_qualification,
                                              @Field("key_skills") String keySkills,
                                              @Field("why_us") String whyUs,
                                              @Field("location") String location,
                                              @Field("salary_min") double salaryMin,
                                              @Field("salary_max") double salaryMax,
                                              @Field("experience_min") double experience_min,
                                              @Field("experience_max") double experienceMax,
                                              @Field("type_id") int typeId,
                                              @Field("start_month") int startMonth,
                                              @Field("start_year") int startYear,
                                              @Field("end_month") int end_month,
                                              @Field("end_year") int end_year,
                                              @Field("resume_required") int resumeRequired,
                                              @Field("joining") String joining);

    *//*
   * Update Company JobsAPI
   * *//*
    @FormUrlEncoded
    @POST(AppConstants.BASE_URL + profileTag + "/companies/{company_id}/jobs/{jobID}")
    Call<JsonObject> callUpdateCompanyJobsAPI(@Path("profileId") int profileId,
                                              @Path("company_id") int companyId,
                                              @Path("jobID") int jobID,
                                              @Field("privacy_id") int privacy_id,
                                              @Field("title") String title,
                                              @Field("description") String description,
                                              @Field("minimum_qualification") String minimum_qualification,
                                              @Field("key_skills") String keySkills,
                                              @Field("why_us") String whyUs,
                                              @Field("location") String location,
                                              @Field("salary_min") double salaryMin,
                                              @Field("salary_max") double salaryMax,
                                              @Field("experience_min") double experience_min,
                                              @Field("experience_max") double experienceMax,
                                              @Field("type_id") int typeId,
                                              @Field("start_month") int startMonth,
                                              @Field("start_year") int startYear,
                                              @Field("end_month") int end_month,
                                              @Field("end_year") int end_year,
                                              @Field("resume_required") int resumeRequired,
                                              @Field("joining") String joining,
                                              @Field("_method") String patch);

    *//*
   * GET  Company JobsAPI
   * *//*
    @GET(AppConstants.BASE_URL + profileTag + "/companies/{company_id}/jobs")
    Call<JsonObject> callGetCompanyJobsAPI(@Path("profileId") int profileId, @Path("company_id") int companyId);

    *//*
   * Delete Company Jobs API
   * *//*
    @DELETE(AppConstants.BASE_URL + profileTag + "/companies/{company_id}/jobs/{jobID}")
    Call<JsonObject> callDeleteCompanyJobsAPI(@Path("profileId") int profileId,
                                              @Path("company_id") int companyId,
                                              @Path("jobID") int jobID);

    *//*
    * POST -   Apply Company jobs
    * *//*
    @Multipart
    @POST(AppConstants.BASE_URL + profileTag + "/companies/{company_id}/jobs/{jobID}/apply")
    Call<JsonObject> callApplyCompanyJobsAPI(@Path("profileId") int profileId,
                                             @Path("company_id") int companyId,
                                             @Path("jobID") int jobID,
                                             @Part MultipartBody.Part file, @Part("message") RequestBody message);

    *//*
     * POST -   UnApply Company jobs
     * *//*
    @POST(AppConstants.BASE_URL + profileTag + "/companies/{company_id}/jobs/{jobID}/unapply")
    Call<JsonObject> callUnApplyCompanyJobsAPI(@Path("profileId") int profileId,
                                               @Path("company_id") int companyId,
                                               @Path("jobID") int jobID);

    *//*
    * Get - Create  Company applications
    * *//*
    @GET(AppConstants.BASE_URL + profileTag + "/companies/{company_id}/jobs/{jobID}/applications")
    Call<JsonObject> callCreateCompanyApplicationsAPI(@Path("profileId") int profileId,
                                                      @Path("company_id") int companyId,
                                                      @Path("jobID") int jobID, @Query("tags") int tag);

    *//*
   * Post - Shortlist  Company applicants API
   * *//*
    @POST(AppConstants.BASE_URL + profileTag + "/companies/{company_id}/jobs/{jobID}/applications/{shortlistedProfileId}/shortlist")
    Call<JsonObject> callCompanyShortlistApplicantsAPI(@Path("profileId") int profileId,
                                                       @Path("company_id") int companyId,
                                                       @Path("jobID") int jobID, @Path("shortlistedProfileId") int shortlistedProfileId);


    *//**********************************************************************************************//*

    *//*----------------------------- Collaborate API Integration-----------------------------------*//*
 *//*
   * Post Collaborate API for profile
   * *//*
    @Multipart
    @POST(AppConstants.BASE_URL + profileTag + "/collaborate")
    Call<JsonObject> callCreateCollaborateAPI(@Path("profileId") int profileId, @PartMap() Map<String, RequestBody> partMap,
                                              @Part MultipartBody.Part files, @Part ArrayList<MultipartBody.Part> fileList);

    *//*
   * Post Collaborate API for company
   * *//*
    @Multipart
    @POST(AppConstants.BASE_URL + profileTag + "/companies/{company_id}/collaborate")
    Call<JsonObject> callCreateCollaborateCompanyAPI(@Path("profileId") int profileId,
                                                     @Path("company_id") int companyId, @PartMap() Map<String, RequestBody> partMap,
                                                     @Part MultipartBody.Part files, @Part ArrayList<MultipartBody.Part> fileList);

    *//*
   * Update Collaborate API
   * *//*
    @Multipart
    @POST(AppConstants.BASE_URL + profileTag + "/collaborate/{collaborateID}")
    Call<JsonObject> callUpdateCollaborateAPI(@Path("profileId") int profileId,
                                              @Path("collaborateID") int collaborateID, @PartMap() Map<String, RequestBody> partMap,
                                              @Part MultipartBody.Part files, @Part ArrayList<MultipartBody.Part> fileList);

    *//*
 * Update Collaborate Company API
 * *//*
    @Multipart
    @POST(AppConstants.BASE_URL + profileTag + "/companies/{company_id}/collaborate/{collaborateID}")
    Call<JsonObject> callUpdateCompanyCollaborateAPI(@Path("profileId") int profileId,
                                                     @Path("company_id") int companyId,
                                                     @Path("collaborateID") int collaborateID, @PartMap() Map<String, RequestBody> partMap,
                                                     @Part MultipartBody.Part files, @Part ArrayList<MultipartBody.Part> fileList);

    *//*
   * GET Collaborate API
   * *//*
    @GET(AppConstants.BASE_URL + profileTag + "/collaborate")
    Call<JsonObject> callGetCollaborateAPI(@Path("profileId") int profileId);

    *//*
   * Delete Collaborate API
   * *//*
    @DELETE(AppConstants.BASE_URL + profileTag + "/collaborate/{collaborateID}")
    Call<JsonObject> callDeleteCollaborateAPI(@Path("profileId") int profileId,
                                              @Path("collaborateID") int collaborateID);

    *//*
     * Approve & Reject Collaborate API // type  --- approve or reject
     * *//*
    @FormUrlEncoded
    @POST(AppConstants.BASE_URL + profileTag + "/collaborate/{collaborateID}/{collaborate_type}")
    Call<JsonObject> callApproveRejectCollaborateAPI(@Path("profileId") int profileId,
                                                     @Path("collaborateID") int collaborateID,
                                                     @Path("collaborate_type") String type,
                                                     @Field("profile_id") int profileID);

    *//*
   * Apply Collaborate API - Individual
   * *//*
    @FormUrlEncoded
    @POST(AppConstants.BASE_URL + "collaborate/{collaborateID}/apply")
    Call<JsonObject> callApplyIndividualCollaborateAPI(@Path("collaborateID") int collaborateID,
                                                       @Field("profile_id") int profileID,
                                                       @Field("message") String message);

    *//*
    * Apply Collaborate API -Company
  * *//*
    @FormUrlEncoded
    @POST(AppConstants.BASE_URL + "collaborate/{collaborateID}/apply")
    Call<JsonObject> callApplyCompanyCollaborateAPI(@Path("collaborateID") int collaborateID,
                                                    @Field("company_id") int profileID,
                                                    @Field("message") String message);

    *//*
     * Applications Collaborate API
     * *//*
    @GET(AppConstants.BASE_URL + "collaborate/{collaborateID}/applications")
    Call<JsonObject> callApplicationCollaborateAPI(@Path("collaborateID") int collaborateID);

    *//**********************************************************************************************//*

    *//*-----------------------------Filters API Integration-----------------------------------*//*
    *//*
     *  Selected Filters API i.e category_type = people,jobs,recipes,companies etc
     * *//*
    @GET(AppConstants.BASE_URL + "{category_type}/{selectedFilters}")
    Call<JsonObject> callSelectedFiltersAPI(@Path("category_type") String type,
                                            @Path("selectedFilters") String filters);

    // Get Filters API
    @GET(AppConstants.BASE_URL + "{category_type}/filters")
    Call<JsonObject> callGetFiltersAPI(@Path("category_type") String type);

    *//**********************************************************************************************//*

    *//*-----------------------------Similar API Integration-----------------------------------*//*
    @GET(AppConstants.BASE_URL + "similar/{model_type}/{model_id}")
    Call<JsonObject> callSimilarAPI(@Path("model_type") String type,
                                    @Path("model_id") int id);

    *//**********************************************************************************************//*

    *//*-----------------------------Profile Experience Organisations Search API Integration-----------------------------------*//*
    @GET(AppConstants.BASE_URL + "autocomplete/filter/people/Work%20experience")
    Call<JsonObject> callOrganisationsAPI(@Query("term") String term);

    *//*-----------------------------Chat Group API Integration-----------------------------------*//*
    @GET(AppConstants.BASE_URL + "chatGroup")
    Call<JsonObject> callChatGroupAPI();

    *//*-----------------------------Tagging API----------------------------------------------*//*
    @GET(AppConstants.BASE_URL + "profile/tagging")
    Call<JsonObject> callProfileTaggingAPI(@Query("term") String term);

    *//**********************************************************************************************//*

    *//*----------------------------Settings API Integration-----------------------------------*//*

    *//* Get All Settings (User) *//*
    @GET(AppConstants.BASE_URL + "settings")
    Call<JsonObject> callGetUserSettingsAPI();

    *//* Save Setting (User) *//*
    @FormUrlEncoded
    @POST(AppConstants.BASE_URL + "settings")
    Call<JsonObject> callSaveUserSettingAPI(@Field("setting_id") int settingID,
                                            @Field("type") String type,
                                            @Field("value") int value);

    *//* Get All Settings (Company) *//*
    @GET(AppConstants.BASE_URL + "settings/company/{company_id}")
    Call<JsonObject> callGetCompanySettingsAPI(@Path("company_id") int companyID);

    *//* Save Setting (Company) *//*
    @FormUrlEncoded
    @POST(AppConstants.BASE_URL + "settings")
    Call<JsonObject> callSaveCompanySettingAPI(@Field("setting_id") int settingID,
                                               @Field("type") String type,
                                               @Field("value") int value,
                                               @Field("company_id") int companyID);

    @GET(AppConstants.BASE_URL + "/jobs")

    Call<JsonObject> callGetFiltersAPI(@Body JsonObject object);
*/}


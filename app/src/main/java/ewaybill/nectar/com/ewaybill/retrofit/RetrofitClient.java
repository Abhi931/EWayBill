package ewaybill.nectar.com.ewaybill.retrofit;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import ewaybill.nectar.com.ewaybill.utils.AppConstants;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Abhishek on 25/07/17.
 * This class is used to initialize the retrofit, set time out along with the headers with the http request.
 */

public class RetrofitClient {

   public static final String TAG = RetrofitClient.class.getSimpleName();

    private static Retrofit retrofit = null;
    public static OkHttpClient client;
    public static final int DEFAULT_TIMEOUT_SEC = 60;

    public static Retrofit getClient() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT_SEC, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT_SEC, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT_SEC, TimeUnit.SECONDS);

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        /*.header(AppConstants.AUTH_TOKEN, AppConstants.AUTH_START +
                                PreferenceManager.getActiveInstance(null).getmAuthorizationToken())
                        .header(AppConstants.APP_VERSION_KEY, CodeUtils.getAppVersionName(TagTasteApplicationInitializer.getmCurrentContext()))
                        .header(AppConstants.ACCEPT, "application/json")*/
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });


        client = httpClient.build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(AppConstants.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    /*// -------------------- Facebook response ----
    public static JsonObject fbResponseApiData(String fbToken, JsonObject jsonObject, String email) {
        String name = jsonObject.get("name").toString();
        String emailID = jsonObject.get("email").toString();
        String providerID = jsonObject.get("id").toString();
        String picture = jsonObject.get("picture").getAsJsonObject().get("data").getAsJsonObject().get("url").toString();

        JsonObject obj = new JsonObject();
        obj.addProperty(AppConstants.FIELD_FB_ACCESS_TOKEN, fbToken);
        obj.addProperty(AppConstants.FIELD_FB_NAME, name);
        obj.addProperty(AppConstants.FIELD_FB_EMAIL, TextUtils.isEmpty(emailID) ? email : emailID);
        obj.addProperty(AppConstants.FIELD_FB_PICTURE, picture);
        obj.addProperty(AppConstants.FIELD_FB_PROVIDERID, providerID);
        LoggerUtils.logD(TAG, obj + "");
        return obj;
    }

    // -------------------- Append fields & values in server API Url.
    public static JsonObject getRegisterApiData(String name, String email, String password, String password_confirmation, String inviteCode)
    {

        JsonObject user = new JsonObject();
        JsonObject obj = new JsonObject();
        obj.addProperty(AppConstants.FIELD_NAME,name);
        obj.addProperty(AppConstants.FIELD_EMAIL,email);
        obj.addProperty(AppConstants.FIELD_PASSWORD,password);
        obj.addProperty(AppConstants.FIELD_PASSWORD_CONFIRMATION,password_confirmation);

        user.add(AppConstants.FIELD_USER,obj);
        user.addProperty("invite_code",inviteCode);
        LoggerUtils.logD(TAG, user + "");
        return user;
    }

    public static Map<String, String> getShareAsMessageApiData(String preview, ArrayList<ShareAsMessageSelectedItem> mSelectedItems, String message, String url){

        Map<String, String> fields;
        fields = new HashMap<>();
        fields.put(AppConstants.FIELD_PREVIEW, preview);
        int followerIndex, groupIndex;
        followerIndex = 0;
        groupIndex = 0;
        String messagePlusUrl = message + "\n" + url;
        for(ShareAsMessageSelectedItem item : mSelectedItems){
            if(item.getType().equalsIgnoreCase(AppConstants.TYPE_FOLLOWER)){
                fields.put(AppConstants.FIELD_PROFILE_ID + "[" + followerIndex + "]", String.valueOf(item.getId()));
                followerIndex++;
            }
            else {
                fields.put(AppConstants.FIELD_CHAT_ID + "[" + groupIndex + "]", String.valueOf(item.getId()));
                groupIndex++;
            }
        }
        fields.put(AppConstants.FIELD_MESSAGE, messagePlusUrl);
        fields.put(AppConstants.FIELD_URL, url);
        return fields;
    }


    *//* This function is used for fetch and update the experience API *//*
    public static JsonObject fetchExperienceCategoryApiData(String company, String designation,
                                                            String description, String location,
                                                            String startDate, String endDate,
                                                            String currentCompany, int profileID) {
        JsonObject obj = new JsonObject();
        obj.addProperty(AppConstants.COMPANY, company);  //mandatory
        obj.addProperty(AppConstants.DESIGNATION, designation); //mandatory
        obj.addProperty(AppConstants.DESCRIPTION, description); //mandatory
        obj.addProperty(AppConstants.LOCATION, location);
        obj.addProperty(AppConstants.START_DATE, startDate);// mandatory    //mm-yyyy
        obj.addProperty(AppConstants.END_DATE, endDate);
        obj.addProperty(AppConstants.CURRENT_COMPANY, currentCompany);
        obj.addProperty(AppConstants.PROFILE_ID, profileID);

        LoggerUtils.logD(TAG, obj + "");
        return obj;
    }

    *//* This function is used for fetch and update the education API *//*
    public static JsonObject fetchEducationCategoryApiData(String degree, String college,
                                                           String field, String grade, String percentage,
                                                           String startDate, String endDate,
                                                           int ongoing, int location) {
        JsonObject obj = new JsonObject();
        obj.addProperty(AppConstants.DEGREE, degree);  //mandatory
        obj.addProperty(AppConstants.COLLEGE, college); //mandatory
        obj.addProperty(AppConstants.FIELD, field); //mandatory
        obj.addProperty(AppConstants.GRADE, grade);
        obj.addProperty(AppConstants.PERCENTAGE, percentage);// mandatory
        obj.addProperty(AppConstants.START_DATE, startDate); //mm-yyyy
        obj.addProperty(AppConstants.END_DATE, endDate);
        obj.addProperty(AppConstants.ONGOING, ongoing);
        obj.addProperty(AppConstants.LOCATION, location);

        LoggerUtils.logD(TAG, obj + "");
        return obj;
    }


    *//* This function is used for fetch and update the patent API *//*
    public static JsonObject fetchPatentCategoryApiData(int id, String title,
                                                        String description, String publishDate,
                                                        int patentNumber,
                                                        String url, int profileID) {
        JsonObject obj = new JsonObject();
        obj.addProperty(AppConstants.ID, id);
        obj.addProperty(AppConstants.TITLE, title); // mandatory
        obj.addProperty(AppConstants.DESCRIPTION, description);
        obj.addProperty(AppConstants.PUBLISH_DATE, publishDate);
        obj.addProperty(AppConstants.PATENT_NUMBER, patentNumber);
        obj.addProperty(AppConstants.URL, url);
        obj.addProperty(AppConstants.PROFILE_ID, profileID);

        LoggerUtils.logD(TAG, obj + "");
        return obj;
    }

    *//* This function is used for fetch and update the book API *//*
    public static JsonObject fetchBookCategoryApiData(int id, String title,
                                                      String description, String publisher,
                                                      String releaseDate,
                                                      String url, String isbn) {
        JsonObject obj = new JsonObject();
        obj.addProperty(AppConstants.ID, id);
        obj.addProperty(AppConstants.TITLE, title); //mandatory
        obj.addProperty(AppConstants.DESCRIPTION, description);
        obj.addProperty(AppConstants.PUBLISHER, publisher);
        obj.addProperty(AppConstants.RELEASE_DATE, releaseDate);
        obj.addProperty(AppConstants.URL, url);
        obj.addProperty(AppConstants.ISBN, isbn);

        LoggerUtils.logD(TAG, obj + "");
        return obj;
    }

    *//* This function is used for fetch and update the TVShows API *//*
    public static JsonObject fetchTVShowsCategoryApiData(int id, String title,
                                                         String description, String channel,
                                                         String date,
                                                         String url, String appearedAs) {
        JsonObject obj = new JsonObject();
        obj.addProperty(AppConstants.ID, id);
        obj.addProperty(AppConstants.TITLE, title); //mandatory
        obj.addProperty(AppConstants.DESCRIPTION, description);
        obj.addProperty(AppConstants.CHANNEL, channel);
        obj.addProperty(AppConstants.DATE, date);//mm-yyyy
        obj.addProperty(AppConstants.URL, url);
        obj.addProperty(AppConstants.APPEARED_AS, appearedAs);

        LoggerUtils.logD(TAG, obj + "");
        return obj;
    }


    *//*This function is used update the Comapny Profile API *//*
    public static JsonObject updateCompanyProfileApiData(String name, String about,
                                                         String logo, String heroImage,
                                                         int phone, String emailID,
                                                         String regAddress, String establishOn,
                                                         int type, int empCount,
                                                         int clientCount, String annualStartDate,
                                                         String annulaEndDate, String fbURL,
                                                         String twitterURL, String linkedlnCount,
                                                         String instagramURL, String ytURL,
                                                         String pInterestURL, String googleURL,
                                                         String tagline, String establishment,
                                                         String cuisines, String websites,
                                                         String milestones, String speciality,
                                                         String handle, String city) {
        JsonObject obj = new JsonObject();
        obj.addProperty(AppConstants.NAME, name); //MANDATORY
        obj.addProperty(AppConstants.ABOUT, about);
        obj.addProperty(AppConstants.LOGO, logo);
        obj.addProperty(AppConstants.HERO_IMAGE, heroImage);
        obj.addProperty(AppConstants.PHONE, phone);
        obj.addProperty(AppConstants.EMAIL, emailID); //mandatory
        obj.addProperty(AppConstants.REGISTERED_ADDRESS, regAddress);

        obj.addProperty(AppConstants.ESTABLISHED_ON, establishOn); //dd-mm-yyyy
        obj.addProperty(AppConstants.TYPE, type);
        obj.addProperty(AppConstants.EMPLOYEE_COUNT, empCount);
        obj.addProperty(AppConstants.CLIENT_COUNT, clientCount);
        obj.addProperty(AppConstants.ANNUAL_REVENUE_START, annualStartDate);
        obj.addProperty(AppConstants.ANNUAL_REVENUE_END, annulaEndDate);

        obj.addProperty(AppConstants.FACEBOOK_URL, fbURL);
        obj.addProperty(AppConstants.TWITTER_URL, twitterURL);
        obj.addProperty(AppConstants.LINKEDLN_URL, linkedlnCount);
        obj.addProperty(AppConstants.INSTAGRAM_URL, instagramURL);
        obj.addProperty(AppConstants.YOUTUBE_URL, ytURL);
        obj.addProperty(AppConstants.PINTEREST_URL, pInterestURL);
        obj.addProperty(AppConstants.GOOGLE_PLUS_URL, googleURL);

        obj.addProperty(AppConstants.TAGLINE, tagline);
        obj.addProperty(AppConstants.ESTABLISHMENTS, establishment);
        obj.addProperty(AppConstants.CUISINES, cuisines);
        obj.addProperty(AppConstants.WEBSITES, websites);
        obj.addProperty(AppConstants.MILESTONES, milestones);
        obj.addProperty(AppConstants.SPECIALITY, speciality);
        obj.addProperty(AppConstants.HANDLE, handle);
        obj.addProperty(AppConstants.CITY, city);

        LoggerUtils.logD(TAG, obj + "");
        return obj;
    }


    *//* This function is used for Company Awards API*//*
    public static JsonObject fetchCompanyAwardsAPIData(String name, String description, String date) {

        JsonObject obj = new JsonObject();
        obj.addProperty(AppConstants.NAME, name);
        obj.addProperty(AppConstants.DESCRIPTION, description);
        obj.addProperty(AppConstants.DATE, date);

        LoggerUtils.logD(TAG, obj + "");
        return obj;
    }


    *//* This function is used for Company Books API*//*
    public static JsonObject fetchCompanyBooksAPIData(String id, String title, String description,
                                                      String publisher, String releaseDate,
                                                      String url, String isbn) {

        JsonObject obj = new JsonObject();
        obj.addProperty(AppConstants.ID, id);
        obj.addProperty(AppConstants.TITLE, title);
        obj.addProperty(AppConstants.DESCRIPTION, description);
        obj.addProperty(AppConstants.PUBLISHER, publisher);
        obj.addProperty(AppConstants.RELEASE_DATE, releaseDate);//mm-yyyy
        obj.addProperty(AppConstants.URL, url);
        obj.addProperty(AppConstants.ISBN, isbn);

        LoggerUtils.logD(TAG, obj + "");
        return obj;
    }

    *//* This function is used for Company Patents API*//*
    public static JsonObject fetchCompanyPatentsAPIData(String title, String description, String awardOn,
                                                        String issuedBy, int number, int companyID, String url) {

        JsonObject obj = new JsonObject();
        obj.addProperty(AppConstants.TITLE, title);
        obj.addProperty(AppConstants.DESCRIPTION, description);
        obj.addProperty(AppConstants.AWARDED_ON, awardOn);//mm-yyyy
        obj.addProperty(AppConstants.ISSUED_BY, issuedBy);
        obj.addProperty(AppConstants.NUMBER, number);
        obj.addProperty(AppConstants.COMPANY_ID, companyID);
        obj.addProperty(AppConstants.URL, url);

        LoggerUtils.logD(TAG, obj + "");
        return obj;
    }


    *//* This function is used for Jobs Creation & Updation API*//*
    public static JsonObject fetchJobsCreateAPIData(String type, String title, String description,
                                                    String why_us, String location, String key_skills,
                                                    int profile_id, String salary_min,
                                                    String salary_max, String experience_min,
                                                    String experience_max, String joining,
                                                    int company_id, int type_id,
                                                    int privacy_id, boolean resume_required,
                                                    String minimum_qualification) {

        JsonObject obj = new JsonObject();
        obj.addProperty(AppConstants.TITLE, title);
        obj.addProperty(AppConstants.DESCRIPTION, description);
        obj.addProperty(AppConstants.WHY_US, why_us);
        obj.addProperty(AppConstants.KEY_SKILLS, key_skills);
        obj.addProperty(AppConstants.LOCATION, location);
        obj.addProperty(AppConstants.PROFILE_ID, profile_id);
        obj.addProperty(AppConstants.SALARY_MIN, salary_min);
        obj.addProperty(AppConstants.SALARY_MAX, salary_max);
        obj.addProperty(AppConstants.EXPERIENCE_MIN, experience_min);
        obj.addProperty(AppConstants.EXPERIENCE_MAX, experience_max);
        obj.addProperty(AppConstants.JOINING, joining); // i.e immediate,easy
        obj.addProperty(AppConstants.TYPE_ID, type_id);
        obj.addProperty(AppConstants.PRIVACY_ID, privacy_id);
        obj.addProperty(AppConstants.COMPANY_ID, company_id);
        obj.addProperty(AppConstants.RESUME_REQUIRED, resume_required);
        obj.addProperty(AppConstants.MIN_QUALIFICATION, minimum_qualification);

        if (type.equalsIgnoreCase(AppConstants.PUT_QUERY)) {
            obj.addProperty("_method", "patch");
        }

        LoggerUtils.logD(TAG, obj + "");
        return obj;
    }

    public static JsonArray fetchShareShoutoutAPIData(int profileID, int privacyID, String shoutoutText, PreviewData previewData) {

        JsonArray array = new JsonArray();
        JsonObject obj1 = new JsonObject();
        JsonObject obj2 = new JsonObject();
        JsonObject obj3 = new JsonObject();
        JsonObject obj4 = new JsonObject();
        JsonObject obj5 = new JsonObject();
        JsonObject obj6 = new JsonObject();
        JsonObject obj7 = new JsonObject();
        JsonObject obj8 = new JsonObject();
        JsonObject obj9 = new JsonObject();

        obj1.addProperty(AppConstants.PROFILE_ID, profileID);
        obj2.addProperty(AppConstants.PRIVACY_ID, privacyID);
        obj3.addProperty(AppConstants.CONTENT, shoutoutText);
        obj4.addProperty("preview[image]", previewData.getImage());
        obj5.addProperty("preview[description]", previewData.getDescription());
        obj6.addProperty("preview[site_name]", previewData.getSiteName());
        obj7.addProperty("preview[title]", previewData.getTitle());
        obj8.addProperty("preview[type]", previewData.getType());
        obj9.addProperty("preview[url]", previewData.getUrl());

        array.add(obj1);
        array.add(obj2);
        array.add(obj3);
        array.add(obj4);
        array.add(obj5);
        array.add(obj6);
        array.add(obj7);
        array.add(obj8);
        array.add(obj9);

        LoggerUtils.logD(TAG, array + "");
        return array;
    }

    public static JsonObject fetchPreviewAPIData( PreviewData previewData) {

        JsonObject obj1 = new JsonObject();
        obj1.addProperty("image", previewData.getImage());
        obj1.addProperty("description", previewData.getDescription());
        obj1.addProperty("site_name", previewData.getSiteName());
        obj1.addProperty("title", previewData.getTitle());
        obj1.addProperty("type", previewData.getType());
        obj1.addProperty("url", previewData.getUrl());

        LoggerUtils.logD(TAG, obj1 + "");
        return obj1;
    }

    //Add Profile Experience API
    public static JsonObject callExperienceAPI(String designation, String companyname, String location,
                                               String startDate,String endDate, String currentCompany, String description) {

        JsonObject obj1 = new JsonObject();

        obj1.addProperty("designation", designation);
        obj1.addProperty("company", companyname);
        obj1.addProperty("location", location);
        obj1.addProperty("start_date", startDate);
        obj1.addProperty("end_date", endDate);
        obj1.addProperty("current_company", currentCompany);
        obj1.addProperty("description", description);

        LoggerUtils.logD(TAG, obj1 + "");
        return obj1;
    }

    //Add Profile Education API
    public static JsonObject callEducationAPI(String schoolName, String degree, String field ,String location,
                                               String startDate,String endDate,String ongoing,  String description) {

        JsonObject obj1 = new JsonObject();

        obj1.addProperty("college", schoolName);
        obj1.addProperty("degree", degree);
        obj1.addProperty("field", field);
        obj1.addProperty("location", location);
        obj1.addProperty("start_date", startDate);
        obj1.addProperty("end_date", endDate);
        obj1.addProperty("ongoing", ongoing);
        obj1.addProperty("description",description);

        LoggerUtils.logD(TAG, obj1 + "");
        return obj1;
    }

    // Add Profile Projects API
    public static JsonObject callProjectsAPI(String title, String completedOn , String description) {

        JsonObject obj1 = new JsonObject();

        obj1.addProperty("title", title);
        obj1.addProperty("completed_on", completedOn);
        obj1.addProperty("description", description);

        LoggerUtils.logD(TAG, obj1 + "");
        return obj1;
    }

    // Add Profile Patents API
    public static JsonObject callPatentsAPI(String title, String patentNumber, String publishDate , String description) {

        JsonObject obj1 = new JsonObject();

        obj1.addProperty("title", title);
        obj1.addProperty("patent_number", patentNumber);
        obj1.addProperty("publish_date", publishDate);
        obj1.addProperty("description", description);

        LoggerUtils.logD(TAG, obj1 + "");
        return obj1;
    }

    // Add Profile Training API
    public static JsonObject callTrainingAPI(String title, String completedOn , String description) {

        JsonObject obj1 = new JsonObject();

        obj1.addProperty("title", title);
        obj1.addProperty("completed_on", completedOn);
        obj1.addProperty("trained_from", description);

        LoggerUtils.logD(TAG, obj1 + "");
        return obj1;
    }

    // Add Profile Awards API
    public static JsonObject callAwardsAPI(String title, String completedOn , String description) {

        JsonObject obj1 = new JsonObject();

        obj1.addProperty("name", title);
        obj1.addProperty("date", completedOn);
        obj1.addProperty("description", description);

        LoggerUtils.logD(TAG, obj1 + "");
        return obj1;
    }

    // Add Profile TVShows API
    public static JsonObject callTVShowsAPI(String title, String channelName, String appearedAs,
                                            String url,String completedOn , String description) {

        JsonObject obj1 = new JsonObject();

        obj1.addProperty("title", title);
        obj1.addProperty("channel", channelName);
        obj1.addProperty("appeared_as", appearedAs);
        obj1.addProperty("url", url);
        obj1.addProperty("date", completedOn);
        obj1.addProperty("description", description);

        LoggerUtils.logD(TAG, obj1 + "");
        return obj1;
    }

    // Add Profile Publisher API
    public static JsonObject callBooksAPI(String title, String publisher,
                                            String url,String completedOn , String description) {

        JsonObject obj1 = new JsonObject();

        obj1.addProperty("title", title);
        obj1.addProperty("publisher", publisher);
        obj1.addProperty("url", url);
        obj1.addProperty("release_date", completedOn);
        obj1.addProperty("description", description);

        LoggerUtils.logD(TAG, obj1 + "");
        return obj1;
    }

    public static JsonObject callPeopleFiltersApi(String location) {

        JsonObject obj = new JsonObject();
        obj.addProperty("filters[location][]", location);

        LoggerUtils.logD(TAG, obj + "");
        return obj;
    }


    // This function is used to send data in multipart form
    public static RequestBody getRequestBody(Object o)
    {
        if(o instanceof String)
        {
            RequestBody.create(MediaType.parse("text/plain"), (String) o);
        }
        if(o instanceof byte[])
        {
            RequestBody.create(MediaType.parse("image/jpeg"), (byte[]) o);
        }

        return null;
    }*/
}

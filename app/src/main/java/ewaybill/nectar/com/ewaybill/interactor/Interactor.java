package ewaybill.nectar.com.ewaybill.interactor;

/**
 * Created by Abhishek on 27-Jul-17.
 */

public interface Interactor {
    void callApi(ApiInteractor apiInteractor, Object... args);
}

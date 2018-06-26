# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\Abhishek\AppData\Local\Android\Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
# Google Play Services library
-keep class * extends java.util.ListResourceBundle {
    protected Object[][] getContents();
}

-keep public class com.google.android.gms.common.internal.safeparcel.SafeParcelable {
    public static final *** NULL;
}

-keepnames @com.google.android.gms.common.annotation.KeepName class *

-keepclassmembernames class * {
    @com.google.android.gms.common.annotation.KeepName *;
}

-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}
-keepclassmembers enum * {
public static **[] values();
public static ** valueOf(java.lang.String);
}

-dontwarn butterknife.internal.**
-keep class **$$ViewInjector { *; }
-keepnames class * { @butterknife.InjectView *;}

##---------------Begin: proguard configuration for Gson  ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.

# Gson specific classes
-keep class sun.misc.Unsafe { *; }
#-keep class com.google.gson.stream.** { *; }


-dontwarn rx.**
-dontwarn okio.**


-keepattributes Signature
-keepattributes Annotation

# OkHttp
-keepattributes Signature
-keepattributes *Annotation*
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**


-keep class com.parse.*{ *; }
-dontwarn com.parse.**
-dontwarn com.squareup.picasso.**
-keepclasseswithmembernames class * {
    native <methods>;
}

## Retrofit
-dontwarn retrofit2.**
-dontwarn org.codehaus.mojo.**
-keep class retrofit2.** { *; }
-keepattributes Signature

-keepnames class * implements java.io.Serializable
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
-keepattributes Exceptions
-keepattributes *Annotation*

-keepattributes RuntimeVisibleAnnotations
-keepattributes RuntimeInvisibleAnnotations
-keepattributes RuntimeVisibleParameterAnnotations
-keepattributes RuntimeInvisibleParameterAnnotations

-keepclasseswithmembers class * {
    @retrofit2.* <methods>;
}

-keepclasseswithmembers interface * {
    @retrofit2.* <methods>;
}

-keep class com.google.android.gms.** { *; }
-dontwarn com.google.android.gms.**
-keep class com.google.gson.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class ewaybill.nectar.com.ewaybill.model.** { *; }
-keep class ewaybill.nectar.com.ewaybill.presenter.** { *; }
-keep class ewaybill.nectar.com.ewaybill.interactor.** { *; }
-keep class ewaybill.nectar.com.ewaybill.fragment.** { *; }
-keep class ewaybill.nectar.com.ewaybill.jsonModelResponses.** { *; }
-keep class ewaybill.nectar.com.ewaybill.localDB.** { *; }
-keep class ewaybill.nectar.com.ewaybill.retrofit.** { *; }
-keep class ewaybill.nectar.com.ewaybill.sql.** { *; }
-keep class ewaybill.nectar.com.ewaybill.testSqlDatabase.** { *; }
-keep class ewaybill.nectar.com.ewaybill.viewstate.** { *; }
-keep class ewaybill.nectar.com.ewaybill.adapter.** { *; }
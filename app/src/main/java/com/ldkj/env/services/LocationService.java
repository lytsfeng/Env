package com.ldkj.env.services;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.model.LatLng;
import com.ldkj.env.utils.Attribute;
import com.ldkj.env.utils.LogUtils;

public class LocationService extends Service implements AMapLocationListener {

    public static final String ACTION_LOCATION_CHANGE = "com.ldkj.env.LOCATION_CHANGE";
    public static final String KEY_LOCATION_CHANGE = "key_location_change";

    private final static int CHECK_POSITION_INTERVAL = 6 * 1000; // 重新获取位置信息的时间间隔
    private final static int CHECK_POSITION_DISTANCE = 20; // 重新获取位置信息的距离间隔
    private static final double DIS = 20; // 两点之间的最小距离


    private LocationManagerProxy locationManager = null;
    private Intent intent;

    private LatLng oldLatlng = null;



    public LocationService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.w(Attribute.TAG, "服务启动");
        startLocation();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new LocalBinder();
    }

    @Override
    public void onDestroy() {
        stopLocation();
        super.onDestroy();
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        boolean _isChange = false;
        LatLng _LatLng = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
        if(oldLatlng != null){
            if(AMapUtils.calculateArea(_LatLng,oldLatlng) > DIS){
                _isChange = true;
            }
        }else {
            _isChange = true;
        }
        if(_isChange){
            oldLatlng = _LatLng;
            intent = new Intent(ACTION_LOCATION_CHANGE);
            intent.putExtra(KEY_LOCATION_CHANGE, oldLatlng);
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        }
    }

    /**
     * 开始定位
     */
    private void startLocation(){
        locationManager = LocationManagerProxy.getInstance(this);
		/*
		 * mAMapLocManager.setGpsEnable(false);//
		 * 1.0.2版本新增方法，设置true表示混合定位中包含gps定位，false表示纯网络定位，默认是true Location
		 * API定位采用GPS和网络混合定位方式
		 * ，第一个参数是定位provider，第二个参数时间最短是2000毫秒，第三个参数距离间隔单位是米，第四个参数是定位监听者
		 */
        locationManager.requestLocationUpdates(
                LocationProviderProxy.AMapNetwork, 2000, 10, this);
    }

    /**
     * 停止定位
     */
    private void stopLocation(){
        if(locationManager != null){
            locationManager.removeUpdates(this);
            locationManager.destroy();
        }
        locationManager = null;
    }



    public final class LocalBinder extends Binder {
        public LocationService getService() {
            return LocationService.this;
        }
    }


    //————————————————————————————————————————————————————————————————————

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

}

package com.desaco.permissiondemo.utils;

import android.content.Context;

import androidx.annotation.NonNull;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import java.util.List;

/**
 * @author dengwen
 * @date 2020/7/14.
 */
public class PermissionUtils {
    /**
     *
     * @param context
     * @param allPermissions
     */
    public static void requestPermission(final Context context, final String... allPermissions) {
        AndPermission.with(context)
                .runtime()
                .permission(allPermissions)
//                .rationale(new RuntimeRationale())  //权限暂时被用户拒绝后， 给用户的 温馨提示
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {// 授权成功
                        // 使用EventBus 进行通信
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(@NonNull List<String> permissions) {
                        if (AndPermission.hasAlwaysDeniedPermission(context, permissions)) {
                            // 用户 永久禁止权限后，跳转设置页
                        } else {
                            // 暂时 禁止授权！ 温馨提示
                        }
                    }
                })
                .start();
    }
}

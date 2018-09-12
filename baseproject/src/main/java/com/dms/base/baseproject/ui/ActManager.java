package com.dms.base.baseproject.ui;

import android.app.Activity;
import android.content.Context;

import java.util.Stack;



/**
 * Created by 龚万全 on 2016/11/24.
 * Description:
 */

public class ActManager {
    private static Stack<BaseActivity> activityStack;
    private static ActManager instance;

    private ActManager() {
    }

    /**
     * 单实例 , UI无需考虑多线程同步问题
     */
    public static ActManager getAppManager() {
        if (instance == null) {
            instance = new ActManager();
        }
        return instance;
    }

    /**
     * 添加Activity到栈
     */
    public void addActivity(BaseActivity activity) {
        if (activityStack == null) {
            activityStack = new Stack<BaseActivity>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（栈顶Activity）
     */
    public BaseActivity currentActivity() {
        if (activityStack == null || activityStack.isEmpty()) {
            return null;
        }
        BaseActivity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 获取当前Activity（栈顶Activity） 没有找到则返回null
     */
    public BaseActivity findActivity(Class<?> cls) {
        BaseActivity activity = null;
        for (BaseActivity aty : activityStack) {
            if (aty.getClass().equals(cls)) {
                activity = aty;
                break;
            }
        }
        return activity;
    }

    /**
     * 判斷當前activity是否在堆棧倒數第二個
     * @param cls
     * @return
     */
    public boolean isLastActivity(Class<?> cls) {
        int position = activityStack.size() - 2;
        if(position < 0) {
            return false;
        }
        if(cls == activityStack.get(position).getClass()) {
            return true;
        }
        return false;
    }

    /**
     * 结束当前Activity（栈顶Activity）
     */
    public void finishActivity() {
        BaseActivity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity(重载)
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定的Activity(重载)
     */
    public void finishActivity(Class<?> cls) {
        for (BaseActivity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
                break;
            }
        }
    }

    /**
     * 关闭除了指定activity以外的全部activity 如果cls不存在于栈中，则栈全部清空
     *
     * @param cls
     */
    public void finishOthersActivity(Class<?> cls) {
        for (BaseActivity activity : activityStack) {
            if (!(activity.getClass().equals(cls))) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 应用程序退出
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            android.app.ActivityManager activityMgr = (android.app.ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
            System.exit(0);
        }
    }
}

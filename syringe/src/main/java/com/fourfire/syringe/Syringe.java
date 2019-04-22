package com.fourfire.syringe;

import android.app.Activity;

import java.lang.reflect.InvocationTargetException;

public class Syringe {
    public static void sringe(Activity activity) {
        String activityName = activity.getClass().getName();
        String generateName = activityName + "_Syringe";
        try {
            Class.forName(generateName).getConstructor(activity.getClass()).newInstance(activity);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

package com.nurseryapps.todoapplicationg2.Base;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.afollestad.materialdialogs.MaterialDialog;

/**
 * Created by Mohamed Nabil Mohamed (Nobel) on 1/28/2019.
 * byte code SA
 * m.nabil.fci2015@gmail.com
 */
public class BaseFragment extends Fragment {
    BaseActivity activity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity=(BaseActivity) context;
    }

    public MaterialDialog showMessage(int titleResId, int messageResId, int posResText){
        return activity.showMessage(titleResId,messageResId,posResText);

    }


    public MaterialDialog showConfirmationMessage(int titleResId, int messageResId, int posResText,
                                                  MaterialDialog.SingleButtonCallback onPosAction){
        return activity.showConfirmationMessage(titleResId,messageResId,posResText,onPosAction);

    }
    public MaterialDialog showMessage(String title,String message,String posText){

        return activity.showMessage(title,message,posText);
    }
    public MaterialDialog showProgressBar(){

        return activity.showProgressBar();
    }
    public void hideProgressBar() {
        activity.hideProgressBar();
    }
}

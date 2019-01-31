package com.example.hp.onlineshop.Bases;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.hp.onlineshop.R;

/**
 * Created by HP on 1/21/2019.
 */

public class BaseFragment extends Fragment {


    public AlertDialog.Builder showConfirmationDialog(String title, String message,
                                                      String posActionText,
                                                      String NegActionText,
                                                      DialogInterface.OnClickListener posAction,
                                                      DialogInterface.OnClickListener NegAction){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton(posActionText,posAction);
        alertDialog.setNegativeButton(NegActionText,NegAction);
        alertDialog.show();
        return alertDialog;
    }


    public MaterialDialog showConfirmationDialog(String title, String message,
                                                 String posText, String negText,
                                                 MaterialDialog.SingleButtonCallback posAction,
                                                 MaterialDialog.SingleButtonCallback negAction){
        MaterialDialog dialog = new MaterialDialog.Builder(getActivity())
                .title(title)
                .content(message)
                .positiveText(posText)
                .negativeText(negText)
                .onNegative(negAction)
                .onPositive(posAction)
                .show();

        return dialog;

    }
    MaterialDialog dialog;
    public MaterialDialog showProgressBar(){
        dialog =   new MaterialDialog.Builder(getActivity())
                .content(getString(R.string.loading))
                .progress(true,0)
                .cancelable(false)
                .show();

        return dialog;

    }
    public void hideProgressBar(){

        if(dialog !=null && dialog.isShowing())
            dialog.dismiss();
    }
}

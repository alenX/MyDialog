package demo.alenx.org.mydialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by wangss on 2015/7/8.
 */
public class MyDialog extends Dialog {
    private Context context;
    private static MyDialog myDialog = null;
    public MyDialog(Context context) {
        super(context);
        this.context = context;
    }

    public MyDialog(Context context, int theme) {
        super(context, theme);
    }

    protected MyDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static MyDialog createDialog(Context context){
        myDialog = new MyDialog(context);
        myDialog.setContentView(R.layout.customprogressdialog);
        myDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        return myDialog;
    }

    public void onWindowFocusChanged(boolean isFocus){
        if (myDialog ==null){
            return;
        }
        ImageView mImageView = (ImageView)myDialog.findViewById(R.id.loadingImageView);
        AnimationDrawable animationDrawable = (AnimationDrawable)mImageView.getBackground();
        animationDrawable.start();
    }

    public MyDialog setTitle(String title){
        myDialog.setTitle(title);
        return myDialog;
    }

    public MyDialog setMessage(String message){
        TextView mTextView = (TextView)myDialog.findViewById(R.id.loadingTextView);
        if (mTextView!=null){
            mTextView.setText(message);
        }
        return myDialog;
    }


}

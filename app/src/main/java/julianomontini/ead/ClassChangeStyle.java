package julianomontini.ead;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by Juliano on 19/10/2016.
 */

public class ClassChangeStyle {

    public static void recursiveLoopChildren(ViewGroup parent,Context context,int style) {
        for (int i = parent.getChildCount() - 1; i >= 0; i--) {
            final View child = parent.getChildAt(i);
            if (child instanceof ViewGroup) {
                recursiveLoopChildren((ViewGroup) child,context,style);
                // DO SOMETHING WITH VIEWGROUP, AFTER CHILDREN HAS BEEN LOOPED
            } else {
                if (child != null) {

                    if(((child instanceof Button ) || (child instanceof EditText))&& !( child instanceof CheckBox)){

                        switch (style){
                            case 1:
                                child.setBackground(ResourcesCompat.getDrawable(context.getResources(),R.drawable.layout_button,null));
                                break;
                            case 2:
                                child.setBackground(ResourcesCompat.getDrawable(context.getResources(),R.drawable.layout_button_green,null));
                                break;
                        }

                    }


                }
            }
        }
    }

}

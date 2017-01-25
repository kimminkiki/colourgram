package com.m.colourgram;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Minnie on 11/1/16.
 */
public class CustomEditTextView extends EditText {

    public CustomEditTextView(Context context){
        super(context);
    }

    public CustomEditTextView(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
    }

    public CustomEditTextView(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
    }

    private OnTextLengthListener onTextLengthListener = null;

    public void setOnTextLengthListener(OnTextLengthListener listener){
        onTextLengthListener = listener;
    }

    public interface OnTextLengthListener {
        public abstract void onTextLength(int length);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
         if(onTextLengthListener != null){
             onTextLengthListener.onTextLength(text.length());
             super.onTextChanged(text, start, lengthBefore, lengthAfter);
         }
    }
}

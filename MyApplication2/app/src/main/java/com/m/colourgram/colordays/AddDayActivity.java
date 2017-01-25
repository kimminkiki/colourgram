package com.m.colourgram.colordays;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.m.colourgram.CustomEditTextView;
import com.m.colourgram.MainActivity;
import com.m.colourgram.R;
import com.m.colourgram.utils.RealmHelper;
import com.m.colourgram.calendar.CalendarHelper;

/**
 * add a day
 * using text field
 */
public class AddDayActivity extends AppCompatActivity implements View.OnClickListener, CustomEditTextView.OnTextLengthListener {
    private RealmHelper realmHelper;
    private CalendarHelper calendarHelper;

    private GridView mGridView;

    private String[] mColorData;

    private String mSelectedColor;

    private CustomEditTextView mEditorText;
    private TextView mCountText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_day);

        mEditorText =  ((CustomEditTextView)findViewById(R.id.edit_mood));
        mCountText = (TextView)findViewById(R.id.text_count);

        mEditorText.setOnTextLengthListener(this);

        Intent intent = getIntent();
        String mood = intent.getStringExtra("mood");
        if(!"".equals(mood)){
            mEditorText.setText(mood);
        }
        realmHelper  = new RealmHelper(this);
        calendarHelper = new CalendarHelper();
        ((TextView) findViewById(R.id.text_date)).setText(calendarHelper.getCurrentDate());
        ((TextView) findViewById(R.id.text_day)).setText(calendarHelper.getCurrentDayOfWeek(calendarHelper.getCurrentYear(),calendarHelper.getCurrentMonth(),calendarHelper.getCurrentDate() ));
        ((Button) findViewById(R.id.btn_done)).setOnClickListener(this);



        mColorData = getResources().getStringArray(R.array.color_list_code);

        mGridView = (GridView) findViewById(R.id.grid_color_palette);
        mGridView.setAdapter(new ColorGridAdapter());

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mSelectedColor = mColorData[i];
                ((RelativeLayout) findViewById(R.id.layout_colorday)).setBackgroundColor(Color.parseColor(mColorData[i]));
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_done :

                String mood = mEditorText.getText().toString().trim();
                realmHelper.saveColorDay(mSelectedColor, mood);

                startActivity(new Intent(getBaseContext(), MainActivity.class));
        }
    }

    @Override
    public void onTextLength(int length) {
        mCountText.setText(String.valueOf(length));
    }

    public class ColorGridAdapter extends BaseAdapter{
        LayoutInflater inflater;

        @Override
        public int getCount() {
            return mColorData.length;
        }

        @Override
        public String getItem(int i) {
            return mColorData[i];
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if(view == null){
                view = inflater.inflate(R.layout.item_list_colors, viewGroup, false);
            }

            ((TextView)view.findViewById(R.id.item_grid_color)).setBackgroundColor(Color.parseColor(mColorData[i]));
            return view;
        }
    }
}

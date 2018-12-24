package jp.androidbook.threefruitsanim;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {
    public final static String APPLE = "apple";
    public final static String BANANA = "banana";
    public final static String CHERRY = "cherry";
    ImageView ivApple, ivBanana, ivCherry;
    private String selectedFruit;
    RadioGroup radioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        ivApple = findViewById(R.id.iv_apple);
        ivBanana = findViewById(R.id.iv_banana);
        ivCherry = findViewById(R.id.iv_cherry);
        setImageBaseAlpha();
        ivApple.setOnClickListener(MyClick);
        ivBanana.setOnClickListener(MyClick);
        ivCherry.setOnClickListener(MyClick);

        radioGroup = findViewById(R.id.radioGroup);

        findViewById(R.id.button_2nd_activity).setOnClickListener(MyClick);
    }


    View.OnClickListener MyClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();

            switch (id) {
                case R.id.iv_apple:
                    setImageBaseAlpha();
                    ivApple.setImageAlpha(255);
                    ivApple.setColorFilter(Color.parseColor("#0fe500"), PorterDuff.Mode.LIGHTEN);
                    selectedFruit = APPLE;
                    break;
                case R.id.iv_banana:
                    setImageBaseAlpha();
                    ivBanana.setImageAlpha(255);
                    ivBanana.setColorFilter(Color.parseColor("#0fe500"), PorterDuff.Mode.LIGHTEN);
                    selectedFruit = BANANA;
                    break;
                case R.id.iv_cherry:
                    setImageBaseAlpha();
                    ivCherry.setImageAlpha(255);
                    ivCherry.setColorFilter(Color.parseColor("#0fe500"), PorterDuff.Mode.LIGHTEN);
                    selectedFruit = CHERRY;
                    break;

                case R.id.button_2nd_activity:
                    if (!preCheck()) {
                        break;
                    }
                    int transition = radioGroup.getCheckedRadioButtonId();
                    switch (transition) {
                        case -1: transition=0;break;
                        case R.id.radioButton_Bounce_Left: transition = R.anim.bounce_l2r;break;
                        case R.id.radioButton_Bounce_Right: transition = R.anim.bounce_r2l;break;
                        case R.id.radioButton_Zoom_Twist: transition = R.anim.zoom_in_twist;break;
                        case R.id.radioButton_Stand_Up: transition = R.anim.stand_up; break;

                        default:break;
                    }

                    Intent i = SecondActivity.getIntent(FirstActivity.this, selectedFruit);
//                    startActivity(i);
//                    overridePendingTransition(transition, 0);

                    startActivity(i, ActivityOptions.makeCustomAnimation(FirstActivity.this, transition, 0).toBundle());

                    break;
                default: break;

            }


        }
    };

    void setImageBaseAlpha() {
        ivApple.setImageAlpha(200);
        ivApple.clearColorFilter();
        ivBanana.setImageAlpha(200);
        ivBanana.clearColorFilter();
        ivCherry.setImageAlpha(200);
        ivCherry.clearColorFilter();
    }

    boolean preCheck() {
        if (selectedFruit == null || selectedFruit.equalsIgnoreCase("")) {
            Toast.makeText(FirstActivity.this, "No Fruit Selected!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}

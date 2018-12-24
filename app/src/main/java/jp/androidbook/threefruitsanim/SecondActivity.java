package jp.androidbook.threefruitsanim;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SecondActivity extends AppCompatActivity {
    final static String EXTRA_FRUIT = "fruit_incoming1234";

    LinearLayout ll;
    ImageView ivFruit;
    boolean isLayoutDone = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ll = findViewById(R.id.linear_layout_2nd);
        ivFruit = findViewById(R.id.ivFruit_Picked);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ivFruit.getVisibility() == View.VISIBLE) {
                    ivFruit.setVisibility(View.INVISIBLE);
                }else if (ivFruit.getVisibility() == View.INVISIBLE) {
                    ivFruit.setVisibility(View.VISIBLE);
                }

                int test = ll.getOrientation();
                switch (test) {
                    case LinearLayout.VERTICAL:
                        ll.setOrientation(LinearLayout.HORIZONTAL);break;
                    case LinearLayout.HORIZONTAL:
                        ll.setOrientation(LinearLayout.VERTICAL);break;
                }
            }
        });


        if (getIntent().getStringExtra(EXTRA_FRUIT) != null) {
            switch (getIntent().getStringExtra(EXTRA_FRUIT)) {
                case FirstActivity.APPLE:
                    ivFruit.setImageResource(R.drawable.apple);
                    break;
                case FirstActivity.BANANA:
                    ivFruit.setImageResource(R.drawable.banana);
                    break;
                case FirstActivity.CHERRY:
                    ivFruit.setImageResource(R.drawable.cherry);
                    break;
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        ll.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ll.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                if (!isLayoutDone) {
                    float xOffset = ivFruit.getWidth()/2;
                    float yOffset = ivFruit.getHeight()/2;
                    ivFruit.setX(ll.getWidth()/2 - xOffset);
                    ivFruit.setY(ll.getHeight()/2 - yOffset);
                    isLayoutDone = true;
                }

            }
        });





    }

    public static Intent getIntent(Context context, String fruit) {
        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra(EXTRA_FRUIT, fruit);
        return intent;
    }



}

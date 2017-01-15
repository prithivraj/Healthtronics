/*
 * $Id$
 * 09/01/17
 */
package com.healthtronics.healthtronics;

import android.content.Intent;
import com.daimajia.androidanimations.library.Techniques;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

public class SplashActivity extends AwesomeSplash {

  @Override public void initSplash(ConfigSplash configSplash) {

    //Customize Circular Reveal
    configSplash.setBackgroundColor(R.color.strokeColor); //any color you want form colors.xml
    configSplash.setAnimCircularRevealDuration(500); //int ms
    configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);  //or Flags.REVEAL_LEFT
    configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM); //or Flags.REVEAL_TOP

    //Choose LOGO OR PATH; if you don't provide String value for path it's logo by default

    //Customize Logo
    configSplash.setLogoSplash(R.mipmap.logo); //or any other drawable
    configSplash.setAnimLogoSplashDuration(1000); //int ms
    configSplash.setAnimLogoSplashTechnique(Techniques.FadeInDown); //choose one form Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)*/
    configSplash.setBackgroundColor(R.color.splash_back);

    //Customize Path
    //configSplash.setPathSplash(Constants.DROID_LOGO); //set path String
    configSplash.setOriginalHeight(400); //in relation to your svg (path) resource
    configSplash.setOriginalWidth(400); //in relation to your svg (path) resource
    configSplash.setAnimPathStrokeDrawingDuration(1000);
    configSplash.setPathSplashStrokeSize(6); //I advise value be <5
    configSplash.setPathSplashStrokeColor(R.color.strokeColor); //any color you want form colors.xml
    configSplash.setAnimPathFillingDuration(1000);


    //Customize Title
    configSplash.setTitleSplash("Healthtronics");
    configSplash.setTitleTextColor(R.color.splash_title);
    configSplash.setTitleTextSize(50f); //float value
    configSplash.setAnimTitleDuration(1000);
    configSplash.setAnimTitleTechnique(Techniques.SlideInDown);
    //configSplash.setTitleFont("fonts/myfont.ttf"); //provide string to your font located in assets/fonts/
  }

  @Override public void animationsFinished() {
    Intent myIntent = new Intent(SplashActivity.this, MainActivity.class);
    startActivity(myIntent);
    finish();
  }
}

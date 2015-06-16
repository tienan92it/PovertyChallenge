package com.appable.povertychallenge.utils;

import android.util.Patterns;

public class Utils {

	public final static boolean isValidEmail(CharSequence target) {
	    if (target == null) {
	        return false;
	    } else {
	        return Patterns.EMAIL_ADDRESS.matcher(target).matches();
	    }
	}
}

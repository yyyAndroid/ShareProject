/*
 * Copyright (C) 2017 WantData. All Rights Reserved.
 *
 * Last modified: 17-5-7 下午5:47
 */

package com.jumi.shareproject.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;


public class WaUI {
	
	public static final int UI_DEFAULT_STATUSBAR_HEIGHT = 18;
	
	public static Point getScreenPoint(View view) {
		if (view == null) {
			return null;
		}
		int[] pos = new int[2];
		view.getLocationOnScreen(pos);
		return new Point(pos[0], pos[1]);
	}

	/**
	 * 强制对View及其子视图进行递归刷新
	 * 
	 * @param view
	 */
	public static void forceChildrenInvalidateRecursively(View view) {
		if (view instanceof ViewGroup) {
			ViewGroup childGroup = (ViewGroup) view;
			int childCnt = childGroup.getChildCount();
			for (int i = 0; i < childCnt; i++) {
				View childView = childGroup.getChildAt(i);
				forceChildrenInvalidateRecursively(childView);
			}
		}
		if (view != null) {
			view.invalidate();
		}
	}
	
	public static void forceChildrenRelayoutRecursively(View view) {
		if (view instanceof ViewGroup) {
			ViewGroup childGroup = (ViewGroup) view;
			int childCnt = childGroup.getChildCount();
			for (int i = 0; i < childCnt; i++) {
				View childView = childGroup.getChildAt(i);
				forceChildrenRelayoutRecursively(childView);
			}
		}
		if (view != null) {
			view.requestLayout();
		}
	}

	public static View removeFromParent(View child) {
		if (child != null) {
			View parent = (View) child.getParent();
			if (parent != null && parent instanceof ViewGroup) {
				((ViewGroup) parent).removeView(child);
			}
		}
		return child;
	}
	
	public static void setBackground(View view, Drawable drawable) {
		view.setBackgroundDrawable(drawable);
	}
	
	public static int getLeftOnScreen(View view) {
		int pos[] = new int[2];
		view.getLocationOnScreen(pos);
		return pos[0];
	}
	
	public static int getRightOnScreen(View view) {
		int pos[] = new int[2];
		view.getLocationOnScreen(pos);
		return pos[0] + view.getWidth();
	}
	
	public static int getTopOnScreen(View view) {
		int pos[] = new int[2];
		view.getLocationOnScreen(pos);
		return pos[1];
	}
	
	public static int getBottomOnScreen(View view) {
		int pos[] = new int[2];
		view.getLocationOnScreen(pos);
		return pos[1] + view.getHeight();
	}
	
	@SuppressLint("NewApi")
	public static boolean isViewVisible(View view) {
		boolean isVisible = true;
		if (view == null) {
			return false;
		}
		if (view.getWidth() == 0 || view.getHeight() == 0) {
			isVisible = false;
		}
		if (!view.isShown()) {
			isVisible = false;
		}
		if (view.getAlpha() == 0) {
			isVisible = false;
		}
		if (getBottomOnScreen(view) <= 1 || getRightOnScreen(view) <= 1 || getTopOnScreen(view) >= getScreenHeight(view.getContext()) - 1
				|| getLeftOnScreen(view) >= getScreenWidth(view.getContext()) - 1) {
			isVisible = false;
		}
		View parent = (View) view.getParent();
		if (getBottomOnScreen(view) <= getTopOnScreen(parent) || getRightOnScreen(view) <= getLeftOnScreen(parent) 
				|| getTopOnScreen(view) >= getBottomOnScreen(parent) || getLeftOnScreen(view) >= getRightOnScreen(parent)) {
			isVisible = false;
		}
		return isVisible;
	}
	
	public static int getScreenWidth(Context context) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		return dm.widthPixels;
	}
	
	public static int getScreenHeight(Context context) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		return dm.heightPixels;
	}

	public static int getValuesDimen(Context context, int px){
//		return (int) context.getResources().getDimension(res);
		return px;
	}

	public static int getValueToDensityDimen(Context context, int px){
		return px2dip(context, px);
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	public static int getDensityDimen(Context context, int dimen) {
		final DisplayMetrics dm = context.getResources().getDisplayMetrics();
		return (Math.round(dimen * dm.density));
	}
	
	public static int getScaledDensityDimen(Context context, int dimen){
		final DisplayMetrics dm = context.getResources().getDisplayMetrics();
		return (Math.round(dimen * dm.scaledDensity));
	}
	
	public static final int centerSize(int total, int inside) {
		return (total - inside) >> 1;
	}

	public static final void layoutChildAbsoluteCenter(View child, int tWidth, int tHeight) {
		layoutChildAbsoluteCenter(child, tWidth, tHeight, 0, 0);
	}

	public static final void layoutChildAbsoluteCenter(View child, int tWidth, int tHeight, int offsetX, int offsetY) {
		int w, h, x, y;
		w = child.getMeasuredWidth();
		h = child.getMeasuredHeight();
		x = centerSize(tWidth, w) + offsetX;
		y = centerSize(tHeight, h) + offsetY;
		child.layout(x, y, x + w, y + h);
	}
	
	public static void measureExactly(View view, int width, int height) {
		int widthSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
		int heightSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
		view.measure(widthSpec, heightSpec);
	}

	public static void layoutViewAtPos(View view, int offsetX, int offsetY) {
		view.layout(offsetX, offsetY, offsetX + view.getMeasuredWidth(), offsetY + view.getMeasuredHeight());
	}
	
	public static void drawDrawable(Canvas canvas, Drawable drawable, int offsetX, int offsetY) {
		drawable.setBounds(offsetX, offsetY, offsetX + drawable.getIntrinsicWidth(), offsetY + drawable.getIntrinsicHeight());
		drawable.draw(canvas);
	}

	/**
	 * 缓存一下这个值，不是每次都去取。
	 */
	private static int sStatusbarHeight = -1;
	
	public static int getStatusbarHeight(Context context) {
		if (sStatusbarHeight >= 0) {
			return sStatusbarHeight;
		}
		int statusbarHeight = 0;
		int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			statusbarHeight = context.getResources().getDimensionPixelSize(resourceId);
		}
		if (statusbarHeight > 0) {
			sStatusbarHeight = statusbarHeight;
			return statusbarHeight;
		}
		statusbarHeight = getDensityDimen(context, UI_DEFAULT_STATUSBAR_HEIGHT);
		sStatusbarHeight = statusbarHeight;
		return statusbarHeight;
	}

	public static int getCommonTitleBarHeight(Context context) {
		return getDensityDimen(context, 48);
	}

	public static int getSize(Context context, int id) {
		return context.getResources().getDimensionPixelSize(id);
	}

	public static int getColor(Context context, int id) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			return context.getResources().getColor(id, null);
		} else {
			return context.getResources().getColor(id);
		}
	}

	public static boolean isQueenTarget(MotionEvent event, View view) {
		if (view == null) {
			return false;
		}
		int[] queenLocation = new int[2];
		view.getLocationOnScreen(queenLocation);
		int bottom = queenLocation[1] - view.getScrollY();
		int top = bottom +  view.getMeasuredHeight();
		int left = queenLocation[0] - view.getScrollX();
		int right = left + view.getMeasuredWidth();
		int y = (int) event.getRawY();
		int x = (int) event.getRawX();
		return (y > bottom && y < top && x > left && x < right);
	}

	public static StateListDrawable getDrawableByColor(int cornerSize, int color) {
		int[] colors = new int[]{color, color};

		float[] radii = new float[8];
		for (int i = 0; i < radii.length; i++) {
			radii[i] = cornerSize;
		}
		GradientDrawable stateNormal = new GradientDrawable();
		stateNormal.setShape(GradientDrawable.RECTANGLE);
		stateNormal.setCornerRadii(radii);
		stateNormal.setColor(colors[0]);
		stateNormal.setStroke(1, colors[1]);

		GradientDrawable statePressed = new GradientDrawable();
		statePressed.setShape(GradientDrawable.RECTANGLE);
		statePressed.setCornerRadii(radii);
		statePressed.setColor(colors[1]);

		StateListDrawable stateListDrawable = new StateListDrawable();
		stateListDrawable.addState(new int[]{}, stateNormal);
		stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, statePressed);
		return stateListDrawable;
	}

	public static StateListDrawable getDrawableByColor(int cornerSize, int bg, int border) {

		float[] radii = new float[8];
		for (int i = 0; i < radii.length; i++) {
			radii[i] = cornerSize;
		}
		GradientDrawable stateNormal = new GradientDrawable();
		stateNormal.setShape(GradientDrawable.RECTANGLE);
		stateNormal.setCornerRadii(radii);
		stateNormal.setColor(bg);
		stateNormal.setStroke(1, border);

		GradientDrawable statePressed = new GradientDrawable();
		statePressed.setShape(GradientDrawable.RECTANGLE);
		statePressed.setCornerRadii(radii);
		statePressed.setColor(border);

		StateListDrawable stateListDrawable = new StateListDrawable();
		stateListDrawable.addState(new int[]{}, stateNormal);
		stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, statePressed);
		return stateListDrawable;
	}

	public static StateListDrawable getDrawableByColor(int cornerSize, int bg, int borderColor, int borderWidth) {

		float[] radii = new float[8];
		for (int i = 0; i < radii.length; i++) {
			radii[i] = cornerSize;
		}
		GradientDrawable stateNormal = new GradientDrawable();
		stateNormal.setShape(GradientDrawable.RECTANGLE);
		stateNormal.setCornerRadii(radii);
		stateNormal.setColor(bg);
		stateNormal.setStroke(borderWidth, borderColor);

		GradientDrawable statePressed = new GradientDrawable();
		statePressed.setShape(GradientDrawable.RECTANGLE);
		statePressed.setCornerRadii(radii);
		statePressed.setColor(borderColor);

		StateListDrawable stateListDrawable = new StateListDrawable();
		stateListDrawable.addState(new int[]{}, stateNormal);
		stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, statePressed);
		return stateListDrawable;
	}

    public static String getString(Context context, int sId) {
		return context.getResources().getString(sId);
    }
}

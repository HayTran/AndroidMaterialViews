/*
 * AndroidMaterialViews Copyright 2015 Michael Rapp
 *
 * This program is free software: you can redistribute it and/or modify 
 * it under the terms of the GNU Lesser General Public License as published 
 * by the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU 
 * General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public 
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>. 
 */
package de.mrapp.android.view;

import static de.mrapp.android.view.util.Condition.ensureNotNull;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * A floating action button, which has been designed according to the Material
 * design guidelines.
 * 
 * Refer to
 * http://www.google.com/design/spec/components/buttons-floating-action-
 * button.html for further information on the Material design guidelines.
 * 
 * @author Michael Rapp
 * 
 * @since 1.0.0
 */
public class FloatingActionButton extends FrameLayout {

	/**
	 * Contains all possible sizes of a floating action button.
	 */
	public enum Size {

		/**
		 * A floating action button's normal size.
		 */
		NORMAL,

		/**
		 * A floating action button's small size.
		 */
		SMALL;

	}

	/**
	 * The image view, which is used to show the floating action button's
	 * shadow.
	 */
	private ImageView shadowImageView;

	/**
	 * The image button, which is used to show the floating action button's
	 * background and icon.
	 */
	private ImageButton imageButton;

	/**
	 * The floating action button's size.
	 */
	private Size size;

	/**
	 * The floating action button's color.
	 */
	private int color;

	/**
	 * The color of the ripple effect, which is shown when the floating action
	 * button is pressed on Lollipop devices.
	 */
	private int rippleColor;

	/**
	 * Initializes the view.
	 * 
	 * @param attributeSet
	 *            The attribute set, the view's attributes should be obtained
	 *            from, as an instance of the type {@link AttributeSet}
	 */
	private void initialize(final AttributeSet attributeSet) {
		inflateLayout();
		setSize(Size.NORMAL);
		setColor(getAccentColor());
		setRippleColor(getHighlightColor());
	}

	/**
	 * Inflates the view's layout.
	 */
	private void inflateLayout() {
		inflateShadowImageView();
		inflateImageButton();
	}

	/**
	 * Inflates the image view, which is used to show the floating action
	 * button's shadow.
	 */
	private void inflateShadowImageView() {
		shadowImageView = new ImageView(getContext());
		addView(shadowImageView, LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
	}

	/**
	 * Inflates the image button, which is used to show the floating action
	 * button's background and icon.
	 */
	private void inflateImageButton() {
		imageButton = new ImageButton(getContext());
		LayoutParams layoutParams = new LayoutParams(0, 0);
		layoutParams.gravity = Gravity.CENTER;
		addView(imageButton, layoutParams);
	}

	/**
	 * Adapts the shadow of the floating action button, depending on its size.
	 */
	private void adaptShadow() {
		if (getSize() == Size.NORMAL) {
			shadowImageView
					.setImageResource(R.drawable.floating_action_button_shadow_normal);
		} else {
			// TODO
		}
	}

	/**
	 * Adapts the size of the image button, which is used to show the floating
	 * image button's background and icon, depending on the floating button's
	 * size.
	 */
	private void adaptImageButtonSize() {
		int pixelSize = getPixelSize();
		LayoutParams layoutParams = (LayoutParams) imageButton
				.getLayoutParams();
		layoutParams.width = pixelSize;
		layoutParams.height = pixelSize;
		imageButton.setLayoutParams(layoutParams);
		imageButton.requestLayout();
	}

	/**
	 * Returns the size of the floating action button in pixels, depending on
	 * its current size.
	 * 
	 * @return The size of the floating action button in pixels as an
	 *         {@link Integer} value
	 */
	private int getPixelSize() {
		if (getSize() == Size.NORMAL) {
			return getResources().getDimensionPixelSize(
					R.dimen.floating_action_button_size_normal);
		} else {
			return getResources().getDimensionPixelSize(
					R.dimen.floating_action_button_size_small);
		}
	}

	/**
	 * Returns the color of the theme attribute <code>R.attr.colorAccent</code>.
	 * 
	 * @return The color of the theme attribute <code>R.attr.colorAccent</code>
	 *         as an {@link Integer} value
	 */
	private int getAccentColor() {
		TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(
				new int[] { R.attr.colorAccent });
		return typedArray.getColor(0, 0);
	}

	/**
	 * Returns the color of the theme attribute
	 * <code>R.attr.colorControlHighlight</code>.
	 * 
	 * @return The color of the theme attribute
	 *         <code>R.attr.colorControlHighlight</code> as an {@link Integer}
	 *         value
	 */
	private int getHighlightColor() {
		TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(
				new int[] { R.attr.colorControlHighlight });
		return typedArray.getColor(0, 0);
	}

	/**
	 * Creates a new floating action button, which has been designed according
	 * to the Material design guidelines.
	 * 
	 * @param context
	 *            The context, which should be used by the view, as an instance
	 *            of the class {@link Context}
	 */
	public FloatingActionButton(final Context context) {
		this(context, null);
	}

	/**
	 * Creates a new floating action button, which has been designed according
	 * to the Material design guidelines.
	 * 
	 * @param context
	 *            The context, which should be used by the view, as an instance
	 *            of the class {@link Context}
	 * @param attributeSet
	 *            The attribute set, the view's attributes should be obtained
	 *            from, as an instance of the type {@link AttributeSet}
	 */
	public FloatingActionButton(final Context context,
			final AttributeSet attributeSet) {
		super(context, attributeSet);
		initialize(attributeSet);
	}

	/**
	 * Creates a new floating action button, which has been designed according
	 * to the Material design guidelines.
	 * 
	 * @param context
	 *            The context, which should be used by the view, as an instance
	 *            of the class {@link Context}
	 * @param attributeSet
	 *            The attribute set, the view's attributes should be obtained
	 *            from, as an instance of the type {@link AttributeSet}
	 * @param defaultStyle
	 *            The default style to apply to this view. If 0, no style will
	 *            be applied (beyond what is included in the theme). This may
	 *            either be an attribute resource, whose value will be retrieved
	 *            from the current theme, or an explicit style resource
	 */
	public FloatingActionButton(final Context context,
			final AttributeSet attributeSet, final int defaultStyle) {
		super(context, attributeSet, defaultStyle);
		initialize(attributeSet);
	}

	/**
	 * Creates a new floating action button, which has been designed according
	 * to the Material design guidelines.
	 * 
	 * @param context
	 *            The context, which should be used by the view, as an instance
	 *            of the class {@link Context}
	 * @param attributeSet
	 *            The attributes of the XML tag that is inflating the view, as
	 *            an instance of the type {@link AttributeSet}
	 * @param defaultStyle
	 *            The default style to apply to this preference. If 0, no style
	 *            will be applied (beyond what is included in the theme). This
	 *            may either be an attribute resource, whose value will be
	 *            retrieved from the current theme, or an explicit style
	 *            resource
	 * @param defaultStyleResource
	 *            A resource identifier of a style resource that supplies
	 *            default values for the preference, used only if the default
	 *            style is 0 or can not be found in the theme. Can be 0 to not
	 *            look for defaults
	 */
	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public FloatingActionButton(final Context context,
			final AttributeSet attributeSet, final int defaultStyle,
			final int defaultStyleResource) {
		super(context, attributeSet, defaultStyle, defaultStyleResource);
		initialize(attributeSet);
	}

	/**
	 * Returns the floating action button's size.
	 * 
	 * @return The floating action button's size as a value of the enum
	 *         {@link Size}. The size may either be <code>NORMAL</code> or
	 *         <code>SMALL</code>
	 */
	public final Size getSize() {
		return size;
	}

	/**
	 * Sets the floating action button's size.
	 * 
	 * @param size
	 *            The size, which should be set, as a value of the enum
	 *            {@link Size}. The size must either be <code>NORMAL</code> or
	 *            <code>SMALL</code>
	 */
	public final void setSize(final Size size) {
		ensureNotNull(size, "The size may not be null");
		this.size = size;
		adaptShadow();
		adaptImageButtonSize();
		requestLayout();
	}

	/**
	 * Returns the floating action button's icon.
	 * 
	 * @return The floating action button's icon as an instance of the class
	 *         {@link Drawable} or null, if no icon has been set
	 */
	public final Drawable getIcon() {
		return imageButton.getDrawable();
	}

	/**
	 * Sets the floating action button's icon.
	 * 
	 * @param icon
	 *            The icon, which should be set, as an instance of the class
	 *            {@link Drawable} or null, if no icon should be set
	 */
	public final void setIcon(final Drawable icon) {
		imageButton.setImageDrawable(icon);
	}

	/**
	 * Sets the floating action button's icon.
	 * 
	 * @param resourceId
	 *            The resource id of the icon, which should be set, as an
	 *            {@link Integer} value. The resource id must correspond to a
	 *            valid drawable resource
	 */
	public final void setIcon(final int resourceId) {
		imageButton.setImageResource(resourceId);
	}

	/**
	 * Returns the floating action button's color.
	 * 
	 * @return The floating action button's color as an {@link Integer} value
	 */
	public final int getColor() {
		return color;
	}

	/**
	 * Sets the floating action button's color.
	 * 
	 * @param color
	 *            The color, which should be set, as an {@link Integer} value
	 */
	@SuppressLint("NewApi")
	@SuppressWarnings("deprecation")
	public final void setColor(final int color) {
		OvalShape shape = new OvalShape();
		ShapeDrawable drawable = new ShapeDrawable(shape);
		drawable.getPaint().setColor(color);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			RippleDrawable rippleDrawable = new RippleDrawable(
					new ColorStateList(new int[][] { {} },
							new int[] { getRippleColor() }), drawable, null);
			imageButton.setBackground(rippleDrawable);
		} else {
			imageButton.setBackgroundDrawable(drawable);
		}
	}

	/**
	 * Returns the color of the ripple effect, which is shown when the floating
	 * action button is pressed on Lollipop devices.
	 * 
	 * @return The color of the ripple effect as an {@link Integer} value
	 */
	public final int getRippleColor() {
		return rippleColor;
	}

	/**
	 * Sets the color of the ripple effect, which should be shown when the
	 * floating action button is pressed on Lollipop devices.
	 * 
	 * @param color
	 *            The color, which should be set, as an {@link Integer} value
	 */
	public final void setRippleColor(final int color) {
		this.rippleColor = color;
		setColor(getColor());
	}

	@Override
	public final void setOnClickListener(final OnClickListener listener) {
		imageButton.setOnClickListener(listener);
	}

	@Override
	public final void setOnLongClickListener(final OnLongClickListener listener) {
		imageButton.setOnLongClickListener(listener);
	}

	@Override
	protected final void onMeasure(final int widthMeasureSpec,
			final int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int shadowSize = getResources().getDimensionPixelSize(
				R.dimen.floating_action_button_shadow_size);
		int pixelSize = getPixelSize() + shadowSize;
		setMeasuredDimension(pixelSize, pixelSize);
	}

}
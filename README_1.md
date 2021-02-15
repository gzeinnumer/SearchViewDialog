## SearchViewDialog -> Customize

<p align="center">
  <img src="https://github.com/gzeinnumer/SearchViewDialog/blob/master/preview/MyLibDialogSearchView_15.jpg"/>
</p>

You can change UI with this configuration.

**1. Title**
- Change Text Title
  - `.setTitle("ini title")`
- Change Text Title Size
  - `.setTitleSize(21)`
- Change Text Title Color
  - `.setTitleColor(getResources().getColor(R.color.colorAccent))`
  - `.setTitleColor(Color.parseColor("#03DAC5"))`
- Change Text Title Alignment
  - `.setTitleAlignment(View.TEXT_ALIGNMENT_TEXT_END)`
    - Available Value :
      - `View.TEXT_ALIGNMENT_TEXT_END`
      - `View.TEXT_ALIGNMENT_CENTER`
      - `View.TEXT_ALIGNMENT_TEXT_START`
      - etc.

**2. Content**
- Change Text Content
  - `.setContent("ini content")`
- Change Text Content Size
  - `.setContentSize(21)`
- Change Text Content Color
  - `.setContentColor(getResources().getColor(R.color.colorAccent))`
  - `.setContentColor(Color.parseColor("#03DAC5"))`
- Change Text Content Alignment
  - `.setContentAlignment(View.TEXT_ALIGNMENT_TEXT_END)`
    - Available Value :
      - `View.TEXT_ALIGNMENT_TEXT_END`
      - `View.TEXT_ALIGNMENT_CENTER`
      - `View.TEXT_ALIGNMENT_TEXT_START`
      - etc.

**3. Filter Field**
- Change Filter Text Field Size
  - `.setTextSearchSize(21)`
- Change Filter Text Field Color
  - `.setTextSearchColor(getResources().getColor(R.color.colorAccent))`
  - `.setTextSearchColor(Color.parseColor("#03DAC5"))`

**4. Text List Item**
- Change Filter Text Field Size
  - `.setTextListSize(21)`
- Change Filter Text Field Color
  - `.setTextListColor(getResources().getColor(R.color.colorAccent))`
  - `.setTextListColor(Color.parseColor("#03DAC5"))`

**5. Content List**
- Change RecyclerView Height
  - `.setContentListHeight(900)` [300](https://github.com/gzeinnumer/SearchViewDialog/blob/master/preview/MyLibDialogSearchView_9.jpg) [900](https://github.com/gzeinnumer/SearchViewDialog/blob/master/preview/MyLibDialogSearchView_8.jpg)

**6. Button Ok**
- Change Text Button Ok
  - `.setBtnOkTitle("Yuhuu")`
- Change Text Button Ok Color
  - `.setBtnOkTitleColor(getResources().getColor(R.color.colorAccent))`
  - `.setBtnOkTitleColor(Color.parseColor("#03DAC5"))`
- Change Icon Button Ok
  - `.setOkIconLeft(R.drawable.ic_baseline_add_24)`
  - `.setOkIconTop(R.drawable.ic_baseline_add_24)`
  - `.setOkIconRight(R.drawable.ic_baseline_add_24)`
  - `.setOkIconBottom(R.drawable.ic_baseline_add_24)`

**7. Button Cancel**
- Change Text Button Cancel
  - `.setBtnCancelTitle("Batal")`
- Change Text Button Cancel Color
  - `.setBtnCancelTitleColor(getResources().getColor(R.color.colorAccent))`
  - `.setBtnCancelTitleColor(Color.parseColor("#03DAC5"))`
- Change Icon Button Cancel
  - `.setCancelIconLeft(R.drawable.ic_baseline_add_24)`
  - `.setCancelIconTop(R.drawable.ic_baseline_add_24)`
  - `.setCancelIconRight(R.drawable.ic_baseline_add_24)`
  - `.setCancelIconBottom(R.drawable.ic_baseline_add_24)`

**8. Parent Button**
- Change Text Button Size
  - `.setButtonTextSize(21)`
- Change Style Button
  - `.setButtonStyle(ButtonStyle.ButtonContained)`
    - Available Value :
      - `ButtonStyle.ButtonContained`
      - `ButtonStyle.ButtonOutlined`
      - `ButtonStyle.ButtonText`
- Change Gravity Parent Button
  - `.setButtonGravity(Gravity.CENTER)`
    - Available Value :
      - `Gravity.CENTER`
      - `Gravity.END`
      - `Gravity.START`
      - etc.
- Change Button Background Color
  - `.setButtonColor(getResources().getColor(R.color.colorPrimary))` or `.setButtonColor(Color.parseColor("#03DAC5"))` you can use this only if you use .setButtonStyle(ButtonStyle.ButtonContained)
##

**Look At Orange Arrow.** you can set shape/canvas from `drawable` to `dialog` with `shape.xml`->`<shape></shape>`. in my example i am using `rounded_corner_2.xml`.

<p align="center">
  <img src="https://github.com/gzeinnumer/SearchViewDialog/blob/master/preview/MyLibDialogSearchView_16.jpg" width="500"/>
</p>

**XML** :
```xml
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle" >

    <corners
        android:bottomLeftRadius="0dp"
        android:bottomRightRadius="0dp"
        android:topLeftRadius="10dp"
        android:topRightRadius="10dp" />

    <solid android:color="@android:color/white" />

</shape>
```
Other Example:
- Same Radius -> `R.drawable.rounded_corner` [xml](https://github.com/gzeinnumer/MyLibDialog/blob/main/example/Shapes/rounded_corner.xml) [Preview](https://github.com/gzeinnumer/MyLibDialog/blob/main/README_SHAPE.md#same-radius)
- Different Radius -> `R.drawable.rounded_corner_2` [xml](https://github.com/gzeinnumer/MyLibDialog/blob/main/example/Shapes/rounded_corner_2.xml) [Preview](https://github.com/gzeinnumer/MyLibDialog/blob/main/README_SHAPE.md#different-radius)
- Dialog 3D -> `R.drawable.rounded_layer` [xml](https://github.com/gzeinnumer/MyLibDialog/blob/main/example/Shapes/rounded_layer.xml) [Preview](https://github.com/gzeinnumer/MyLibDialog/blob/main/README_SHAPE.md#dialog-3d)
- Shadow Dialog -> `R.drawable.dialog_shadow` [xml](https://github.com/gzeinnumer/MyLibDialog/blob/main/example/Shapes/dialog_shadow.xml) [Preview](https://github.com/gzeinnumer/MyLibDialog/blob/main/README_SHAPE.md#shadow-dialog)

**Code** :
```java
new SearchViewDialog(getSupportFragmentManager(), list)
    .setDialogCanvas(getResources().getDrawable(R.drawable.rounded_corner_2))
    ...
    .show();
```

##

**Look At Orange Arrow.** You can change `Width` of dialog, with `percentage` of your screen. you can use value from `0.5` - `1.0` (50% - 100%).

<p align="center">
  <img src="https://github.com/gzeinnumer/SearchViewDialog/blob/master/preview/MyLibDialogSearchView_18.jpg" width="500"/>
</p>

**Code** :
```java
new SearchViewDialog(getSupportFragmentManager(), list)
    .setCanvasWidth(0.5)
    ...
    .show();
```

Preview :
<p align="center">
  <img src="https://github.com/gzeinnumer/SearchViewDialog/blob/master/preview/MyLibDialogSearchView_10.jpg" width="300"/>
</p>

##

**Look At Orange Arrow.** You can change `Height` of dialog, with `number`.

<p align="center">
  <img src="https://github.com/gzeinnumer/SearchViewDialog/blob/master/preview/MyLibDialogSearchView_19.jpg" width="500"/>
</p>

**Code** :
```java
new SearchViewDialog(getSupportFragmentManager(), list)
    .setContentListHeight(1100)
    ...
    .show();
```

Preview :
<p align="center">
  <img src="https://github.com/gzeinnumer/SearchViewDialog/blob/master/preview/MyLibDialogSearchView_20.jpg" width="300"/>
</p>

##

**FullScreenDialog** You can make dialog `FullScreen`.

**Code** :
```java
new SearchViewDialog(getSupportFragmentManager(), list)
    .enableFullScreen()
    ...
    .show();
```
**Notes**. This function will make `.setContentListHeight(1100)` and `.setCanvasWidth(0.5)` ignored.

Preview :
<p align="center">
  <img src="https://github.com/gzeinnumer/SearchViewDialog/blob/master/preview/MyLibDialogSearchView_21.jpg" width="300"/>
</p>

##

**Animation Dialog Show** You can change dialog `animation`.

**Code** :
```java
new SearchViewDialog(getSupportFragmentManager(), list)
    .setAnimationStyle(R.style.CustomDialogStyle)
    ...
    .show();
```
Here is the style
```xml
<resources>

    <!-- res->styles.xml -->
    <style name="CustomDialogStyle" parent="Theme.MaterialComponents.Light.Dialog">
        <item name="android:windowMinWidthMajor">80%</item>
        <item name="android:windowMinWidthMinor">80%</item>
        <item name="android:windowEnterAnimation">@anim/anim_in</item>
        <item name="android:windowExitAnimation">@anim/anim_out</item>
    </style>

</resources>
```
Style that i prepare for you
- [anim_in](https://github.com/gzeinnumer/MyLibDialogFragment/blob/master/lib/src/main/res/anim/anim_in.xml) & [anim_out](https://github.com/gzeinnumer/MyLibDialogFragment/blob/master/lib/src/main/res/anim/anim_out.xml).
- [slide_down](https://github.com/gzeinnumer/MyLibDialogFragment/blob/master/lib/src/main/res/anim/slide_down.xml) & [slide_up](https://github.com/gzeinnumer/MyLibDialogFragment/blob/master/lib/src/main/res/anim/slide_up.xml).


##

[FullCode](https://github.com/gzeinnumer/SearchViewDialog/blob/master/app/src/main/java/com/gzeinnumer/mylibsearchviewdialog/MainActivity.java) **Preview** :

<p align="center">
  <img src="https://github.com/gzeinnumer/SearchViewDialog/blob/master/preview/MyLibDialogSearchView_17.jpg" width="300"/>
</p>
| <img src="https://github.com/gzeinnumer/SearchViewDialog/blob/master/preview/MyLibDialogSearchView_2.jpg" width="300"/> | <img src="https://github.com/gzeinnumer/SearchViewDialog/blob/master/preview/MyLibDialogSearchView_5.jpg" width="300"/> | <img src="https://github.com/gzeinnumer/SearchViewDialog/blob/master/preview/MyLibDialogSearchView_6.jpg" width="300"/> | <img src="https://github.com/gzeinnumer/SearchViewDialog/blob/master/preview/MyLibDialogSearchView_22.jpg" width="300"/> |
|--|--|--|--|

<h1 align="center">
  SearchViewDialog - Easy Drop Down
</h1>

<div align="center">
    <a><img src="https://img.shields.io/badge/Version-3.1.0-brightgreen.svg?style=flat"></a>
    <a><img src="https://img.shields.io/badge/ID-gzeinnumer-blue.svg?style=flat"></a>
    <a><img src="https://img.shields.io/badge/Java-Suport-green?logo=java&style=flat"></a>
    <a><img src="https://img.shields.io/badge/Kotlin-Suport-green?logo=kotlin&style=flat"></a>
    <a href="https://github.com/gzeinnumer"><img src="https://img.shields.io/github/followers/gzeinnumer?label=follow&style=social"></a>
    <br>
    <p>Simple way to select Item Single or Multi</p>
</div>

---
# Content List
* [Download](#download)
* [Feature List](#feature-list)
* [Tech stack and 3rd library](#tech-stack-and-3rd-library)
* [Usage](#usage)
* [Example Code/App](#example-codeapp)
* [Version](#version)
* [Contribution](#contribution)

---
# Download
Add maven `jitpack.io` and `dependencies` in `build.gradle (Project)` :
```gradle
// build.gradle project
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}

// build.gradle app/module
dependencies {
  ...
  implementation 'com.google.android.material:material:1.2.1'
  implementation 'com.github.gzeinnumer:SearchViewDialog:version'

  implementation 'com.github.gzeinnumer:SimpleMaterialStyle:last-vesion'
  //check on https://github.com/gzeinnumer/SimpleMaterialStyle
}
```
---
# Feature List
- [x] [Single Select](#searchviewdialog)
- [x] [Multi Select](#searchviewdialog)
- [x] [Filter](#searchviewdialog)

---
# Tech stack and 3rd library
- Material.io ([docs](https://material.io/develop/android/docs/getting-started))
- Multi and Single Selection in Recyclerview ([docs](https://medium.com/@maydin/multi-and-single-selection-in-recyclerview-d29587a7dee2)) ([example](https://github.com/gzeinnumer/MultiandSingleSelectioninRecyclerView))
- DialogFragment ([docs](https://developer.android.com/reference/android/app/DialogFragment))

---
# Usage

**First Step**. Use `MaterialComponents` in your style :

```xml
<style name="AppTheme" parent="Theme.MaterialComponents.Light.NoActionBar">
    <!-- Customize your theme here. -->
</style>

<style name="CustomDialogStyle" parent="Theme.MaterialComponents.Light.Dialog">
    <item name="colorPrimary">@color/colorPrimary</item>
    <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
    <item name="colorAccent">@color/colorAccent</item>
    <item name="android:windowMinWidthMajor">80%</item>
    <item name="android:windowMinWidthMinor">80%</item>
    <item name="android:windowEnterAnimation">@anim/anim_in</item>
    <item name="android:windowExitAnimation">@anim/anim_out</item>
</style>
```

Add This Line to `res/color.xml`. **Important**
```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="colorPrimary">#6200EE</color>
    <color name="colorPrimaryDark">#3700B3</color>
    <color name="colorAccent">#03DAC5</color>
</resources>
```

If you want to change default font, add `custom_font.ttf` to your `res` directory `res->font`.
Than add this `style` to your `style.xml`/`themes.xml`
```xml
<!-- Base application theme. -->
<!-- Change Base Font -->
<style name="Theme.MyLibsTesting" parent="Theme.MaterialComponents.DayNight.DarkActionBar">
    <item name="android:fontFamily">@font/test_font</item>
</style>

<!-- Change TextView Font -->
<style name="BaseTextView">
    <item name="android:fontFamily">@font/test_font</item>
</style>

<style name="BaseTextView.Bold" parent="BaseTextView">
    <item name="android:fontFamily">@font/test_font</item>
</style>

<style name="BaseTextView.Italic" parent="BaseTextView">
    <item name="android:fontFamily">@font/test_font</item>
</style>

<style name="BaseTextView.Bold.Italic" parent="BaseTextView">
    <item name="android:fontFamily">@font/test_font</item>
</style>

<!-- Change Button Font -->
<style name="MyButtonText" parent="Widget.MaterialComponents.Button.TextButton">
    <item name="android:fontFamily">@font/test_font</item>
</style>

<style name="MyButtonOutlined" parent="Widget.MaterialComponents.Button.OutlinedButton">
    <item name="android:fontFamily">@font/test_font</item>
</style>

<style name="MyButtonContained">
    <item name="android:fontFamily">@font/test_font</item>
</style>

<style name="MyButtonIcon" parent="Widget.MaterialComponents.Button.Icon">
    <item name="android:fontFamily">@font/test_font</item>
</style>
```

#
### SearchViewDialog
Dialog with **1 Title, 1 Content, 1 EditText, 1 RecyclerView, 1 Negative Button, 1 Positive Button**. You can choise `Single Item Select` or `Multi Item Select`. The difference is only in `callback` function.
- **Content Item** there is 3 types data that you can sent to this dialog.

**Type 1**
```java
String[] arrayString = {"M", "Fadli", "Zein"};
new SearchViewDialog<String>(getSupportFragmentManager())
        .setItems(arrayString);
```
**Type 2**
```java
ArrayList<String> listString = new ArrayList<>();
listString.add("Lorem ipsum dolor");
new SearchViewDialog<String>(getSupportFragmentManager())
        .setItems(listString);
```
**Type 3** for this type you should override function `toString()` in your `model pojo`
```java

public class ExampleModel {

    private int id;
    private String name;
    private String address;

    //constructor

    //getter
    //setter

    @Override
    public String toString() {
        return "ExampleModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
```
And dont forget to declare your `model pojo` after `SearchViewDialog<ModelPojo>`
```java
ArrayList<ExampleModel> listObject = new ArrayList<>();
listObject.add(new ExampleModel(1, "Zein", "Balbar"));

new SearchViewDialog<ExampleModel>(getSupportFragmentManager())
        .setItems(listObject);
```
use your `model pojo` to `callBack function`. Example `new SearchViewDialog.OnOkPressedSingle<ExampleModel>(){}` or `new SearchViewDialog.OnOkPressedMulti<ExampleModel>(){}`
```java
//For Single
new SearchViewDialog<ExampleModel>(getSupportFragmentManager())
    .setItems(listObject)
    .onOkPressedCallBackSingle(...);

//For Multi
new SearchViewDialog<ExampleModel>(getSupportFragmentManager())
    .setItems(listObject)
    .onOkPressedCallBackMulti(...);
```
#
- **Single Item Select**. Use `onOkPressedCallBackSingle` to enable `Single Select Item`.
**Code** :
```java
ArrayList<String> list = new ArrayList<>();
list.add("Lorem ipsum dolor");
list.add("sit amet, consectetur");
list.add("adipiscing elit sed do");

new SearchViewDialog<String>(getSupportFragmentManager())
    .setItems(list)
    .setTitle("ini title")
    .setContent("ini content")
    .onOkPressedCallBackSingle(new SearchViewDialog.OnOkPressedSingle<String>() {
        @Override
        public void onOkSingle(String data) {
            String temp = "Single Select : \n"+data.toString();
            tv.setText(temp);
        }
    })
    .onCancelPressedCallBack(new SearchViewDialog.OnCancelPressed() {
        @Override
        public void onCancelPressed() {
            Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
        }
    })
    .show();
```
#
- **Multi Item Select**. Use `onOkPressedCallBackMulti` to enable `Multi Select Item`.
**Code** :
```java
ArrayList<String> list = new ArrayList<>();
list.add("Lorem ipsum dolor");
list.add("sit amet, consectetur");
list.add("adipiscing elit sed do");

new SearchViewDialog<String>(getSupportFragmentManager())
    .setItems(list)
    .setTitle("ini title")
    .setContent("ini content")
    .onOkPressedCallBackMulti(new SearchViewDialog.OnOkPressedMulti<String>() {
        @Override
        public void onOkMulti(List<String> data) {
            String temp = "Multi Select :\n";
            temp = temp + "Total Data => "+data.size()+"\n\n";
            for (String d: data){
                temp = temp + "Value => "+ d +"\n";
            }
            tv.setText(temp);
        }
    })
    .onCancelPressedCallBack(new SearchViewDialog.OnCancelPressed() {
        @Override
        public void onCancelPressed() {
            Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
        }
    })
    .show();
```

or you can write code like this :

```java
SearchViewDialog dialog = new SearchViewDialog<String>(getSupportFragmentManager())
    .setItems(list)
    .setTitle("ini title")
    .setContent("ini content");

dialog.onOkPressedCallBackMulti(new SearchViewDialog.OnOkPressedMulti<String>(){
    @Override
    public void onOkMulti(List<String> data) {

    }
}).

dialog.onCancelPressedCallBack(new SearchViewDialog.OnCancelPressed<String>() {
    @Override
    public void onCancelPressed() {
        Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
    }
});

dialog.show();
```

**Preview** :

| <img src="https://github.com/gzeinnumer/SearchViewDialog/blob/master/preview/MyLibDialogSearchView_2.jpg"/> | <img src="https://github.com/gzeinnumer/SearchViewDialog/blob/master/preview/MyLibDialogSearchView_4.jpg"/> | <img src="https://github.com/gzeinnumer/SearchViewDialog/blob/master/preview/MyLibDialogSearchView_6.jpg"/> |
|:-----------------------------------------------------------------------------------------------------------------|:-----------------------------------------------------------------------------------------------------------------|:-----------------------------------------------------------------------------------------------------------------|
| `Single Select Item` Preview, you only can select **1** Item                                                     | Click `OK` and call function `onOkPressedCallBackSingle`                                                         | Fiture `Filter` will enable on `Single Select` or `Multi Select`                                                 |


| <img src="https://github.com/gzeinnumer/SearchViewDialog/blob/master/preview/MyLibDialogSearchView_5.jpg" /> | <img src="https://github.com/gzeinnumer/SearchViewDialog/blob/master/preview/MyLibDialogSearchView_7.jpg"/> |
|:------------------------------------------------------------------------------------------------------------------|:-----------------------------------------------------------------------------------------------------------------|
| `Multi Select Item` Preview, you can select more than **1** Item                                                  | Click `OK` and call function `onOkPressedCallBackMulti`                                                          |
#
#### SearchViewDialog -> Customize

<p align="center">
  <img src="https://github.com/gzeinnumer/SearchViewDialog/blob/master/preview/MyLibDialogSearchView_15.jpg" width="500"/>
</p>

You can Customize your dialog UI. [**ReadMore**](https://github.com/gzeinnumer/SearchViewDialog/blob/master/README_1.md).

---
# Example Code/App

**FullCode [MainActivity](https://github.com/gzeinnumer/SearchViewDialog/blob/master/app/src/main/java/com/gzeinnumer/searchviewdialog/MainActivity.java)**

[Sample Code And App](https://github.com/gzeinnumer/MyLibDialogSearchViewExample)

---
# Version
- **2.0.3**
  - First Release
- **2.0.4**
  - Add animation and set custom animation show
- **2.0.5**
  - Bug Fixing
- **2.0.6**
  - Color
- **2.0.8**
  - Bug Fixing
- **2.0.9**
  - Bug Fixing
- **2.1.0**
  - Bug Fixing Color
- **2.1.1**
  - Bug Fixing Color
- **3.0.0**
  - Support SDK 16
- **3.0.1**
  - Bug Color
- **3.0.3**
  - Bug Fixing
- **3.0.4**
  - Bug Fixing
- **3.0.5**
  - Bug Fixing
- **3.0.6**
  - Bug Fixing
- **3.0.7**
  - Bug Fixing
- **3.0.7**
  - Fixing Space
- **3.1.0**
  - New Style

---
# Contribution
You can sent your constibution to `branch` `open-pull`.

---

```
Copyright 2020 M. Fadli Zein
```

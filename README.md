# Android Dots Indicator
[![](https://jitpack.io/v/fajaragungpramana/dots-indicator.svg)](https://jitpack.io/#fajaragungpramana/dots-indicator)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
</br>
</br>
Library for android. Dots Indicator for ViewPager.

## Installation
Add it in your root build.gradle at the end of repositories:
```gradle
allProjects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
Add the dependency:
```gradle
dependencies {
	implementation 'com.github.fajaragungpramana:dots-indicator:0.0.1'
}
```

## Usage
Define a view in your layout file:
```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center"
    android:orientation="vertical">

    <androidx.viewpager.widget.ViewPager
        android:id="@+id/view_pager"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_centerInParent="true" />

    <com.github.fajaragungpramana.dotsindicator.DotsIndicator
        android:id="@+id/dot_indicator"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        app:dotColor="@android:color/darker_gray"
        app:dotCount="5"
        app:dotSelectedColor="@color/purple_500"
        app:dotSize="16dp"
        app:dotSpacing="4dp" />

</RelativeLayout>
```

To set with view pager:
```kotlin
viewPager.adapter = Pager(this)
dotIndicator.setWithViewPager(viewPager)
```

## Preview
When i sliding the view pager.
<a href="url"><img src="https://github.com/fajaragungpramana/assets/blob/master/DotsIndicator/GIF-210202_004200.gif" align="left" height="640" width="320" ></a>

## Documentation
Attribute for FieldInput
| Attribute Name | Default Value | Description |
|----------------|---------------|-------------|
| dotColor | #CCCCCC | Set dot color |
| dotCount | 5 | Size of dot |
| dotSelectedColor | #FF0000 | Set dot color when is selected |
| dotSize | 16dp | Set dot size |
| dotSpacing | 4dp | Set dot space |

<b>Note:</b>
For this version `0.0.1` please make sure, you set the value attribute `dotCount` in your layout xml file same with your view pager adapter size.

## License
Copyright 2021 Fajar Agung Pramana

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

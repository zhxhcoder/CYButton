# CYButton
CYButton 告别各种shape文件

~~~
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:CYButton="http://schemas.android.com/tools"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <com.zhxh.cybutton.component.CYButton
        android:id="@+id/btnGo"
        style="?android:attr/borderlessButtonStyle"
        android:layout_width="150dp"
        android:layout_height="40dp"
        android:text="3"
        app:XborderRadius="0dp"
        app:XbuttonColor="#ff700332"
        app:XinnerRadius="0dp"
        app:XouterRadius="5dp"
        app:XshadowColor="#ffbba332"
        app:XshadowEnabled="false"
        app:XshadowHeight="0dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.458" />

    <com.zhxh.cybutton.component.CYButton
        style="?android:attr/borderlessButtonStyle"
        android:layout_width="150dp"
        android:layout_height="40dp"
        android:text="4"
        app:XborderRadius="0dp"
        app:XbuttonColor="#ffaa1332"
        app:XinnerRadius="0dp"
        app:XouterRadius="45dp"
        app:XshadowColor="#ffbba332"
        app:XshadowEnabled="false"
        app:XshadowHeight="0dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.585" />

    <com.zhxh.cybutton.component.CYButton
        android:layout_width="150dp"
        android:layout_height="40dp"
        android:text="6"
        app:XborderRadius="12dp"
        app:XbuttonColor="#0ff000"
        app:XinnerRadius="10dp"
        app:XouterRadius="10dp"
        app:XshadowColor="#0000000"
        app:XshadowEnabled="false"
        app:XshadowHeight="0dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.836" />

    <com.zhxh.cybutton.component.CYButton
        style="?android:attr/borderlessButtonStyle"
        android:layout_width="150dp"
        android:layout_height="40dp"
        android:text="5"
        app:XborderRadius="0dp"
        app:XbuttonColor="#ff134432"
        app:XinnerRadius="0dp"
        app:XouterRadius="0dp"
        app:XshadowColor="#ffbba332"
        app:XshadowEnabled="false"
        app:XshadowHeight="0dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.726" />

    <com.zhxh.cybutton.component.CYButton
        style="?android:attr/borderlessButtonStyle"
        android:layout_width="40dp"
        android:layout_height="40dp"
        android:text="2"
        app:XborderRadius="0dp"
        app:XbuttonColor="#ffaaa332"
        app:XinnerRadius="0dp"
        app:XouterRadius="45dp"
        app:XshadowColor="#ffbba332"
        app:XshadowEnabled="false"
        app:XshadowHeight="0dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.348" />

    <com.zhxh.cybutton.component.CYButton
        style="?android:attr/borderlessButtonStyle"
        android:layout_width="80dp"
        android:layout_height="80dp"
        android:text="1"
        app:XborderRadius="0dp"
        app:XbuttonColor="#ff11a332"
        app:XinnerRadius="0dp"
        app:XouterRadius="45dp"
        app:XshadowColor="#ff11a332"
        app:XshadowEnabled="false"
        app:XshadowHeight="0dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.159" />

</android.support.constraint.ConstraintLayout>

~~~


buttonColor 设置button的前景色
outerRadius 设置button的圆角角度
...

效果图如下：

![](https://github.com/zhxhcoder/CYButton/blob/master/app/screenshots/cybutton.gif)

~~~
    <com.zhxh.cybutton.component.CYButton
        android:layout_width="150dp"
        android:layout_height="40dp"
        android:text="6"
        app:XborderRadius="1dp"
        app:XbuttonColor="#0ff000"
        app:XinnerRadius="10dp"
        app:XouterRadius="10dp"
        app:XshadowColor="#000000"
        app:XshadowEnabled="false"
        app:XshadowHeight="0dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.836" />
~~~
XborderRadius 存在值时表示空心button


![](https://github.com/zhxhcoder/CYButton/blob/master/app/screenshots/cy6.png)



第二版更新为未完待续






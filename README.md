# CYButton
CYButton 告别各种shape文件

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
        android:text="CYButton"
        CYButton:borderRadius="0dp"
        CYButton:buttonColor="#ffaaa332"
        CYButton:innerRadius="0dp"
        CYButton:outerRadius="45dp"
        CYButton:shadowColor="#ffbba332"
        CYButton:shadowEnabled="false"
        CYButton:shadowHeight="0dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

</android.support.constraint.ConstraintLayout>

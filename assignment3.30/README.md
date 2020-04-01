# Chapter-3

-   使用 Lottie 库实现简单动画：
    -   checkbox 选中 ☑️时动画无限重复播放。
    -   checkbox 未选中时动画根据seekbar拖动进度播放。
-   使用属性动画练习 `AnimatorSet`, `scale/fade` 动画效果。
    -   中间的 View 的大小颜色渐变。
    -   可以通过点击 button 弹出对话框调整变化速率
    -   可以调整颜色变化的区间（使用第三方库com.pes.androidmaterialcolorpickerdialog.ColorPicker 调色板
-   使用 `ViewPager` 和 `Fragment` 做一个简单版的好友列表界面
    -   使用 `ViewPager` 和 `Fragment` 做个可滑动界面，`ViewPager` 是横向的可滑动容器，每个界面的对应位置都使用Fragment填充。
    -   使用 `TabLayout` 添加 Tab 支持（顶部的选项栏）
    -   对于好友列表 `Fragment`，使用 Lottie 实现 Loading 效果，在 5s 后展示实际的列表，要求这里的动效是淡入淡出
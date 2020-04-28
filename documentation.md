# Mobile-GoldExperience


## Notes

  * Please do not expect the progress bar will be appeared since all data are stored offline


## Creative Thinking

  * Use `GoldExperience` as the main class and the main data controller
  * By using `GoldExperience`, we could store some data as long as the app has not been destroyed (see Android Lifecycle)
  * Use (maybe) MVC-like architecture as its clean architecture to support clean code 
  * Use `Helper` class
  * Use `Place`, `UserProfile`, and `Plan` as the data's container class
  * Use template XML and abstract class for multiple usage of profile form (and prevent a lot of duplicate codes)
  * Use `res/values/strings` to store all texts instead of hard-coded those texts
  * Throw an exception whenever there is invalid data on registration or update profile form
  * Auto fill email after register or logout
  * Implement `LoadDataIndicator` to display `ProgressDialog` as a data loader handler


## References

  * https://android--code.blogspot.com/2015/08/android-radiogroup-set-selected-index.html
  * https://developer.android.com/guide/topics/ui/dialogs#java
  * https://developer.android.com/reference/android/content/Context.html#getString%28int%29
  * https://developer.android.com/reference/android/support/design/widget/BottomNavigationView
  * https://mkyong.com/android/android-radio-buttons-example/
  * https://stackoverflow.com/questions/14933330/datepicker-how-to-popup-datepicker-when-click-on-edittext
  * https://stackoverflow.com/questions/16076985/listview-row-buttons-how-do-i-create-a-custom-adapter-that-connects-a-view-oncl/16077840#16077840
  * https://stackoverflow.com/questions/18677421/android-relativelayout-in-scrollview
  * https://stackoverflow.com/questions/18854060/how-to-implement-progressbar-while-loading-data/18854219#18854219
  * https://stackoverflow.com/questions/1944656/android-global-variable
  * https://stackoverflow.com/questions/24992936/how-to-check-if-a-radiobutton-is-checked-in-a-radiogroup-in-android
  * https://stackoverflow.com/questions/3438276/how-to-change-the-text-on-the-action-bar
  * https://stackoverflow.com/questions/3676095/relativelayout-height-to-fill-remaining-space/30149147
  * https://stackoverflow.com/questions/5271387/get-color-int-from-color-resource/32253801#32253801
  * https://www.journaldev.com/17899/java-simpledateformat-java-date-format


## Useful Information

  * http://www.programmersought.com/article/1187676681/


## Image Resources

  * https://www.flaticon.com/authors/freepik

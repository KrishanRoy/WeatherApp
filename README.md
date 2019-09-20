# WeatherApp
A simple weather app that uses Dark Sky Api and shows temperature in Fahrenheit scale. It shows current weather as well as hourly forecast and eight day forecast.
## To run this app
Clone the repository. Connect your device to your Pc/Mac. From the Android Studio, run the cloned project through your 
Android device. Emulator might show some bugs. When you are not using the app, please uninstall since it charges money
after first 1000 calls everyday.
## Probable drawbacks and tradeoffs
Please turn your location on. When the app first launches, it does not have a last location, so FusedLocationProvider cannot fetch last location quickly enough.
So it would not load the forecast. Do not worry. Close the app and restart; the app should perform fine. And if you still
face problems, please check your device's location services. 
## Libraries Used
* LiveData from Android JetPack Architecture Components
* MVVM Architecture
* RxJava
* Google Location feature
* Retrofit
* JUnit
* Room Database
## Future Implementations
* Temperature in Celcius scale
* Have a faster UI

# AccuWeatherApp 🌤️

A modern Android weather application built with Jetpack Compose and Kotlin that provides real-time weather information for cities worldwide.

## 🚀 Features

- **Real-time Weather Data**: Get current weather conditions including temperature, humidity, wind speed, and more
- **City Search**: Search for weather information in any city worldwide
- **Modern UI**: Built with Jetpack Compose for a smooth and responsive user experience
- **Progress Indicators**: Loading animations while fetching weather data
- **Travel Advisory**: Weather-based travel recommendations
- **Clean Architecture**: MVVM pattern with Repository design pattern
- **Responsive Design**: Adaptive UI that works across different screen sizes

## 📱 Screenshots

*[Add screenshots of your app here]*

## 🏗️ Architecture

This project follows the MVVM (Model-View-ViewModel) architecture pattern and implements clean architecture principles:

### Architecture Components:
- **Model**: Data classes for weather responses and API handling
- **View**: Jetpack Compose UI components
- **ViewModel**: Business logic and state management
- **Repository**: Data access layer
- **Network**: Retrofit-based API service

### Project Structure:
```
app/src/main/java/com/example/accuweatherapp/
├── model/
│   ├── ItemData.kt
│   └── WeatherResponse.kt
├── network/
│   ├── WeatherApiClient.kt
│   └── WeatherService.kt
├── repository/
│   └── WeatherRepository.kt
├── util/
│   ├── Color.kt
│   ├── MyUtils.kt
│   ├── Theme.kt
│   └── Type.kt
├── view/
│   ├── SearchCity.kt
│   └── WeatherActivity.kt
└── viewmodel/
    ├── SearchViewModel.kt
    ├── WeatherViewModel.kt
    └── WeatherViewModelFactory.kt
```

## 🛠️ Technology Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM + Repository Pattern
- **Networking**: Retrofit2 + Gson
- **Dependency Injection**: Manual dependency injection
- **Async Operations**: Kotlin Coroutines
- **State Management**: LiveData + Compose State
- **Build System**: Gradle (Kotlin DSL)
- **Analytics**: Firebase Analytics

### Dependencies:
```kotlin
// Core Android
implementation("androidx.core:core-ktx")
implementation("androidx.lifecycle:lifecycle-runtime-ktx")
implementation("androidx.activity:activity-compose")

// Jetpack Compose
implementation("androidx.compose.ui:ui")
implementation("androidx.compose.ui:ui-graphics")
implementation("androidx.compose.ui:ui-tooling-preview")
implementation("androidx.compose.material3:material3")

// Networking
implementation("com.squareup.retrofit2:retrofit")
implementation("com.squareup.retrofit2:converter-gson")

// Firebase
implementation("com.google.firebase:firebase-analytics")
```

## ⚙️ Setup and Installation

### Prerequisites:
- Android Studio Arctic Fox or later
- JDK 8 or higher
- Android SDK API level 29 or higher
- OpenWeatherMap API key

### Installation Steps:

1. **Clone the repository:**
   ```bash
   git clone https://github.com/SakhileKhuzwayo222/WeatherApp--Part2.git
   cd WeatherApp--Part2
   ```

2. **Open in Android Studio:**
   - Launch Android Studio
   - Select "Open an existing Android Studio project"
   - Navigate to the cloned repository folder

3. **Configure API Key:**
   - Sign up for a free API key at [OpenWeatherMap](https://openweathermap.org/api)
   - Add your API key to the constants file or build configuration
   - Update the `API_ID` constant in your utils/Constants.kt file

4. **Build and Run:**
   - Sync the project with Gradle files
   - Build the project
   - Run on an emulator or physical device (API level 29+)

## 🔑 API Configuration

This app uses the OpenWeatherMap API. To set up:

1. Register at [OpenWeatherMap](https://openweathermap.org/api)
2. Get your free API key
3. Add the API key to your project (recommended: use BuildConfig or local.properties)

## 🎯 Usage

1. **Launch the App**: Open AccuWeatherApp on your device
2. **View Current Weather**: The app displays weather for a default city
3. **Search Cities**: Tap the plus icon to search for weather in other cities
4. **Real-time Updates**: Weather data is fetched in real-time from OpenWeatherMap API

### Features in Detail:

- **Current Weather Display**: Temperature, humidity, pressure, wind speed
- **Weather Conditions**: Clear visual representation of weather conditions
- **City Search**: Type any city name to get its weather information
- **Loading States**: Progress indicators during data fetching
- **Error Handling**: Graceful handling of network errors and invalid searches

## 🧪 Testing

The project includes unit and instrumented tests:

```bash
# Run unit tests
./gradlew test

# Run instrumented tests
./gradlew connectedAndroidTest
```

## 📊 Performance

- **Fast Loading**: Efficient API calls with caching
- **Smooth UI**: Jetpack Compose provides 60fps animations
- **Memory Efficient**: Proper lifecycle management and resource cleanup
- **Network Optimized**: Minimal API calls with proper error handling

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**Sakhile Khuzwayo**
- GitHub: [@SakhileKhuzwayo222](https://github.com/SakhileKhuzwayo222)

## 🙏 Acknowledgments

- [OpenWeatherMap](https://openweathermap.org/) for providing the weather API
- [Jetpack Compose](https://developer.android.com/jetpack/compose) for the modern UI toolkit
- Android development community for continuous support

## 📱 Minimum Requirements

- **Android Version**: API level 29 (Android 10.0)
- **Target SDK**: API level 34
- **RAM**: 2GB minimum
- **Storage**: 50MB free space
- **Internet**: Required for real-time weather data

## 🔮 Future Enhancements

- [ ] 7-day weather forecast
- [ ] Weather maps integration
- [ ] Location-based weather
- [ ] Weather alerts and notifications
- [ ] Offline mode with cached data
- [ ] Multiple cities dashboard
- [ ] Weather widgets
- [ ] Dark/Light theme toggle

---

*Built with ❤️ using Jetpack Compose and Kotlin*
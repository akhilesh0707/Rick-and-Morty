[![Akhilesh StackOverflow](https://img.shields.io/badge/Akhilesh-StackOverflow-orange.svg?style=for-the-badge)](https://stackoverflow.com/users/1548824/akhilesh0707)
[![Akhilesh LinkedIn](https://img.shields.io/badge/Akhilesh-LinkedIn-blue.svg?style=for-the-badge)](https://www.linkedin.com/in/akhilesh0707/)

# Rick-and-Morty
The Rick And Morty - App consuming a [Rick and Morty API](https://rickandmortyapi.com/) to display Characters it has been built with clean architecture principles, Repository Pattern, and MVVM pattern as well as Architecture Components.

This app shows the usage of the new Navigation Architecture Component in collaboration with the Bottom Navigation view with separate back stack history for each tab.

**App features:**
- List of Rick and Morty characters
- Detail of characters
- Bookmark character
- Light/ Dark theme.

## Screenshots
<img alt="List" src="art/screenshot.png">

## Architecture
Uses concepts of the notorious Uncle Bob's architecture called [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html).</br>

* Better separation of concerns. Each module has a clear API., Feature related classes life in different modules and can't be referenced without explicit module dependency.
* Features can be developed in parallel eg. by different teams
* Each feature can be developed in isolation, independently from other features
* faster compile time
<img src="art/app_architecture.png">

## Modules:
* **rick-and-morty-ui** - It uses all the components and classes releated to Android Framework. It gets the data from presentation layer and shows on UI. (**access all the modules**)
* **data** - The data layer implements the repository interface that the domain layer defines. This layer provide a single source of truth for data. (Kotlin module that **can only access domain module**)
* **remote** - Handles data interacting with the network. (**can only access data module**)
* **cache** - Handles data interacting with the local storing (Room DB). (**can only access data module**)
* **domain** - The domain layer contains the UseCases that encapsulate a single and very specific task that can be performed. This task is part of the business logic of the application. (Kotlin module that **cannot access any other module**)
* **presentation** - MVVM with ViewModels exposing LiveData that the UI consume. The ViewModel does not know anything about it's consumers. (Android module that **can only access domain module**)

## MAD Scorecard
[<img src="art/mad_scorecard.png">](https://madscorecard.withgoogle.com/scorecards/1519405986/)

## Tech stack - Library:

- [Kotlin](https://kotlinlang.org/)
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) A coroutine is a concurrency design pattern that you can use on Android to simplify code that executes asynchronously
- [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/)
- [Dagger-Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- [Kotlin-DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html)
- JetPack
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) 
  - [Lifecycle](https://developer.android.com/jetpack/androidx/releases/lifecycle) 
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) 
  - [Room](https://developer.android.com/topic/libraries/architecture/room)
  - [Navigation](https://developer.android.com/guide/navigation/navigation-getting-started)
  - [Data Binding](https://developer.android.com/topic/libraries/data-binding)
  - [MVVM Architecture]() (View - DataBinding - ViewModel - Model)
  - Repository pattern



## TODO
### CI/CD (Github actions/Bitrise
### Jococo for test coverage
### Ktlint 
### Credit

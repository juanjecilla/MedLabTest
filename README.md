# The MovieDB Example

This is an example of TheMovieDB API usage made in Kotlin with third party libraries.

## MVP approach
First application is located in just one module called "app".

*Used libraries*:
- Dagger2
- Realm
- Retrofit
- EventBus
- Others (UI)

## MVVM approach
Second application follows Clean architecture organized in three modules:
- *Domain:" Pure kotlin module that contains the model, repository interfaces and app use cases.
- *Data:* Android module using framaworks to manage databases and data.
- *Presentation:* Android app to present the information using the MVVM design pattern.

*Used libraries*:
- Koin
- Room
- Retrofit
- Others (UI)

## Next Steps
- Finish tests in domain, data and presentation modules to increase Coverage
- Migrate app module to Clean Architecture.
- Create tests for MVP pattern.
- Clone all features from app module in the presentation module.
- Improve application functionalities.

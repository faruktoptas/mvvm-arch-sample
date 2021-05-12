# mvvm-arch-sample ![Android CI Action](https://github.com/faruktoptas/mvvm-arch-sample/workflows/build/badge.svg)
[WORK-IN-PROGRESS] 

* Less boilerplate code
* Easy to read
* Testable (Unit and Instrumantation)
* Modularized by layer (Feature modules coming soon)


## Architecture 
<img width="685" alt="Screen Shot 2021-01-25 at 22 47 37" src="https://user-images.githubusercontent.com/1595227/105757712-61aa1380-5f5f-11eb-9c65-ce07db7667c9.png">


<img width="660" alt="Screen Shot 2021-01-25 at 22 52 58" src="https://user-images.githubusercontent.com/1595227/105758350-34aa3080-5f60-11eb-89f5-3f7010b59b47.png">

## What is used?
* JetPack ViewModel/LiveData
* Coroutines
* Koin
* DataBinding
* Unit Tests
* Instrumentation Tests
* Jacoco test coverage

## Samples

### Calling an api service (Errors are handled in base classes)
`MainViewModel.kt`
```kotlin
fun fetchItems() {
    callService({ repo.getAlbums() }, {
        albumsLive.postValue(it)
    })
}
```

`MainRepositoryImpl.kt`
```kotlin
override suspend fun getAlbums() = apiWrapper { api.getAlbums() }}
```

### Calling an api service with overrided error case
```kotlin
fun fetchItems() {
    callService({ repo.getAlbums() },
        success = {
            albumsLive.postValue(it)
        },
        failure = {
            if (it is AError.Authorization) {
                // clear token
            } else {
                postError(it)
            }
        }
    )
}
```

## Testing
* All business logic is kept in ViewModels. Trying to keep ViewModel coverages near 100%.
* All network cases also tested with [ApiWrapperTest](https://github.com/faruktoptas/mvvm-arch-sample/blob/master/network/src/test/java/me/toptas/architecture/network/ApiWrapperTest.kt)

<img width="1103" alt="Screen Shot 2021-01-25 at 23 04 05" src="https://user-images.githubusercontent.com/1595227/105759529-a171fa80-5f61-11eb-9195-440388a6c29f.png">

<img width="1167" alt="Screen Shot 2021-01-25 at 23 04 33" src="https://user-images.githubusercontent.com/1595227/105759634-b2bb0700-5f61-11eb-8a86-8433e13ef77f.png">

* Ui bound cases and view classes are tested with Espresso.

## Todos
* [ ] At least 2 screens with more interaction
* [ ] Navigation
* [ ] Feature modules
* [ ] Easy and readeble Espresso tests with [Robot pattern](https://medium.com/android-bits/espresso-robot-pattern-in-kotlin-fc820ce250f7)
* [x] Github actions


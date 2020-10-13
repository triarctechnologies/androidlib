# TriArc Technologies Android Library

TriArc Technologies Android library developed in `Kotlin` using `androidx` library which can be consumed by other applications.

## Modules

### EventBus

Wrapper over Android `LocalBroadcastReceiver`. Below is the code snippet on how to use the module:

Gradle Dependency:
```js
android {
    implementation "com.triarc.androidlib:eventbus:$version"
}
```

Event emitter example:
```js

class MainActivity : AppCompatActivity() {

    private val sampleEvent = "this_is_my_event"
    private val sampleEventWithData = "this_is_my_event_with_data"
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        EventBus.build(context = applicationContext).send(sampleEvent)
        
        EventBus.build(context = applicationContext).send(sampleEventWithData, Bundle())
    }
}

```

Event consumer example
```js

class MainActivity : AppCompatActivity(), EventBus.Listener {

    private val sampleEvent = "this_is_my_event"
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        EventBus.build(context = applicationContext, listener = this).attach(sampleEvent)
    }

    override fun onEvent(event: String, bundle: Bundle?) {
        //This method would be called when "sampleEvent" is posted on EventBus
    }
}

```

Since EventBus is a wrapper on `BroadcastReceivers` the `onEvent()` method runs on main thread.
 
### Logger

Wrapper over Android `Log` module, wraps all methods provided by `Log` class. Below is the code snippet on how to use the module:

Gradle Dependency:
```js
android {
    implementation "com.triarc.androidlib:logger:$version"
}
```

`Logger` consumer example:

```js
private val logger: Logger = LoggerImpl.getLogger(target = this)
```

This will create a `Logger` instance, which can be used to log messages.

Available methods provided by `Logger` `interface` are:

```js
    
    interface Logger {
        fun wtf(logMsg: String)
        fun wtf(logMsg: String, throwable: Throwable)
        fun info(logMsg: String)
        fun error(logMsg: String)
        fun error(logMsg: String, throwable: Throwable)
        fun debug(logMsg: String)
        fun debug(logMsg: String, throwable: Throwable)
        fun warning(logMsg: String)
        fun warning(logMsg: String, throwable: Throwable)
    }

```

### Network

Wrapper over `Retrofit` module using `RxJava` pattern to achieve network calls. 

Gradle Dependency:
```js
android {
    implementation "com.triarc.androidlib:network:$version"
}
```

`Network` module exposes an `abstract` class `NeworkHandlerImpl` which `overrides` the `request()` method of `NetworkHandler` and expects consumers to `override` `parseResponse()` method based on how they want to handle response. One such example is:

`NetworkHandler` module implementation:

```js

    abstract class NetworkHandlerImpl(private val observer: Scheduler, private val scheduler: Scheduler) : NetworkHandler {
        private val logger: Logger = LoggerImpl.getLogger(this)
    
        override fun <Response> request(observable: Observable<Response>): Observable<Response> {
            return Observable.create { subscriber ->
                observable.subscribeOn(scheduler).observeOn(observer).subscribe({ response ->
                    parseResponse(response, subscriber)
                    logger.info("API call successful")
                }, {
                    logger.error("API call failed, message:${it.message}", it)
                    subscriber.onError(Error(code = Error.Code.COMMUNICATION_ERROR, cause = it))
                })
            }
        }
    }

```

`Network` module also exposes `Error` class which is extension of `Throwable`. It expects `code` along with the `Throwable` parameters, helpful for consumer to take further action(s) based on error code.
 
### Support

Wrapper over Android SDK. Provides some very useful libraries for `views` following MVP design pattern, some `controllers`[singletons] providing wrapper utility functions and `managers` as extension to Android manager classes.
 
 Gradle Dependency:
 ```js
 android {
     implementation "com.triarc.androidlib:support:$version"
 }
 ```

## Project Settings

To consume TriArc's Android Library, project level `build.gradle` files must be configured with below code:
 ```js
 
 allprojects {
     repositories {
         google()
         jcenter()
 
         mavenLocal()
         maven {
             name = "androidlib"
             url = uri("https://maven.pkg.github.com/triarctechnologies/androidlib")
             credentials {
                 username = System.getenv('GITHUB_USER') ?: project.properties['GITHUB_USER']
                 password = System.getenv('GITHUB_TOKEN') ?: project.properties['GITHUB_TOKEN']
             }
         }
     }
 }

 ```

## Versioning

TriArc's `androidlib` is published by CircleCI directly. For the latest version of all libraries, please see [library versions](https://github.com/triarctechnologies?tab=packages). 

## Author

* TriArc Technologies

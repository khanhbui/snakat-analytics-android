# snakat-analytics-android

## Installation
1. Add this to your project as a git submodule
```sh
cd ~/sample_app/
git submodule add https://github.com/khanhbui/snakat-analytics-android.git snakat-analytics
```
2. Create a file, named *config.gradle*, which defines sdk versions, target versions and dependencies.
```groovy
ext {
    plugins = [
            library: 'com.android.library'
    ]

    android = [
            compileSdkVersion: 31,
            buildToolsVersion: "31.0.0",
            minSdkVersion    : 14,
            targetSdkVersion : 31
    ]

    dependencies = [
            appcompat: 'androidx.appcompat:appcompat:1.4.1'
    ]
}
```
3. Add this line on top of *build.gradle*
```groovy
apply from: "config.gradle"
```
4. Add this line to *settings.gradle*
```groovy
include ':snakat-analytics'
```
5. Add this line to dependencies section of *app/build.gradle*
```groovy
implementation project(path: ':snakat-analytics')
```

## Usage

### Initialization
```java
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Context context = getApplicationContext();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
```

If you want to see logs while developing your app. Please use this.
```java
```

## License
```
MIT License

Copyright (c) 2022 Khanh Bui

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
